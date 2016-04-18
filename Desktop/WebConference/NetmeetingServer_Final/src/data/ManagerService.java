package data;

public class ManagerService implements ManagerInterface{
	private Manager manager;
	
	public ManagerService(Manager  manager) {
		this.manager =manager;
	}

	public ManagerService(String n, String id, String p, String t) {
		manager = new Manager(n, id, p, t);
		
	}
	
	public String getManagerID(){
		return manager.getID();
	}
	
	// 修改自己的密码
	public boolean modifyPassword(String  old,String p) {
		if(manager.checkPassWord(old))
		{
			manager.amendPassword(p);
		
		  return true;
		}
		else
			return false;
		
	}
	
	// 增加员工
	public String addStaff( String n, String id, String p, String t) {
		return manager.addStaff(n,id,p,t);
	}

	// 修改员工信息
	public void modifyStaff(String n,String t, Staff staff) {
		manager.amendStaff(n,t,staff);

	}

	// 删除员工
	public void deleteStaff(String id) {
		manager.deleteStaff(id);

	}

	@Override
	public void writeBack() {
		manager.writeBack();
		
	}
	
	

}
