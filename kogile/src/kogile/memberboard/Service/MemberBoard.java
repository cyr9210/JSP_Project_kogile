package kogile.memberboard.Service;

public class MemberBoard {
	private String name;
	private String mail;
	
	public MemberBoard(){}
	
	public MemberBoard(String name, String mail) {
		super();
		this.name = name;
		this.mail = mail;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
