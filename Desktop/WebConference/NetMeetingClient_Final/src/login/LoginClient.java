package login;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import network.ConnectConfig;

import data.Staff;

public class LoginClient {
	private int port = ConnectConfig.loginPort;
	private String IP = ConnectConfig.serverIP;
	private Socket s;
	private Staff checkStaff;
	private ObjectInputStream ois;
	private ObjectInputStream inputFromServer;
	private ObjectOutputStream toServer;
	private ArrayList<Staff> userlist;

	public LoginClient(String ID, String password) {
		try {
			s = new Socket(IP, port);

			toServer = new ObjectOutputStream(s.getOutputStream());

			Staff staff = new Staff(ID, password);
			toServer.writeObject(staff);

			inputFromServer = new ObjectInputStream(s.getInputStream());
			this.setCheckStaff((Staff) inputFromServer.readObject());

		} catch (IOException ex) {
			System.err.println(ex);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public void setCheckStaff(Staff checkStaff) {
		this.checkStaff = checkStaff;
	}

	public Staff getCheckStaff() {
		return checkStaff;
	}

	public ArrayList<Staff> getUserList() {
		return userlist;
	}

}
