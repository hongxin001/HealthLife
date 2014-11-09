package com.healthslife.pedometer.tools;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.R.integer;
import android.content.Context;
import android.util.Log;

//import com.healthslife.setting.AppSetting;

public class RemoteControlClient {

	public DatagramSocket udpSocket	=null;
	public String hostName	=	"115.28.45.241";
	public String portName	=	"59995";
	public String username	=	null;
	public String password	=	null;
	public String newPassword	=	null;
	public ReceiveCommandLine recieveInfo;
//	private AppSetting mAppSetting;
	private Context ctx;
	
	private static RemoteControlClient sSingletonInstance	=null; 
	
	public static RemoteControlClient getInstance(Context ctx) {
		if (sSingletonInstance==null) {
			sSingletonInstance=new RemoteControlClient(ctx);
		}
		
		return sSingletonInstance;
	}
	
	public  RemoteControlClient configNetwork(String hostnameString,String portNameString) {
		this.hostName=hostnameString;
		this.portName=portNameString;
		return this;
	}
	
	public RemoteControlClient configUser(String usernameString,String passwordString) {
		this.username	=	usernameString;
		this.password	=	passwordString;
		return this;
	}
	
	public RemoteControlClient configUser(String usernameString,
			String passwordString, String newpasswordString) {
		configUser(usernameString, newpasswordString).setNewPassword(
				newpasswordString);
		return this;
	}

	private RemoteControlClient(Context ctx) {
		// TODO Auto-generated constructor stub
		try {
			this.udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.ctx = ctx;
//		mAppSetting = new AppSetting(this.ctx);
//		String account = (String) mAppSetting.getRemoteAccount();
//		String psw = (String) mAppSetting.getRemotepsw();
        String account = "00068";
        String psw     = "1994";
		if ((account != "") && (psw != "")) {
			this.configUser(account, psw);
		}

	}
	
	public RemoteControlClient config (String hoString,String port,String username,String password) {
		configUser(username, password);
		configNetwork(hoString, port);
		return this;
	}
	
	public RemoteControlClient config(String hoString,String port,String username,String password,String newPsswordString) {
		configUser(username, newPsswordString, newPsswordString);
		configNetwork(hoString, hoString);
		return this;
	}
	
	public void init(IReceiveInfoHandler handle) {
		try {
			udpSocket=new DatagramSocket();
			new Thread(new ReceiveThread(new DatagramSocket(Integer.parseInt(portName)),handle),"Second Thread").start();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public String send(SendCommandLine commandLine) {
		byte[] buffer = new byte[100];
		commandLine.setUsername(username).setPassword(password);
		buffer=commandLine.getSendCommandString().getBytes();
		DatagramPacket receivePacket = null;
		try {
			DatagramPacket sSend = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(hostName), Integer.parseInt(portName));
			byte[] recvBuf = new byte[15000];
			receivePacket =
				    new DatagramPacket(new byte[recvBuf.length], recvBuf.length);
			this.udpSocket.send(sSend);
			this.udpSocket.receive(receivePacket);
			Log.v("message","Received: " + new String(receivePacket.getData()).trim());
			
			
			System.out.println(commandLine.getSendCommandString());
            this.udpSocket.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = new String(receivePacket.getData()).trim();
		return message;
		
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}
