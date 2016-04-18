package textChat;

import java.io.IOException;
import java.io.PrintStream;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import network.ConnectConfig;

/**
 * the class is used as a socket sever and receives information from clients
 * translate to all clients
 * */
public class TextChatServer implements Runnable {

	private ServerSocket server;
	private int port = ConnectConfig.textChatPort;
	private Vector<SocketClient> clients = new Vector<SocketClient>();

	private Vector<Object> messageHavedoneList = new Vector<Object>();

	public void startTextChatServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("TextChat server has started.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void run() {
		
		this.startTextChatServer();
		
		while (true) {
			try {
				Socket so = server.accept();
				SocketClient sclient = new SocketClient(so, this);
				sclient.start();
                
				sclient.wtrite(messageHavedoneList);//这行代码到底是在干神马？
				System.out.println("Client" + sclient + "  Linked");
				clients.add(sclient);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void dispatch(Object ob, SocketClient sc) {
		messageHavedoneList.add(ob);
		for (int i = 0; i < clients.size(); i++) {
			if (!clients.elementAt(i).equals(sc))
				clients.elementAt(i).wtrite(ob);

			System.out.println(ob);//控制台输出用户发的文字信息
		}

	}

	public void close(SocketClient socketClient) {
		clients.remove(socketClient);
	}

	public static void main(String[] args) {
		TextChatServer textChat = new TextChatServer();
		Thread severChat = new Thread(textChat);
		severChat.start();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
