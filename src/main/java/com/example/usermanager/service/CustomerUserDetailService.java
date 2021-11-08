package com.example.usermanager.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.usermanager.dto.MyUser;
import com.example.usermanager.entity.Role;
import com.example.usermanager.entity.User;
import com.example.usermanager.respository.UserRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

   
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = repo.findByEmail(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("USER not found");
		}

		// put thong tin vao security duy tri thong tin nguoi dung
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		MyUser myUser = new MyUser(userEntity.getLastName(), userEntity.getPassword(), true, true, true, true, authorities);
		myUser.setFullname(userEntity.getFirstName() + userEntity.getLastName());
		return myUser;

	}
}
