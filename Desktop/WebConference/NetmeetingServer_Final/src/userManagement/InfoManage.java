package userManagement;

import java.io.*;
import java.util.ArrayList;

import data.Manager;
import data.Staff;
import data.User;

public class InfoManage {
	ArrayList<Staff> staff_list = new ArrayList<Staff>();

	public void read() {
		try {
			// 读入用户信息
			File staffFile = new File("StaffInfo" + File.separator
					+ "Staff.txt");
			FileReader fileReader2 = new FileReader(staffFile);
			BufferedReader bufreader2 = new BufferedReader(fileReader2);
			String line2 = null;
			while ((line2 = bufreader2.readLine()) != null) {
				if (line2.equals(""))
					break;
				String[] info = line2.split("/");
				if (info[0].equals("User"))
					staff_list
							.add(new User(info[1], info[2], info[3], info[4]));
				else
					staff_list.add(new Manager(info[1], info[2], info[3],
							info[4]));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 查询员工
	public Staff searchStaff(String id) {
		if (!staff_list.isEmpty())
			for (int i = 0; i < staff_list.size(); i++)
				if (id.equals(staff_list.get(i).getID()))
					return staff_list.get(i);
		return null;
	}

	// 增加员工
	public void addStaff(Staff staff) {
		if (staff instanceof User)
			staff_list.add(staff);

	}

	// 删除员工
	public boolean deleteStaff(String id) {
		Staff staff = this.searchStaff(id);
		if (staff instanceof User)
			return staff_list.remove(staff);
		return false;
	}

	// 修改员工信息
	public void amendStaff(String n, String p, Staff staff) {
		staff.setName(n);
		staff.setPosition(p);

	}

	// 登录检查
	public Staff checkUser(String id, String password) {
		if (staff_list.isEmpty())
			return null;
		for (int i = 0; i < staff_list.size(); i++)
			if (id.equals(staff_list.get(i).getID()))
				if (password.equals(staff_list.get(i).getPassword()))
					return staff_list.get(i);
		return null;

	}

	public void writeBack() {

		try {
			File file1 = new File("StaffInfo" + File.separatorChar
					+ "Staff.txt");
			FileWriter filewriter1 = new FileWriter(file1);
			BufferedWriter buffer1 = new BufferedWriter(filewriter1);
			if (!staff_list.isEmpty())
				for (int i = 0; i < staff_list.size(); i++)
					if (staff_list.get(i) instanceof User) {
						buffer1.write("User" + "/"
								+ staff_list.get(i).toString());
						buffer1.newLine();
					} else {
						buffer1.write("Manager" + "/"
								+ staff_list.get(i).toString());
						buffer1.newLine();
					}
			buffer1.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
