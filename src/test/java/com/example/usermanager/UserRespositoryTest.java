package com.example.usermanager;

import com.example.usermanager.entity.User;
import com.example.usermanager.respository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRespositoryTest {

        @Autowired
        private UserRepository repo;

        @Autowired
        private TestEntityManager entityManager;

        @Test
        public void testCreateUser(){
            User user = new User();
            user.setEmail(("yentthss140071@fpt.edu.vn"));
            user.setPassword("TranthihoangYen");
            user.setFirstName("Hoang");
            user.setLastName("Yen");

            User savedUser = repo.save(user);

            User existUer = entityManager.find(User.class, savedUser.getId());
            Assertions.assertThat(existUer.getEmail()).isEqualTo(user.getEmail());

        }

        @Test
    public void testFindUserByEmail(){
            String email ="Cunplong.1@gmail.com";

            User user = repo.findByEmail(email);
            Assertions.assertThat(user).isNotNull();

        }
}
