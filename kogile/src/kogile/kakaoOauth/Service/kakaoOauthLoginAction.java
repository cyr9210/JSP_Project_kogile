package kogile.kakaoOauth.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class kakaoOauthLoginAction implements Action {

	private String redirectPath = "";
	private boolean isRedirect;
	
	//KaKaoOauth Redirect
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ȸ������ ������, �α��� ������ DedirectAction ��Ű���� �մϴ�.
		
		KakaoApi kakaoApi = new KakaoApi();
		kakaoApi.setRequest(request);
		
		//Access����� �޾ƿ���, getAccessToken���� ���ǿ� AccessToken ����.
		kakaoApi.getAccessToken(request.getParameter("code"));
		
		HttpSession session = request.getSession();
		
		//AccessToken�� �޾Ƽ� �װ����� ȸ���� id�� ��ȸ���Ŀ� �̳��� �α����Ϸ�����, ȸ�������Ϸ����� Ȯ���ؾ� �Ѵ�.
		int userId = kakaoApi.tokenValidationCk();
		
		if(userId > 0) {
			//ȸ�� ID�� ���������� �̰Ÿ� ���ǿ� ��Ƽ� �����̷�Ʈ�� �Ѵ�.
			session.setAttribute("kakaoId", userId);
			
			//ȸ�������� �õ��ϴ°���, �α����� �õ��ϴ°��� Ȯ���Ѵ�.
			redirectPath = "ckKogileMem.kakaoOauth";
			isRedirect = true;
		}else {
			System.out.println("kakao tokenValidationCh error");
		}
		
		ActionForward forward = new ActionForward();
		
		forward.setPath(redirectPath);
		forward.setRedirect(isRedirect);
		return forward;
	}
}
