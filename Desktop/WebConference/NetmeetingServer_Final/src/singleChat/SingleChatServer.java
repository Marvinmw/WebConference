/*
 * 服务器启动9999端口开始监听
 * */
package singleChat;

import java.net.*;

import java.io.*;

import network.ConnectConfig;



public class SingleChatServer implements Runnable {
	/*public static void main(String[] args) {
		SingleServer ms = new SingleServer();
		Thread t=new Thread(ms);
		t.start();
	}*/

	ManageClientThread mmc = new ManageClientThread();
	Socket s;
	ServerSocket ss;
	Message ms;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	String u;

	public SingleChatServer() {
		try {
			ss = new ServerSocket(ConnectConfig.singleChatPort);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {

		while (true) {

			System.out.println("SinleChat server has started");
			// 用户登录的时候与服务器建立连接，把User传给服务器
			try {
				s = ss.accept();

				System.out.println("已经建立连接");
				ois = new ObjectInputStream(s.getInputStream());

				u = (String) ois.readObject();

				// 服务器单开一个线程接收客户端发过来的Message 包
				ServerReceiveThread srt = new ServerReceiveThread(s);
				srt.start();
				mmc.addClientThread(u, srt); // 将创建的线程放入HashMap中
				System.out.println(u + "加入HashMap");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
