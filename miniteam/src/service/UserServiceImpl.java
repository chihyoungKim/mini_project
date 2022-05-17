package service;

import static util.Util.nextInt;
import static util.Util.nextLine;

import java.util.ArrayList;

import domain.User;

public class UserServiceImpl implements UserService{
	
	private static UserServiceImpl userServiceImpl = new UserServiceImpl();
	private UserServiceImpl() {}
	public static UserServiceImpl getInstance() {
		return userServiceImpl;
	}
	
	private ArrayList<User> users = new ArrayList<User>(); // 사용자들 계정 관리
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	
	
	
	public void init() {
		for(boolean a = true ; a ; ) {
			System.out.println("               ┌--┐");
			System.out.println("            ┌--┘  └--┐");
			System.out.println("            └--┐  ┌--┘");
			System.out.println("               └--┘");
			System.out.println("      ┌---휴먼 대학 병원----┐");
			System.out.println("      |  ┌---┐ ┌---┐ ┌---┐  |");
			System.out.println("      |  └---┘ └---┘ └---┘  |");
			System.out.println("      |  ┌---┐ ┌---┐ ┌---┐  |");
			System.out.println("      |  └---┘ |   | └---┘  |");
			System.out.println("      └--------└---┘--------┘");
			System.out.println("               진료중 09:00 ~ 17:00");
			System.out.println();
			System.out.println();
			System.out.println("  ┌----------------------------┐");
			System.out.println("  |    메뉴를 선택해 주세요    |");		
			System.out.println("  ├----------------------------┤");
			System.out.println("  | (1) 회원가입 | (2) 로그인  |");
			System.out.println("  └----------------------------┘");
			int input = nextInt(">", 1, 2);
			if(input==0) {continue;}
			
			switch (input) {
			case 1:
				
				join();
				a = false;
				break;
				
			case 2:
				
				logIn();
				a = false;
				break;
			}
		}
	}
	
	public void join() {
		System.out.println("┌------------------------┐");
		System.out.println("| 이름를 입력해 주세요   |");
		System.out.println("└------------------------┘");
		String name = nextLine(">", 1);
		System.out.println("┌-----------------------------┐");
		System.out.println("| 핸드폰번호를 입력해 주세요  |");
		System.out.println("└-----------------------------┘");
		String num = nextLine(">", 7);
		while(true) {
			System.out.println("┌------------------------┐");
			System.out.println("|   ID를 입력해 주세요   |");
			System.out.println("└------------------------┘");
			String id = nextLine(">", 2);
			
			
			User user = findBy(id);
			if(user != null) {
				System.out.println("  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
				System.out.println("  ████▌▄▌▄▐▐▌█████");
				System.out.println("  ████▌▄▌▄▐▐▌▀████");
				System.out.println("  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");

				continue;
			}
			User user2 = new User();
			user2.setId(id);
			System.out.println("┌------------------------┐");
			System.out.println("|   PW를 입력해 주세요   |");
			System.out.println("└------------------------┘");
			String password = nextLine(">", 2);
			user2.setPassword(password);
			user2.setName(name);
			user2.setNum(num);
			users.add(user2);
			currentUser = user2;
			break;
		}
		
		
	}

	public static User currentUser = null; // 현재 로그인된 유저
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public void logIn() {
		boolean b = true ;
		while (b) {
			System.out.println("┌------------------------┐");
			System.out.println("|  ID를 입력해 주세요.   |");
			System.out.println("└------------------------┘");
			String id = nextLine(">", 2);
			System.out.println("┌------------------------┐");
			System.out.println("|  PW를 입력해 주세요.   |");
			System.out.println("└------------------------┘");
			String password = nextLine(">", 2);
			User user = findBy(id, password);
			if(user == null) {
//				System.out.println(" * 잘못 입력하셨습니다. *");
				System.out.println("  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
				System.out.println("  ████▌▄▌▄▐▐▌█████");
				System.out.println("  ████▌▄▌▄▐▐▌▀████");
				System.out.println("  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
				continue;
			}
			System.out.println();
			System.out.println(user.getName() + "님 환영합니다.");
			currentUser = user;
			b = false;
		}
	}
	
	
	public void exit() { // 탈퇴
		boolean b = true;
		while(b) {
			String id = nextLine(" id : ", 2); 
			if(currentUser.getId().equals(id)) {
				String password= nextLine(" password : ", 2);
				if(currentUser.getPassword().equals(password)) {
//					System.out.println("  ┌---------------------------┐");
//					System.out.println("  |     탈퇴하시겠습니까?     |");		
//					System.out.println("  ├---------------------------┤");
//					System.out.println("  |  (1) 예   |  (2) 아니오   |");
//					System.out.println("  └---------------------------┘");
//					int YN = nextInt(">", 1, 2);
//					if (YN == 1) {
						users.remove(findBy(id));
						System.out.println("탈퇴되었습니다. 이용해주셔서 감사합니다.");
						b=false;
						currentUser = null;
//					}
//					else {
//						
//						return;
//					}
					
				}
				else {
					System.out.println("  password가 틀렸습니다.");
					System.out.println("  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
					System.out.println("  ████▌▄▌▄▐▐▌█████");
					System.out.println("  ████▌▄▌▄▐▐▌▀████");
					System.out.println("  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
					continue;
				}
			}
			else {
				System.out.println("  ID가 틀렸습니다.");
				System.out.println("  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
				System.out.println("  ████▌▄▌▄▐▐▌█████");
				System.out.println("  ████▌▄▌▄▐▐▌▀████");
				System.out.println("  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
				continue;
			}
		}
	}
	
	public void addDoc() { // 의사 계정 추가
		System.out.println("┌------------------------┐");
		System.out.println("| 이름를 입력해 주세요   |");
		System.out.println("└------------------------┘");
		String name = nextLine(">", 1);
		System.out.println("┌-----------------------------┐");
		System.out.println("| 핸드폰번호를 입력해 주세요  |");
		System.out.println("└-----------------------------┘");
		String num = nextLine(">", 7);
		while(true) {
			System.out.println("┌------------------------┐");
			System.out.println("|  ID를 입력해 주세요.   |");
			System.out.println("└------------------------┘");
			String id = nextLine(">", 2);
			User user = findBy(id);
			if(user != null) {
				System.out.println(" * 아이디가 중복되었습니다. *");
				continue;
			}
			User user2 = new User();
			user2.setId(id);
			System.out.println("┌------------------------┐");
			System.out.println("|  PW를 입력해 주세요.   |");
			System.out.println("└------------------------┘");
			String password = nextLine(">", 2);
			user2.setPassword(password);
			user2.setName(name);
			user2.setNum(num);
			user2.setDoctor(true);
			for (int i = 1; i < users.size(); i++) {
				if(!(users.get(i).isDoctor())) {
					users.add(i, user2);
					break;
				}
			}
			currentUser = user2;
			break;
		}
	}
	
	public User findBy(String userId) {
		User user = null;
		for(User u : users) {
			if(u.getId().equals(userId)) {
				user = u;
			}
 		}
		return user;
	}
	
	public User findBy(String userId, String password) {
		User user = null;
		for(User u : users) {
			if(u.getId().equals(userId) && u.getPassword().equals(password)) {
				user = u;
			}
		}
		return user;
	}
	
	public void modify() { // 개인정보 수정 
		System.out.println("  ┌--------------------------------┐");
		System.out.println("  |  (1) 이름    | (2) 핸드폰번호  |");		
		System.out.println("  ├--------------------------------┤");
		System.out.println("  |  (3) 아이디  |  (4) 비밀번호   |");
		System.out.println("  └--------------------------------┘");
		int modify = nextInt(">", 1, 4);
		
		for (User u : users) { 
			if (UserServiceImpl.getCurrentUser().getId().equals(u.getId())) { 
				
				switch(modify) {
				case 1:
					System.out.println("수정할 '이름'을 입력하세요.");
					String nameChange = nextLine(">", 1);
					u.setName(nameChange);
					break;
				case 2:
					System.out.println("수정할 '핸드폰번호'를 입력하세요.");
					String phoneNumChange = nextLine(">", 7);
					u.setNum(phoneNumChange);
					break;
				case 3:
					System.out.println("수정할 'ID'를 입력하세요.");
					String idChange = nextLine(">", 2);
					u.setId(idChange);
					break;
				case 4:
					System.out.println("수정할 '비밀번호'를 입력하세요.");
					String passwordChange = nextLine(">", 2);
					u.setPassword(passwordChange);
				default:
					break;
				}
			}
		}
	}	
}