package com.project.BookMyShow.Service;

import com.project.BookMyShow.Models.Movie;
import com.project.BookMyShow.Repository.MovieRepository;
import com.project.BookMyShow.RequestDto.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){

        Movie movie = Movie.builder().movieName(movieRequestDto.getName()).releaseDate(movieRequestDto.getReleaseDate()).duration(movieRequestDto.getDuration()).build();
        movieRepository.save(movie);

        return "Movie Successfully added";
    }
}
