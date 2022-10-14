package edu.spring.ex03;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class ReplyDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);

	@Autowired
	private ReplyDAO dao;

	@Test
	public void testDAO() {
//		testInsert();
		testSelectAll();
//		testUpdate();
//		testdelete();
	}

	private void testdelete() {
		int result = dao.delete(3);
		if (result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
	}

	private void testUpdate() {
		ReplyVO vo = new ReplyVO(3, 0, null, "수정됨", null);
		int result = dao.update(vo);
		if (result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	}

	private void testSelectAll() {
		List<ReplyVO> list = dao.select(3);
		for (ReplyVO vo : list) {
			logger.info(vo.toString());
		}

	}

	private void testInsert() {
		ReplyVO vo = new ReplyVO(0, 3, "아이디", "댓글내용", null);
		int result = dao.insert(vo);
		if (result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	}

}
