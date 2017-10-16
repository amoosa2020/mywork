package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.annotation.Id;


@Document(collection="mytodos")
public class MyTodo {
	 	 	
		@Id
		private String id;
	
		private String title;
		private String status;
		private Boolean completed;

			
		public MyTodo() {
			super();
		}
	
	 	public MyTodo(String title) {
	        this.title = title;
	    }

	 	public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }
	    
	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }
	    
	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public Boolean getCompleted() {
	        return completed;
	    }

	    public void setCompleted(Boolean completed) {
	        this.completed = completed;
	    }
}
