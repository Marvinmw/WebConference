package singleChat;

import java.util.HashMap;

public class ManageClientConServerThread {
	public static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();

	// 向hm中添加线程
	public static void addClientConServerThread(String userName,
			ClientConServerThread ccst) { // userName是发送方的name,srt是服务器为发送方创建的线程
		hm.put(userName, ccst); // 添加入
	}

	public static ClientConServerThread getClientConServerThread(String userName) { // 通过用户name得到连接
		return (ClientConServerThread) hm.get(userName);
	}
}
