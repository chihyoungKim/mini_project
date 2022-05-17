package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import service.ReservationServiceImpl;



public class Util {
	
	public static Calendar calendar = Calendar.getInstance();
	
	public static final SimpleDateFormat FMT = new SimpleDateFormat("yyyyMMddHH");
	public static final SimpleDateFormat FMT_YM = new SimpleDateFormat("yyyyMM");
	public static final SimpleDateFormat FMT_YMD = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat FMT_MD = new SimpleDateFormat("MM월 dd일");
	public static final SimpleDateFormat reserv = new SimpleDateFormat("yyyy년 MM월 dd일 HH시");
	
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static Date parse(String str, SimpleDateFormat fmt) {
		Date date = null;
		try {
			date = fmt.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return date;
	}
	
	
	public static void cal(String year2, String month2) { // 해당 월 달력을 보여준다.
		
		int year = Integer.parseInt(year2);
		int month = Integer.parseInt(month2);
		
		String[] days = {"일", "월", "화", "수", "목", "금", "토"};
		Calendar cal = calendar;
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(new SimpleDateFormat("                    yyyy년 MM월 ").format(cal.getTime()));
		System.out.println();
		
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		int lastDate = cal.get(Calendar.DATE);
		
		
		for (int i = 0; i < days.length; i++) {
			System.out.printf("%6s", days[i]);
			
		}
		System.out.println();
		System.out.println("==================================================");
		
		for (int i = 1; i <= lastDate + startDay - 1; i++) {
			int dpDate = i - (startDay-1);
			if(dpDate >= 1) {
				System.out.printf("%7d", i - (startDay-1));
			}
			else {
				System.out.printf("%7c", ' ');
			}
			if(i % 7 == 0) {
				System.out.println();
				System.out.println();
			}
		}		
		System.out.println();
		System.out.printf("%38s", "토,일요일은 진료하지 않습니다.");
		System.out.println();
		System.out.println();
		
	}
	
	
	public static String nextLine(String input) {
		return nextLine(input, 0);
	}
	public static String nextLine(String input, int select) {
		// select
		// 0 : 입력받은값 그대로
		// 1 : 한글
		// 2 : 두글자이상의 영어 숫자
		// 3 : 4자리 숫자 year
		// 4 : 2자리 숫자 month
		// 5 : 2자리 숫자 date
		// 9 : 2자리 숫자 doctor rest date
		// 6 : 병원진료시간
		// 7 : 핸드폰번호
		// 8 : 예외처리 후 출력을 위한 용도
		
		String str = null;
		boolean b = true;
		while(b) {
			switch (select) {
			case 0:
				System.out.print(input);
				str = scanner.nextLine();
				b=false;
				break;
			case 1:
				System.out.print(input);
				String str1 = scanner.nextLine();
				int cnt = 0;
				for(int i = 0; i <str1.length(); i++) {
					if(!(str1.charAt(i) >= '가' && str1.charAt(i) <= '힣')) {
						cnt++;
					}
				}
				if(cnt!=0) {
					System.out.println("한글로 입력해주세요.");
					System.out.println();
					break;
				}
				select = 8;
				str = str1;
				break;
			case 2:
				System.out.print(input);
				String str2 = scanner.nextLine();
				int cnt2 = 0;
				if(str2.length() > 1) {
					for(int i = 0; i <str2.length(); i++) {
						if((str2.charAt(i) < 'a' || str2.charAt(i) > 'z') && (str2.charAt(i) < 'A' 
								|| str2.charAt(i) > 'Z') && (str2.charAt(i) < '0' || str2.charAt(i) > '9')) {
							cnt2++;
						}
					}
				}
				else {
					System.out.println("ID 혹은 password는 두글자 이상의 영어와 숫자로만 가능합니다.");
					System.out.println();
					break;
				}
				if(cnt2!=0) {
					System.out.println("ID 혹은 password는 두글자 이상의 영어와 숫자로만 가능합니다.");
					System.out.println();
					break;
				}
				select = 8;
				str = str2;
				break;
			case 3:
				System.out.print(input);
				String str3 = scanner.nextLine();
				int cnt3 = 0;
				if(str3.length() == 4 ) {
					for (int i = 0; i < str3.length(); i++) {
						if(str3.charAt(i) < '0' || str3.charAt(i) > '9') {
							cnt3++;
						}
					}
				}
				else {
					System.out.println("연도는 4자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				if(cnt3!=0) {
					System.out.println("연도는 4자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				calendar  = Calendar.getInstance();
				String y = FMT.format(calendar.getTime());
				int year = Integer.parseInt(str3);
				int currentYear = Integer.parseInt(y.substring(0, 4));
				if(!(year >= currentYear && year <= currentYear + 1)) {
					System.out.println("올해랑 내년만 예약할수있습니다.");
					break;
				}
				select = 8;
				str = str3;
				break;
			case 4:
				System.out.print(input);
				String str4 = scanner.nextLine();
				int cnt4 = 0;
				try {
					if(!(Integer.parseInt(str4) > 0 && Integer.parseInt(str4) < 13)) {
						System.out.println("존재하지 않는 월 입니다.");
						System.out.println("다시 입력해 주세요.");
						break;
					}
				} catch (Exception e) {
					System.out.println("다시 입력해 주세요.");
				}
				if(str4.length() == 2 ) {
					for (int i = 0; i < str4.length(); i++) {
						if(str4.charAt(i) < '0' || str4.charAt(i) > '9') {
							cnt4++;
						}
					}
				}
				else {
					System.out.println("월은 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				if(cnt4!=0) {
					System.out.println("월은 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				
				String mon = FMT.format(calendar.getTime());
				int month = Integer.parseInt(ReservationServiceImpl.getExceptYear() + str4);
				int currentYearMonth = Integer.parseInt(mon.substring(0, 6));
				
				
				
				if(month < currentYearMonth) {
					System.out.println("지금은 " + mon.substring(4, 6) + "월 입니다.");
					System.out.println("다시 확인해주세요.");
					break;
				}
				
				select = 8;
				str = str4;
				break;
			case 5:
				System.out.print(input);
				String str5 = scanner.nextLine();
				int cnt5 = 0;
				if(str5.length() == 2 ) {
					for (int i = 0; i < str5.length(); i++) {
						if(str5.charAt(i) < '0' || str5.charAt(i) > '9') {
							cnt5++;
						}
					}
				}
				else {
					System.out.println("날짜는 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				if(cnt5!=0) {
					System.out.println("날짜는 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				
				Calendar cal2 = Calendar.getInstance();
				int currentDay = Integer.parseInt(FMT_YMD.format(cal2.getTime()).substring(6));
				
				cal2.set(cal2.YEAR, Integer.parseInt(ReservationServiceImpl.getExceptYear()));
				cal2.set(cal2.MONTH, Integer.parseInt(ReservationServiceImpl.getExceptMonth()) - 1);
				cal2.set(cal2.DATE, 1);
				
				cal2.add(cal2.MONTH, 1);
				cal2.add(cal2.DATE, -1);
				
				int lastDate = cal2.get(calendar.DATE);
				int date = Integer.parseInt(str5);
				if(date > lastDate) {
					System.out.println("이번 달의 마지막 날은 " + FMT_YMD.format(cal2.getTime()).substring(6) + "입니다.");
					System.out.println("다시 입력해주세요.");
					break;
				}
//				현재 월일 경우 추가해서 &&조건
				else if(ReservationServiceImpl.getExceptMonth() == (FMT_YM.format(Calendar.getInstance().getTime())).substring(4)
						&& date < currentDay) {
					System.out.println("오늘은 " + date + "일 입니다.");
					break;
				}
				select = 8;
				str = str5;
				break;
			case 6:
				System.out.print(input);
				String str6 = scanner.nextLine();
				int cnt6 = 0;
				if(str6.length() == 2 ) {
					for (int i = 0; i < str6.length(); i++) {
						if(str6.charAt(i) < '0' || str6.charAt(i) > '9') {
							cnt6++;
						}
					}
				}
				else {
					System.out.println("시간은 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				
				int time = Integer.parseInt(str6);
				if(time < 9 || time > 16) {
					System.out.println("  ┌-----------------------------------------┐");
					System.out.println("  | < 병원 진료시간은 09:00~17:00 입니다 >  |");
					System.out.println("  |=========================================|");
					System.out.println("  |        문의사항은 02) 527 - 1234        |");
					System.out.println("  └-----------------------------------------┘");
					break;
				}
				else if(time == 12) {
					System.out.println("  ┌-----------------------------------------┐");
					System.out.println("  |    < 12:00~13:00은 점심시간입니다. >    |");
					System.out.println("  |=========================================|");
					System.out.println("  |        문의사항은 02) 527 - 1234        |");
					System.out.println("  └-----------------------------------------┘");
					break;
				}
				if(cnt6!=0) {
					System.out.println("시간은 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				select = 8;
				str = str6;
				break;
			case 7:
				System.out.print(input);
				String str7 = scanner.nextLine();
				int cnt7 = 0;
				if(str7.length() == 11 ) {
					for (int i = 0; i < str7.length(); i++) {
						if(str7.charAt(i) < '0' || str7.charAt(i) > '9') {
							cnt7++;
						}
					}
				}
				else {
					System.out.println("핸드폰번호는 11자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				if(cnt7!=0) {
					System.out.println("숫자로만 입력해주세요.");
					System.out.println();
					break;
				}
				select = 8;
				str = str7;
				break;
			case 8:
				b = false;
				break;
			case 9:
				System.out.print(input);
				String str9 = scanner.nextLine();
				int cnt9 = 0;
				if(str9.length() == 2 ) {
					for (int i = 0; i < str9.length(); i++) {
						if(str9.charAt(i) < '0' || str9.charAt(i) > '9') {
							cnt9++;
						}
					}
				}
				else {
					System.out.println("날짜는 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				if(cnt9!=0) {
					System.out.println("날짜는 2자리의 숫자로 입력해야합니다.");
					System.out.println();
					break;
				}
				
				Calendar cal3 = Calendar.getInstance();
				int currentDay2 = Integer.parseInt(FMT_YMD.format(cal3.getTime()).substring(6));
				
				
				ReservationServiceImpl.setExceptYear(FMT_YMD.format(cal3.getTime()).substring(0, 4));
				ReservationServiceImpl.setExceptMonth(FMT_YMD.format(cal3.getTime()).substring(4, 6));
				cal3.set(cal3.YEAR, Integer.parseInt(ReservationServiceImpl.getExceptYear()));
				cal3.set(cal3.MONTH, Integer.parseInt(ReservationServiceImpl.getExceptMonth()) - 1);
				cal3.set(cal3.DATE, 1);
				
				cal3.add(cal3.MONTH, 1);
				cal3.add(cal3.DATE, -1);
				
				int lastDate2 = cal3.get(calendar.DATE);
				int date2 = Integer.parseInt(str9);
				if(date2 > lastDate2) {
					System.out.println("이번 달의 마지막 날은 " + FMT_YMD.format(cal3.getTime()).substring(6) + "입니다.");
					System.out.println("다시 입력해주세요.");
					break;
				}
				else if(date2 < currentDay2) {
					System.out.println("다시 입력해주세요.");
					break;
				}
				ReservationServiceImpl.setExceptYear(FMT_YMD.format(calendar.getTime()).substring(0, 4));
				ReservationServiceImpl.setExceptMonth(FMT_YMD.format(calendar.getTime()).substring(4, 6));
				select = 8;
				str = str9;
				break;
			}
			
		}
		return str;
	}
	
	public static int nextInt(String input) {
		return nextInt(input, 0, 1000);
	}
	
	public static int nextInt(String input, int start, int end) {
		int result = 0;
		try {
			result = Integer.parseInt(nextLine(input));
		} catch (NumberFormatException e) {
			return 0;
		}
		if(result < start || result > end) {
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("다시 입력해주세요.");
			System.out.println();
			return 0;
		}
		return result;
	}
	
	public static String currentTime() {
		long now = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return format.format(now);
	}
	
	public static int korCnt(String str) {
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)>='가' && str.charAt(i)<='힣') {
				cnt++;
			}
		}
		return cnt;
	}
	
	public static String convert(String str, int size) {
		String formatter = String.format(str, size - korCnt(str));
		try {
			if(size < korCnt(str) || str.length() > size) {
				throw new Exception();
			}
		} catch (Exception e) {
			int cnt = 0;
			for (int i = 0; i < size-cnt; i++) {
				if(str.charAt(i)>='가' && str.charAt(i)<='힣') {
					cnt++;
				}
			}
			str = str.substring(0, size-cnt);
			
			formatter = String.format(str + " ...", size);
			
		}
		
		return String.format(formatter, str);
	}
	
}