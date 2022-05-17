package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import service.UserServiceImpl;
import util.Util;

public class Reservation {
	
	UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
	
	private String doctorId;
	private String userId;
	private String time;
	private String restReason;
	private boolean rest = false;
	
	public Reservation () {
		
	}
	
	public Reservation(String doctorId, String time, String restReason, boolean rest) {
		setDoctorId(doctorId);
		setTime(time);
		setRestReason(restReason);
		setRest(rest);
		
	}
	
	public Reservation(String doctorId, String userId, String time) {
		setDoctorId(doctorId);
		setUserId(userId);
		this.time = time;
	}

	public boolean isRest() {
		return rest;
	}

	public void setRest(boolean rest) {
		this.rest = rest;
	}

	public String getrestReason() {
		return restReason;
	}

	public void setRestReason(String restReason) {
		this.restReason = restReason;
	}

	public String getTime() {
		return time;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		String patName = null, docName = null;
		for (int i = 0; i < userServiceImpl.getUsers().size(); i++) {
			if(userServiceImpl.getUsers().get(i).getId().equals(userId)) {
				docName = userServiceImpl.getUsers().get(i).getName();
			}
			
		}
		for (int i = 0; i < userServiceImpl.getUsers().size(); i++) {
			if(userServiceImpl.getUsers().get(i).getId().equals(doctorId)) {
				patName = userServiceImpl.getUsers().get(i).getName();
			}
			
		}
		String str = null;
		try {
			str = new SimpleDateFormat("yyyy년 MM월 dd일 HH시").format(Util.FMT.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%s에 %3s의사 %3s환자 예약", str, patName, docName);
	}
	
}