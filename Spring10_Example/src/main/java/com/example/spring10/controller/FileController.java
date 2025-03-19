package com.example.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.service.FileService;


@Controller
public class FileController {
	
	@Autowired private FileService service;

	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(long num){
		
		return service.getResponse(num);
	}
	
		//파일 삭제 요청 처리
		@GetMapping("/file/delete")
		public String delete(long num) {
			service.deleteFile(num);
			
			return "file/delete";
		}
		/*
		//글 수정 반영 요청 처리
		@PostMapping("/file/update")
		public String update(FileDto dto) {
			service.updateFile(dto);
		
			return "file/update";
		}

		//글 수정 폼 요청 처리
		@GetMapping("/file/edit")
		public String edit(long num, Model model) {
			//수정할 글정보를 얻어와서 Model 객체에 담는다.
			FileDto dto=service.getByNum(num);
			model.addAttribute("dto", dto);
			
			return "file/edit";
		}
		*/
		@GetMapping("/file/new")
		public String newFile() {
			
			return "file/new";
		}
		
		@PostMapping("/file/save")
		public String save(FileDto dto, RedirectAttributes ra) {
			//dto 에는 title 과 myFile 정보가 들어 있다.
			service.saveFile(dto);
			
			ra.addFlashAttribute("msg", "파일을 성공적으로 업로드 했습니다.");
			return "redirect:/file/list";
		}
		/*
		 * 	dto 에 글번호만 있는 경우도 있고
		 *  검색과 관련된 정보도 같이 있을 수 있다.
		
		@GetMapping("/file/view")
		public String view(PostDto dto, Model model, HttpSession session) {
			
			PostDto resultDto=service.getDetail(dto);
			model.addAttribute("postDto", resultDto);
			
			//새로 작성한 글이 아닌 경우에만 조회수 관련 처리를 한다.
			if(model.getAttribute("saveMessage") == null) {
				//조회수 관련 처리를 한다.
				String sessionId=session.getId();
				service.manageViewCount(dto.getNum(), sessionId);
			}
			
			return "post/view";
		}
		*/
		/*
		 * 	pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1로 설정
		 * 	검색 키워드도 파라미터로 넘어오면 PostDto 의 condition 과 keyword 가 null 이 아니다
		 * 	검색 키워드가 넘어오지 않으면 PostDto 의 condition 과 keyword 는 null 이다
		 */
		
		@GetMapping("/file/list")
		public String list(@RequestParam(defaultValue = "1") int pageNum, FileDto search, Model model) {
			FileListDto dto=service.getFiles(pageNum, search);
			model.addAttribute("dto", dto);
			return "file/list";
		}
		/*
		@GetMapping("/file/list")
		public String list(Model model) {
			//서비스를 이용해서 파일 목록 얻어오기
			List<FileDto> list=service.getFiles();
			//응답에 필요한 데이터를 Model 객체에 담는다.
			model.addAttribute("list", list);
			//template 페이지에서 응답하기
			return "file/list";
		}
		*/
}
