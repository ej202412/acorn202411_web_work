package com.example.spring10.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

public interface FileService {
	public FileListDto getFiles(int pageNum, FileDto search);
	public FileDto getByNum(long num);
	public FileDto getDetail(FileDto dto);
	public void saveFile(FileDto dto);
	public void updateFile(FileDto dto);
	public void deleteFile(long num);
	public ResponseEntity<InputStreamResource> getResponse(long num);
	
}
