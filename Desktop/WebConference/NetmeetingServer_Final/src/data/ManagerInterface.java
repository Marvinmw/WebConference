package data;

public interface ManagerInterface {
	
	//获取管理员工号
	public String getManagerID();
	
	//修改自己的密码
	public boolean modifyPassword(String  old,String p);
	
	//增加员工
	public String addStaff( String n, String id, String p, String t);

	//修改员工信息
	public void modifyStaff(String n,String t,Staff staff);

	//删除员工
	public void deleteStaff(String id);

	public void writeBack();


}
