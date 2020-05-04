package org.kodluyoruz.javabootcamp.libraryproject.dao.model;


import java.util.Date;

public class LibraryRequestContext {
    private String title;
    private String description;
    private String comment;
    private String authorId;
    private String publicationYear;

    public LibraryRequestContext() {
    }

    public LibraryRequestContext(String title, String description, String comment, String authorId, String publicationYear) {
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.authorId = authorId;
        this.publicationYear = publicationYear;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "LibraryRequestContext{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", authorId='" + authorId + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
