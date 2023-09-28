package com.purplecloud;

import lombok.Builder;

import javax.persistence.*;

import java.util.Objects;
@Entity
@Builder
public class Library {
    @Id
    @SequenceGenerator(
            name = "book_id_sequence",
            sequenceName = "book_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_id_sequence"

    )
    private Integer id;
    private String bookTitle;
    private String authorName;
    private String genre;

    public Library(Integer id, String bookTitle, String authorName, String genre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.genre = genre;
    }

    public Library() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id) && Objects.equals(bookTitle, library.bookTitle) && Objects.equals(authorName, library.authorName) && Objects.equals(genre, library.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookTitle, authorName, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
