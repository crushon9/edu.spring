package edu.spring.ex03.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImple.class);
	@Autowired
	private ReplyDAO replayDAO;

	@Override
	public int create(ReplyVO vo) {
		logger.info("create() 호출");
		return replayDAO.insert(vo);
	}

	@Override
	public List<ReplyVO> read(int boardId) {
		logger.info("read() 호출 : boardId = " + boardId);
		return replayDAO.select(boardId);
	}

	@Override
	public int update(ReplyVO vo) {
		logger.info("update() 호출");
		return replayDAO.update(vo);
	}

	@Override
	public int delete(int replyId) {
		logger.info("delete() 호출 : replyId = " + replyId);
		return replayDAO.delete(replyId);
	}
}
