package voiceChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sound.sampled.*;

import network.ConnectConfig;

/**
 * 发送语音部分
 */
public class VoiceSender implements Runnable {
	private TargetDataLine line;
	private int bufferLength = 1024;
	private Thread voiceSenderThread;
	private boolean isStart;

	private DatagramPacket sendPacket;
	private DatagramSocket sendSocket;
	private String serverIP = ConnectConfig.serverIP;
	private int sendPort = ConnectConfig.sendVoicePort;

	public VoiceSender() {
		try {
			sendSocket = new DatagramSocket(sendPort);
			sendPacket = new DatagramPacket(new byte[0], 0,
					InetAddress.getByName(serverIP), sendPort);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		AudioFormat format = new AudioFormat(8000, 16, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		isStart = false;
	}

	public void run() {
		byte[] buffer = new byte[bufferLength];
		while (isStart && !voiceSenderThread.isInterrupted()) {
			line.read(buffer, 0, buffer.length);// 接受麦克风的数据写入buffer
			this.sendVoice(buffer);
		}
	}

	public void sendVoice(byte[] buffer) {
		sendPacket.setData(buffer);
		sendPacket.setLength(buffer.length);
		try {
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			voiceSenderThread.interrupt();
			isStart = false;
			e.printStackTrace();
		}
	}

	public void start() {
		if (voiceSenderThread == null || !voiceSenderThread.isAlive()) {
			voiceSenderThread = new Thread(this);
			line.start();
			voiceSenderThread.start();
			isStart = true;
		}
	}

	public void stop() {
		voiceSenderThread.interrupt();
		line.stop();
		isStart = false;
	}

	public void connectServer() {
		try {
			sendSocket = new DatagramSocket(sendPort);

		} catch (IOException e) {
			e.printStackTrace();
		}
		sendPacket = new DatagramPacket(new byte[0], 0);
	}

}
