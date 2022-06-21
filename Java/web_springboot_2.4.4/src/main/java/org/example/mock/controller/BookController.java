package org.example.mock.controller;

import org.example.mock.service.BookService;
import org.example.mock.service.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable("title") String title) {
        return bookService.queryByTitle(title);
    }

    @GetMapping("/get")
    public Book getBookByTitle2(@RequestParam("title") String title) {
        return bookService.queryByTitle(title);
    }
}
