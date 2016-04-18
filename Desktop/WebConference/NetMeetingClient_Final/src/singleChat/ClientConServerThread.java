package singleChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.SingleChatFrame;

public class ClientConServerThread extends Thread {
	private Socket s;
	private boolean isRun = true;

	public ClientConServerThread(Socket s) {
		this.s = s;

	}

	public Socket getSocket() {
		return s;
	}

	public void setSocket(Socket s) {
		this.s = s;
	}

	public void run() {
		ObjectInputStream ois = null;
		InputStream in = null;

		while (isRun) {
			try {
				in = s.getInputStream();
				if (in != null) {
					ois = new ObjectInputStream(in);
				}

				Message ms = (Message) ois.readObject();

				SingleChatFrame singlechat = isNewChatting(ms);
				singlechat.setVisible(true);
				singlechat.showMessage(ms);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Duo to network£¬can not receive temporarily!");
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			if (ois != null)
				ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SingleChatFrame isNewChatting(Message ms) {
		SingleChatFrame singlechat = ManageSingleChater.getSingleChat(ms
				.getGetterName() + " " + ms.getSenderName());

		if (singlechat == null) {
			if (JOptionPane.showConfirmDialog(null, ms.getSenderName()
					+ "wants to chat with you£¬yes or not?", "Exit Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				singlechat = new SingleChatFrame(ms.getGetterName(),
						ms.getSenderName());
				ManageSingleChater.addSingleChat(
						ms.getGetterName() + " " + ms.getSenderName(),
						singlechat);
			}
		}
		return singlechat;
	}

	public void stopChater() {
		isRun = false;
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
