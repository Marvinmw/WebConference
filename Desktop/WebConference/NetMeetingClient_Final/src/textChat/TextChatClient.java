package textChat;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import network.ConnectConfig;

public class TextChatClient implements Runnable {
	private static Socket client;
	private static String ip = ConnectConfig.serverIP;
	private static int port = ConnectConfig.textChatPort;

	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	private TextChatDealer chatDealer;

	private boolean isRun = true;

	public TextChatClient(TextChatDealer chatDealer) {

		try {
			this.chatDealer = chatDealer;
			client = new Socket(ip, port);
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 静态调用此方法放送文本信息
	public static void writeObject(Object ob) {
		try {
			System.out.println("TextChat command write:" + ob);
			output.writeObject(ob);
		} catch (IOException e) {
			restartClient();
			e.printStackTrace();
		}
	}

	// 异常处理
	private static void restartClient() {
		try {
			client.close();
			client = new Socket(ip, port);
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (isRun) {
			try {
				Object ob = input.readObject();

				if (ob != null) {

					System.out.println("TextChat command read:" + ob);
					dispach(ob);
				}

			} catch (IOException e) {
				restartClient();
				// e.printStackTrace();
			} catch (ClassNotFoundException e) {
				restartClient();
				// e.printStackTrace();
			}
		}
	}

	// 把接受的的字符添加到文本框里
	private void dispach(Object ob) {
		if (ob instanceof Vector)
			for (int i = 0; i < ((Vector<Object>) ob).size(); i++)
				chatDealer.appendString((String) ((Vector<Object>) ob).get(i));
		else
			chatDealer.appendString((String) ob);
	}

	public void stop() {
		// TODO Auto-generated method stub
		isRun = false;
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}
