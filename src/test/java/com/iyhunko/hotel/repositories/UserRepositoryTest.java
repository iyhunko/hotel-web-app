package com.iyhunko.hotel.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.iyhunko.hotel.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser()
    {
        User user = new User();
        user.setEmail("iva@gmail.com");
        user.setPassword("test123");
        user.setFirstname("Ivan");
        user.setLastname("Hunko");

        User savedUser = repo.save(user);

        User existingUser = entityManager.find(User.class, savedUser.getId());


        assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail()
    {
        String email = "iva@gmail.com";

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
