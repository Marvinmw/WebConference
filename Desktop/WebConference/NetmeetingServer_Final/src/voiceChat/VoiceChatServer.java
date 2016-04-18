package voiceChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;



public class VoiceChatServer implements Runnable{

	private ServerSocket server;
	/*
	private DatagramSocket sendSocket, receriveSocket;
	private DatagramPacket sendPacket, recerivePacket;
	private int sendPort = 10002;
	private int receivePort = 10001;
	private ArrayList<String> userIPs = new ArrayList<String>();
	*/
	private int voicePort = 8003;
		
	public VoiceChatServer(){
		try {
			server = new ServerSocket(voicePort);
			System.out.println("VoiceChat server has started.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				Socket so = server.accept();
				String clientIP = so.getInetAddress().getHostAddress();
				
				VoiceChatCommand vcc = new VoiceChatCommand(so, clientIP);
				Thread th = new Thread(vcc);
				th.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
