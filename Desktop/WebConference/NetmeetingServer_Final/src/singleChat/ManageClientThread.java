/*
 * �������˹�������û�������Ϣ���߳�
 * */
package singleChat;

import java.util.*;

public class ManageClientThread {
	public static HashMap<String, ServerReceiveThread>  hm = new HashMap<String, ServerReceiveThread>();

	// ��hm������߳�
	public static void addClientThread(String userName, ServerReceiveThread srt) { // userName�Ƿ��ͷ���name,srt�Ƿ�����Ϊ���ͷ��������߳�
		hm.put(userName, srt); // �����
	}

	public static ServerReceiveThread getClientThread(String userName) { // ͨ���û�name�õ�����
		return (ServerReceiveThread) hm.get(userName);
	}
}
