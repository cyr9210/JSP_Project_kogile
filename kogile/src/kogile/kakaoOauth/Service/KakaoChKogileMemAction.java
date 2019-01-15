package kogile.kakaoOauth.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kogile.kakaoOauth.DAO.KaKaoDao;
import kogile.module.ExterMember;

public class KakaoChKogileMemAction implements Action {

	private String redirectPath = "";
	private boolean isRedirect;
	
	private static final String REGISTER_FORM_PATH = "view/registerForm.jsp";
	private static final String MAIN_PAGE = "view/KakaoOauthTest.jsp";
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�켱 ���ǿ��� ID ��������.
		HttpSession session = request.getSession();
		int kakaoId = (int) session.getAttribute("kakaoId");
		
		ExterMember extermember = null;
		KaKaoDao dao = KaKaoDao.getInstance();
		extermember = dao.selectExterMember(kakaoId);
		//�α��� �õ�. �����ϸ� session�� total_m_no��� �̸����� ����� ID�� �ֽ��ϴ�.
		if(extermember != null) {
			System.out.println("�湮ȯ���մϴ�." +extermember.getExter_m_email());
			System.out.println(extermember.getExter_mem_name());
			//�α��ν� ���������� ������.. ���ǿ� ���������� ��� �ѹ��� �־��ݽô�. total_m_no
			redirectPath = MAIN_PAGE;
			isRedirect = true;
		}else{
			System.out.println("ȸ�������� �ȵǾ��ֳ׿� ȸ������ �Ϸ� ���ô�.");
			redirectPath = REGISTER_FORM_PATH;
			isRedirect = true; //���ǿ� KaKaoId�� ����ä�� �α��� ������ �̵�.
		}
		ActionForward forward = new ActionForward();
		forward.setPath(redirectPath);
		forward.setRedirect(isRedirect);
		return forward;
	}

}
