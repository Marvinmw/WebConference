/*
 * ��ĳ���Ϸ��û���½�ɹ��󣬷����������һ���߳������ϵؼ����ͻ��˷���������Ϣ
 * */
package singleChat;

import java.io.*;

import java.net.*;

public class ServerReceiveThread extends Thread {
	Socket s; // ��½�ɹ����Ǹ��û���Socket
	Message ms;

	public ServerReceiveThread(Socket s) {
		this.s = s;
	}

	public void run() {
		ObjectInputStream ois = null;
		InputStream input = null;

		while (true) {
			// ����߳̿��Եõ��ͻ��˵ķ���������Ϣ
			try {
				input = this.s.getInputStream();

				ois = new ObjectInputStream(input);

				ms = (Message) ois.readObject();
				System.out.println("���������߳��Ѿ�����");
				System.out.println(ms.getSenderName() + "��"
						+ ms.getGetterName() + "˵:" + ms.getCon());

				// ȡ�ý����˵�ͨѶ�߳�
				ServerReceiveThread srt = ManageClientThread.getClientThread(ms
						.getGetterName());
				ObjectOutputStream oos = new ObjectOutputStream(srt.s
						.getOutputStream());
				oos.writeObject(ms); // ������ת�������շ�

			} catch (Exception e) {
			
				break;
			}
		}
		try {
			if (ois != null)
				ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
