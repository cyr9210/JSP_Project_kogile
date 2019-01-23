package kogile.interMem.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InterMemLogoutAction implements Action {
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("���θ�� �α׾ƿ�");

		ActionForward forward = new ActionForward();
		forward.setPath(LoginPath.HOME);
		forward.setRedirect(true);
		return forward;
	}
}
