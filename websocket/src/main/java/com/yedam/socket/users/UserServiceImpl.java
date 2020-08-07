package com.yedam.socket.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserDAOMybatis userDAO;
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

	public int insertUser(UserVO dto) {		
		return userDAO.insertUser(dto);		
	}

	@Override
	public List<Map> getRole(UserVO vo) {
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO vo = new UserVO();
		vo.setLoginId(username);
		vo = userDAO.getUser(vo); // 사용자가 존재하지 않는 경우
		if(vo == null) {
			throw new UsernameNotFoundException("no user");
		}
		vo.setAuthorities(getAuthorities(vo)); //vo에 롤권한 부여
		return vo;
	}
	
	public Collection<GrantedAuthority> getAuthorities(UserVO vo) {
		List<Map> authList = userDAO.getRole(vo);
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Map map : authList) {
			authorities.add(new SimpleGrantedAuthority(
					(String) map.get("ROLE_NAME")));
		}
		return authorities;
	}

}
