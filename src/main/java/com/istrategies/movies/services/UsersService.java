package com.istrategies.movies.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istrategies.movies.repositories.UserRepository;
import com.istrategies.movies.models.User;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public User findBy(int id_users) {
		return userRepository.findById(id_users).get();
	}
	
	public boolean isEmptyByID(int id_users) {
		return userRepository.findById(id_users).isEmpty();
	}
	
}
