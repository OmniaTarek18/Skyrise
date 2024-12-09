package com.example.backend.Utilites;

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
}
