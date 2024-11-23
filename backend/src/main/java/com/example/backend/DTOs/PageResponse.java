package com.example.backend.DTOs;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int currentPage) {
}
