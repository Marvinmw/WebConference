package voiceChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class TransferVoice implements Runnable {
	private DatagramSocket sendSocket, receiveSocket;
	private DatagramPacket sendPacket, receivePacket;
	private byte[] buffer;
	private int bufferSize = 1024;
	
	private int sendPort = 10002;
	private int receivePort = 10001;
	private static ArrayList<String> userIPs = new ArrayList<String>();

	public TransferVoice() {
		try {
			sendSocket = new DatagramSocket(sendPort);
			receiveSocket = new DatagramSocket(receivePort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void run() {
		while (true) {
			try {
				buffer = new byte[bufferSize];
				receivePacket = new DatagramPacket(buffer, bufferSize);
				receiveSocket.receive(receivePacket);
				String senderIP = receivePacket.getAddress().getHostAddress();
				
				byte data[] = receivePacket.getData();
				System.out.println("收到来自 " + senderIP + " 的语音");
				
				if(!userIPs.isEmpty()){
					for(int i = 0; i < userIPs.size(); i++){
						if(senderIP.equalsIgnoreCase(userIPs.get(i))){
							continue;
						}else{
							sendPacket = new DatagramPacket(data, bufferSize, InetAddress.getByName(userIPs.get(i)), sendPort);
							sendSocket.send(sendPacket);
						}						
					}
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized static void doVoiceCommand(String command, String ip) {
		if (command.equalsIgnoreCase("StartVoice")) {
			userIPs.add(ip);
		} else {
			userIPs.remove(ip);
		}
	}

	public synchronized static void removeIP(String ip) {
		userIPs.remove(ip);
	}
}
