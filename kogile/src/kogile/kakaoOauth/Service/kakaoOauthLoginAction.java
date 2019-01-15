package kogile.kakaoOauth.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class kakaoOauthLoginAction implements Action {

	/*redirect�� ��û�ϴ� �޼���*/
	private String redirectPath = "";
	private boolean isRedirect;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		KakaoApi kakaoApi = new KakaoApi();
		kakaoApi.setRequest(request);
		
		//���ǿ� AccessToken ����.
		kakaoApi.getAccessToken(request.getParameter("code"));
		
		HttpSession session = request.getSession();
		String ac = (String) session.getAttribute("access_token");
		//kakaoApi.
		//System.out.println(ac);
		//AccessToken�� �޾Ƽ� �װ����� ȸ���� id�� ��ȸ���Ŀ� �̳��� �α����Ϸ�����, ȸ�������Ϸ����� Ȯ���ؾ� �Ѵ�.
		int userId = kakaoApi.tokenValidationCk();
		
		if(userId > 0) {
			//ȸ�� ID�� ���������� �̰Ÿ� ���ǿ� ��Ƽ� �����̷�Ʈ�� �Ѵ�.
			session.setAttribute("kakaoId", userId);
			redirectPath = "ckKogileMem.kakaoOauth";
			isRedirect = true;
		}else {
			System.out.println("ȸ�����̵𸦸���������");
		}
		
		ActionForward forward = new ActionForward();
		
		forward.setPath(redirectPath);
		forward.setRedirect(isRedirect);
		return forward;
	}
}
