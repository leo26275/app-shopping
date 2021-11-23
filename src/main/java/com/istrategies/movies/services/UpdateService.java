package com.istrategies.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istrategies.movies.models.Update;
import com.istrategies.movies.repositories.UpdateRepository;

@Service
public class UpdateService {
	
	@Autowired
	private UpdateRepository updateRepository;
	
	public Update create(Update update) {
		return updateRepository.save(update);
	}
}
