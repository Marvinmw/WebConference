package singleChat;

import java.util.HashMap;

public class ManageClientConServerThread {
	public static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();

	// ��hm������߳�
	public static void addClientConServerThread(String userName,
			ClientConServerThread ccst) { // userName�Ƿ��ͷ���name,srt�Ƿ�����Ϊ���ͷ��������߳�
		hm.put(userName, ccst); // �����
	}

	public static ClientConServerThread getClientConServerThread(String userName) { // ͨ���û�name�õ�����
		return (ClientConServerThread) hm.get(userName);
	}
}
