package com.app.birthday.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.birthday.models.Birthday;
import com.app.birthday.repositoris.BirthdayRepository;

@RestController
public class BirthdayController {

	@Autowired
	private BirthdayRepository birthdayRepository;
	
	private BirthdayController(BirthdayRepository birthdayRepository) {
		this.birthdayRepository = birthdayRepository;
	}
	
	@GetMapping("/birthdays/birthday/{id}")
	private EntityModel<Birthday> getBirthday(@PathVariable String id) {
		
		Birthday birthday = birthdayRepository.findById(id).
				orElse(null);
		return EntityModel.of(birthday,
				linkTo(methodOn(BirthdayController.class).getBirthday(id)).withSelfRel() );
	}
	
	@GetMapping("/birthdays/{user}")
	private List<EntityModel<Birthday>> birthdaysByUser(@PathVariable String user) {
		
		List<EntityModel<Birthday>> emBirthdayList = new ArrayList<EntityModel<Birthday>>();
		List<Birthday> birtdhayList = birthdayRepository.findByUser(user);
		for (Birthday birthday : birtdhayList) {
			emBirthdayList.add(EntityModel.of(birthday, linkTo(methodOn(BirthdayController.class).getBirthday(birthday.getId())).withSelfRel()));
		}
		
		return emBirthdayList;
	}
	
	@PostMapping("/birthdays")
	private Birthday newBirthday(@RequestBody Birthday birthday) {
		
		return birthdayRepository.insert(birthday);
	}
	
	@PutMapping("/birthdays/{id}")
	private Birthday updateBirthday(@RequestBody Birthday newBirthday, @PathVariable String id) {
		
		return birthdayRepository.findById(id)
			.map(birthday -> {
				birthday.setName(newBirthday.getName());
				birthday.setSurname(newBirthday.getSurname());
				birthday.setBirthday_date(newBirthday.getBirthday_date());
				birthday.setUser(newBirthday.getUser());
				birthday.setNotes(newBirthday.getNotes());
				birthday.setImportant(newBirthday.isImportant());
				return birthdayRepository.save(birthday);
			})
			.orElseGet(() -> {
				newBirthday.setId(id);
				return birthdayRepository.save(newBirthday);
			});
	}
	
	@DeleteMapping("/birthdays/{id}")
	private void deleteBirthday(@PathVariable String id) {
		
		birthdayRepository.deleteById(id);
	}
}
