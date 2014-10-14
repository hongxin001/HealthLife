package com.healthslife.control.tools;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RemoteControlClient {

	public DatagramSocket udpSocket	=null;
	public String hostName	=	"115.28.45.241";
	public String portName	=	"59995";
	public String username	=	null;
	public String password	=	null;
	public String newPassword	=	null;
	public ReceiveCommandLine recieveInfo;
	
	
	private static RemoteControlClient sSingletonInstance	=null; 
	
	public static RemoteControlClient getInstance() {
		if (sSingletonInstance==null) {
			sSingletonInstance=new RemoteControlClient();		
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
	
	public RemoteControlClient configUser(String usernameString,String passwordString,String newpasswordString) {
		configUser(usernameString, newpasswordString).setNewPassword(newpasswordString);
		return this;
	}
	
	private RemoteControlClient() {
		// TODO Auto-generated constructor stub
        try {
            this.udpSocket=new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
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
	
	public void send(SendCommandLine commandLine) {
		byte[] buffer = new byte[100];
		commandLine.setUsername(username).setPassword(password);
		buffer=commandLine.getSendCommandString().getBytes();
		try {
			DatagramPacket sSend = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(hostName), Integer.parseInt(portName));
			this.udpSocket.send(sSend);
			System.out.println(commandLine.getSendCommandString());
            //this.udpSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
