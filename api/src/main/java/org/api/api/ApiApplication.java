package org.api.api;
import org.api.api.model.User;
import org.api.api.service.UserService;
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
    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
//          userService.createRole(new Role(null,"ROLE_USER"));
//          userService.createRole(new Role(null,"ROLE_ADMIN"));
//          userService.createRole(new Role(null,"ROLE_OWNER"));
        //   userService.createUser(new User(null,"parishkar singh","parishkar","parishkar.singh@esko.com","parishkar@@",new ArrayList<>()));
//          userService.addRoleToUser("parishkar","ROLE_ADMIN");


            // userService.createUser(new User(null, "profile-pic", "Chinmay", "chin", "chinmay.bapat@esko.com", "chinmay@@", null, null, null));
            // userService.createUser(new User(null,  "profile-pic","Pallavi", "pall", "pallavi.bapat@esko.com", "pall@@", null, null, null));
            
            // userService.createUser(new User(null, "profile-pic", "indranil", "indra", "indra.bapat@esko.com", "indra@@", null, null, null));
            // userService.createUser(new User(null, "profile-pic", "suresh", "sure", "suresh.bapat@esko.com", "suresh@@", null, null, null));
            
            // User temp = userService.getUserById("660534c53725550e6253b47e");
            // temp.setUsername("SURESHBAPAT");
            // userService.updateUser("660534c53725550e6253b47e", temp);





        };
    }

}
