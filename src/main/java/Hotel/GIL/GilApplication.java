package Hotel.GIL;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Hotel.GIL.Repositories.UserEntityRepository;
import Hotel.GIL.models.Role;
import Hotel.GIL.models.RoleName;
import Hotel.GIL.models.UserEntity;




import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class GilApplication {

	public static void main(String[] args) {
		SpringApplication.run(GilApplication.class, args);
	}
	
	//     @Bean
    // public CommandLineRunner start(UserEntityRepository userEntityRepository
    // , BCryptPasswordEncoder passwordEncoder){
    //     return args -> {  
    //         userEntityRepository.save(new UserEntity(null,"lotfi",passwordEncoder.encode("12345"), null,  null, 0, Arrays.asList(new Role(RoleName.ROLE_ADMIN))) );
    //         userEntityRepository.save(new UserEntity(null,"ali",passwordEncoder.encode("123"), null, "mail", 0, Arrays.asList(new Role(RoleName.ROLE_USER))) );
   
    //     };
    // }
}
    