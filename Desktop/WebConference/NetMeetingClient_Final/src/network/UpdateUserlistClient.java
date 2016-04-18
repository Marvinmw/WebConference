package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import data.Staff;

public class UpdateUserlistClient extends Thread {
	private int port = ConnectConfig.updateUserPort;
	private String IP = ConnectConfig.serverIP;
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<Staff> userlist = null;
	private JList jlist_user;
	private AbstractListModel alm_userList;

	private String[] newUserlist;

	private MeetingCommand meetingCommand;
	private boolean isConnect = true;
	private String staffName;

	public UpdateUserlistClient(JList jlist_user, String staffName) {
		this.jlist_user = jlist_user;
		this.staffName = staffName;
		try {
			s = new Socket(IP, port);
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			oos.writeObject(MeetingCommand.JoinMeeting);
			oos.writeUTF(staffName);
			oos.flush();
			while (isConnect) {
				meetingCommand = (MeetingCommand) ois.readObject();
				switch (meetingCommand) {
				case JoinMeeting:
					userlist = (ArrayList<Staff>) ois.readObject();
					updateUIList();
					break;
				case AddStaff:
					//
					String addStaffName = (String) ois.readUTF();
					addStaff(addStaffName);
					//
					updateUIList();
					System.out.println(addStaffName);
					break;
				case RemoveStaff:
					String removeStaffName = (String) ois.readUTF();
					removeStaff(removeStaffName);
					updateUIList();
					break;
				case LeaveMeeting:
					isConnect = false;
					break;
				}
			}
			ois.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void leaveMeeting() {
		try {
			System.out.println("client");
			oos.writeObject(MeetingCommand.LeaveMeeting);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addStaff(String staffName) {
		userlist.add(new Staff(staffName, "062", "062", "FRIENDS"));
	}

	private void removeStaff(String staffName) {
		for (Staff staff : userlist) {
			if (staff.getName().equals(staffName)) {
				userlist.remove(staff);
				break;
			}
		}
	}

	private void updateUIList() {
		if (userlist != null) {

			for (int i = 0; i < userlist.size() - 1; i++) {
				for (int j = userlist.size() - 1; j > i; j--) {
					if (userlist.get(j).getName()
							.equals(userlist.get(i).getName())) {
						userlist.remove(j);
					}
				}
			}

			newUserlist = new String[userlist.size()];
			for (int i = 0; i < userlist.size(); i++) {
				newUserlist[i] = userlist.get(i).getName();
			}
			if (jlist_user != null) {
				alm_userList = new AbstractListModel() {
					String[] userStrings = newUserlist;

					public int getSize() {
						return userStrings.length;
					}

					public Object getElementAt(int i) {
						return userStrings[i];
					}
				};
				jlist_user.setModel(alm_userList);
			}
		}
	}

	public ArrayList<Staff> getUserList() {
		return userlist;
	}

}
