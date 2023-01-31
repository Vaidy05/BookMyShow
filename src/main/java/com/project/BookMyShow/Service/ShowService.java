package com.project.BookMyShow.Service;


import com.project.BookMyShow.Models.*;
import com.project.BookMyShow.Repository.MovieRepository;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.ShowSeatRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.RequestDto.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(ShowRequestDto showRequestDto){

        ShowEntity show = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        Movie movie = movieRepository.findByMovieName(showRequestDto.getMovieName());

        Theatre theatre = theatreRepository.findById(showRequestDto.getTheatreId()).get();

        show.setMovie(movie);
        show.setTheatre(theatre);

        movie.getShowList().add(show);
        theatre.getShowList().add(show);

        List<ShowSeat> showSeatList = createShowSeat(theatre.getTheatreSeatList());

        show.setShowSeatList(showSeatList);

        for(ShowSeat showSeat : showSeatList){
            showSeat.setShowEntity(show);
        }

        theatreRepository.save(theatre);
        movieRepository.save(movie);

        return "Show successfully added";
    }

    public List<ShowSeat> createShowSeat(List<TheatreSeat> theatreSeatList){

        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheatreSeat seat:theatreSeatList){
            ShowSeat showSeat = ShowSeat.builder().seatNo(seat.getSeatNo()).seatType(seat.getSeatType()).build();
            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);

        return showSeatList;
    }
}
