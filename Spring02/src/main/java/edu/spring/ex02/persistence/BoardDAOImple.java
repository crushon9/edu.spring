package edu.spring.ex02.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;

//@Component 모든 Spring 관리 구성 요소에 대한 일반 스테레오타입
//ㄴ @Repository *영속 계층(Persistence Layer)
//ㄴ @Service *서비스 계층 (Service/Business Layer)
//ㄴ @Controller *표현 계층(Presentation Layer)

@Repository // @Component 의 하위 어노테이션
// * 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
// - 해당 주석 처리시 서버 시작할때 Spring Component Bean 으로 등록됨
// 	 즉, servlet-context.xml의 component-scan을 통해 설정된 component을 찾아와 bean으로 등록

// - 스프링 프레임 워크가 bean을 생성하기 위해서는 root-context.xml (혹은 하위 servlet-context.xml)에  bean으로 등록해야함
//  <context:component-scan../>
public class BoardDAOImple implements BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImple.class);
	// board-mapper.xml 의 namespace 키 값과 동일하게 설정
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

	@Override
	public List<BoardVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCount() {
		logger.info("getTotalCount() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<BoardVO> select(String memberId) {
		logger.info("select() 호출 : memberId = " + memberId);
		return sqlSession.selectList(NAMESPACE + ".select_by_memberid", "%" + memberId + "%");
	}

	@Override
	public List<BoardVO> selectByTitleOrContent(String keyword) {
		logger.info("selectByTitleOrContent() 호출 : keyword = " + keyword);
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", "%" + keyword + "%");
	}
}
