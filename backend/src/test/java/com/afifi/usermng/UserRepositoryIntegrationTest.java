package com.afifi.usermng;

import com.afifi.usermng.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void whenFindById_thenReturnUser() {
//        User user = new User();
//        entityManager.persist(user);
//        entityManager.flush();
//
//        // when
//        User found = userRepository.findById(user.getId()).get();
//
//        // then
//        assertThat(found.getFirstName())
//                .isEqualTo(user.getFirstName());
//    }

}
