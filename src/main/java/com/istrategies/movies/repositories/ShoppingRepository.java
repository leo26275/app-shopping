package com.istrategies.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.istrategies.movies.models.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Integer> {

}
