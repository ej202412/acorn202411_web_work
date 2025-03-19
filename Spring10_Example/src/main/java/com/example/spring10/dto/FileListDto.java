package com.example.spring10.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileListDto {
	private List<FileDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
	private int totalRow;
	private String findQuery;
	private String condition;
	private String keyword;
}
