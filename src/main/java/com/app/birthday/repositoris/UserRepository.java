package com.app.birthday.repositoris;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.birthday.models.User;


public interface UserRepository extends MongoRepository<User, String>{

	public User findByUserName(String user);

}
