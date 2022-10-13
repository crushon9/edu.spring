package edu.spring.ex02;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

// Spring TestContext Framework를 사용하기 위한 선언
@RunWith(SpringJUnit4ClassRunner.class)
// Context 환경 설정 파일이 있는 위치를 선언
// src/main/webapp/WEB-INF/spring/ 디렉토리와 그 하위 디렉토리 아래에 있는 모든 xml 파일을 검색(root-context.xml, servlet-context.xml)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
// WebApplicationContext 객체를 로드하여 사용하겠다는 선언
@WebAppConfiguration
public class BoardControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardControllerTest.class);
	@Autowired
	private WebApplicationContext wac; // 웹 어플리케이션 객체
	private MockMvc mock;

	// @Before JUnit 테스트 작업에 앞서 초기화 작업을 수행하는 메소드
	// @Test 테스트해야 하는 내용을 작성하는 메소드
	// @After 테스트 작업이 끝난 후 자동으로 실행되는 메소드
	@Before
	public void beforeTest() {
		logger.info("beforeTest() 호출");
		// 컨트롤러 메소드에게 요청을 보낼 수 있는 mockup 객체 생성
		mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test() {
		testList();
	}

	private void testList() {
		logger.info("testList() 호출");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("page", "1"); // String 형태로 파라미터를 보냄
		params.add("numsPerPage", "2");

		// get(url).파라미터
		// "/board/list?파라미터"가 호출되었기 때문에 해당되는 컨트롤러가 작동했음
		RequestBuilder requestBuilder = get("/board/list").params(params);
		try {
			mock.perform(requestBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
