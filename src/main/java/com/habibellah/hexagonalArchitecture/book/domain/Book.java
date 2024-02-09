package com.habibellah.hexagonalArchitecture.book.domain;

import com.habibellah.hexagonalArchitecture.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
    private String id;
    private String title;
    private Author author;
}
