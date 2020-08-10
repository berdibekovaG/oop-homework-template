package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;


public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    public SimpleAuthorRepository() {
    }

    public SimpleAuthorRepository(Author[] authors) {
        this.authors = authors;
    }

    @Override
    public boolean save(Author author) {
        if (author != null && !author.getName().equals("") && !author.getLastName().equals("")
                && (this.findByFullName(author.getName(), author.getLastName()) == null)) {
            Author[] authorCopy = new Author[authors.length + 1];
            for (int i = 0; i < authors.length; i++) {
                authorCopy[i] = authors[i];
            }
            authorCopy[authorCopy.length-1] = author;
            authors = authorCopy;
            return true;
        } else return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (name.equals(author.getName()) && lastname.equals(author.getLastName())) {
                return author;
            } else continue;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (author != null) {
            Author[] copyOfAuthor;
            int countOfCopy = 0;
            Author findAuthor = this.findByFullName(author.getName(), author.getLastName());
            if (findAuthor != null) {
                copyOfAuthor = new Author[authors.length - 1];
                for (Author a : authors) {
                    if (!a.getName().equals(findAuthor.getName()) &&
                            !a.getLastName().equals(findAuthor.getLastName())) {
                        copyOfAuthor[countOfCopy] = a;
                        countOfCopy++;
                    } else continue;
                }
                authors = copyOfAuthor;
                return true;
            } else return false;
        }else return false;
    }

    @Override
    public int count() {
        return ((authors!=null) ? authors.length:0);
    }
}
