package kogile.interMem.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kogile.interMem.Controller.InterMemController;
import kogile.interMem.Service.LoginPath;


@WebServlet("/logout")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ǿ� Total..�� ����.
		//���� �̻���� ����ȸ������, �ܺ�ȸ������ �Ǵ��ؾ� �Ѵ�.
		System.out.println("logout!");
		boolean isInterMem = isInterMem(request, response);

		String Path ="";
		if(isInterMem){
			//�ٷ� ���� �����ϰ� HOME���� ���ϴ�.
			System.out.println("����ȸ�� �α׾ƿ� ó��");
			Path = "view/" + "logout.interMem";
		}else{
			System.out.println("�ܺ�ȸ�� �α׾ƿ� ó��");
			Path = "view/" + "logout.kakaoOauth";
		}
		
		response.sendRedirect(Path);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected boolean isInterMem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean res = false;
		HttpSession session = request.getSession();
		
		//���ǿ� kakaoId�� �ִٸ� �ܺ�ȸ���̴ٶ�� �Ǵ�.
		Object kakaoId = null;
		if((kakaoId = session.getAttribute("kakaoId")) == null) {
			res = true;
		}	
		return res;
	}
}
