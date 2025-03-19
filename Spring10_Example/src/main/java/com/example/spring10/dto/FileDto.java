package com.example.spring10.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("fileDto")
@Data
public class FileDto {
	private long num;
	private String uploader;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private String uploadedAt;
	//파일 업로드 폼에 있는 input type="file" 의 name 속성의 value 와 필드명이 일치해야 한다.
	// <input type="file" name="myFile">
	private MultipartFile myFile;
	//페이징 처리할 때 필요한 필드
	private int startRowNum;
	private int endRowNum;
	private String condition; //검색 조건 writer 또는 title 또는 title+content
	private String keyword; //검색 키워드
	private long prevNum; //이전글의 글번호
	private long nextNum; //다음글의 글번호
}
