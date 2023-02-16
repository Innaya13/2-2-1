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

      User user1 = new User ("Анатолий", "Егоров", "egorov@yandex.ru");
      User user2 = new User ("Александр", "Лазицкий", "lasitski@yandex.ru");
      User user3 = new User ("Петр", "Хмельницкий", "hmelnitsky@yandex.ru");
      User user4 = new User ("Марк", "Попов", "popov@yandex.ru");

      Car car1 = new Car ("Запорожец", 966);
      Car car2 = new Car ("Волга", 311055);
      Car car3 = new Car ("Volkswagen", 1992);
      Car car4 = new Car ("Ford", 30290);

      userService.add(user1.setCar(car1).getUser());
      userService.add(user2.setCar(car2).getUser());
      userService.add(user3.setCar(car3).getUser());
      userService.add(user4.setCar(car4).getUser());

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }

      String mod = "Broom";
      int ser = 976459;

      try {
         User testUser = userService.getUserByCar(mod, ser);
         System.out.println(testUser);
      } catch (NoResultException e) {
         e.printStackTrace();
         System.out.println("У этой машины нет владельца");
      }

      context.close();
   }
}
