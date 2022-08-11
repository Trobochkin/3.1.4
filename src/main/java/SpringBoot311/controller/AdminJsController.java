package SpringBoot311.controller;

import SpringBoot311.model.Role;
import SpringBoot311.model.User;
import SpringBoot311.service.RoleService;
import SpringBoot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

@RequestMapping("/admin")
@Controller
public class AdminJsController {

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminJsController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String testPage(Model model, Principal principal){
        User userLogged = userService.findByName(principal.getName());
        model.addAttribute("user_logged", userLogged);
        Collection<Role> roleList = roleService.findAll();
        model.addAttribute("list_roles", roleList);
        return "AdminJs";
    }
}

