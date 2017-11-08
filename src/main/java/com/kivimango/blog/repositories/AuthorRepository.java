package com.kivimango.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kivimango.blog.domain.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

	Author findByName(String name);

}
