package edu.spring.ex03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.service.ReplyService;

// * RESTful URL과 의미
// (POST)	/replies  		 : 댓글추가 (=insert)
// (GET) 	/replies/all/숫자  : 해당 글 번호(boardId)의 모든 댓글 검색 (=select)
// (PUT) 	/replies/숫자  	 : 해당 댓글 번호(replyId)의 내용을 수정 (=update)
// (DELETE)	/replies/숫자 	 : 해당 댓글 번호(replyId)의 댓글 삭제 (=delete)

@RestController // = @Controller + @ResponseBody 섞은거
// @RestController의 모든 콘트롤러 메소드들은, @ResponseBody 어노테이션 없이, 뷰가 아닌 데이터 자체를 클라이언트(브라우저)에게 서비스(리턴)하는 메소드가 됨
@RequestMapping(value = "/replies")
public class ReplyRESTController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRESTController.class);

	@Autowired
	private ReplyService replyService;

	// **POST는 테스트 할때 URL(=GET방식)로는 할수 없으니 API tester를 사용해야함

	@PostMapping // (POST)/replies
	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo) {
		// @RequestBody : 클라이언트에서 전송받은 JSON데이터를 자바객체로 변환해주는 annotation(주석)
		logger.info("createReply() 호출 : vo = " + vo.toString());

		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰는 객체
		// 데이터와 HttpStatus를 전송, <T>는 보내고자 하는 데이터 Type
		int result = replyService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{boardId}") // (GET)/replies/all/숫자
	public ResponseEntity<List<ReplyVO>> readReplies(@PathVariable("boardId") int boardId) {
		// @PathVariable : URL의 {boardId}과 매칭시켜주는 annotation
		logger.info("readReplies() 호출 : boardId = " + boardId);
		List<ReplyVO> list = replyService.read(boardId);
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK); // 자동으로 JSON으로 파싱됨
	}
	
	@PutMapping("/{replyId}") // (PUT)/replies/숫자 
	public ResponseEntity<Integer> updateReply(@PathVariable("replyId") int replyId, @RequestBody ReplyVO vo) {
		logger.info("updateReply() 호출 : replyId = " + vo.getReplyId());
		int result = replyService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{replyId}") // (DELETE)/replies/숫자
	public ResponseEntity<Integer> deleteReply(@PathVariable("replyId") int replyId) {
		logger.info("deleteReply() 호출 : replyId = " + replyId);
		int result = replyService.delete(replyId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
