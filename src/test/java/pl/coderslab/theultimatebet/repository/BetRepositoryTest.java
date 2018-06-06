package pl.coderslab.theultimatebet.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest  {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BetRepository betRepository;


    @Test
    public void findBetById() {
    }

    @Test
    public void findAllByUserIdOrderByCreatedDesc() {
    }

    @Test
    public void findAllByResultOrderByCreatedDesc() {
    }

    @Test
    public void findAllByUserIdAndResultOrderByCreatedDesc() {
    }
}