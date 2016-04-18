/*
 * 服务器端管理监听用户发送信息的线程
 * */
package singleChat;

import java.util.*;

public class ManageClientThread {
	public static HashMap<String, ServerReceiveThread>  hm = new HashMap<String, ServerReceiveThread>();

	// 向hm中添加线程
	public static void addClientThread(String userName, ServerReceiveThread srt) { // userName是发送方的name,srt是服务器为发送方创建的线程
		hm.put(userName, srt); // 添加入
	}

	public static ServerReceiveThread getClientThread(String userName) { // 通过用户name得到连接
		return (ServerReceiveThread) hm.get(userName);
	}
}
