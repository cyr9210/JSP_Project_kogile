package kogile.kakaoOauth.Controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kogile.kakaoOauth.Service.Action;
import kogile.kakaoOauth.Service.ActionForward;
import kogile.kakaoOauth.Service.KakaoChKogileMemAction;
import kogile.kakaoOauth.Service.KakaoOauthLogoutAction;
import kogile.kakaoOauth.Service.KakaoOauthRedirectAction;
import kogile.kakaoOauth.Service.kakaoInsertMemberAction;
import kogile.kakaoOauth.Service.kakaoOauthLoginAction;


@WebServlet("*.kakaoOauth")
public class kakaoOauthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public kakaoOauthController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
    	String fullUri = request.getRequestURI();
    	
    	Pattern regex = Pattern.compile("(([a-z A-Z]+).kakaoOauth)");
    	Matcher regexMatcher = regex.matcher(fullUri);
    	
    	String command = "";
    	if(regexMatcher.find()) {
    		command = regexMatcher.group();
    	}
		
    	System.out.println("���� command:" + command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command != null) {
			if(command.equals("redirect.kakaoOauth")) { //īī���� �α����� ����
				 action = (Action) new KakaoOauthRedirectAction();
				 try {
					forward = ((KakaoOauthRedirectAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("login.kakaoOauth")) { //�α���ó��
				action = (Action) new kakaoOauthLoginAction();
				try {
					forward = ((kakaoOauthLoginAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("logout.kakaoOauth")) { //�α׾ƿ�
				action = (Action) new KakaoOauthLogoutAction();
				try {
					forward = ((KakaoOauthLogoutAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("register.kakaoOauth")) { //ȸ������
				System.out.println("register");
				action = (Action) new kakaoInsertMemberAction();
				try {
					forward = ((kakaoInsertMemberAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("ckKogileMem.kakaoOauth")) { //�츮 ȸ������ Ȯ���ϱ�
				action = (Action) new KakaoChKogileMemAction();
				try {
					forward = ((KakaoChKogileMemAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				try {
					response.sendRedirect(forward.getPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				try {
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("forward�� null�̳�? �ϴ� �����ٰ�.");
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
