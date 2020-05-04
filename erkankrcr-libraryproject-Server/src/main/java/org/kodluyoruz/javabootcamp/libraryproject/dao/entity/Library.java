package org.kodluyoruz.javabootcamp.libraryproject.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "library", schema = "public")
public class Library implements Serializable {
    @Id
    @Column(name = "library_id")
    private UUID id;

    @Column(name = "reading_start")
    @Temporal(TemporalType.DATE)
    private Date readingStart;

    @Column(name = "reading_end")
    @Temporal(TemporalType.DATE)
    private Date readingEnd;

    @Column(name = "added_time")
    @Temporal(TemporalType.DATE)
    private Date addedTime;

    @Column(name = "iscomplete")
    private boolean isComplete;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Library() {
    }

    public Library(UUID uuid ,Date readingStart ,Date readingEnd ,Date addedTime ,boolean isComplete ,User user ,Book book) {
        this.id = uuid;
        this.readingStart = readingStart;
        this.readingEnd = readingEnd;
        this.addedTime = addedTime;
        this.isComplete = isComplete;
        this.user = user;
        this.book = book;
    }

    public Library(Date readingStart ,Date readingEnd ,Date addedTime ,boolean isComplete ,User user ,Book book) {
        this.id = UUID.randomUUID();
        this.readingStart = readingStart;
        this.readingEnd = readingEnd;
        this.addedTime = addedTime;
        this.isComplete = isComplete;
        this.user = user;
        this.book = book;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getReadingStart() {
        return readingStart;
    }

    public void setReadingStart(Date readingStart) {
        this.readingStart = readingStart;
    }

    public Date getReadingEnd() {
        return readingEnd;
    }

    public void setReadingEnd(Date readingEnd) {
        this.readingEnd = readingEnd;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBooks() {
        return book;
    }

    public void setBooks(Book book) {
        this.book = book;
    }

    public boolean getComplete(){
        return this.isComplete;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", readingStart=" + readingStart +
                ", readingEnd=" + readingEnd +
                ", addedTime=" + addedTime +
                ", isComplete=" + isComplete +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return isComplete == library.isComplete &&
                Objects.equals(id, library.id) &&
                Objects.equals(readingStart, library.readingStart) &&
                Objects.equals(readingEnd, library.readingEnd) &&
                Objects.equals(addedTime, library.addedTime) &&
                Objects.equals(user, library.user) &&
                Objects.equals(book, library.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, readingStart, readingEnd, addedTime, isComplete, user, book);
    }
}
