package com.healthslife.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;

import org.apache.http.util.ExceptionUtils;

import com.nostra13.universalimageloader.utils.L;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


/*
 * Linux command to send UDP:
 * #socat - UDP-DATAGRAM:192.168.1.255:11111,broadcast,sp=11111
 */
public class UDPListenerService extends Service {
	
	
	Boolean shouldListenForUDPBroadcast = false;
	DatagramSocket socket;
	
	private void listenAndWaitAndThrowIntent(InetAddress broadcastIP, Integer port) throws IOException,SocketException {
		byte[] recvBuf = new byte[15000];
		if (socket == null || socket.isClosed()) {
//				DatagramChannel channel = DatagramChannel.open();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
				socket = new DatagramSocket(/*port, broadcastIP*/);
				socket.setBroadcast(true);
		}
		//socket.setSoTimeout(1000);
		DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length,broadcastIP,port);
		Log.e("UDP", "Waiting for UDP broadcast");
		try {
			Log.e("UDP","wait receive");
			socket.receive(packet);
			Log.e("UDP","sucess receive");
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("UDP","failed receive");
		}
		String senderIP = packet.getAddress().getHostAddress();
		String message = new String(packet.getData()).trim();
		Log.e("UDP", "Got UDB broadcast from " + senderIP + ", message: " + message);
		broadcastIntent(senderIP, message);
		socket.close();
	}

	private void broadcastIntent(String senderIP, String message) {
		Intent intent = new Intent("android.intent.action.MY_BROADCAST");
		intent.putExtra("sender", senderIP);
		intent.putExtra("message", message);
		sendBroadcast(intent);
	}
	
	Thread UDPBroadcastThread;
	
	void startListenForUDPBroadcast() {
		UDPBroadcastThread = new Thread(new Runnable() {
			public void run() {
				
					InetAddress broadcastIP = null;
					try {
						broadcastIP = InetAddress.getByName("115.28.45.241");
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //172.16.238.42 //192.168.1.255
					Integer port = 59995;
					while (shouldRestartSocketListen) {
						try {
							listenAndWaitAndThrowIntent(broadcastIP, port);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//if (!shouldListenForUDPBroadcast) throw new ThreadDeath();
			}
		});
		UDPBroadcastThread.start();
	}
	
	private Boolean shouldRestartSocketListen=true;
	
	void stopListen() {
		shouldRestartSocketListen = false;
		socket.close();
	}
	
	@Override
	public void onCreate() {
		
	};
	
	@Override
	public void onDestroy() {
		stopListen();
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		shouldRestartSocketListen = true;
		startListenForUDPBroadcast();
		Log.i("UDP", "Service started");
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
}
