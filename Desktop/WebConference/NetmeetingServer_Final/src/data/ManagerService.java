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
	
	// �޸��Լ�������
	public boolean modifyPassword(String  old,String p) {
		if(manager.checkPassWord(old))
		{
			manager.amendPassword(p);
		
		  return true;
		}
		else
			return false;
		
	}
	
	// ����Ա��
	public String addStaff( String n, String id, String p, String t) {
		return manager.addStaff(n,id,p,t);
	}

	// �޸�Ա����Ϣ
	public void modifyStaff(String n,String t, Staff staff) {
		manager.amendStaff(n,t,staff);

	}

	// ɾ��Ա��
	public void deleteStaff(String id) {
		manager.deleteStaff(id);

	}

	@Override
	public void writeBack() {
		manager.writeBack();
		
	}
	
	

}
