package app;


import static util.Util.nextInt;

import service.BoardServiceImpl;
import service.ReservationServiceImpl;
import service.Service;
import service.UserServiceImpl;
import util.Util;

public class ReservationEx {
	
	
	public static void main(String[] args) {
		Service service = new Service();
		UserServiceImpl UserService = UserServiceImpl.getInstance();
		ReservationServiceImpl ReservationService = ReservationServiceImpl.getInstance();
		BoardServiceImpl BoardService = BoardServiceImpl.getInstance();
		
		service.test(); // 테스트삼아 계정 미리생성
		service.testBoard(); // 테스트 게시글 25개 추가
		
		for(boolean b = true, c = true ; b ; ) {
			UserService.init();
			
			if(UserServiceImpl.getCurrentUser().getId().equals("admin")) { // 관리자메뉴
				for (boolean d = true; d ; ) {
					service.admin();
					int select = nextInt("입력해주세요 >", 1, 5);
					//1. 환자계정관리 2. 의사계정관리 3. 전체예약현황 4. 로그아웃 5. 종료
					switch (select) {
					case 1:
						service.patientsDelete();
						break;
					case 2:
						service.doctors();
						break;
					case 3:
						service.reservations();
						break;
					case 4:
						d = false;
						break;
					case 5:
						System.out.println("  ┌------------------------┐");
						System.out.println("  | 프로그램을 종료합니다. |");
						System.out.println("  └------------------------┘");
						d = false;
						b = false;
						break;
					default:
						System.out.println(" * 잘못 입력하셨습니다. *");
						break;
					}
				}
			}
			
			else if(UserServiceImpl.getCurrentUser().isDoctor()) { // 의사메뉴
				for(c = true; c; ) {
					System.out.println("  ┌----------------------------------------------------┐");
					System.out.println("  |              < 메뉴를 선택해 주세요 >              |");		
					System.out.println("  ├----------------------------------------------------┤");
					System.out.println("  |  (1) 진료 확인  |  (2) 휴무등록  |   (3) 게시판    |");		
					System.out.println("  ├----------------------------------------------------┤");
					System.out.println("  |  (4) 마이페이지 |  (5) 로그아웃  |    (6) 종료     |");
					System.out.println("  └----------------------------------------------------┘");
					
					int choice = nextInt(">", 1, 6);
					if(choice == 0) {
						continue;
					}
					
					switch (choice) {
					case 1:
						ReservationService.mysch();
						break;
					case 2:
						ReservationService.restDoc();
						break;
					case 3:
						BoardService.boardSystem();
						break;
					case 4:
						ReservationService.myPage();
						break;
					case 5:
						c = false;
						break;
					case 6:
						System.out.println("┌------------------------┐");
						System.out.println("| 프로그램을 종료합니다. |");
						System.out.println("└------------------------┘");
						c = false;
						b = false;
						break;
					default:
						break;
					}
				}
				
				
			}
			
			else { // 환자메뉴
				for(c = true ; c ; ) {
//				System.out.println("1. 예약 2. 예약확인 3. 변경및취소 4. 게시판 5. 마이페이지 6. 로그아웃 7. 프로그램 종료");
					System.out.println();
					System.out.println();
					System.out.println("  ┌----------------------------------------------------┐");
					System.out.println("  |              < 메뉴를 선택해 주세요 >              |");		
					System.out.println("  ├----------------------------------------------------┤");
					System.out.println("  |   (1) 예약  | (2) 예약 확인  | (3) 변경 및 취소    |");		
					System.out.println("  ├----------------------------------------------------┤");
					System.out.println("  | (4) 게시판 |(5) 마이페이지 |(6) 로그아웃 |(7) 종료 |");
					System.out.println("  └----------------------------------------------------┘");
					int choice = nextInt(">");
					
					switch (choice) {
					
					case 1:
						ReservationService.reservation();
						break;
					case 2:
						ReservationService.mysch();
						break;
					case 3:
						ReservationService.change();
						break;
						
					case 4:
						BoardService.board();
						BoardService.boardSystem();
						break;
					case 5:
						ReservationService.myPage();
						if(UserServiceImpl.getCurrentUser() == null) {
							c = false;
						}
						break;
					case 6:
						System.out.println("로그아웃되었습니다.");
						c = false;
						break;
						
					case 7:
						System.out.println("┌------------------------┐");
						System.out.println("| 프로그램을 종료합니다. |");
						System.out.println("└------------------------┘");
						b = false;
						c = false;
						break;
						
					}
				}
				
			}
			
		}
	}
}