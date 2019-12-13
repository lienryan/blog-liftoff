package launchcode.org.blogliftoff;

import launchcode.org.blogliftoff.models.User;
import launchcode.org.blogliftoff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogLiftoffApplication  implements CommandLineRunner {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BlogLiftoffApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = new User("Admin","admin@gmail.com","123456");
		userService.createAdmin(admin);
	}
}


