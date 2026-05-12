package com.example.repository;

import com.example.model.Book;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepository {
    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public BookRepository() {
        // 初期データ
        books.put(1L, new Book(1L, "Spring入門", "太郎", "978-1234567890", 3000.0));
        books.put(2L, new Book(2L, "Java完全ガイド", "花子", "978-0987654321", 4500.0));
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idCounter.getAndIncrement());
        }
        books.put(book.getId(), book);
        return book;
    }

    public void deleteById(Long id) {
        books.remove(id);
    }
}