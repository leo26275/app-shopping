package tech.istrategies.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.istrategies.movies.models.Update;
import tech.istrategies.movies.repositories.UpdateRepository;

@Service
public class UpdateService {
	
	@Autowired
	private UpdateRepository updateRepository;
	
	public Update create(Update update) {
		return updateRepository.save(update);
	}
}
