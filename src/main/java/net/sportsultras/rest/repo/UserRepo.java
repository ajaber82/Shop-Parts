package net.sportsultras.rest.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.sportsultras.rest.model.User;

public interface UserRepo extends MongoRepository<User, String> {
	
	public Optional<User> findByEmail(String email) ; 

}
