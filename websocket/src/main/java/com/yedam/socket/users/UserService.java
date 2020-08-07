package com.yedam.socket.users;

import java.util.List;
import java.util.Map;

public interface UserService {
    //조회
	public UserVO getUser(UserVO vo);
	
	//권한(롤) 조회
	public List<Map> getRole(UserVO vo);
	
	//등록
	public int insertUser(UserVO vo);
	
}
