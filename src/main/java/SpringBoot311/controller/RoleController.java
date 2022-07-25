package SpringBoot311.controller;

import SpringBoot311.model.Role;
import SpringBoot311.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/roles")
@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String getRoles(Model model) {
        Collection<Role> roleList = roleService.findAll();
        model.addAttribute("roles", roleList);
        return "roles";
    }

    @GetMapping(value = "/new")
    public String getNew(Model model) {
        model.addAttribute("empty_role", new Role());
        return "new-roles";
    }

    @PostMapping(value = "/new_role")
    public String addRole(@ModelAttribute("empty_role") Role role) {
        if (role.getName() == null) { return "redirect:/roles/"; }
        roleService.addRole(role.getName());
        return "redirect:/roles/";
    }

    @GetMapping("/delete/{id}")
    public String removeRole(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return "redirect:/roles/";
    }
}
