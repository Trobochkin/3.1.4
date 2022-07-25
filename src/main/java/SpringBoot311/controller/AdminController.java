package SpringBoot311.controller;

import SpringBoot311.model.User;
import SpringBoot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUser(Principal principal, Model model){
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping(value = "/new")
    public String getNew(Model model) {
        model.addAttribute("empty_user", new User());
        return "new-user";
    }

    @PostMapping(value = "/new_user")
    public String addUser(@ModelAttribute("empty_user") User user) {
        if (user.getName() == null || user.getAge() == 0) { return "redirect:/admin/users"; }
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/make-admin/{id}")
    public String makeAdmin(@PathVariable("id") Long id) {
        userService.userAddRole(id, "ROLE_ADMIN");
        return "redirect:/admin/users";
    }

    @GetMapping("/make-user/{id}")
    public String makeUser(@PathVariable("id") Long id) {
        userService.userAddRole(id, "ROLE_USER");
        return "redirect:/admin/users";
    }

    @GetMapping("/update/{id}")
    public String getChange(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("process_user", user);
        return "update";
    }

    @PostMapping(value = "/update_execute")
    public String changeUser(@ModelAttribute("process_user") User user) {
        if (user.getName() == null || user.getAge() == 0) { return "redirect:/admin/users"; }
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
}
