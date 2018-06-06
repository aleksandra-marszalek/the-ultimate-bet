package pl.coderslab.theultimatebet.repository;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.theultimatebet.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest  {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BetRepository betRepository;


    @Test
    public void findBetByIdShouldReturnNullWhenDatabaseEmpty() {
        assertNull(betRepository.findBetById(1L));
    }

    @Test
    public void findAllByUserIdOrderByCreatedDescShouldReturnNullWhenDatabaseEmpty() {
        assertEquals(Collections.EMPTY_LIST, betRepository.findAllByUserIdOrderByCreatedDesc(1L));
    }

    @Test
    public void findAllByResultOrderByCreatedDescShouldReturnNullWhenDatabaseEmpty() {
        assertEquals(Collections.EMPTY_LIST, betRepository.findAllByResultOrderByCreatedDesc("won"));
    }

    @Test
    public void findAllByUserIdAndResultOrderByCreatedDescShouldReturnNullWhenDatabaseEmpty() {
        assertEquals(Collections.EMPTY_LIST, betRepository.findAllByUserIdAndResultOrderByCreatedDesc(1L, "won"));
    }

}