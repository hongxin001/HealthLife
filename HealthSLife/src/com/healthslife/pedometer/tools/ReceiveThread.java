package com.healthslife.pedometer.tools;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveThread implements Runnable{
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private IReceiveInfoHandler handler;
	
	public void call(String str) {
		handler.call(str);
	}

	public ReceiveThread(DatagramSocket socket,IReceiveInfoHandler a_handle) {
		this.setSocket(socket);
		this.setHandler(a_handle);
	}

	public void setHandler(IReceiveInfoHandler a_handle) {
		this.handler=a_handle;
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public DatagramPacket getPacket() {
		return packet;
	}

	public void setPacket(DatagramPacket packet) {
		this.packet = packet;
	}
	@Override
	public void run() {
		while(true) {
			byte[] bytes	=	new byte[1024];
			packet=new DatagramPacket(bytes, bytes.length);
			try {
				System.out.println("thread second");
				socket.receive(packet);
				String dataString=new String(bytes, 0, packet.getLength(),"US-ASCII");
				handleString(dataString);
				System.out.println("thread second");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void handleString(String str) {
		this.handler.call(str);
	}
}
