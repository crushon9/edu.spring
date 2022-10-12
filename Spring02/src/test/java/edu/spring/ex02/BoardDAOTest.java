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
import edu.spring.ex02.persistence.BoardDAO;

// SqlSessionTest : sqlSession 자체에 대한 테스트
// BoardDAOTest : BoardDAOImple의 단위 테스트 (dao.insert(), select(), update(), delete() )

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(OracleJDBCTest.class);

	@Autowired // 이건 주입받은 객체가 아닌가? @Repository @Component 처리를 했다는데...?
	private BoardDAO dao;

	@Test
	public void testDAO() {
		testInsert();
//		testSelectAll();
//		testSelectByBoardId();
//		testUpdate();
//		testDelete();
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

}
