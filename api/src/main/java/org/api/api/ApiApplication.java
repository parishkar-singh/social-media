package org.api.api;
import org.api.api.model.User;
// import org.api.api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
//     @Bean
//     CommandLineRunner run(UserService userService){
//         return args -> {
// //          userService.createRole(new Role(null,"ROLE_USER"));
// //          userService.createRole(new Role(null,"ROLE_ADMIN"));
// //          userService.createRole(new Role(null,"ROLE_OWNER"));
//         //   userService.createUser(new User(null,"parishkar singh","parishkar","parishkar.singh@esko.com","parishkar@@",new ArrayList<>()));
// //          userService.addRoleToUser("parishkar","ROLE_ADMIN");
//         };
//     }

}
