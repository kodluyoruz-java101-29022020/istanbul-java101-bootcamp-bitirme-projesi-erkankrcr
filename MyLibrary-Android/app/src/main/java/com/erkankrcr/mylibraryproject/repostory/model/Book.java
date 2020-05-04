package com.erkankrcr.mylibraryproject.repostory.model;

import com.google.gson.annotations.SerializedName;

public class Book {

	@SerializedName("author")
	private Author author;

	@SerializedName("description")
	private String description;

	@SerializedName("publicationYear")
	private Object publicationYear;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setPublicationYear(Object publicationYear){
		this.publicationYear = publicationYear;
	}

	public Object getPublicationYear(){
		return publicationYear;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Books{" + 
			"author = '" + author + '\'' + 
			",description = '" + description + '\'' + 
			",publicationYear = '" + publicationYear + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}