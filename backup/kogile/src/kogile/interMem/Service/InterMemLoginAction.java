package kogile.interMem.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kogile.interMem.DAO.InterMemDao;
import kogile.module.InterMember;

/*
 * kogile ����Ʈ �α��� ó��
 */

public class InterMemLoginAction implements Action {

	private String redirectPath = "";
	private boolean isRedirect;

	InterMemDao dao = InterMemDao.getInstance();
	
	static final boolean DEBUG = false;
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		//�̹� �����ߴ��� Ȯ��.
		String email = (String) request.getParameter("email");
		System.out.println("����ڰ� �Է��� �̸���" + email);
		InterMember member = null;
		
		//���� �������� ����ó��
		if((member = isAlreadyMember(email)) == null) {
			System.out.println("���̵� ã�� �� �����ϴ�.");
			redirectPath = LoginPath.INTER_MEM_LOGIN; // loginForm���� �ٽ� �̵�.
			isRedirect = true;
		//ȸ����Ͽ� id�� �����Ѵ�.
		}else {
			//������ member ������ id�� ��й�ȣ ��ġ�ϴ��� Ȯ��.
			String plainPw = (String)request.getParameter("password");
			String hashedPw = Sha256Hash.sha256(plainPw);
			System.out.println("����� �Է� ���" + hashedPw);
			
			if(member.getPassword().equals(hashedPw)){
				
				System.out.println("�α��� ����");
				int member_no = (int) member.getMember_no();
				System.out.println("���θ����ȣ: " + member_no);
				int totalMemNum = totalMemNum(member_no);
				System.out.println(totalMemNum);
				session.setAttribute("total_m_no", totalMemNum);
				
				redirectPath = LoginPath.HOME;
				isRedirect = true;
			}else{
				System.out.println("���̵�� ��й�ȣ�� Ȯ���� �ּ���.");
				redirectPath = LoginPath.INTER_MEM_LOGIN; // loginForm���� �ٽ� �̵�.
				isRedirect = true;
			}
		}
		
		ActionForward forward = new ActionForward();
		
		forward.setPath(redirectPath);
		forward.setRedirect(isRedirect);
		return forward;
	}
	
	public InterMember isAlreadyMember(String email) {
		InterMember member = null; 
		member = dao.selectInterMember(email);
		return member;
	}
	
	public int totalMemNum(int member_no) {
		int totalNum = dao.selectTotalMemNumWithInMem(member_no);
		return totalNum;
		
	}
	
}
