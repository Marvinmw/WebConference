package whiteBoard;

import java.io.IOException;

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
public class WhiteBoardServer implements Runnable {

	private ServerSocket server;
	private int port = ConnectConfig.whiteBoardPort;
	private Vector<SocketClient> clients = new Vector<SocketClient>();

	private Vector<ActionEventNet> commondHavedoneList = new Vector<ActionEventNet>();

	public void startServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("WhiteBoard server has started.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
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

	public void run() {
		startServer();
		while (true) {
			try {
				Socket so = server.accept();
				SocketClient sclient = new SocketClient(so, this);
				sclient.start();

				System.out.println("Client" + sclient + "  Linked");
				clients.add(sclient);
				if (!commondHavedoneList.isEmpty())
					this.sendHistoyData(sclient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void dispach(ActionEventNet ob, SocketClient sc) {
		commondHavedoneList.add(ob);
		for (int i = 0; i < clients.size(); i++) {
			if (!clients.elementAt(i).equals(sc))
				clients.elementAt(i).wtrite(ob);

			System.out.println("Client  " + ob + "   use");
		}

	}

	public void close(SocketClient socketClient) {
		clients.remove(socketClient);
	}

	public void sendHistoyData(SocketClient sclient) {
		sclient.wtrite(new WhiteBoardHistoryData(commondHavedoneList));
	}

}
