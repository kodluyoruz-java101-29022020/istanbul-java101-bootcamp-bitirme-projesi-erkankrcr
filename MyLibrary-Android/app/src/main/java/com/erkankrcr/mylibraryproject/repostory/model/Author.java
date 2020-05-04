package com.erkankrcr.mylibraryproject.repostory.model;

import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("authorLastName")
	private String authorLastName;

	@SerializedName("authorName")
	private String authorName;

	@SerializedName("id")
	private String id;

	public void setAuthorLastName(String authorLastName){
		this.authorLastName = authorLastName;
	}

	public String getAuthorLastName(){
		return authorLastName;
	}

	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}

	public String getAuthorName(){
		return authorName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"authorLastName = '" + authorLastName + '\'' + 
			",authorName = '" + authorName + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}