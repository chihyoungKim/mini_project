package service;

import static util.Util.cal;
import static util.Util.nextInt;
import static util.Util.nextLine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import domain.Reservation;
import domain.User;
import util.Util;

public class ReservationServiceImpl implements ReservationService {
	
	private static String exceptYear;
	private static String exceptMonth;
	
	UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

	private static ReservationServiceImpl reservationService = new ReservationServiceImpl();

	private ReservationServiceImpl() {
	}

	public static ReservationServiceImpl getInstance() {
		return reservationService;
	}

	private ArrayList<Reservation> reservations = new ArrayList<Reservation>(); // 계정과 진료예약 같이 관리

	public ArrayList<Reservation> getReservation() {
		return reservations;
	}
	
	public static String getExceptYear() {
		return exceptYear;
	}
	public static String getExceptMonth() {
		return exceptMonth;
	}

	public static void setExceptYear(String exceptYear) {
		ReservationServiceImpl.exceptYear = exceptYear;
	}

	public static void setExceptMonth(String exceptMonth) {
		ReservationServiceImpl.exceptMonth = exceptMonth;
	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public static ReservationServiceImpl getReservationService() {
		return reservationService;
	}

	public void reservation() {
		System.out.println();
		System.out.println("진료 받으실 연도를 입력해주세요.");
		System.out.println("ex) 2022년일 경우 -> 2022");
		String year = nextLine(">", 3);
		
		exceptYear = year;
		
		System.out.println("진료 받으실 월을 입력해주세요.");
		System.out.println("ex) 2월일 경우 -> 02");
		String month = nextLine(">", 4);
		
		exceptMonth = month;

		cal(year, month);

		System.out.println("진료 받으실 날짜를 입력해주세요.");
		System.out.println("ex) 7일 일 경우 -> 07");
		String day = nextLine(">", 5); // 날짜일 경우 해당 월의 날짜만큼만 나와야하는데?;;
		System.out.println();

		reservDay(year, month, day);

		reservUi();
		System.out.println("ex) 9시 예약할 경우 -> 09");
		String hour = null;
		hour = nextLine(">", 6);

		String time = year + month + day + hour;
		String time2 = year + month + day;
		// 예약한 내용에 대해서 진료가 이뤄졌는지? - 의사가 로그인해서 처리해야하나? - 맨마지막에

		for (int i = 1; i < userServiceImpl.getUsers().size(); i++) {
			if (userServiceImpl.getUsers().get(i).isDoctor()) {
				System.out.println(i + "번 의사 : " + userServiceImpl.getUsers().get(i).getName());
			}
		}

		System.out.println();
		System.out.println("ex) 2번 의사에게 진료받고싶은 경우 -> 2");
		int cnt = 0;
		for (User u : userServiceImpl.getUsers()) {
			if(u.isDoctor()) {
				cnt++;
			}
		}
		boolean c = true;
		int docIndex = 0;
		while(c) {
			docIndex = nextInt(">", 1, cnt);
			if(docIndex!=0) {c = false;}
		}

		String docId = userServiceImpl.getUsers().get(docIndex).getId();
		String userId = UserServiceImpl.getCurrentUser().getId();

		for (Reservation r : reservations) {
			if (r.getTime().equals(time) && r.getUserId().equals(userId)) {
				System.out.println("  ┌-------------------------------------------┐");
				System.out.println("  |  이미 예약되어 있어 예약이 불가능합니다.  |");
				System.out.println("  └-------------------------------------------┘");
				System.out.println("");

			}
			else if(r.getTime().equals(time2) && r.isRest()) {System.out.println("이 날은 휴진입니다.");}
			else {
				reservations.add(new Reservation(docId, userId, time));

				System.out.println("  ┌--------------------------┐");
				System.out.println("  |  예약이 완료되었습니다.  |");
				System.out.println("  └--------------------------┘");
				System.out.println();
				break;
			}
		}
	}

	// 변경 및 취소 메서드
	public void change() {
		int cnt = 0;
		for (Reservation r : reservations) {
			if(r.getUserId().equals(UserServiceImpl.getCurrentUser().getId())) {
				cnt++;
			}
		}
		if(cnt == 0) {
			System.out.println("예약된 진료가 없습니다.");
			return;
		}
		
		boolean b = true;
		while (b) {
			mysch();
			System.out.println("변경 및 취소할 예약을 입력하세요.");
			System.out.println("ex) 2020년 -> 2020");
			String year = nextLine(">", 3);
			System.out.println("ex) 2월 -> 02");
			String month = nextLine(">", 4);
			System.out.println("ex) 7일 -> 07");
			String day = nextLine(">", 5);
			System.out.println("ex) 오전 9시 -> 09");
			String time = nextLine(">", 6);
			Date time2 = Util.parse(year + month + day + time, Util.FMT);
			String t = Util.FMT.format(time2);

			for (Reservation r : reservations) {
				if (UserServiceImpl.getCurrentUser().getId().equals(r.getUserId())
						&& r.getTime().equals(t)) {
					System.out.println("변경(1), 취소(2)를 선택해주세요.");
					int choice = nextInt(">");
					switch (choice) {
					case 1:
						System.out.println("변경할 날짜 및 시간을 입력하세요.");
						System.out.println("ex) 2020년 -> 2020");
						String yearChange = nextLine(">", 3);
						System.out.println("ex) 2월 -> 02");
						String monthChange = nextLine(">", 4);
						System.out.println("ex) 7일 -> 07");
						String dayChange = nextLine(">", 5);
						System.out.println("ex) 오전 9시 -> 09");
						String timeChange = nextLine(">", 6);
						String t2 = yearChange + monthChange + dayChange + timeChange;
						r.setTime(t2);
						System.out.println("변경이 완료되었습니다.");
						b = false;
						break;
					case 2:
						reservations.remove(r);
						System.out.println("예약이 취소되었습니다.");
						b = false;
						break;
					default:
						break;
					}
					break;
				}
			}
		}
	}

	// 해당 날짜 예약 현황 출력표
	public void reservDay(String year, String month, String day) {

		int intYear = Integer.parseInt(year);
		int intMonth = Integer.parseInt(month);
		int intDay = Integer.parseInt(day);

		String time = null;
		for (Reservation r : reservations) {
			time = r.getTime();
			time = Util.FMT_YMD.format(Util.parse(time, Util.FMT));
			Date date = new Date(intYear, intMonth, intDay);
			String time2 = Util.FMT_YMD.format(date);
			// 정석 10장 메서드 참고 parse(checked exception) // util에서 처리했다.
			// format
			if (time.equals(time2)) {
				System.out.println(r);
			}
		}
	}

	// 본인 예약 확인 메서드
	public void mysch() {
		System.out.println();
		for (Reservation r : reservations) {
			if (UserServiceImpl.getCurrentUser().isDoctor()) { // doctor 관점
				if (r.getDoctorId().equals(UserServiceImpl.getCurrentUser().getId()) && !(r.isRest())) {
					String time = r.getTime();
					String reserv = Util.reserv.format(Util.parse(time, Util.FMT));
					System.out.print(reserv);
					String patName = null;
					for (int j = 0; j < userServiceImpl.getUsers().size(); j++) {
						if (userServiceImpl.getUsers().get(j).getId().equals(r.getUserId())) {
							patName = userServiceImpl.getUsers().get(j).getName();
						}
					}

					System.out.println("  ");
					System.out.printf("%s환자 진료%n", patName);
				}
			}

			else { // patient 관점
				if (r.getUserId().equals(UserServiceImpl.getCurrentUser().getId())) {
					String time = r.getTime();
					String reserv = Util.reserv.format(Util.parse(time, Util.FMT));
					System.out.print(reserv);
					String docName = null;
					for (int j = 0; j < userServiceImpl.getUsers().size(); j++) {
						if (userServiceImpl.getUsers().get(j).getId().equals(r.getDoctorId())) {
							docName = userServiceImpl.getUsers().get(j).getName();
						}
					}
					System.out.println("  ");
					System.out.printf("에 %s의사 진료예약이 있습니다.%n", docName);
					System.out.println();

				}
			}
		}
	}

	public void reservUi() {
	      System.out.println("              * TIME *");
	      System.out.println();
	      System.out.println("     1시간 간격으로 진행됩니다."); 
	      System.out.println("    ┌-----┐┌-----┐┌-----┐┌-----┐");
	      System.out.println("    |09:00||10:00||11:00||Lunch|");
	      System.out.println("    ├-----┘├-----┘├-----┘├-----┘");
	      System.out.println("    |13:00||14:00||15:00||15:00|");
	      System.out.println("    ├-----┘└-----┘└-----┘└-----┘");
	      System.out.println("    |16:00|");
	      System.out.println("    └-----┘");
	      System.out.println("     - 같은 날 예약 중복 불가 -");
	      System.out.println();
	      System.out.println("             * DOCTOR *");
	      System.out.println("              ┌-----┐ ");
		  System.out.println("              | ･ω ･ | ");
		  System.out.println("              └-----┘ ");
	      System.out.println("-> 진료 받을 '시간'과 '의사'를 입력해주세요");
	      System.out.println();
	   }

	// 정보 수정 메서드 myPage()
	public void myPage() {
		System.out.println();
		System.out.println("                         << 마이페이지 >> ");
		System.out.println("       =====================================================");
		System.out.println("  ┌---VIP-------------------------------------------------------┐");
		System.out.println();
		System.out.println("     * " + UserServiceImpl.getCurrentUser().getName() + "님 환영합니다. *");
		System.out.println();
		System.out.println("     < 내 정보 >");
		System.out.println();
		if(UserServiceImpl.getCurrentUser().isDoctor()) {
			System.out.println("내 휴무일 : ");
			System.out.println();
			for(Reservation r : reservations) {
				if(r.isRest() && r.getDoctorId().equals(UserServiceImpl.getCurrentUser().getId())) {
					System.out.println(Util.FMT_MD.format(Util.parse(r.getTime(), Util.FMT)));
				}
			}
		}
		System.out.println();
		System.out.println("  내 예약 : ");
		mysch();
		System.out.println();
		System.out.println("     ID : " + UserServiceImpl.getCurrentUser().getId());
		System.out.println("     PW : " + UserServiceImpl.getCurrentUser().getPassword());
		System.out.println("     핸드폰 번호 : " + UserServiceImpl.getCurrentUser().getNum());
		System.out.println();
		System.out.println("                                                   (탈퇴하기)");
		System.out.println("  └-------------------------------------------------------------┘");
		System.out.println();
		System.out.println("  ┌--------------------------------------------┐");
		System.out.println("  | (1) 탈퇴 | (2) 회원정보 변경 | (3) 뒤로가기|");
		System.out.println("  └--------------------------------------------┘");
		int choice = nextInt(">", 1, 3); // 메뉴로 다시 돌아가는거 추가?
		if(choice == 1) {
			reservExit();
			userServiceImpl.exit();
		} else if(choice == 2) {
			userServiceImpl.modify();
		}
	}
	
	public void reservExit() { // 계정 삭제시 해당계정의 예약 삭제 메서드
		for (int i = 0; i < reservations.size(); i++) {
			if(reservations.get(i).getUserId().equals(UserServiceImpl.getCurrentUser().getId())) {
				reservations.remove(reservations.get(i));
			}
		}
		
	}
	
	public void restDoc() { // 휴가 신청 메서드
		Calendar calendar = Calendar.getInstance();
		String date = Util.FMT_YM.format(calendar.getTime());
		String year = date.substring(0, 4);
		String month = date.substring(4);
		cal(year, month);
		System.out.println("======================================================");
		System.out.println("                     휴가신청");
		System.out.println("======================================================");
		System.out.println("작성자 : " + UserServiceImpl.getCurrentUser().getName());
		System.out.println();
		String restReason = nextLine("사유 : ");
		System.out.println("날짜 : ");
		System.out.println("ex) 9일 -> 09");
		String day = nextLine(">", 9);
		String time = date + day + "00";
		rest(time, restReason);
	}
	
	public void rest(String time, String restReason) { // 휴가등록 메서드
		for (int i = 0; i < reservations.size() ; i++) {
			if(reservations.get(i).getDoctorId().equals(UserServiceImpl.getCurrentUser().getId())
					&& (reservations.get(i).getTime().substring(0, 8)).equals(time)) {
				System.out.println("이미 예약된 진료가 있어 휴가신청이 거부되었습니다.");
				return;
			}
			else if(reservations.get(i).getTime().equals(time)) {
				System.out.println("이미 등록된 휴무일입니다.");
				return;
			}
		}
		reservations.add(new Reservation(UserServiceImpl.getCurrentUser().getId(), time, restReason, true));
		System.out.println("휴가가 신청완료 되었습니다.");
	}
}