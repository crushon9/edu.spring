package edu.spring.ex05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.ex05.util.FileUploadUtil;
import edu.spring.ex05.util.MediaUtil;

@Controller
@RequestMapping
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	// servlet-context.xml 파일에 설정된 문자열 리소스 주입
	@Resource(name = "uploadPath")
	private String uploadPath;

	@GetMapping("/upload")
	public void uploadGET() {
		logger.info("uploadGET()호출 : " + uploadPath);
	}

	@PostMapping("/upload")
	public void uploadPOST(MultipartFile file, Model model) {
		logger.info("uploadPOST()호출 : " + uploadPath);
		logger.info("파일이름 : " + file.getOriginalFilename());
		logger.info("파일크기 : " + file.getSize());

		String savedFile = saveUploadFile(file);
		logger.info("savedFile = " + savedFile);
	}

	@PostMapping("/upload2")
	public String uploadPOST2(MultipartFile[] files, Model model) {
		logger.info("uploadPOST2()호출 : " + uploadPath);
		String savedFiles = "";
		for (MultipartFile f : files) {
			savedFiles += saveUploadFile(f) + " ";
		}
		logger.info("savedFiles = " + savedFiles);
		return "upload";

	}

	@GetMapping("/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET() 호출");
	}

	@PostMapping("/upload-ajax")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files) throws IOException {
		logger.info("uploadAjaxPOST() 호출");

		// 파일 하나만 저장
		String result = null; // result : 파일 경로 및 썸네일 이미지 이름
		result = FileUploadUtil.saveUploadedFile(uploadPath, files[0].getOriginalFilename(), files[0].getBytes());
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// display 함수를 호출하면 서버에서 이미지를 확인할 수 있음 - 파일 경로를 전송해야 함
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		logger.info("display() 호출");

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		// uploadPath 아래의 / fileName ==> DB에는 파일경로와 이름만 저장함
		String filePath = uploadPath + "/" + fileName;
		in = new FileInputStream(filePath);

		// 파일 확장자
		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info(extension);

		// 응답 헤더(response header)에 Content-Type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.getMediaType(extension));

		// 데이터 전송
		entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), httpHeaders, HttpStatus.OK);
		return entity;
	}

	private String saveUploadFile(MultipartFile file) {
		// UUID : 업로드 하는 파일 이름이 중복되지 않도록
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);

		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("파일저장성공");
			return savedName;
		} catch (IOException e) {
			logger.error("파일저장실패");
			e.printStackTrace();
			return null;
		}
	}
}
