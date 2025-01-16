package com.example.Cinema.controller;

import com.example.Cinema.dto.MovieDto;
import com.example.Cinema.dto.UserDto;
import com.example.Cinema.mapper.UserMapper;
import com.example.Cinema.model.Movie;
import com.example.Cinema.model.RegistrationRequest;
import com.example.Cinema.model.User;
import com.example.Cinema.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "users";
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/users/create")
    public String renderCreateUserForm(Model model){
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());
        return "/users/create";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttribute){
        registrationRequest.setPassword("123");
        UserDto userDto = userService.registerUser(registrationRequest);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String renderDeleteUserForm(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Select User to delete");
        model.addAttribute("users", userDtos);
        return "/users/delete";
    }

    @PostMapping("/users/delete")
    @Transactional //if the delete fails for any reason, the changes can be rolled back
    public String deleteUsers(@RequestParam("selectedUsername") String selectedUsername) {
        if (selectedUsername != null && !selectedUsername.isEmpty()) {
            userService.deleteUserByUsername(selectedUsername);
        }
        return "redirect:/users";
    }

    @GetMapping("/users/update")
    public String renderUpdateUserForm(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Select User to delete");
        model.addAttribute("users", userDtos);
        model.addAttribute("user", new User());
        return "/users/update";
    }

    @PostMapping("/users/update")
    @Transactional
    public String updateUser(@RequestParam("selectedUsername") String selectedUsername, @ModelAttribute User user) {
        if (selectedUsername != null && !selectedUsername.isEmpty()) {
            User existingUser = userService.findUserEntityByUsername(selectedUsername);
            if (existingUser != null) {

                existingUser.setUsername(user.getUsername());
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setEmailAddress(user.getEmailAddress());

                userService.updateUser(existingUser);
            }
        }
        return "redirect:/users";
    }




}
