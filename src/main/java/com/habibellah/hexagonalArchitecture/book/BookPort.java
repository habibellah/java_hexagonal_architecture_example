package com.habibellah.hexagonalArchitecture.book;

import com.habibellah.hexagonalArchitecture.book.outputDto.BookOutputData;

import java.util.List;

public interface BookPort {
    BookOutputData save(BookOutputData bookOutputData);

    void deleteBook(String id);

    List<BookOutputData> findAllBook();

    boolean isExist(String isbn);
}
