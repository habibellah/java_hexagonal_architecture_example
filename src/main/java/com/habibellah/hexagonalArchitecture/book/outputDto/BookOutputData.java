package com.habibellah.hexagonalArchitecture.book.outputDto;

import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import com.habibellah.hexagonalArchitecture.author.outputDto.FullNameOutputData;
import com.habibellah.hexagonalArchitecture.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOutputData{
    private String id;
    private String title;
    private AuthorOutputData authorOutputData;

   public static BookOutputData fromBook(Book book){
        return new BookOutputData(book.getId(),book.getTitle(),new AuthorOutputData(book.getAuthor().getId(),new FullNameOutputData(book.getAuthor().getFullName().getFName(),book.getAuthor().getFullName().getLName())));
    }
}

