package SpringBoot311.controller;

import SpringBoot311.model.Role;
import SpringBoot311.model.User;
import SpringBoot311.service.RoleService;
import SpringBoot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "")
    public String getUsers(Principal principal, Model model) {
        User userLogged = userService.findByName(principal.getName());
        model.addAttribute("user_logged", userLogged);
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        User empty = new User();
        model.addAttribute("empty_user", empty);
        Collection<Role> roleList = roleService.findAll();
        model.addAttribute("list_roles", roleList);
        return "admin-panel";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("empty_user") User user, @RequestParam(value = "role") String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userService.addRoll(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(role);
        user.setRoles(userService.addRoll(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
