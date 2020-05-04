package org.kodluyoruz.javabootcamp.libraryproject.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "author",schema = "public")
public class Author implements Serializable {
    @Id
    @Column(name = "author_id")
    private UUID id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_lastname")
    private String authorLastName;

    public Author() {
    }

    public Author(String authorName ,String authorLastName ){
        this.id = UUID.randomUUID();
        this.authorName = authorName;
        this.authorLastName = authorLastName;
    }

    public Author(UUID uuid ,String authorName ,String authorLastName ){
        this.id = uuid;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(authorName, author.authorName) &&
                Objects.equals(authorLastName, author.authorLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, authorLastName);
    }
}
