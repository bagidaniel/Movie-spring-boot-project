package hu.unideb.inf.movie.repository;

import hu.unideb.inf.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

    List<Movie> findMoviesByTitle(String title);

    List<Movie> findMoviesByGenre(String genre);
}
