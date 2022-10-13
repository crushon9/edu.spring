package edu.spring.ex02.pageutil;

// 페이지 번호들의 링크를 만들기 위한 유틸리티 클래스
public class PageMaker {
	private PageCriteria criteria; // 현재페이지와, 페이지당게시글수를 담고있는 PageCriteria클래스
	private int totalCount; // 전체 게시글 개수
	private int LinksPerPage; // 한 페이지에 표기되는 페이지링크의 개수
	private int startPageNo; // 현재 시작 페이지 링크 번호
	private int endPageNo; // 현재 끝 페이지 링크 번호
	private boolean hasPrev; // 화면에 보이는 시작 페이지 번호보다 작은 숫자의 페이지가 있는지
	private boolean hasNext; // 화면에 보이는 끝 페이지 번호보다 큰 숫자의 페이지가 있는지

	// 기본생성자 한페이페이지링크수 정의
	public PageMaker() {
		this.LinksPerPage = 3;
	}

	// 매개변수 있는 생성자
	public PageMaker(PageCriteria criteria, int totalCount) {
		this.LinksPerPage = 3;
		this.criteria = criteria;
		this.totalCount = totalCount;
		setPageData();
	}

	// getter/setter
	public PageCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(PageCriteria criteria) {
		this.criteria = criteria;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLinksPerPage() {
		return LinksPerPage;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	// 전체링크수 계산하여 리턴
	public int getRealEndPageNo() {
		// 전체링크수 = 올림(전체게시글 / 한페이지당표기될게시글수)
		int realEndPageNo = (int) Math.ceil((double) totalCount / criteria.getNumsPerPage());
		return realEndPageNo;
	}

	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산하여 세팅
	public void setPageData() {// Math.ceil (올림)
		// 전체링크수 = 올림(전체게시글 / 한페이지당표기될게시글수)
		int realEndPageNo = getRealEndPageNo();
		// 현재마지막페이지 = 올림(현재페이지번호 / 한페이지에 출력할 페이지링크 수 ) * 한페이지에 출력할 페이지링크 수
		int tempEndPageNo = (int) Math.ceil((double) criteria.getPage() / LinksPerPage) * LinksPerPage;

		// 현재 페이지의 startPageNo을 구하는 공식
		startPageNo = tempEndPageNo - LinksPerPage + 1;

		// 현재 페이지의 endPageNo을 구하는 조건문
		// curEndPageNo > realEndPageNo 일때는 realEndPageNo로 할당
		// curEndPageNo < realEndPageNo 일때는 curEndPageNo로 할당
		if (tempEndPageNo > realEndPageNo) {
			endPageNo = realEndPageNo;
		} else {
			endPageNo = tempEndPageNo;
		}

		// 첫 페이지 번호일 때는 이전버튼 없게
		if (startPageNo == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		// 마지막 페이지 번호일 때는 다음버튼 없게
		if (endPageNo == realEndPageNo) {
			hasNext = false;
		} else {
			hasNext = true;
		}
	}// end setPageData()

	public boolean isHasPrev() {
		return hasPrev;
	}

	public boolean isHasNext() {
		return hasNext;
	}

} // end PageMaker
