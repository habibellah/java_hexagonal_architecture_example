package com.habibellah.hexagonalArchitecture.author;



import com.habibellah.hexagonalArchitecture.author.inputDto.InputAuthorData;
import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;

import java.util.List;

public interface AuthorPort{
    AuthorOutputData save(AuthorOutputData authorOutputData);

    void deleteAuthor(Long id);

   List<AuthorOutputData> findAllAuthors();

    AuthorOutputData updateAuthor(InputAuthorData inputUpdateAuthor);

    boolean isExist(Long id);
}