package com.habibellah.hexagonalArchitecture.book.persistence;

import com.habibellah.hexagonalArchitecture.book.BookPort;
import com.habibellah.hexagonalArchitecture.book.outputDto.BookOutputData;
import com.habibellah.hexagonalArchitecture.book.persistence.entity.BookEntity;
import com.habibellah.hexagonalArchitecture.book.persistence.crudRepository.BookCrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookPersistence implements BookPort {

    private final BookCrudRepository bookCrudRepository;

    public BookPersistence(BookCrudRepository bookCrudRepository) {
        this.bookCrudRepository = bookCrudRepository;
    }

    @Override
    public BookOutputData save(BookOutputData bookOutputData) {

        return BookEntity.toBookOutputData(bookCrudRepository.save(BookEntity.fromBookOutputData(bookOutputData)));
    }

    @Override
    public void deleteBook(String id) {
    bookCrudRepository.deleteById(id);
    }

    @Override
    public List<BookOutputData> findAllBook() {
        Iterable<BookEntity> authors = bookCrudRepository.findAll();
        return StreamSupport.stream(authors.spliterator(), false)
                .map(BookEntity::toBookOutputData)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExist(String isbn) {
        return bookCrudRepository.existsById(isbn);
    }
}
