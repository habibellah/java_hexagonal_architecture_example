package com.habibellah.hexagonalArchitecture.book.persistence.entity;

import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import com.habibellah.hexagonalArchitecture.author.outputDto.FullNameOutputData;
import com.habibellah.hexagonalArchitecture.author.persistence.entity.AuthorEntity;
import com.habibellah.hexagonalArchitecture.book.outputDto.BookOutputData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    private String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;

    public static BookEntity fromBookOutputData(BookOutputData bookOutputData){
        return new BookEntity(bookOutputData.getId(),bookOutputData.getTitle(),AuthorEntity.fromAuthorOutputData(bookOutputData.getAuthorOutputData()));
    }

    public static BookOutputData toBookOutputData(BookEntity bookEntity){
        return new BookOutputData(bookEntity.getIsbn(),
                bookEntity.getTitle(),
                new AuthorOutputData(bookEntity.getAuthorEntity().getId(),
                        new FullNameOutputData(bookEntity.getAuthorEntity().getFName(),bookEntity.getAuthorEntity().getLName())));
    }
}