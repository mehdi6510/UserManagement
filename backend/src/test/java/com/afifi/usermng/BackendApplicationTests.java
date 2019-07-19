package com.afifi.usermng;

import com.afifi.usermng.model.User;
import com.afifi.usermng.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BackendApplicationTests {
//
//    private UserRepository userRepository;
//
//    public BackendApplicationTests(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        User user1 = new User("Alice", 23);
//        User user2 = new User("Bob", 38);
//        //save product, verify has ID value after save
//        assertNull(user1.getId());
//        assertNull(user2.getId());//null before save
//        this.userRepository.save(user1);
//        this.userRepository.save(user2);
//        assertNotNull(user1.getId());
//        assertNotNull(user2.getId());
//    }
//
//    @Test
//    public void testFetchData() {
//        /*Test data retrieval*/
//        User userA = userRepository.findByName("Bob");
//        assertNotNull(userA);
//        assertEquals(38, userA.getAge());
//        /*Get all products, list should only have two*/
//        Iterable<User> users = userRepository.findAll();
//        int count = 0;
//        for (User p : users) {
//            count++;
//        }
//        assertEquals(count, 2);
//    }
//
//    @Test
//    public void testDataUpdate() {
//        /*Test update*/
//        User userB = userRepository.findByName("Bob");
//        userB.setAge(40);
//        userRepository.save(userB);
//        User userC = userRepository.findByName("Bob");
//        assertNotNull(userC);
//        assertEquals(40, userC.getAge());
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        this.userRepository.deleteAll();
//    }
//
//}
