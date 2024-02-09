package com.habibellah.hexagonalArchitecture.author.persistence.crudRepository;


import com.habibellah.hexagonalArchitecture.author.persistence.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorCrudRepository extends CrudRepository<AuthorEntity,Long> {
}
