package dz.univ.bechar.mda;

import java.util.Arrays;

import dz.univ.bechar.mda.entity.Membership;
import dz.univ.bechar.mda.entity.Role;
import dz.univ.bechar.mda.entity.User;
import dz.univ.bechar.mda.repository.MembershipRepository;
import dz.univ.bechar.mda.repository.RoleRepository;
import dz.univ.bechar.mda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	@Autowired
	private UserRepository repository;
	@Autowired
	private MembershipRepository membershipRepository;
	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		//run for first time
		// injectData();
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	public boolean injectData(){
		try {
			Role user= new Role("User");
			Membership freemember= new Membership("Free");
			roleRepository.save(user);
			membershipRepository.save(freemember);
			repository.save(new User("medbenzkeri","12345678","medbenzekri213@gmail.com",user));
			repository.save(new User("Jack", "y8KbckjS"));
			repository.save(new User("Chloe", "mQy4Hs6r"));
			repository.save(new User("Kim", "xn5Jf3F7"));
			repository.save(new User("David", "9cRNw7bd"));
			repository.save(new User("Michelle", "b9ZCVXJN"));
			return true;
		}catch (Exception e){
			System.out.println(e);

		}
		return false;
	}


}
