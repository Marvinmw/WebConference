package userManagement;

import data.Staff;

public interface InfoManageInterface {

	// 查询员工
	public abstract Staff searchStaffService(String id);

	// 增加员工
	public abstract void addStaffService(Staff staff);

	// 删除员工
	public abstract boolean deleteStaffService(String id);

	// 修改员工信息
	public void amendStaffService(String n, String t, Staff staff);

	public abstract Staff checkUser(String id, String password);

	public void writeBack();

}
