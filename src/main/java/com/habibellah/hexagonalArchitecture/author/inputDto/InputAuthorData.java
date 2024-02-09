package com.habibellah.hexagonalArchitecture.author.inputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
@AllArgsConstructor
@NoArgsConstructor
public class InputAuthorData {
    private Long id;
    private FullNameInputData fullNameInputData;
}