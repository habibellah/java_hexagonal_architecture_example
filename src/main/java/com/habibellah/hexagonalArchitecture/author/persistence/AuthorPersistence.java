package com.habibellah.hexagonalArchitecture.author.persistence;

import com.habibellah.hexagonalArchitecture.author.AuthorPort;
import com.habibellah.hexagonalArchitecture.author.inputDto.InputAuthorData;
import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import com.habibellah.hexagonalArchitecture.author.persistence.entity.AuthorEntity;
import com.habibellah.hexagonalArchitecture.author.persistence.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AuthorPersistence implements AuthorPort {

    private final AuthorRepository authorRepository;

    public AuthorPersistence(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorOutputData save(AuthorOutputData authorOutputData) {
    return AuthorEntity.toAuthorOutputData(authorRepository.save(AuthorEntity.fromAuthorOutputData(authorOutputData)));
    }

    @Override
    public void deleteAuthor(Long id) {
    authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorOutputData> findAllAuthors() {
        Iterable<AuthorEntity> authors = authorRepository.findAll();
        return StreamSupport.stream(authors.spliterator(), false)
                .map(AuthorEntity::toAuthorOutputData)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorOutputData updateAuthor(InputAuthorData inputUpdateAuthor) {
       return authorRepository.findById(inputUpdateAuthor.getId()).map(existedAuthor->{
           Optional.ofNullable(inputUpdateAuthor.getFullNameInputData().getFname()).ifPresent(existedAuthor::setFName);
           Optional.ofNullable(inputUpdateAuthor.getFullNameInputData().getLname()).ifPresent(existedAuthor::setLName);
            return AuthorEntity.toAuthorOutputData(authorRepository.save(existedAuthor));
        }).orElseThrow(()->new RuntimeException("author does not exist"));
    }

    @Override
    public boolean isExist(Long id) {
        return authorRepository.existsById(id);
    }
}
