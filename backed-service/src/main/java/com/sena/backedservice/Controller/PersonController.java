package com.sena.backedservice.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sena.backedservice.Entity.Person;
import com.sena.backedservice.IService.IPersonService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/person")
public class PersonController {

	@Autowired
	private IPersonService service;
	
	@GetMapping
	public List<Person> all() {
		return service.all();
	}
	
	@GetMapping("{id}")
	public Optional<Person> show(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Person save(@RequestBody Person person) {
		return service.save(person);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Person update(@PathVariable Long id, @RequestBody Person person) {
		Optional<Person> op = service.findById(id);
		
		if (!op.isEmpty()) {
			Person personsUpdate = op.get();
            BeanUtils.copyProperties(person, personsUpdate, "id");
            return service.save(personsUpdate);
		}
		
		return person;
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
