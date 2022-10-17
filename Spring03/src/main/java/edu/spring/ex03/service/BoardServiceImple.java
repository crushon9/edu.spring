package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;
import edu.spring.ex03.persistence.BoardDAO;

//@Component 모든 Spring 관리 구성 요소에 대한 일반 스테레오타입
//ㄴ @Repository *영속 계층(Persistence Layer)
//ㄴ @Service *서비스 계층 (Service/Business Layer)
//ㄴ @Controller *표현 계층(Presentation Layer)

@Service // @Component 의 하위 어노테이션
// * 서비스 계층 (Service/Business Layer)
// - 표현계층(Presentation Layer)과 영속계층(Persistence Layer) 사이를 연결하여 두 계층이 직접적으로 통신하지 않도록 하는역할
// - 트랜잭션(transaction) 관리
// - DB와 상관없이 데이터 처리가능
public class BoardServiceImple implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImple.class);
	@Autowired
	private BoardDAO dao; //@Repository로 스프링이 관리하는 bean으로 등록되었기때문에 @Autowired 사용가능

	@Override
	public int create(BoardVO vo) {
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public List<BoardVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		logger.info("startPageNo = " + criteria.getStart());
		logger.info("endPageNo = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public BoardVO read(int boardId) {
		logger.info("read() 호출 : boardId = " + boardId);
		return dao.select(boardId);
	}

	@Override
	public int update(BoardVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int boardId) {
		logger.info("delete() 호출 : boardId = " + boardId);
		return dao.delete(boardId);
	}

	@Override
	public int getTotalCount() {
		logger.info("getTotalCount() 호출");
		return dao.getTotalCount();
	}

}
