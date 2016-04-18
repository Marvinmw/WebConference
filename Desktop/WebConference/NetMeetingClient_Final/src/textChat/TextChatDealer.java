package textChat;

import javax.swing.JTextArea;

public class TextChatDealer {
	private TextChatClient client = new TextChatClient(this);
	private JTextArea text;

	public TextChatDealer(JTextArea text) {
		this.text = text;
		startnet();
	}

	// ��������
	private void startnet() {
		Thread th = new Thread(client);
		th.start();
	}

	// ��ʾ���ܵ����ַ���
	public void appendString(String message) {
		text.append(message + "\n");
	}

	// ����message
	public void sendMessage(String message) {
		TextChatClient.writeObject(message);
	}
	
	public void stop(){
		client.stop();
	}
}
