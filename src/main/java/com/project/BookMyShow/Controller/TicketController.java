package com.project.BookMyShow.Controller;

import com.project.BookMyShow.RequestDto.TicketRequestDto;
import com.project.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketRequestDto ticketRequestDto){

        try{
            return ticketService.bookTicket(ticketRequestDto);
        }
        catch(Exception e){
            return "Requested seats are not available";
        }
    }
}
