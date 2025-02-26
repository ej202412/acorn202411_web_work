package com.example.spring07.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
	private long id; //pk
	private String uploader;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private String uploadedAt;
	//파일 업로드 폼에 있는 input type="file" 의 name 속성의 value 와 필드명이 일치해야 한다.
	// <input type="file" name="myFile">
	private MultipartFile myFile;
}
