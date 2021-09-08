package eu.opensource.ordermanagement.service.impl;

import eu.opensource.ordermanagement.domain.Role;
import eu.opensource.ordermanagement.repository.RoleRepository;
import eu.opensource.ordermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {

        return roleRepository.findByName(name);
    }
}
