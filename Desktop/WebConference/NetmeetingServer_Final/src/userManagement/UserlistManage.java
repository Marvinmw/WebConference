package userManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.Manager;
import data.Staff;
import data.User;

public class UserlistManage {
	ArrayList<Staff> user_list;
	ArrayList<Staff> newUserList = new ArrayList<Staff>();
	private Staff checkStaff;

	public UserlistManage() {

	}

	public void writeBack(ArrayList<Staff> user_list) {
		this.user_list = user_list;
		try {
			File file1 = new File("StaffInfo" + File.separatorChar
					+ "Userlist.txt");
			FileWriter filewriter1 = new FileWriter(file1);
			BufferedWriter buffer1 = new BufferedWriter(filewriter1);
			if (!user_list.isEmpty())
				for (int i = 0; i < user_list.size(); i++)
					if (user_list.get(i) instanceof User) {
						buffer1.write("User" + "/"
								+ user_list.get(i).toString());
						buffer1.newLine();
					}

			buffer1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Staff> read() {
		newUserList.removeAll(newUserList);
		try {
			// 读入用户信息
			File staffFile = new File("StaffInfo" + File.separator
					+ "Userlist.txt");
			FileReader fileReader2 = new FileReader(staffFile);
			BufferedReader bufreader2 = new BufferedReader(fileReader2);
			String line2 = null;
			while ((line2 = bufreader2.readLine()) != null) {
				if (line2.equals(""))
					break;
				String[] info = line2.split("/");
				if (info[0].equals("User"))
					newUserList
							.add(new User(info[1], info[2], info[3], info[4]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newUserList;
	}

	public void removeLeaveUser(String leaveName) {
		newUserList.removeAll(newUserList);
		try {
			// 读入用户信息
			File staffFile = new File("StaffInfo" + File.separator
					+ "Userlist.txt");
			FileReader fileReader2 = new FileReader(staffFile);
			BufferedReader bufreader2 = new BufferedReader(fileReader2);
			String line2 = null;
			while ((line2 = bufreader2.readLine()) != null) {
				if (line2.equals(""))
					break;
				String[] info = line2.split("/");
				if (info[0].equals("User"))
					newUserList
							.add(new User(info[1], info[2], info[3], info[4]));
			}
			System.out.println("userList:" + newUserList.size());
			System.out.println(leaveName);
			int index = 0;
			for (int i = 0; i < newUserList.size(); i++) {
				if (newUserList.get(i).getName().equals(leaveName)) {
					index = i;
					System.out.println("Remove");
				}
			}
			newUserList.remove(index);
			writeBack(newUserList);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
