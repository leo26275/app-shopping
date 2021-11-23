package com.istrategies.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.istrategies.movies.models.Update;

@Repository
public interface UpdateRepository extends JpaRepository<Update, Integer> {

}
