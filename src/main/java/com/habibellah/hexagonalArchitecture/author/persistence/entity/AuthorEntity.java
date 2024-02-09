package com.habibellah.hexagonalArchitecture.author.persistence.entity;

import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import com.habibellah.hexagonalArchitecture.author.outputDto.FullNameOutputData;
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
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_id_sequence")
    private Long id;

    private String fName;

    private String lName;

    public static AuthorEntity fromAuthorOutputData(AuthorOutputData authorOutputData){
        return new AuthorEntity(0L,authorOutputData.getFullNameOutputData().getFName(),authorOutputData.getFullNameOutputData().getLName());
    }

    public static AuthorOutputData toAuthorOutputData(AuthorEntity authorEntity){
        return new AuthorOutputData(authorEntity.getId(),new FullNameOutputData(authorEntity.getFName(),authorEntity.getLName()));
    }
}