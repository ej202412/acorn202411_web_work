package com.example.spring05.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring05.dto.TodoDto;

@Repository
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<TodoDto> getList() {
		
		List<TodoDto> list=session.selectList("todo.getList");
		
		return list;
	}

	@Override
	public void insert(TodoDto dto) {
		// TODO Auto-generated method stub
		session.insert("todo.insert", dto);
	}

	@Override
	public void update(TodoDto dto) {
		// TODO Auto-generated method stub
		session.update("todo.update", dto);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		session.delete("todo.delete", id);
	}

	@Override
	public TodoDto getData(int id) {
		TodoDto dto=session.selectOne("todo.getData", id);
		return dto;
	}

}
