package org.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.task.config.AppConfig;
import org.task.user.User;
import org.task.user.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println("Получение пользователей: " + userService.findAll());
        System.out.println("Получение пользователя: " + userService.findUser(2L));
        userService.create(new User(6L, "Пользователь"));
        userService.delete(6L);
    }
}
