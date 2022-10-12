package edu.spring.ex02;

import javax.sql.DataSource;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class DataSourceTest {
	private static final Logger logger = LoggerFactory.getLogger(OracleJDBCTest.class);
	
	// Spring Framework가 관리하는 DataSource 객체를 주입받음
	// - root-context.xml에서 bean으로 선택된 DataSource 객체를 주입받음
	// ??? 항상 root-context.xml 파일인건가??? 
	@Autowired
	private DataSource ds;

}