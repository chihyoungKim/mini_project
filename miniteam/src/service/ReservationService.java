package service;

public interface ReservationService {
	
	// 해당 날짜 예약 현황 출력표
	abstract public void reservDay(String year, String month, String day);
	
	// 예약 메서드
	abstract public void reservation();
	
	// 변경 및 취소 메서드
	abstract public void change();
	
	// 본인 스케줄 확인 메서드  의사id일 경우와 환자id일 경우 출력 형태가 달라야한다.
	abstract public void mysch();
	
	// 의사 휴진신청 메서드
	abstract public void restDoc();
}