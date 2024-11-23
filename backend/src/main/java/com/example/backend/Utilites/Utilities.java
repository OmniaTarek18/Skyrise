package com.example.backend.Utilites;

import org.springframework.data.domain.Sort;

public class Utilities {
    public static Sort sort(String direction, String sortby) {
        if (direction == null || sortby == null)
            return null;

        direction = direction.toLowerCase();
        return Sort.by(Sort.Direction.fromString(direction), sortby);
    }
}
