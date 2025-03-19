package com.example.spring10.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao{

	@Autowired
	private SqlSession session; //의존 객체
	
	@Override
	public List<FileDto> getList(FileDto dto) {
		// TODO Auto-generated method stub
		return session.selectList("file.getList", dto);
	}

	@Override
	public int insert(FileDto dto) {
		//파일의 번호는 files_seq 라는 이름의 시퀀스로 넣기
		return session.insert("file.insert", dto);
	}

	@Override
	public int update(FileDto dto) {
		//번호를 이용해서 title 만 수정하도록 한다
		return session.update("file.update", dto);
	}

	@Override
	public int delete(long num) {
		//번호를 이용해서 삭제
		return session.delete("file.delete", num);
	}
	
	@Override
	public int getCount(FileDto dto) {
		// TODO Auto-generated method stub
		return session.selectOne("file.getCount", dto);
	}

	@Override
	public long getSequence() {
		// TODO Auto-generated method stub
		return session.selectOne("file.getSequence");
	}

	@Override
	public FileDto getData(long num) {
		// TODO Auto-generated method stub
		return session.selectOne("file.getData", num);
	}

	@Override
	public FileDto getDetail(FileDto dto) {
		// TODO Auto-generated method stub
		return session.selectOne("file.getDetail", dto);
	}


}
