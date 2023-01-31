package com.project.BookMyShow.Service;

import com.project.BookMyShow.Models.User;
import com.project.BookMyShow.Repository.UserRepository;
import com.project.BookMyShow.RequestDto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){

        User user = User.builder().name(userRequestDto.getName()).mobileNo(userRequestDto.getMobileNo()).build();
        try{
            userRepository.save(user);
        }
        catch(Exception e){
            return "User cannot be added";
        }
        return "User added successfully";
    }
}
