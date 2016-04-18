package voiceChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.sound.sampled.*;

import network.ConnectConfig;
/**
 * 收音部分
 */
public class VoiceReceiver implements Runnable{	
	private SourceDataLine line;
	private byte[] buffer;
	private int bufferSize = 1024;
	private Thread voiceReceiverThread;
	private boolean isStart;
	private boolean isClose;
	
	private DatagramPacket receivePacket;
	private DatagramSocket receiveSocket;
	private int receivePort = ConnectConfig.receiveVoicePort;
	
	public VoiceReceiver(){		
		try {
			receiveSocket = new DatagramSocket(receivePort);
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		buffer = new byte[bufferSize];
		receivePacket = new DatagramPacket(buffer, bufferSize);
		isClose = false;
		
		AudioFormat format =new AudioFormat(8000,16,2,true,true);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
		
        try {
        	line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, 1024); 
        } catch (LineUnavailableException e1) {
        	e1.printStackTrace();
        } 
    }

	public void run() {
		while(isStart && !voiceReceiverThread.isInterrupted()){
			try {
				receiveSocket.receive(receivePacket);
			} catch (IOException e) {
				isStart=false;
				voiceReceiverThread.interrupt();
				//e.printStackTrace();
			}
			
			byte[] data = receivePacket.getData();
			
			line.write(data, 0, data.length);//将data的数据转化为音频
		}
	}
	
	public void start(){
		if(voiceReceiverThread == null || !voiceReceiverThread.isAlive()){
			voiceReceiverThread = new Thread(this);
			line.start();
			isStart = true;
			this.connect();
			voiceReceiverThread.start();
		}
	}
	
	
	
	public void stop(){
		voiceReceiverThread.interrupt();
		line.stop();
		isStart = false;
		
		if(receiveSocket != null){
			receiveSocket.close();
			isClose = true;
		}
	}
	
	public void connect(){
		if(isClose){
			try {
				receiveSocket = new DatagramSocket(receivePort);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			buffer = new byte[bufferSize];
			receivePacket = new DatagramPacket(buffer, bufferSize);
			isClose = false;
		}
	}
}
