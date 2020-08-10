package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        book = (book instanceof SchoolBook ? (SchoolBook) book : null);
        if (book != null && (authorService.findByFullName(((SchoolBook) book).getAuthorName(),
                ((SchoolBook) book).getAuthorLastName()) != null)) {
            return schoolBookBookRepository.save((SchoolBook) book);
        } else return false;
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {

        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {

        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {

        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        if (schoolBookBookRepository.findByName(name).length > 0) {
            return authorService.findByFullName(schoolBookBookRepository.findByName(name)[0].getAuthorName(),
                    schoolBookBookRepository.findByName(name)[0].getAuthorLastName());
        } else return null;
    }
}
