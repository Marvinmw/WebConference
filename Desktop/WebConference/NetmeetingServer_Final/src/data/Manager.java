package data;

import userManagement.InfoManageService;


public class Manager extends Staff{
	private static InfoManageService infoManage = new InfoManageService();

	public Manager(){
		this("None","-1","None","None");
	}
	
	public Manager(String n, String id, String p, String t){
		name = n;
		ID = id;
		password = p;
		position = t;		
	}
	
	// 修改员工信息
	public void amendStaff(String n, String t, Staff staff) {
		infoManage.amendStaffService(n, t, staff);
	}

	// 删除员工
	public void deleteStaff(String id) {
		infoManage.deleteStaffService(id);
	}

	// 增加员工
	public String addStaff( String n, String id, String p, String t) {
		Staff staff = new Staff(n, id, p,t);
		infoManage.addStaffService(staff);
		return staff.getID();
	}
	
	// 修改自己的密码
	public void amendPassword(String p) {
		this.setPassword(p);
	}

	public void writeBack() {
		infoManage.writeBack();
		
	}
	
	// 输出时调用，生成文本
	public String toString() {
		return name + "/" + ID + "/" + password + "/" + position ;
	}
	
	

}
