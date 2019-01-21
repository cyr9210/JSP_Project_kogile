package kogile.kakaoOauth.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kogile.interMem.DAO.InterMemDao;
import kogile.module.IsInterMem;
import kogile.module.TotalMemInfo;

public class TotalMemInfoSelectAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TotalMemNo�� ����ڸ� ��ȸ�Ѵ�.
		IsInterMem memberNum = null;
		InterMemDao dao = InterMemDao.getInstance();
		memberNum = dao.IsInterMember(11);
		
		TotalMemInfo memInfo = null;
		//�Ϲ�ȸ���� �ش�
		if(memberNum.getMember_no() != null) {
			int num = Integer.parseInt(memberNum.getMember_no());
			memInfo = dao.interMemSearch(num);
		}else {
			int num = Integer.parseInt(memberNum.getExter_m_no());
			memInfo = dao.exterMemSearch(num);
		}
		
		System.out.println("�̸�:" + memInfo.getMember_name());
		System.out.println("�̸���:" + memInfo.getMember_email());
		return null;
	}

}
