package textChat;

import javax.swing.JTextArea;

public class TextChatDealer {
	private TextChatClient client = new TextChatClient(this);
	private JTextArea text;

	public TextChatDealer(JTextArea text) {
		this.text = text;
		startnet();
	}

	// 启动网络
	private void startnet() {
		Thread th = new Thread(client);
		th.start();
	}

	// 显示接受到的字符串
	public void appendString(String message) {
		text.append(message + "\n");
	}

	// 发送message
	public void sendMessage(String message) {
		TextChatClient.writeObject(message);
	}
	
	public void stop(){
		client.stop();
	}
}
