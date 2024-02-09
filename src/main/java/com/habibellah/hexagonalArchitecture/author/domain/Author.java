package com.habibellah.hexagonalArchitecture.author.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author{
    Long id;
    private FullName fullName;
}
