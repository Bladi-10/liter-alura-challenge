package com.bladi._k.liter_alura_challenge.repository;

import com.bladi._k.liter_alura_challenge.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String title);
    List<Book> findByLanguage(String language);
}
