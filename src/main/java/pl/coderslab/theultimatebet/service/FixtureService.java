package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Role;
import pl.coderslab.theultimatebet.repository.RoleRepository;


/**
 * Service providing one method used in {@link  org.springframework.context.ConfigurableApplicationContext}
 * in {@link pl.coderslab.theultimatebet.TheUltimateBetApplication} class as the Application starts.
 */
@Service
public class FixtureService {

    @Autowired
    RoleRepository roleRepository;

    /**
     * Method used to create specific roles and add them to DB.
     */
    public void createRoles() {
        Role role = new Role();
        role.setName("user");
        Role role2 = new Role();
        role2.setName("admin");
        roleRepository.save(role);
        roleRepository.save(role2);
    }
}
