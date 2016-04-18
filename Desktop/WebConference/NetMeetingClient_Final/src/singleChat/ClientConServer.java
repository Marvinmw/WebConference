package singleChat;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import network.ConnectConfig;

public class ClientConServer {

	public Socket s;
	private String ip = ConnectConfig.serverIP;
	private int port = ConnectConfig.singleChatPort;
	private String name;
	private ClientConServerThread ccst;

	public ClientConServer(String na) {
		name = na;
		SendUserToServer(name);
	}

	public void SendUserToServer(Object o) {
		try {
			s = new Socket(ip, port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ccst = new ClientConServerThread(s);

			ccst.start();

			ManageClientConServerThread.addClientConServerThread((String) o,
					ccst);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		ccst.stopChater();
	}

}
