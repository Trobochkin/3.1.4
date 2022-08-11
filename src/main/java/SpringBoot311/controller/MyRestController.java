package SpringBoot311.controller;

import SpringBoot311.model.User;
import SpringBoot311.service.RoleService;
import SpringBoot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    private final String DELETE = "ПЧЕЛ УДАЛЕН";

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyRestController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/users")
    public User newUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return user;
    }

    @PutMapping(value = "/users")
    public User updateUser(@RequestBody User user) {
        if (user.getPassword() != null && user.getPassword().length() < 15) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return DELETE;
    }
}
