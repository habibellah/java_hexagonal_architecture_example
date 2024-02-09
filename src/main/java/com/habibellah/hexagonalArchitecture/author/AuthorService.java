package com.habibellah.hexagonalArchitecture.author;

import com.habibellah.hexagonalArchitecture.author.domain.Author;
import com.habibellah.hexagonalArchitecture.author.domain.FullName;
import com.habibellah.hexagonalArchitecture.author.inputDto.InputAuthorData;
import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService{

    private final AuthorPort authorPort;

    public AuthorService(AuthorPort authorPort) {
        this.authorPort = authorPort;
    }

   public AuthorOutputData createAuthor(InputAuthorData inputAuthorData){
        Author author = new Author(inputAuthorData.getId(),new FullName(inputAuthorData.getFullNameInputData().getFname(), inputAuthorData.getFullNameInputData().getLname()));
        AuthorOutputData authorOutputData = AuthorOutputData.fromAuthor(author);
       return authorPort.save(authorOutputData);
    }
   public void deleteAuthor(Long id){
        authorPort.deleteAuthor(id);
    }

   public AuthorOutputData updateAuthor(Long id, InputAuthorData inputUpdateAuthor){
        inputUpdateAuthor.setId(id);
        return authorPort.updateAuthor(inputUpdateAuthor);
    }

    public List<AuthorOutputData> findAllAuthor(){
        return authorPort.findAllAuthors();
    }

    public boolean isExist(Long id) {
        return authorPort.isExist(id);
    }
}