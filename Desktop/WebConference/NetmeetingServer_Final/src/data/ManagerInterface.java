package data;

public interface ManagerInterface {
	
	//��ȡ����Ա����
	public String getManagerID();
	
	//�޸��Լ�������
	public boolean modifyPassword(String  old,String p);
	
	//����Ա��
	public String addStaff( String n, String id, String p, String t);

	//�޸�Ա����Ϣ
	public void modifyStaff(String n,String t,Staff staff);

	//ɾ��Ա��
	public void deleteStaff(String id);

	public void writeBack();


}
