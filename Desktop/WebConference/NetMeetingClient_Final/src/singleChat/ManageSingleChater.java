package singleChat;

import java.util.HashMap;

import view.SingleChatFrame;

public class ManageSingleChater {
	private static HashMap hm = new HashMap<String, SingleChatFrame>();

	public static void addSingleChat(String LoginidAndFrienfid,
			SingleChatFrame sc) {
		hm.put(LoginidAndFrienfid, sc);
	}

	public static SingleChatFrame getSingleChat(String LoginidAndFrienfid) {
		return (SingleChatFrame) hm.get(LoginidAndFrienfid);

	}
}
