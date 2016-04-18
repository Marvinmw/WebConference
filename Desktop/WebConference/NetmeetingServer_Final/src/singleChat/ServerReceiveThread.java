/*
 * 当某个合法用户登陆成功后，服务器会产生一个线程来不断地监听客户端发过来的信息
 * */
package singleChat;

import java.io.*;

import java.net.*;

public class ServerReceiveThread extends Thread {
	Socket s; // 登陆成功的那个用户的Socket
	Message ms;

	public ServerReceiveThread(Socket s) {
		this.s = s;
	}

	public void run() {
		ObjectInputStream ois = null;
		InputStream input = null;

		while (true) {
			// 这个线程可以得到客户端的发过来的信息
			try {
				input = this.s.getInputStream();

				ois = new ObjectInputStream(input);

				ms = (Message) ois.readObject();
				System.out.println("单人聊天线程已经启动");
				System.out.println(ms.getSenderName() + "对"
						+ ms.getGetterName() + "说:" + ms.getCon());

				// 取得接收人的通讯线程
				ServerReceiveThread srt = ManageClientThread.getClientThread(ms
						.getGetterName());
				ObjectOutputStream oos = new ObjectOutputStream(srt.s
						.getOutputStream());
				oos.writeObject(ms); // 服务器转发给接收方

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
