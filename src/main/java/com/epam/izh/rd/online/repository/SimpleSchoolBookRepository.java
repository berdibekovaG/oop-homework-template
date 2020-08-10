package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {

        if ((book != null) && (!book.getName().equals(""))) {
            SchoolBook[] copyOfScBook = new SchoolBook[this.count() + 1];
            for (int i = 0; i < this.count(); i++) {
                copyOfScBook[i] = schoolBooks[i];
            }
            copyOfScBook[copyOfScBook.length - 1] = book;
            schoolBooks = copyOfScBook;
            return true;
        } else return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findBook = new SchoolBook[this.count()];
        int foundedCount = 0;
        for (SchoolBook sb : schoolBooks) {
            if (sb.getName().equals(name)) {
                findBook[foundedCount] = sb;
                foundedCount++;
            } else continue;
        }
        if (foundedCount > 0) {
            findBook = Arrays.copyOfRange(findBook, 0, foundedCount);
            return findBook;
        } else return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        if (name != null) {
            SchoolBook[] sb;
            int countOfCopy = 0;
            SchoolBook[] findBook = this.findByName(name);
            if (findBook.length > 0) {
                sb = new SchoolBook[this.count() - findBook.length];
                for (SchoolBook elem : schoolBooks) {
                    if (!elem.getName().equals(name)) {
                        sb[countOfCopy] = elem;
                        countOfCopy++;
                    } else continue;
                }
                schoolBooks = sb;
                return true;
            } else return false;
        } else return false;
    }

    @Override
    public int count() {
        return (schoolBooks != null ? schoolBooks.length : 0);
    }
}
