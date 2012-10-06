package com.example.anothertodo.model;

public class Task {
	
	public Task(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Task(String taskName) {
		this.name = taskName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	private Long id;
	private String name;

}
