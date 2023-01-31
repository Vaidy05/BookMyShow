package com.project.BookMyShow.Service;

import com.project.BookMyShow.Enum.SeatType;
import com.project.BookMyShow.Models.Theatre;
import com.project.BookMyShow.Models.TheatreSeat;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.Repository.TheatreSeatRepository;
import com.project.BookMyShow.RequestDto.TheatreRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    public String addTheatre(TheatreRequestDto theatreRequestDto){

        Theatre theatre = Theatre.builder().name(theatreRequestDto.getName()).address(theatreRequestDto.getAddress()).city(theatreRequestDto.getCity()).build();

        List<TheatreSeat> theatreSeatList = createTheatreSeat();

        theatre.setTheatreSeatList(theatreSeatList);

        for(TheatreSeat theatreSeat : theatreSeatList){
            theatreSeat.setTheatre(theatre);
        }

        theatreRepository.save(theatre);

        return "Theatre added successfully";
    }

    public List<TheatreSeat> createTheatreSeat(){

        List<TheatreSeat> theatreSeatList = new ArrayList<>();

        for(int i=0;i<5;i++){

            char ch = (char) ('A'+i);
            String seatNo = "1"+ch;
            TheatreSeat theatreSeat = new TheatreSeat(seatNo, SeatType.CLASSIC,100);
            theatreSeatList.add(theatreSeat);
        }

        for(int i=0;i<5;i++){

            char ch = (char) ('A'+i);
            String seatNo = "2"+ch;
            TheatreSeat theatreSeat = new TheatreSeat(seatNo, SeatType.PLATINUM,200);
            theatreSeatList.add(theatreSeat);
        }

        theatreSeatRepository.saveAll(theatreSeatList);

        return theatreSeatList;
    }
}
