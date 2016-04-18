/*
 * ����������9999�˿ڿ�ʼ����
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
			// �û���¼��ʱ����������������ӣ���User����������
			try {
				s = ss.accept();

				System.out.println("�Ѿ���������");
				ois = new ObjectInputStream(s.getInputStream());

				u = (String) ois.readObject();

				// ����������һ���߳̽��տͻ��˷�������Message ��
				ServerReceiveThread srt = new ServerReceiveThread(s);
				srt.start();
				mmc.addClientThread(u, srt); // ���������̷߳���HashMap��
				System.out.println(u + "����HashMap");

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
