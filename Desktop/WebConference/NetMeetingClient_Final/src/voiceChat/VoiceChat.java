package voiceChat;

import java.net.Socket;

public class VoiceChat {
	private VoiceReceiver voiceReceiver;
	private VoiceSender voiceSender;
	
	private ConnectServerThread cst;
	
	public VoiceChat(){
		cst = new ConnectServerThread();
		Thread t = new Thread(cst);
		t.start();
		
		voiceSender = new VoiceSender();
		voiceReceiver = new VoiceReceiver();
	}	
	
	public void startVoiceChat(){
		cst.sendCommand("StartVoice");
		voiceReceiver.start();
		voiceSender.start();
	}
	
	public void stopVoiceChat(){
		cst.sendCommand("StopVoice");
		voiceReceiver.stop();
		voiceSender.stop();
	}
}
