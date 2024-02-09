package com.habibellah.hexagonalArchitecture.book.persistence.crudRepository;

import com.habibellah.hexagonalArchitecture.book.persistence.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookCrudRepository extends CrudRepository<BookEntity,String> {
}