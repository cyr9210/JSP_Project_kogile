package kogile.chat.DAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import kogile.chat.DTO.ChatDTO;
import net.sf.json.JSONArray;

public class ChatService {
	public static ChatService cs = new ChatService();
	public static ChatDAO dao;
	public static ChatService getInstance() {
		dao=ChatDAO.getInstance();
		return  cs;
	}

	public int insertChatService(HttpServletRequest request) {
		ChatDTO chat = new ChatDTO();
		
		chat.setChat_name(request.getParameter("chatName"));
		chat.setChat_contents(request.getParameter("chatContent"));
		
		return dao.insertChat(chat);
	}

	public void listChatService(HttpServletResponse response) throws IOException{
		
		System.out.println("Ȯ��");

		JSONArray jsonArr = new JSONArray();
		List<ChatDTO> list =dao.listChat();
		System.out.println(list);
		
		jsonArr = JSONArray.fromObject(list);
		System.out.println(jsonArr.toString());
		response.getWriter().print(jsonArr.toString());
		
		

	}
}

