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
	
	// �޸�Ա����Ϣ
	public void amendStaff(String n, String t, Staff staff) {
		infoManage.amendStaffService(n, t, staff);
	}

	// ɾ��Ա��
	public void deleteStaff(String id) {
		infoManage.deleteStaffService(id);
	}

	// ����Ա��
	public String addStaff( String n, String id, String p, String t) {
		Staff staff = new Staff(n, id, p,t);
		infoManage.addStaffService(staff);
		return staff.getID();
	}
	
	// �޸��Լ�������
	public void amendPassword(String p) {
		this.setPassword(p);
	}

	public void writeBack() {
		infoManage.writeBack();
		
	}
	
	// ���ʱ���ã������ı�
	public String toString() {
		return name + "/" + ID + "/" + password + "/" + position ;
	}
	
	

}
