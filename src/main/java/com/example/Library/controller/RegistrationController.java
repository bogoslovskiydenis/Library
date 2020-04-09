package com.example.Library.controller;

import com.example.Library.domain.User;
import com.example.Library.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public class RegistrationController {
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping ("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user , Map<String, Object> model){
       User userFromDb =  userRepository.findByUsername(user.getUsername());
       //проверка в базе на наличие пользователя
       if(userFromDb != null){
           model.put("message" , "User exist!");
           return "regostration";
       }
       user.setActive(true);
       //user.setRoles(Collections.singleton()); //создает сет с единственным значением
        userRepository.save(user);
        return "/redirect:loginpage";
    }

}
