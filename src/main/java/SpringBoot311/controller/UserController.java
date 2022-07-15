package SpringBoot311.controller;

import SpringBoot311.model.User;
import SpringBoot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "/users";
    }

    @GetMapping(value = "/new")
    public String getNew(Model model) {
        model.addAttribute("empty_user", new User());
        return "/new";
    }

    @PostMapping(value = "/new_user")
    public String addUser(@ModelAttribute("empty_user") User user) {
        if (user.getName() == null || user.getAge() == 0) { return "redirect:/users"; }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String getChange(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("process_user", user);
        return "/update";
    }

    @PostMapping(value = "/update_execute")
    public String changeUser(@ModelAttribute("process_user") User user) {
        if (user.getName() == null || user.getAge() == 0) { return "redirect:/users"; }
        userService.saveUser(user);
        return "redirect:/users";
    }
}
