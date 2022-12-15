package hu.unideb.inf.movie.entity;

import javax.persistence.*;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Director")
    private String director;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "Year")
    private String release;

    public Movie() {
    }

    public Movie(String title, String director, String genre, String release) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
