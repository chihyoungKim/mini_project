
package service;

public interface UserService {
	

	//로그인 or 회원가입
	abstract public void init();
	
	//회원가입 메서드
	abstract public void join();
	
	//로그인 메서드
	abstract public void logIn();
	
	//탈퇴 메서드
	abstract public void exit();
	
	abstract public void addDoc();
	
	abstract public void modify();  
	
}