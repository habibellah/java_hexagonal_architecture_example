package com.habibellah.hexagonalArchitecture.author.persistence;

import com.habibellah.hexagonalArchitecture.author.AuthorPort;
import com.habibellah.hexagonalArchitecture.author.inputDto.InputAuthorData;
import com.habibellah.hexagonalArchitecture.author.outputDto.AuthorOutputData;
import com.habibellah.hexagonalArchitecture.author.persistence.entity.AuthorEntity;
import com.habibellah.hexagonalArchitecture.author.persistence.crudRepository.AuthorCrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AuthorPersistence implements AuthorPort {

    private final AuthorCrudRepository authorCrudRepository;

    public AuthorPersistence(AuthorCrudRepository authorCrudRepository) {
        this.authorCrudRepository = authorCrudRepository;
    }

    @Override
    public AuthorOutputData save(AuthorOutputData authorOutputData) {
    return AuthorEntity.toAuthorOutputData(authorCrudRepository.save(AuthorEntity.fromAuthorOutputData(authorOutputData)));
    }

    @Override
    public void deleteAuthor(Long id) {
    authorCrudRepository.deleteById(id);
    }

    @Override
    public List<AuthorOutputData> findAllAuthors() {
        Iterable<AuthorEntity> authors = authorCrudRepository.findAll();
        return StreamSupport.stream(authors.spliterator(), false)
                .map(AuthorEntity::toAuthorOutputData)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorOutputData updateAuthor(InputAuthorData inputUpdateAuthor) {
       return authorCrudRepository.findById(inputUpdateAuthor.getId()).map(existedAuthor->{
           Optional.ofNullable(inputUpdateAuthor.getFullNameInputData().getFname()).ifPresent(existedAuthor::setFName);
           Optional.ofNullable(inputUpdateAuthor.getFullNameInputData().getLname()).ifPresent(existedAuthor::setLName);
            return AuthorEntity.toAuthorOutputData(authorCrudRepository.save(existedAuthor));
        }).orElseThrow(()->new RuntimeException("author does not exist"));
    }

    @Override
    public boolean isExist(Long id) {
        return authorCrudRepository.existsById(id);
    }
}
