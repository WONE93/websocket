package com.yedam.socket.users;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOMybatis {

	@Autowired
	SqlSessionTemplate mybatis;

	// 단건조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> mybatis getUser()");
		return mybatis.selectOne("UserDAO.getUser", vo);
	}
	
	public List<Map> getRole(UserVO vo) {
		return mybatis.selectList("UserDAO.getRole", vo);
	}

	// 등록
	public int insertUser(UserVO vo) {
		System.out.println("===> mybatis insertUser()");
		return mybatis.update("UserDAO.insertUser", vo);
	}
}