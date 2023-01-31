package com.project.BookMyShow.Controller;


import com.project.BookMyShow.RequestDto.TheatreRequestDto;
import com.project.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

     @Autowired
    TheatreService theatreService;

     @PostMapping("/add_theatre")
    public String addTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
         return theatreService.addTheatre(theatreRequestDto);
     }
}
