package userManagement;

import data.Staff;

public interface InfoManageInterface {

	// ��ѯԱ��
	public abstract Staff searchStaffService(String id);

	// ����Ա��
	public abstract void addStaffService(Staff staff);

	// ɾ��Ա��
	public abstract boolean deleteStaffService(String id);

	// �޸�Ա����Ϣ
	public void amendStaffService(String n, String t, Staff staff);

	public abstract Staff checkUser(String id, String password);

	public void writeBack();

}
