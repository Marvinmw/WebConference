package voiceChat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import network.ConnectConfig;

public class ConnectServerThread implements Runnable{
	private Socket socket;
	private String serverIP = ConnectConfig.serverIP;
	private int serverPort = ConnectConfig.voiceChatPort;	
	private ObjectOutputStream oos;
	
	public ConnectServerThread(){
		try {
			this.socket = new Socket(serverIP, serverPort);
			
		} catch (UnknownHostException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendCommand(String command){
		try {
			System.out.println("VoiceChatCommand:" + command);
			oos.writeObject(command);
		} catch (IOException e) {
			restart();
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// “Ï≥£¥¶¿Ì
	private void restart() {
		try {
			socket.close();
			socket = new Socket(serverIP, serverPort);
			oos = new ObjectOutputStream(socket.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
