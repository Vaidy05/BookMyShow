package com.project.BookMyShow.Service;

import com.project.BookMyShow.Models.ShowEntity;
import com.project.BookMyShow.Models.ShowSeat;
import com.project.BookMyShow.Models.Ticket;
import com.project.BookMyShow.Models.User;
import com.project.BookMyShow.Repository.ShowRepository;
import com.project.BookMyShow.Repository.TicketRepository;
import com.project.BookMyShow.Repository.UserRepository;
import com.project.BookMyShow.RequestDto.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String bookTicket(TicketRequestDto ticketRequestDto) throws Exception{

        List<String> requestedSeats = ticketRequestDto.getRequestedSeats();

        ShowEntity show = showRepository.findById(ticketRequestDto.getShowId()).get();

        User user = userRepository.findById(ticketRequestDto.getUserId()).get();

        List<ShowSeat> showSeatList = show.getShowSeatList();

        List<ShowSeat> bookedSeats = new ArrayList<>();

        for(ShowSeat showSeat : showSeatList){

            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        if(bookedSeats.size()!= requestedSeats.size())
            throw new Exception("Requested seats are not available");

        Ticket ticket = new Ticket();

        double totalamount = 0;
        double multiplier = show.getMultiplier();
        String seatsalloted="";
        int rate=0;

        for(ShowSeat bookedSeat:bookedSeats){

            bookedSeat.setBooked(true);
            bookedSeat.setBookedOn(new Date());
            bookedSeat.setTicket(ticket);
            bookedSeat.setShowEntity(show);

            String seatNo = bookedSeat.getSeatNo();

            seatsalloted = seatsalloted + seatNo + ",";

            if(seatNo.charAt(0)=='1'){
                rate=100;
            }
            else{
                rate=200;
            }

            totalamount = totalamount+multiplier*rate;
        }

        ticket.setBookedOn(new Date());
        ticket.setShowEntity(show);
        ticket.setAmount((int)totalamount);
        ticket.setUser(user);
        ticket.setShowSeatList(bookedSeats);
        ticket.setSeatsAlloted(seatsalloted);

        ticketRepository.save(ticket);

        return "Booked ticket successfully";
    }
}
