package whiteBoard;

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
	private WhiteBoardServer boardserver;
	private Vector<Object> bufferclientboard = new Vector<Object>();
	public ActionEventNet umldata;

	public SocketClient(Socket socket, WhiteBoardServer bo) {
		socketClient = socket;
		boardserver = bo;
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
				umldata = (ActionEventNet) obinput.readObject();

				if (umldata != null)
					boardserver.dispach(umldata, this);

			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				umldata = null;
			}

		}
		boardserver.close(this);
	}

	public void wtrite(Object ob) {
		// TODO Auto-generated method stub
		try {
			getObjectOutputStreamInstance().writeObject(ob);
			System.out.println("write()" + ob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
