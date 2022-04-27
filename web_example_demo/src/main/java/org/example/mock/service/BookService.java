package org.example.mock.service;


import org.example.mock.service.entity.Book;

public interface BookService {
    Book queryByTitle(String title);
}
