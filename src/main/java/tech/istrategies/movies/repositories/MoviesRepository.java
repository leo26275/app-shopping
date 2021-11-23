package tech.istrategies.movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.istrategies.movies.models.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer>{
	
	@Query(value="SELECT * FROM movies WHERE movies.availability = ?", nativeQuery=true)
	List<Movie> fidByAvailibility(boolean availability);
	
	@Query(value="SELECT * FROM movies WHERE movies.availability = TRUE", nativeQuery=true)
	List<Movie> fidByMovies();
	
	@Query(value="SELECT * FROM movies WHERE movies.title = ?", nativeQuery=true)
	List<Movie> fidByMoviesName(String title);
	
	@Query(value="SELECT m.sale_price FROM movies m WHERE m.id_movies = ?", nativeQuery=true)
	double returnSalePrice(int id_movies);
}
