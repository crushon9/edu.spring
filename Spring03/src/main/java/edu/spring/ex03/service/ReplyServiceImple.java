package edu.spring.ex03.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.BoardDAO;
import edu.spring.ex03.persistence.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImple.class);
	@Autowired
	private ReplyDAO replayDAO;
	@Autowired
	private BoardDAO boardDAO;

	// @Transactional
	// 두 개의 DB 변경이 생길때
	// 위의 내용은 수행되었고 아래 내용은 에러가 발생한 경우, 위의 내용을 rollback 해주는 어노테이션
	// root-context.xml에서 설정
	@Override
	public int create(ReplyVO vo) {
		logger.info("create() 호출");
		// 댓글이 입력되면(insert) 게시판의 ReplyCnt가 1증가(update)
		replayDAO.insert(vo);
		logger.info("댓글입력성공");
		boardDAO.updateReplyCnt(1, vo.getBoardId());
		logger.info("게시판 댓글 개수 업데이트 성공");
		return 1;
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
	public int delete(int replyId, int boardId) {
		logger.info("delete() 호출 : replyId = " + replyId);
		// 댓글이 삭제면(delete) 게시판의 ReplyCnt가 1감소(update)
		replayDAO.delete(replyId);
		logger.info("댓글삭제성공");
		boardDAO.updateReplyCnt(-1, boardId);
		logger.info("게시판 댓글 개수 업데이트 성공");
		return 1;
	}
}
