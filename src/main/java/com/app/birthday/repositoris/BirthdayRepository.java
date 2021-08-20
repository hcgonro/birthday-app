package com.app.birthday.repositoris;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.birthday.models.Birthday;

public interface BirthdayRepository extends MongoRepository<Birthday, String>{

	public List<Birthday> findByUser(String usuari);
}
