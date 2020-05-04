package com.erkankrcr.mylibraryproject.repostory.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Library{

	@SerializedName("books")
	private Book book;

	@SerializedName("addedTime")
	private Object addedTime;

	@SerializedName("id")
	private String id;

	@SerializedName("complete")
	private boolean complete;

	@SerializedName("readingStart")
	private Object readingStart;

	@SerializedName("readingEnd")
	private Object readingEnd;

	public void setBook(Book book){
		this.book = book;
	}

	public Book getBook(){
		return book;
	}

	public void setAddedTime(Date addedTime){
		this.addedTime = addedTime;
	}

	public Object getAddedTime(){
		return addedTime;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setComplete(boolean complete){
		this.complete = complete;
	}

	public boolean isComplete(){
		return complete;
	}

	public void setReadingStart(long readingStart){
		this.readingStart = readingStart;
	}

	public Object getReadingStart(){
		return readingStart;
	}

	public void setReadingEnd(Long readingEnd){
		this.readingEnd = readingEnd;
	}

	public Object getReadingEnd(){
		return readingEnd;
	}

	@Override
 	public String toString(){
		return 
			"Library{" + 
			"books = '" + book + '\'' +
			",addedTime = '" + addedTime + '\'' + 
			",id = '" + id + '\'' + 
			",complete = '" + complete + '\'' + 
			",readingStart = '" + readingStart + '\'' + 
			",readingEnd = '" + readingEnd + '\'' + 
			"}";
		}
}