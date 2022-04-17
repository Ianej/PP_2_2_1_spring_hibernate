package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);
      List<User> userList = new ArrayList<>();
      for(int i = 1, y = 0; i < 5; i++, y++) {
         userList.add(new User("User" + i, "Lastname" + i, "user" + i + "@mail.ru"));
         userList.get(y).setCar(new Car("auto" + i, i * 5));
         userService.add(userList.get(y));
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+(user.getCar()==null ? "нема машины" : user.getCar().getModel()));
         System.out.println(user.getCar()==null ? "" : "Series = "+(user.getCar().getSeries()));
         System.out.println();
      }
      System.out.println(userService.carOwner("auto1", 5).getFirstName());
      context.close();
   }
}
