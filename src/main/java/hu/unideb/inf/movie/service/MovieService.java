package hu.unideb.inf.movie.service;

import hu.unideb.inf.movie.entity.Movie;
import hu.unideb.inf.movie.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    public List<Movie> getMovies(){
        return movieRepo.findAll();
    }

    public List<Movie> getMoviesByTitle(String title) {
        return movieRepo.findMoviesByTitle(title);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepo.findMoviesByGenre(genre);
    }

    public Optional<Movie> getById(long id) {
        return movieRepo.findById(id);
    }

    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> saveAll(List<Movie> movieList) {
        return movieRepo.saveAll(movieList);
    }

    public void deleteById(long id) {
        movieRepo.deleteById(id);
    }

    public void deleteAll() {
        movieRepo.deleteAll();
    }
}
