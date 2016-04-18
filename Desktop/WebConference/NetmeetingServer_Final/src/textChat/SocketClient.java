package textChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class SocketClient extends Thread {

	private Socket socketClient;
	private ObjectInputStream obinput;
	private ObjectOutputStream oboutput;
	private TextChatServer textServer;
	private Vector<Object> bufferclientboard = new Vector<Object>();
	public Object textData;

	public SocketClient(Socket socket, TextChatServer text) {
		socketClient = socket;
		textServer = text;
		try {
			oboutput = new ObjectOutputStream(socketClient.getOutputStream());
			obinput = new ObjectInputStream(socketClient.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** get the object outputstream of the socket */
	public ObjectOutputStream getObjectOutputStreamInstance() {

		if (socketClient.isConnected()) {
			if (oboutput != null)
				return oboutput;
			else
				try {
					return oboutput = new ObjectOutputStream(socketClient
							.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	/** get the object inputstream of the socket */
	public ObjectInputStream getObjectInputStreamInstance() {
		if (socketClient.isConnected()) {
			if (obinput != null)
				return obinput;
			else
				try {
					return obinput = new ObjectInputStream(socketClient
							.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return null;
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				textData = obinput.readObject();

				if (textData != null)
					textServer.dispatch(textData, this);

			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				textData = null;
			}

		}
		textServer.close(this);
	}

	public void wtrite(Object ob) {
		// TODO Auto-generated method stub
		try {
			this.getObjectOutputStreamInstance().writeObject(ob);
			System.out.println("服务器转发文字信息：\n" + ob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
