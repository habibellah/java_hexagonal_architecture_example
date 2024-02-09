package com.habibellah.hexagonalArchitecture.author.persistence.repository;


import com.habibellah.hexagonalArchitecture.author.persistence.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends CrudRepository<AuthorEntity,Long> {
}
