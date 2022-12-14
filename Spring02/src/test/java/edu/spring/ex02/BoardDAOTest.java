package edu.spring.ex02;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.persistence.BoardDAO;

// SqlSessionTest : sqlSession 자체에 대한 테스트
// BoardDAOTest : BoardDAOImple의 단위 테스트 (dao.insert(), select(), update(), delete() )
// 				즉 Service에서 할 작업을 여기서 하는거네?

// Spring TestContext Framework를 사용하기 위한 선언
@RunWith(SpringJUnit4ClassRunner.class)
// Context 환경 설정 파일이 있는 위치를 선언
// src/main/webapp/WEB-INF/spring/ 디렉토리와 그 하위 디렉토리 아래에 있는 모든 xml 파일을 검색(root-context.xml, servlet-context.xml)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
// WebApplicationContext 객체를 로드하여 사용하겠다는 선언
@WebAppConfiguration
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Autowired
	// @Autowired : 외부에서 객체를 생성해서 주입
	// BoardDAO은 @Repository 처리로 bean으로 올라가있고, 해당 bean을 주입함
	private BoardDAO dao;

	// @Before JUnit 테스트 작업에 앞서 초기화 작업을 수행하는 메소드
	// @Test 테스트해야 하는 내용을 작성하는 메소드
	// @After 테스트 작업이 끝난 후 자동으로 실행되는 메소드
	@Test
	public void testDAO() {
		testInsert();
//		testSelectAll();
//		testSelectByBoardId();
//		testUpdate();
//		testDelete();
//		testSelectPaging();
//		testTotalCount();
//		testSelectBymemberId();
//		testSelectByTitleOrContent();
	}

	private void testInsert() {
		BoardVO vo = new BoardVO(0, "제목", "내용", "아이디", null, 0);
		int result = dao.insert(vo);
		if (result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	}

	private void testSelectAll() {
		List<BoardVO> list = dao.select();
		for (BoardVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testSelectByBoardId() {
		BoardVO vo = dao.select(4);
		logger.info(vo.toString());
	}

	private void testUpdate() {
		BoardVO vo = new BoardVO(4, "수정제목", "수정수정수정내용", null, null, 0);
		int result = dao.update(vo);
		if (result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	}

	private void testDelete() {
		int result = dao.delete(6);
		if (result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
	}

	private void testSelectPaging() {
		PageCriteria criteria = new PageCriteria(1, 5); // 1페이지 5개
		List<BoardVO> list = dao.select(criteria);
		for (BoardVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testTotalCount() {
		int totalCount = dao.getTotalCount();
		logger.info("총 게시글 수 :" + totalCount);
	}

	// TODO : 근데 검색 + 페이징 처리는 어떻게 해야하지?
	private void testSelectBymemberId() {
		List<BoardVO> list = dao.select("M");
		for (BoardVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testSelectByTitleOrContent() {
		List<BoardVO> list = dao.selectByTitleOrContent("수정");
		for (BoardVO vo : list) {
			logger.info(vo.toString());
		}

	}
}
