package service;

import java.util.ArrayList;

import domain.Board; 
import static util.Util.*;

public class BoardServiceImpl implements BoardService{
	private int page = 1;
	private int maxPage;
	private int currentNo = 0;
	
	UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
	
	private static BoardServiceImpl boardServiceImpl = new BoardServiceImpl();
	
	public static BoardServiceImpl getInstance() {
		return boardServiceImpl;
	}
	
	private BoardServiceImpl() { }
	
	public int getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}

	public void setPage(int page) {
		this.page = page;
	}


	private ArrayList<Board> boards = new ArrayList<Board>();
	public ArrayList<Board> getBoard() {
		return boards;
	}
	
	public void nextPage() {
		if(page < maxPage) {
			page += 1;
		}
		else {
			System.out.println("마지막 페이지 입니다.");
		}
	}
	
	public void agoPage() {
		if(page == 1) {
			System.out.println("첫 페이지 입니다.");			
		}
		else {
			page -= 1;
		}
	}
	
	public void write() { // 게시글 작성
		Board b = new Board();
		System.out.println("  ┌-------------------------┐");
		System.out.println("  |  제목을 입력해 주세요.  |");
		System.out.println("  └-------------------------┘");
		String title = nextLine(">");
		System.out.println("  ┌-------------------------┐");
		System.out.println("  |  내용을 입력해 주세요.  |");
		System.out.println("  └-------------------------┘");
		String content = nextLine(">");
		
		b.setNum(boards.get(boards.size() - 1).getNum() + 1);
		b.setContent(content);
		b.setTitle(title);
		b.setWriter(UserServiceImpl.getCurrentUser().getId());
		b.setNow(currentTime());
		boards.add(b);
	}
	
	public void move() {
		for(boolean b = true; b ; ) {
			System.out.println();
			System.out.println("  ┌----------------------------------------┐");
			System.out.println("  | (1) < 이전 | (2) 다음 > | (3) 뒤로가기 |");
			System.out.println("  └----------------------------------------┘");
			int choice = nextInt(" 선택해 주세요. >", 1, 3);
			if(choice == 0) {continue;}
			switch (choice) {
			case 1:
				preWriting();
				break;
			case 2:
				nextWriting();
				break;
			case 3:
				board();
				b = false;
				break;
				
			default:
				break;
			}
			
		}
	}
	
	public void preWriting() {
	      if(getCurrentNo() == 1) {
	         System.out.println("첫 게시글 입니다.");
	         return;
	      }
	      for (int i = 0; i < boards.size(); i++) {
	         if(boards.get(i).getNum() == getCurrentNo()) {
	            read(boards.get(i-1).getNum());
	            break;
	         }
	      }
	   }
	
	public void nextWriting() {
		if(getCurrentNo() == boards.get(boards.size()-1).getNum()) {
			System.out.println("마지막 게시글 입니다.");
			return;
		}
		for (int i = 0; i < boards.size(); i++) {
			if(boards.get(i).getNum() == getCurrentNo()) {
				read(boards.get(i+1).getNum());
				break;
			}
		}
	}
	
	public void read(int j) { // 게시글 읽기
		for (Board b : boards) {
			if(b.getNum() == j) {
				System.out.println("=========================================================================");
				System.out.printf("글번호 : %d%n", b.getNum());
				System.out.printf("제목 : %s%n", b.getTitle());
				System.out.printf("                                                       작성자 : %s님%n", b.getWriter());
				System.out.println("=========================================================================");
				System.out.println();
				
				String rest = b.getContent();
				if(rest.length() < 20) {System.out.println(rest);}
				while(rest.length() >= 20) {
					System.out.println(rest.substring(0, 20));
					if(rest.length() != 0) {
						rest = rest.substring(20);
					}
				}
				System.out.println();
				System.out.printf("작성 시간 : %s%n", b.getNow());
				System.out.println();
			}
		}
		setCurrentNo(j);
	}
	
	public void title() { // 제목 키워드 검색 메서드
		boolean c = true;
		System.out.println("  ┌-------------------------------┐");
		System.out.println("  |  제목 키워드를 입력해 주세요  |");
		System.out.println("  └-------------------------------┘");
		String search = nextLine(">");
		ui1();
		for (Board b : boards) {
			if(b.getTitle().contains(search)) {
				System.out.printf("  %5d%21s%24s%28s          %n", b.getNum(), titleForm(b.getTitle()), b.getWriter(), b.getNow());
				c = false;
			}
		}
		if(c) {
			System.out.println("검색결과가 없습니다.");
		}
		ui2();
	}
	
	
	public void content() { // 임시 - 내용검색 (content이용)
		boolean c = true;
		System.out.println("  ┌---------------------------------------┐");
		System.out.println("  | 검색할 내용의 키워드를 입력해 주세요  |");
		System.out.println("  └---------------------------------------┘");
		String search = nextLine(">");
		ui1();
		for (Board b : boards) {
			if(b.getContent().contains(search)) {
				System.out.printf("  %5d%21s%24s%28s          %n", b.getNum(), titleForm(b.getTitle()), b.getWriter(), b.getNow());
				c = false;
			}
		}
		if(c) {
			System.out.println("검색결과가 없습니다.");
		}
		ui2();
	}
	
	public void writer() { // 임시 - 작성자 검색 (writer이용)
		System.out.println("  ┌-------------------------┐");
		System.out.println("  | 작성자를 입력해 주세요  |");
		System.out.println("  └-------------------------┘");
		String search = nextLine(">", 2);
		boolean c = true;
		ui1();
		for (Board b : boards) {
			if(b.getWriter().contains(search)) {
				System.out.printf("  %5d%21s%24s%28s          %n", b.getNum(), titleForm(b.getTitle()), b.getWriter(), b.getNow());
				c = false;
			}
		}
		if(c) {
			System.out.println("검색결과가 없습니다.");
		}
		ui2();
	}
	
	public void delete() { // 임시 - 게시글 삭제 - 글 번호를 입력받아서 해당 게시글 삭제.
		int num = nextInt("삭제할 글 번호를 입력해주세요 >", 1, boards.size());
		if(num == 0) {return;}

	    Board board =findBy(num);
	    	if (board == null) {
	    		System.out.println("존재하지 않는 글입니다.");
	    	}
	    	if(UserServiceImpl.getCurrentUser().getId().equals(board.getWriter())) {
	    		boards.remove(board);
	    		System.out.println("해당 게시글이 삭제되었습니다.");
	    	}
	    	else {
	    		System.out.println("삭제할 권한이 없습니다.");
	    		System.out.println("본인이 작성한 게시글만 삭제할수 있습니다.");
	    	}
	}
	
	
	
	public void board() { // 게시판 출력
		
		maxPage = (int)(boards.size() / 10f - 0.09) + 1; // 올림연산
		// 게시글이 31개인 경우 3.1 - 0.1이 2.9999999046325683가 나와버리기 때문에 0.09로 조정
//		maxPage = (int)(boards.size()%10 + 0.9) + boards.size()/10;
		
		
		ui1();
		
		int cnt = 1;
		for (int i = boards.size() - 1 - (page - 1) * 10 ; i > -1 && cnt <= 10 ; i--) {
			System.out.printf("  %5d      %12s         %12s          %12s          %n", 
					boards.get(i).getNum(), titleForm(boards.get(i).getTitle()),
					boards.get(i).getWriter(), boards.get(i).getNow());
			cnt++;
		}
		ui2();
		
		if(boards.isEmpty()) {
			System.out.println("게시글이 없습니다.");
		}
		
	}
	private Board findBy(int boardNum) {
	      Board board = null;
	      for (Board b : boards) {
	         if (b.getNum()== boardNum) {
	            board = b;
	         }
	      }
	      return board;
	}
	
	public void search() {
		for (boolean d = true; d;) {
			System.out.println("  ┌----------------------------┐");
			System.out.println("  | 1.제목 | 2.내용 | 3.작성자 |");
			System.out.println("  └----------------------------┘");
			int search = nextInt(">", 1, 3);
			if(search==0) {continue;}
			// 1. 제목 키워드 2. 내용 키워드 3. 작성자 검색
			switch (search) {
			case 1:
				title();
				d = false;
				break;
			case 2:
				content();
				d = false;
				break;
			case 3:
				writer();
				d = false;
				break;
			default:
				break;
			}
		}
	}
	
	public void ui1() {
		System.out.println();
		System.out.println();
		
		System.out.println();
		System.out.println("┌-------------------------------------------------------------------------------┐");
		System.out.println("|     (1) 글쓰기     |     (2) 제목 검색      |     (3) 게시글 삭제      |-|ㅁ|X|");
		System.out.println("├===============================================================================┤");
		System.out.println("|                            게         시        판                            |");
		System.out.println("├===============================================================================┤");
		System.out.println("|   글번호   |         제목          |       작성자       |       작성시간      |");
		System.out.println("=================================================================================");
	}
	
	public void ui2() {
		System.out.println("=================================================================================");
		System.out.println("|                        ** 만족은 자유로운 소통으로 부터 **                    |");		
		System.out.println("|                                                                               |");		
		System.out.println("|                       여기는 휴먼 대학 병원 게시판 입니다.                    |");		
		System.out.println("|                           ┌----┐ ┌----┐ ┌----┐ ┌----┐                         |");
		System.out.println("|                           |^ ^ | |* * | | ~ ~| | @ @|                         |");
		System.out.println("|                           └-0--┘ └-ㅁ-┘ └-ㅇ-┘ └-ㅁ-┘                         |");
		System.out.println("|                                                                               |");		
		System.out.println("|                            문의사항은 02) 527 - 1234                          |");		
		System.out.println("├-------------------------------------------------------------------------------┤");
		System.out.println("|   (4)읽기                    < 이전  |  다음 >           (7)메뉴로 돌아가기   |");
		System.out.println("└-------------------------------------------------------------------------------┘");
		System.out.println("               - 이전페이지는 5번 다음페이지는 6번을 눌러주세요 -");	
		
		
		
		
		System.out.println(); //한 줄 띄기
		System.out.println("                                - page: " + page + " -");
		
	}
	
	public String titleForm(String str) {
		if(str.length() >= 8) {
			return str.substring(0, 7) + "...";
		}
		else {
			return str;
		}
	}
	
	public void boardSystem() {
		for (boolean b = true; b ; ) {
			board();
			int select = nextInt("입력해주세요 >", 1, 7);
			if(select == 0) {continue;}
			//1. 글쓰기 2. 제목 검색 3. 게시글삭제 4. 글읽기 5. 이전페이지 6. 다음페이지 7. BACK
			
			switch (select) {
			case 1:
				write();
				board();
				break;
			case 2:
				search();
				break;
			case 3:
				delete();
				break;
			case 4:
				int no = nextInt("글 번호를 입력해주세요.", 1, boards.size());
				if(no==0) {break;}
				read(no);
				move();
				break;
			case 5:
				agoPage();
				break;
			case 6:
				nextPage();
				break;
			case 7:	
				setPage(1);
				b = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}
	
}