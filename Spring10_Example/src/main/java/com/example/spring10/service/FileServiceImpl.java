package com.example.spring10.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

import com.example.spring10.exception.DeniedException;
import com.example.spring10.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
	
	//파일을 저장할 위치
	@Value("${file.location}")
	private String fileLocation;
	
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=10;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	@Autowired private FileDao fileDao;
	
	/*
	 * 	pageNum 과 검색 조건, 키워드가 담겨 있을 수 있는 FileDto 를 전달하면
	 * 	해당 파일 목록을 리턴하는 메소드
	 */
	
	@Override
	public FileListDto getFiles(int pageNum, FileDto search) {
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=fileDao.getCount(search);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		// startRowNum 과 endRowNum 을 FileDto 객체에 담아서
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		//글 목록 얻어오기
		List<FileDto> list=fileDao.getList(search);
		
		String findQuery="";
		if(search.getKeyword() != null) {
			findQuery="&keyword="+search.getKeyword()+"&condition="+search.getCondition();
		}
		//글 목록 페이지에서 필요한 정보를 모두 FileListDto 에 담는다.
		FileListDto dto=FileListDto.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.totalRow(totalRow)
				.findQuery(findQuery)
				.condition(search.getCondition())
				.keyword(search.getKeyword())
				.build();
		
		return dto;
	}

	@Override
	public FileDto getByNum(long num) {
		// TODO Auto-generated method stub
		return fileDao.getData(num);
	}

	@Override
	public FileDto getDetail(FileDto dto) {
		// TODO Auto-generated method stub
		return fileDao.getDetail(dto);
	}

	@Override
	public void saveFile(FileDto dto) {
		//FileDto 객체에서 MultipartFile 객체를 얻어낸다.
		MultipartFile myFile=dto.getMyFile();
		
		//만일 파일이 업로드 되지 않았다면
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다.");
		}
		
		//원본 파일명
		String orgFileName = myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 문자열로 얻어내기
		String saveFileName = UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath = fileLocation + File.separator + saveFileName;
		try {
			//업로드된 파일을 저장할 파일 객체 생성
			File saveFile=new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//업로더
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//FileDto 에 추가 정보를 담는다.
		dto.setUploader(userName);
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		//dao 를 이용해서 DB 에 저장하기
		fileDao.insert(dto);
		
	}

	@Override
	public void updateFile(FileDto dto) {
		// TODO Auto-generated method stub
		fileDao.update(dto);
	}

	@Override
	public void deleteFile(long num) {
		//파일의 작성자와 로그인된 userName 가 다르면 Exception 을 발생시키고 ExceptionController 에서 처리하게 한다 
		String uploader = fileDao.getData(num).getUploader();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!uploader.equals(userName)) {
			throw new DeniedException("요청이 거부 되었습니다");
		}
		fileDao.delete(num);
	}

	@Override
	public ResponseEntity<InputStreamResource> getResponse(long num) {
		//DB 에서 다운로드해줄 파일의 정보를 읽어온다.
		FileDto dto=fileDao.getData(num);
		String orgFileName=dto.getOrgFileName();
		String saveFileName=dto.getSaveFileName();
		long fileSize=dto.getFileSize();
		
		try {
			//다운로드 시켜줄 원본 파일명
			String encodedName=URLEncoder.encode(orgFileName, "utf-8");
			//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName=encodedName.replaceAll("\\+"," ");
			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers=new HttpHeaders();
			//파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			//파일의 크기 정보도 담아준다.
			headers.setContentLength(fileSize);
			//읽어들일 파일의 경로 구성
			String filePath=fileLocation + File.separator + saveFileName;
			
			//파일에서 읽어들일 스트림 객체
			InputStream is=new FileInputStream(filePath);
			//InputStreamResource 객체의 참조값 얻어내기
			InputStreamResource isr=new InputStreamResource(is);
			//ResponseEntity 객체를 구성해서
			ResponseEntity<InputStreamResource> resEntity=ResponseEntity.ok()
					.headers(headers)
					.body(isr);
			//리턴해주면 파일이 다운로드 된다.
			return resEntity;
		}catch(Exception e) {
			//예외 정보를 콘솔에 출력
			e.printStackTrace();
			//예외 발생시키기
			throw new RuntimeException("파일을 다운로드 하는 중에 에러 발생!");
		}
	
	}


}
