package serverStartup;

import network.LoginServer;
import network.UpdateMeetingServer;
import network.UpdateUserlistServer;
import fileTransport.fileTransport.*;
import singleChat.SingleChatServer;
import textChat.TextChatServer;
import voiceChat.*;
import whiteBoard.WhiteBoardServer;

public class StartServer {
	
	public void startAllServer(){
		this.startLoginServer();
		this.startUpdateServer();
		this.startWhiteBoardServer();
		this.startTextChatServer();
		this.startSingleChatServer();
		this.startVoiceChatServer();
		this.startFileTransportServer();
	}
	
	public void startLoginServer(){
		LoginServer server = new LoginServer();
		Thread t1 = new Thread(server);
		t1.start();
	}
	
	public void startUpdateServer(){
		UpdateMeetingServer updateMeetingServer = new UpdateMeetingServer();
		Thread t2 = new Thread(updateMeetingServer);
		t2.start();
				
		UpdateUserlistServer updateUserlistServer = new UpdateUserlistServer();
		Thread t3 = new Thread(updateUserlistServer);
		t3.start();
	}
	
	//文字聊天服务	
	public void startTextChatServer(){
		TextChatServer textChat = new TextChatServer();
		Thread severTextChat = new Thread(textChat);
		severTextChat.start();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//单人聊天服务
	public void startSingleChatServer(){
		SingleChatServer scs = new SingleChatServer();
		Thread singleChatThread = new Thread(scs);
		singleChatThread.start();
	}
	
	//白板服务
	public void startWhiteBoardServer(){
		WhiteBoardServer wbs = new WhiteBoardServer();
		Thread serverBoard = new Thread(wbs);
		serverBoard.start();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//语音聊天服务
	public void startVoiceChatServer(){
		VoiceChatServer vcs = new VoiceChatServer();
		Thread serverVoice = new Thread(vcs);
		serverVoice.start();
		
		TransferVoice tv = new TransferVoice();
		Thread serverTransfer = new Thread(tv);
		serverTransfer.start();		
	}
	
	//文件传输服务
	public void startFileTransportServer(){
		new FileTransportServer().startServer();
	}
	
	public static void main(String[] args){
		StartServer server = new StartServer();	
		server.startAllServer();
	}
	
}
