package com.aacfahim.streaming_platform.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Metadata {
    private long totalItems;
    private int totalPages;
    private int currentPage;
}

