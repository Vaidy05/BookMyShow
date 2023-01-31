package com.project.BookMyShow.Repository;

import com.project.BookMyShow.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Integer> {

    Theatre findByNameAndCity(String name, String city);
}
