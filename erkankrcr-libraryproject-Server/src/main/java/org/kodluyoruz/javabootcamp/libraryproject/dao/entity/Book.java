package org.kodluyoruz.javabootcamp.libraryproject.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book" , schema = "public")
public class Book implements Serializable {
    @Id
    @Column(name = "book_id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "author_id")
    public Author author;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "library_id")
    public Library library;

    @Column(name = "publication_year")
    @Temporal(TemporalType.DATE)
    private Date publicationYear;

    public Book() {
    }

    public Book(UUID uuid ,String title ,String description ,String comment ,Author author ,Date publicationYear){
        this.id = uuid;
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Book(String title ,String description ,String comment ,Author author ,Date publicationYear){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", author=" + author +
                ", publicationYear=" + publicationYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(description, book.description) &&
                Objects.equals(comment, book.comment) &&
                Objects.equals(author, book.author) &&
                Objects.equals(library, book.library) &&
                Objects.equals(publicationYear, book.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, comment, author, library, publicationYear);
    }
}
