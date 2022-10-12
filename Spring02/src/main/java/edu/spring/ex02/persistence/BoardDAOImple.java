package edu.spring.ex02.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex02.OracleJDBCTest;
import edu.spring.ex02.domain.BoardVO;

@Repository // @Component
// - 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
// - Spring Component Bean 으로 등록함
// - 스프링 프레임 워크가 bean을 생성하기 위해서는 root-context.xml에 bean으로 등록해야함
// - <context:component-scan../>
public class BoardDAOImple implements BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(OracleJDBCTest.class);
	// board-mapper.xml 과 연결
	private static final String NAMESPACE = "edu.spring.ex02.BoardMapper";

	// MyBatis의 SqlSession을 사용하기 위해서 스프링 프레임워크가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BoardVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE가 동일한 mapper를 찾아가서 id="insert"인 insert 태그에 vo 데이터를 전송
		// sqlSession.태그(NAMESPACE + ".아이디", 전송할 데이터);
	}

	@Override
	public List<BoardVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

	@Override
	public BoardVO select(int boardId) {
		logger.info("select() 호출 : boardId = " + boardId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_board_id", boardId);
	}

	@Override
	public int update(BoardVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int boardId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", boardId);
	}
}
