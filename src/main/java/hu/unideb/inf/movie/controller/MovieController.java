package hu.unideb.inf.movie.controller;

import hu.unideb.inf.movie.entity.Movie;
import hu.unideb.inf.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        if (null == movieList) {
            movieList = new ArrayList<>();
        }
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @PostConstruct
    public void updateMovieList() {
        if (!this.getMovieList().isEmpty()) {
            this.getMovieList().clear();
        }
        this.getMovieList().addAll(movieService.getMovies());
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        try {
            List<Movie> movies = movieService.getMovies();

            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/movies/title/{title}")
    public ResponseEntity<List<Movie>> getMovieByTitle(@PathVariable String title) {
        List<Movie> movies = movieService.getMoviesByTitle(title);

        if (!movies.isEmpty()) {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        List<Movie> movies = movieService.getMoviesByGenre(genre);

        if (!movies.isEmpty()) {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie newMovie = movieService.save(new Movie(movie.getTitle(), movie.getDirector(), movie.getGenre(), movie.getRelease()));
            updateMovieList();
            return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovies(@RequestBody List<Movie> movieList) {
        try {
            movieService.saveAll(movieList);
            updateMovieList();
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Optional<Movie> movieData = movieService.getById(id);

        if (movieData.isPresent()) {
            Movie newMovie = movieData.get();
            newMovie.setTitle(movie.getTitle());
            newMovie.setGenre(movie.getGenre());
            newMovie.setRelease(movie.getRelease());
            updateMovieList();
            return new ResponseEntity<>(movieService.save(newMovie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
        try {
            movieService.deleteById(id);
            updateMovieList();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movies")
    public ResponseEntity<HttpStatus> deleteAllMovie() {
        try {
            movieService.deleteAll();
            updateMovieList();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
