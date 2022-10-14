package edu.spring.ex02.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.pageutil.PageMaker;
import edu.spring.ex02.service.BoardService;

//@Component 모든 Spring 관리 구성 요소에 대한 일반 스테레오타입
//ㄴ @Repository *영속 계층(Persistence Layer)
//ㄴ @Service *서비스 계층 (Service/Business Layer)
//ㄴ @Controller *표현 계층(Presentation Layer)

@Controller // @Component 의 하위 어노테이션
//* 표현 계층(Presentation Layer)
// View(페이지)와 Service를 연결하는 역할
// request에 대한 response를 전달하는 역할
@RequestMapping(value = "/board") // url : /ex02/board
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardService;

	@GetMapping("/list") // return값이 없으니깐 list.jsp를 반환
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);

		// Paging 처리
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<BoardVO> list = boardService.read(criteria);
		if (!list.isEmpty()) { // list가 없을때 예외처리
			model.addAttribute("list", list);
			PageMaker pageMaker = new PageMaker(criteria, boardService.getTotalCount());
			model.addAttribute("pageMaker", pageMaker);
		}
	}

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}

	@PostMapping("/register")
	// 아니 register.jsp 에서 POST로 전송하면 자동으로 파라미터값이 vo에 담겨서 여기까지 오는거임? ㅇㅇ
	public String registerPOST(BoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = boardService.create(vo);
		logger.info(result + "행 삽입");
		if (result == 1) {
			reAttr.addFlashAttribute("insert_result", "success"); // 밑에 이동하는 위치로 보냄
			return "redirect:/board/list"; // get방식
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/board/register"; // get방식 registerGET()
		}
	}

	@GetMapping("/detail")
	public void detail(Model model, Integer boardId, PageCriteria criteria) {
		logger.info("detail() 호출 : boardId = " + boardId);
		logger.info(criteria.toString());
		BoardVO vo = boardService.read(boardId);
		model.addAttribute("vo", vo);
		// 게시글 작성 후 글목록으로 돌아갈 페이지를 view에 보냄
		model.addAttribute("criteria", criteria);
	}

	@GetMapping("/update")
	public void updateGET(Model model, Integer boardId, PageCriteria criteria) {
		logger.info("updateGET() 호출 : boardId = " + boardId);
		// 등록되어있는 게시글 정보를 읽어와서 view에 보냄
		BoardVO vo = boardService.read(boardId);
		model.addAttribute("vo", vo);
		// 게시글 작성 후 글목록으로 돌아갈 페이지를 임시로 view에 보냄
		model.addAttribute("criteria", criteria);
	}

	@PostMapping("/update")
	public String updatePOST(BoardVO vo, PageCriteria criteria, RedirectAttributes reAttr) {
		logger.info("updatePOST() 호출");
		logger.info(criteria.toString());
		int result = boardService.update(vo);
		logger.info(result + "행 삽입");
		if (result == 1) {
			reAttr.addFlashAttribute("update_result", "success");
			// 게시글 작성 후 글목록으로 돌아갈 페이지를 get방식으로 view에 보냄
			return "redirect:/board/list?page=" + criteria.getPage() + "&numsPerPage=" + criteria.getNumsPerPage();
		} else {
			reAttr.addFlashAttribute("update_result", "fail");
			return "redirect:/board/update?boardId=" + vo.getBoardId(); // updateGET()
		}
	}
	
	@PostMapping("/delete")
	public String delete(Integer boardId, PageCriteria criteria, RedirectAttributes reAttr) {
		logger.info("updateGET() 호출 : boardId = " + boardId);
		int result = boardService.delete(boardId);
		logger.info(result + "행 삭제");
		if (result == 1) {
			reAttr.addFlashAttribute("delete_result", "success");
			return "redirect:/board/list?page=" + criteria.getPage() + "&numsPerPage=" + criteria.getNumsPerPage();
		}else {
			reAttr.addFlashAttribute("delete_result", "fail");
			return "redirect:/board/list?page=" + criteria.getPage() + "&numsPerPage=" + criteria.getNumsPerPage();
		}
	}

}
