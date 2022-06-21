package org.example.mock.service.impl;

import org.example.mock.service.BookService;
import org.example.mock.service.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Book queryByTitle(String title) {
        Book book = new Book();
        book.setTitle("西游记");
        book.setAuthor("吴承恩");
        book.setType("儿童读物");
        return book;
    }
}
