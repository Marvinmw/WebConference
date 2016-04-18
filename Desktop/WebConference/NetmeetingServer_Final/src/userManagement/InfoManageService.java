package userManagement;

import data.Staff;

public class InfoManageService implements InfoManageInterface {
	private InfoManage infoManage;

	public InfoManageService() {
		infoManage = new InfoManage();
		infoManage.read();

	}

	/*
	 * public void inital(){ infoManage.read();
	 * 
	 * }
	 */

	// ��ѯԱ��

	@Override
	public Staff searchStaffService(String id) {
		return infoManage.searchStaff(id);
	}

	// ����Ա��

	@Override
	public void addStaffService(Staff staff) {
		infoManage.addStaff(staff);
		infoManage.writeBack();

	}

	// ɾ��Ա��

	@Override
	public boolean deleteStaffService(String id) {
		if (infoManage.deleteStaff(id)) {
			infoManage.writeBack();
			return true;
		} else
			return false;
	}

	// �޸�Ա��
	public void amendStaffService(String n, String p, Staff staff) {
		infoManage.amendStaff(n, p, staff);
		infoManage.writeBack();
	}

	public Staff checkUser(String id, String password) {
		return infoManage.checkUser(id, password);
	}

	public void writeBack() {
		infoManage.writeBack();
	}

}
