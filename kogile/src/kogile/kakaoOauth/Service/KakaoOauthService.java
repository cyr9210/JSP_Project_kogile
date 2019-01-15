package kogile.kakaoOauth.Service;

import javax.servlet.http.HttpServletRequest;

import kogile.kakaoOauth.DAO.KaKaoDao;

public class KakaoOauthService {
	private static KakaoOauthService service = new KakaoOauthService();
	private static KaKaoDao dao;
	
	public static KakaoOauthService getInstance() {
		dao = KaKaoDao.getInstance();
		return service;
	}
	
	// īī���� ȸ������ ��ư Ŭ�� => ��Ʈ�ѷ��� ȸ������ �����.
	// �α��� �������� �̵��Ѵ�. => �α������� �����̷�Ʈ
	// Access��ū�� ������ => ���ǿ��� ��ū �����ϱ�.
	// �̸����̶� �̸� �Է��ϰ� �ϱ�. => �׸��� ������ �̵��ؼ� �̸����̶� �̸� �Է��ϰ� �ϱ�
	// ȸ��id�� �̸��ϰ� �̸��� DB�� �����ϱ�. => �׸��� ���������� �������ͼ��� �����ϱ�. �̸� �Ƶ� �̸� kakao���� �ִ� �Ƶ� �ֱ�.

	public static void kakaoRegister(HttpServletRequest request) {
		//redirect
		Action action = new KakaoOauthRedirectAction();
		action.excute(request, response);
		
		KakaoApi kakaoapi = new KakaoApi();
		kakaoapi.setRequest(request);
		
	}
}
