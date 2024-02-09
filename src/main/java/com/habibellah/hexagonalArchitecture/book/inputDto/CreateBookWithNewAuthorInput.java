package com.habibellah.hexagonalArchitecture.book.inputDto;

import com.habibellah.hexagonalArchitecture.author.inputDto.InputAuthorData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookWithNewAuthorInput {
    private String id;
    private String title;
    private InputAuthorData inputAuthorData;
}

