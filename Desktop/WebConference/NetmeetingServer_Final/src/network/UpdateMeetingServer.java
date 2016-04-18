package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class UpdateMeetingServer implements Runnable {
	private int port = ConnectConfig.updateMeetingPort;
	private ServerSocket ss;

	public UpdateMeetingServer() {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {

			while (true) {
				Socket socket = ss.accept();
				Update task = new Update(socket);
				new Thread(task).start();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	private class Update implements Runnable {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;

		public Update(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ArrayList<String> fileList = getFileNames();
				oos.writeObject(fileList);
				oos.flush();

				ois = new ObjectInputStream(socket.getInputStream());
				String newMeetingName = ois.readUTF();
				System.out.println(newMeetingName);
				if (newMeetingName != null) {
					addFile(newMeetingName);
					ObjectOutputStream oosr = new ObjectOutputStream(
							socket.getOutputStream());
					ArrayList<String> fileListRefresh = getFileNames();
					oosr.writeObject(fileListRefresh);
					oosr.flush();
				}
			} catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//e.printStackTrace();
			}
		}
	}

	public void addFile(String newMeetingName) {
		File dir = new File("MeetingList" + File.separator + newMeetingName);
		if (!dir.exists()) {
			dir.mkdir();
		}

	}

	private ArrayList<String> getFileNames() {
		File fileDir = new File("MeetingList" + File.separator);
		File[] fileList = fileDir.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				fileNames.add(fileList[i].getName());
			}
		}

		return fileNames;
	}

	public static void main(String args[]) {
		new UpdateMeetingServer();
	}
}
