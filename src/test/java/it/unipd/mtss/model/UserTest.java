////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class UserTest {
    private User user;

    @Before
    public void setup() {
        user = new User("CF", "Mario", "Rossi",
                "mario.rossi@email.com", LocalDate.of(2000,1,1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullCfTest() {
        new User(null,"Mario","Rossi",
                "mario.rossi@email.com", LocalDate.of(2000,1,1));

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullNameTest() {
        new User("CF",null,"Rossi",
                "mario.rossi@email.com", LocalDate.of(2000,1,1));

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullSurnameTest() {
        new User("CF","Mario",null,
                "mario.rossi@email.com", LocalDate.of(2000,1,1));

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullMailTest() {
        new User("CF","Mario","Rossi",
                null, LocalDate.of(2000,1,1));

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithInvalidBirthdayTest() {
        new User("CF","Mario","Rossi",
                "mario.rossi@email.com", LocalDate.now().plusDays(1));

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullBirthdayTest() {
        new User("CF","Mario","Rossi",
                "mario.rossi@email.com", null);

        fail();
    }
}
