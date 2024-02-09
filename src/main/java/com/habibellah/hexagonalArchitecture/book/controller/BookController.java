package com.habibellah.hexagonalArchitecture.book.controller;

import com.habibellah.hexagonalArchitecture.book.BookService;
import com.habibellah.hexagonalArchitecture.book.inputDto.CreateBookWithNewAuthorInput;
import com.habibellah.hexagonalArchitecture.book.outputDto.BookOutputData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<BookOutputData> createBook(@RequestBody CreateBookWithNewAuthorInput createBookWithNewAuthorInput){
      BookOutputData bookOutputData = bookService.createBookWithNewAuthor(createBookWithNewAuthorInput);
            return new ResponseEntity<>(bookOutputData, HttpStatus.CREATED);
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookOutputData>> getAllBooks(){
        List<BookOutputData> bookOutputDataList = bookService.findAllBook();
        return new ResponseEntity<>(bookOutputDataList,HttpStatus.OK);
    }


    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable("isbn") String isbn
    ){
        if(!bookService.isExist(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
