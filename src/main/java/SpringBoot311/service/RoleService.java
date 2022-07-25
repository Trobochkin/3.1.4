package SpringBoot311.service;

import SpringBoot311.model.Role;
import SpringBoot311.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Collection<Role> findAll() { return roleRepository.findAll(); }

    public void addRole(String role) {
        roleRepository.save(new Role(role));
    }

    public void deleteById(long id) {
        roleRepository.deleteById(id);
    }
}
