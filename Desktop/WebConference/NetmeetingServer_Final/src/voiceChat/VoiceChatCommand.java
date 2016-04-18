package voiceChat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class VoiceChatCommand implements Runnable{
	
	private Socket socket;
	private String clientIP;
	private ObjectInputStream ois;
	private Object voiceCommand;
	
	public VoiceChatCommand(Socket s, String ip){
		this.socket = s;
		this.clientIP = ip;
		
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void run() {
		while (true) {
			try {
				voiceCommand = ois.readObject();

				if (voiceCommand != null)
					TransferVoice.doVoiceCommand((String)voiceCommand, clientIP);

			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				voiceCommand = null;
			}
		}		
		TransferVoice.removeIP(clientIP);
	}
	
}
