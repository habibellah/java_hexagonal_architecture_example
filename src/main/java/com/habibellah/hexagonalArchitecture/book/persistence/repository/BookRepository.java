package com.habibellah.hexagonalArchitecture.book.persistence.repository;

import com.habibellah.hexagonalArchitecture.book.persistence.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity,String> {
}
