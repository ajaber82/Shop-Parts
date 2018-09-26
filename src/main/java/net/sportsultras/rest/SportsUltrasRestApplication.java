package net.sportsultras.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import net.sportsultras.rest.model.User;
import net.sportsultras.rest.model.UserRoles;
import net.sportsultras.rest.repo.UserRepo;

@SpringBootApplication
public class SportsUltrasRestApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(SportsUltrasRestApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		 ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	     Locale.setDefault(Locale.US);
	     messageSource.setBasename("classpath:i18n/messages");
	     messageSource.setCacheSeconds(3600); // Refresh cache once per hour.
	     return messageSource;
	}

	public void run(String... args) throws Exception {
		this.userRepo.deleteAll();
		List<UserRoles> roles = new ArrayList<UserRoles>();
		roles.add(new UserRoles("ROLE_ADMIN"));
		User u = new User("Alaa", "Abuzaghleh", "alaa.abuzaghleh@gmail.com", passwordEncoder().encode("12345678"),
				roles);
		this.userRepo.save(u);

		Optional<User> user = this.userRepo.findById(u.getId());
		if (user.isPresent()) {
			System.out.println(user.get().toString());
		}
	}
}
