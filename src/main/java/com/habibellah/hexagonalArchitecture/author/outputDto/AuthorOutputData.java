package com.habibellah.hexagonalArchitecture.author.outputDto;


import com.habibellah.hexagonalArchitecture.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorOutputData {
    private Long id;
    private FullNameOutputData fullNameOutputData;

    public static AuthorOutputData fromAuthor(Author author) {
        return new AuthorOutputData(author.getId(),new FullNameOutputData(author.getFullName().getFName(),author.getFullName().getLName()));
    }
}