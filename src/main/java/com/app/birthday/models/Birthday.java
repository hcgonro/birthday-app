package com.app.birthday.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Birthdays")
public class Birthday {
	
	@Id
	String id;
	
	String name;
	String surname;
	Date birthday_date;
	String user;
	String notes;
	boolean important;
	
	public Birthday() {
	}
	
	public Birthday(String name, String surname, Date birthday_date, String user, String notes,
			boolean important) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday_date = birthday_date;
		this.user = user;
		this.notes = notes;
		this.important = important;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday_date() {
		return birthday_date;
	}

	public void setBirthday_date(Date birthday_date) {
		this.birthday_date = birthday_date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	@Override
	public String toString() {
		return "Birthday [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday_date=" + birthday_date
				+ ", user=" + user + ", notes=" + notes + ", important=" + important + "]";
	}
	
}
