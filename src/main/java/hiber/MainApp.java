package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Анатолий", "Егоров", "egorov@yandex.ru", new Car("Запорожец", 966)));
      userService.add(new User("Александр", "Лазицкий", "lasitski@yandex.ru", new Car("Волга", 311055)));
      userService.add(new User("Петр", "Хмельницкий", "hmelnitsky@yandex.ru", new Car("Volkswagen", 1992)));
      userService.add(new User("Марк", "Попов", "popov@yandex.ru", new Car("Ford", 30290)));

  //    User user1 = new User ("Анатолий", "Егоров", "egorov@yandex.ru");
  //    User user2 = new User ("Александр", "Лазицкий", "lasitski@yandex.ru");
  //    User user3 = new User ("Петр", "Хмельницкий", "hmelnitsky@yandex.ru");
  //    User user4 = new User ("Марк", "Попов", "popov@yandex.ru");
  //
  //    Car car1 = new Car ("Запорожец", 966);
  //    Car car2 = new Car ("Волга", 311055);
  //    Car car3 = new Car ("Volkswagen", 1992);
  //    Car car4 = new Car ("Ford", 30290);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Волга", 311055));
      context.close();
   }
}
