package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int insert(FileDto dto);
	public int update(FileDto dto);
	public int delete(long num);
	public int getCount(FileDto dto);
	//저장할 파일번호를 생성해서 리턴해주는 메소드
	public long getSequence();
	public FileDto getData(long num);
	public FileDto getDetail(FileDto dto);
}
