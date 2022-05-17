package service;

import static util.Util.nextInt;
import static util.Util.nextLine;

import domain.Board;
import domain.Reservation;
import domain.User;
public class Service{
	
	ReservationServiceImpl reservationService = ReservationServiceImpl.getInstance();
	UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
	BoardServiceImpl boardServiceImpl = BoardServiceImpl.getInstance();
	
	public void test() { // 테스트 계정 및 예약
		
		
		userServiceImpl.getUsers().add(new User("admin", "관리자", "1234", "01047638959"));
		userServiceImpl.getUsers().add(new User("doc1", "이국종", "1234", "01012341234", true));
		userServiceImpl.getUsers().add(new User("doc2", "화타", "1234", "01023452345", true));
		userServiceImpl.getUsers().add(new User("patient", "개아파", "1234", "01034563456"));
		userServiceImpl.getUsers().add(new User("patient2", "막아파", "1234", "01034563456"));
		
		reservationService.getReservation().add(new Reservation("doc1", "patient", "2022040111")); // yyyyMMddHH
		reservationService.getReservation().add(new Reservation("doc2", "patient2", "2022050214"));
	}
	


	public void testBoard() {
		String[] writer = {"patient", "patient2", "doc1", "doc2", "admin"}; // 작성자
		String[] title = {"병원시설이 너무 깔끔한것같아요",
				"이국종의사님 존경합니다",
				"병원시설 너무 좋은것같아요",
				"게시판 누가만들었지 너무잘만들었네",
				"친절해서 좋습니다", 
				"앞으로 이 병원만 찾을것 같아요", 
				"홍길동간호사 불친절 신고합니다.", 
				"화장실 위생상태 너무좋아요"}; // 제목
		
		for (int i = 0; i < 25; i++) {
			String content = "테스트 내용입니다.";
			Board b = new Board();
			boardServiceImpl.getBoard().add(b);
			b.setNum(boardServiceImpl.getBoard().size());
			
			b.setWriter(writer[i%5]);
			b.setTitle(title[i%8]);
			b.setContent(content);
		}
	}
	
	
	
	public void admin() {
		System.out.println("  ┌----------------------------------------------------┐");
		System.out.println("  |                  < 관리자 메뉴 >                   |");		
		System.out.println("  ├----------------------------------------------------┤");
		System.out.println("  |     (1) 환자 관리      |     (2) 의사계정관리      |");		
		System.out.println("  ├----------------------------------------------------┤");
		System.out.println("  |  (3)전체예약현황  |   (4) 로그아웃    | (5) 종료   |");
		System.out.println("  └----------------------------------------------------┘");
		
	}
	
	  public void patientsDelete() {
	      System.out.println("  ┌-------------------------------┐");
	      System.out.println("  |      < 환자 관리 메뉴 >       |");
	      System.out.println("  ├-------------------------------┤");
	      System.out.println("  | (1) 계정 관리 | (2) 계정 삭제 |");
	      System.out.println("  └-------------------------------┘");
	      int patient = nextInt("> ", 1, 2);
	      switch (patient) {
	      case 1:
	         patients();
	         break;
	      case 2:
	         blackList();
	         break;
	      }
	   }
	   
	   public void blackList() {	      
	      
	      String input = nextLine("삭제할 환자의 ID를 입력해주세요 >", 2);
	      for (Reservation r : reservationService.getReservation()) {
	         if (r.getUserId().equals(input)) {
	            reservationService.getReservation().remove(r);
	         }
	      }
	      for (User u : userServiceImpl.getUsers()) {
	         if (u.getId().equals(input)) {
	            userServiceImpl.getUsers().remove(u); 
	            System.out.println("해당 환자의 계정이 삭제되었습니다.");
	            return;
	         }
	      }
	      System.out.println("없는 환자입니다.");
	   }
	
	
	public void patients() {
		for (int i = 1; i < userServiceImpl.getUsers().size(); i++) {
			if(!(userServiceImpl.getUsers().get(i).isDoctor())) {
				System.out.println(userServiceImpl.getUsers().get(i));
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	public void doctors() {
		for (int i = 0; i < userServiceImpl.getUsers().size(); i++) {
			if(userServiceImpl.getUsers().get(i).isDoctor()) {
				System.out.println(userServiceImpl.getUsers().get(i));
			}
		}
		System.out.println("  ┌--------------------------------┐");
		System.out.println("  |  의사계정을 추가하시겠습니까?  |");		
		System.out.println("  ├--------------------------------┤");
		System.out.println("  |    (1) 네     |   (2) 아니요   |");
		System.out.println("  └--------------------------------┘");
		int add = nextInt(">", 1, 2);
		if(add == 1) {
			userServiceImpl.addDoc();
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	public void reservations() {
		for (int i = 0; i < reservationService.getReservation().size(); i++) {
			System.out.println(reservationService.getReservation().get(i));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	
}