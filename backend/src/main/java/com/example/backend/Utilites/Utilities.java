package com.example.backend.Utilites;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utilities {
    public static Sort sort(String direction, String sortby) {

        if (sortby == null)
            return null;
            
        if (direction == null)
            direction = "asc";

        direction = direction.toLowerCase();
        return Sort.by(Sort.Direction.fromString(direction), sortby);
    }
    public static Pageable CreatePage(int pageNumber, int pageSize, Sort sort){
        if(sort == null) 
            return PageRequest.of(pageNumber, 10);
        
        return PageRequest.of(pageNumber, 10, sort);                         
    }
}
