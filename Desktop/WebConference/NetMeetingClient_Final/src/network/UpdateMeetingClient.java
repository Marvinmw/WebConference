package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;

public class UpdateMeetingClient {

	private int port = ConnectConfig.updateMeetingPort;
	private String IP = ConnectConfig.serverIP;
	private ArrayList<String> meetingName = null;
	private ArrayList<String> meetingNameNew = null;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket s;
	JList meetingNameList;
	AbstractListModel newNameModel;

	public UpdateMeetingClient() throws Exception {
		s = new Socket(IP, port);
		try {
			ois = new ObjectInputStream(s.getInputStream());
			meetingName = (ArrayList<String>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refresh(String newMeetingName) {
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeUTF(newMeetingName);
			oos.flush();
			ObjectInputStream oisr = new ObjectInputStream(s.getInputStream());
			meetingNameNew = (ArrayList<String>) oisr.readObject();
			final String[] newFileName = new String[meetingNameNew.size()];
			for (int i = 0; i < meetingNameNew.size(); i++) {
				newFileName[i] = meetingNameNew.get(i);
			}

			newNameModel = new AbstractListModel() {
				String[] fileStrings = newFileName;

				public int getSize() {
					return fileStrings.length;
				}

				public Object getElementAt(int i) {
					return fileStrings[i];
				}
			};
			meetingNameList.setModel(newNameModel);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setJList(JList meetingNameList) {
		this.meetingNameList = meetingNameList;
	}

	public ArrayList<String> getMeetingNameList() {
		return meetingName;
	}

}
