package com.booktracker.book_tracker.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenLibraryAuthorDTO {
    private String name;
    private String key;
    private String topWork;
    private Integer workCount;
    private String birthDate;
}
