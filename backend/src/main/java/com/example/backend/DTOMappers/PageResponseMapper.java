package com.example.backend.DTOMappers;

import org.springframework.data.domain.Page;
import com.example.backend.DTOs.PageResponse;

public class PageResponseMapper {
    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber());
    }
}
