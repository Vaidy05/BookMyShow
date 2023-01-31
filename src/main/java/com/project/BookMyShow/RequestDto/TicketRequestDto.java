package com.project.BookMyShow.RequestDto;


import lombok.Data;


import java.util.List;

@Data
public class TicketRequestDto {

    private List<String> requestedSeats;

    private int showId;

    private int userId;

}
