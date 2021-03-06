package pl.coderslab.theultimatebet.repository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WalletRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void shouldFindWalletByUser() {

        // given
        User user = new User();
        user.setUsername("Jan");
        user.setPassword("jan123");
        user.setFirstname("jan");
        user.setLastname("kowalski");
        user.setEmail("jan123@jan.pl");

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ONE);
        wallet.setUser(user);



        entityManager.persist(user);

        user.setWallet(wallet);

//        entityManager.persist(user);


        entityManager.persist(wallet);



        //when

        Wallet actual = walletRepository.findWalletByUser(user);



        //then
        assertNotNull(actual);
        assertEquals(wallet, actual);
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

        assertNull(walletRepository.findWalletByUser(user));
    }
}