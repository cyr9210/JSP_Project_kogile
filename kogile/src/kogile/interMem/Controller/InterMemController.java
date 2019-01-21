package kogile.interMem.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kogile.interMem.Service.InterMemberInsertAction;
import kogile.interMem.DAO.InterMemDao;
import kogile.module.TotalMemInfo;
import kogile.interMem.Service.Action;
import kogile.interMem.Service.ActionForward;
import kogile.interMem.Service.InterMemLoginAction;
import kogile.kakaoOauth.Service.KakaoChKogileMemAction;
import kogile.kakaoOauth.Service.KakaoOauthLogoutAction;
import kogile.kakaoOauth.Service.KakaoOauthRedirectAction;
import kogile.kakaoOauth.Service.TotalMemInfoSelectAction;
import kogile.kakaoOauth.Service.kakaoInsertMemberAction;
import kogile.kakaoOauth.Service.kakaoOauthLoginAction;

@WebServlet("*.interMem")
public class InterMemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String WEB_SERVLET = "interMem";
    public InterMemController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		request.setCharacterEncoding("utf-8");
    	String fullUri = request.getRequestURI();
    	
    	Pattern regex = Pattern.compile("(([a-z A-Z]+)." + WEB_SERVLET +")");
    	Matcher regexMatcher = regex.matcher(fullUri);
    	
    	String command = "";
    	if(regexMatcher.find()) {
    		command = regexMatcher.group();
    	}
		
    	System.out.println("���� command:" + command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command != null) {
			if(command.equals("ddd." + WEB_SERVLET)) { //�Ϲ�ȸ���� ������ �Է��ϰ�, submit�� ���� ����.
				 //action = (Action) new KakaoOauthRedirectAction();
				 try {
					//forward = ((KakaoOauthRedirectAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("login." + WEB_SERVLET)) { //�α���ó��
				//id�� pw�ޱ�.
				action = (Action) new InterMemLoginAction();
				try {
					forward = ((InterMemLoginAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("redirect." + WEB_SERVLET)) { //�α׾ƿ�
				action = (Action) new KakaoOauthLogoutAction();
				try {
					//forward = ((KakaoOauthLogoutAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("register." + WEB_SERVLET)) { //ȸ������
				action = (Action) new InterMemberInsertAction();
				try {
					forward = ((InterMemberInsertAction) action).excute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("redirect." + WEB_SERVLET)) { //�츮 ȸ������ Ȯ���ϱ�
				action = (Action) new KakaoChKogileMemAction();
				try {
					//forward = ((KakaoChKogileMemAction) action).excute(request, response);
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
			System.out.println("forward Path�� null�Դϴ�. ������ �ּ���.");
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
