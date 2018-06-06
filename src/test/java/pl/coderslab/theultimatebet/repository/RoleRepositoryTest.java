package pl.coderslab.theultimatebet.repository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.theultimatebet.entity.Role;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldReturnNullWhenDatabaseEmpty() {
        assertNull(roleRepository.findByName("USER"));
    }

    @Test
    public void shouldReturnRoleWhenFound() {

        // given
        Role role = new Role();
        role.setName("USER");

        entityManager.persist(role);

        // when
        Role actual = roleRepository.findByName("USER");

        // then
        assertNotNull(actual);
        assertEquals("USER", actual.getName());
        assertThat(actual.getId(), Matchers.greaterThan(0));
    }



}