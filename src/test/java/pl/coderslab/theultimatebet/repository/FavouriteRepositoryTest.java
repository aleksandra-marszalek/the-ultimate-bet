package pl.coderslab.theultimatebet.repository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.theultimatebet.entity.Favourite;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FavouriteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Test
    public void shouldFindFavouriteByUser() {

        // given
        User user = new User();
        user.setUsername("Jan");
        user.setPassword("jan123");
        user.setFirstname("jan");
        user.setLastname("kowalski");
        user.setEmail("jan123@jan.pl");

        entityManager.persist(user);


        Favourite favourite = new Favourite();
        favourite.setUser(user);

        user.setFavourite(favourite);


        entityManager.persist(favourite);



        //when

        Favourite actual = favouriteRepository.findFavouriteByUser(user);



        //then
        assertNotNull(actual);
        assertEquals(favourite, actual);
        assertThat(actual.getId(), Matchers.greaterThan(0L));



    }

    @Test
    public void shouldReturnNullWhenDatabaseEmpty() {
        User user = new User();
        user.setUsername("Jan");
        user.setPassword("jan123");
        user.setFirstname("jan");
        user.setLastname("kowalski");
        user.setEmail("jan123@jan.pl");


        entityManager.persist(user);

        assertNull(favouriteRepository.findFavouriteByUser(user));
    }
}