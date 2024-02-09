package com.habibellah.hexagonalArchitecture.author.controller;


import com.habibellah.hexagonalArchitecture.author.AuthorService;
import com.habibellah.hexagonalArchitecture.author.inputDto.InputAuthorData;
import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorOutputData> createAuthor(@RequestBody InputAuthorData inputAuthorData) {
       AuthorOutputData authorOutputData = authorService.createAuthor(inputAuthorData);
        return new ResponseEntity<>(authorOutputData, HttpStatus.CREATED);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorOutputData>> findAllAuthors(){
        List<AuthorOutputData> authorOutputDataList = authorService.findAllAuthor();
        return new ResponseEntity<>(authorOutputDataList,HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthorBy(@PathVariable("id") Long id){
        if(!authorService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorOutputData> updateAuthor(@PathVariable("id") Long id,@RequestBody InputAuthorData inputUpdateAuthor){
        if(!authorService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       AuthorOutputData updatedAuthor = authorService.updateAuthor(id,inputUpdateAuthor);
        return new ResponseEntity<>(updatedAuthor,HttpStatus.OK);
    }
}
