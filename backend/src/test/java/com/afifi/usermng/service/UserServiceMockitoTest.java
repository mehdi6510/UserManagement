package com.afifi.usermng.service;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;
import com.afifi.usermng.model.mapper.Mapper;
import com.afifi.usermng.repository.UserRepository;
import com.afifi.usermng.service.impl.UserServiceImpl;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceMockitoTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private Mapper mapper = new Mapper();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository, mapper);

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @BeforeEach
    void setMockOutput() {
        long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(new User("Mr", "Mehdi", "Afifi",
                "username", "password", "a@b.com", "09128971245", Boolean.TRUE)));
    }

    @Test
    public void test1GetUserById() {
        try {
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Mr", "Mehdi", "Afifi",
                    "username", "password", "a@b.com", "09128971245", Boolean.TRUE)));

            User user = userService.getUserById(1L);

            assertNotNull(user);
            assertThat(user.getTitle(), is(equalTo("Mr")));
        } catch (Exception e) {
            logger.error("User not found.", e);
            TestCase.fail();
        }
    }

    @Test
    public void test2GetAllUsers() {
        List<User> list = new ArrayList<>();
        User userOne = new User(1L, "Mr", "Mehdi", "Afifi", "username1",
                "password1", "a1@gmail.com", "09128971241", Boolean.TRUE);
        User userTwo = new User(2L, "Mrs", "Mahshid", "Afifi", "username2",
                "password2", "a2@gmail.com", "09128971242", Boolean.FALSE);
        User userThree = new User(3L, "Miss", "Mitra", "Afifi", "username3",
                "password3", "a3@gmail.com", "09128971243", Boolean.FALSE);
        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);
        Mockito.when(userRepository.findAll()).thenReturn(list);

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertThat(users.size(), is(equalTo(3)));
    }

    @Test
    public void test3CreateUser() {
        Mockito.when(userRepository.save(new User())).thenReturn(new User("Mr", "Ali", "Afifi", "username", "password",
                "a@b.com", "09128971245", Boolean.TRUE));

        User user = new User("Mr", "Ali", "Afifi", "username", "password",
                "ali@gmail.com", "09128971245", Boolean.TRUE);

        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertThat(savedUser.getFirstName(), is(equalTo("Ali")));
    }

    @Test
    public void test4UpdateUser() {
        try {
            User oldUser = new User(1L, "Mr", "Mehdi", "Afifi", "username",
                    "password", "a@b.com", "09128971245", Boolean.TRUE, "user", new Date(),
                    "user", new Date());
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(oldUser));

            User newDetailUser = new User(1L, "Mr", "Ali-2", "Afifi-2", "username2",
                    "password2", "ali2@gmail.com", "09128971242", Boolean.TRUE,
                    "user", new Date(), "user", new Date());
            Mockito.when(userRepository.save(oldUser)).thenReturn(newDetailUser);


            User updatedUser = userService.updateUser(1L, newDetailUser);
            assertNotNull(updatedUser);
            assertThat(updatedUser, samePropertyValuesAs(newDetailUser));
        } catch (ResourceNotFoundException ex) {
            TestCase.fail();
        }
    }

    @Test
    public void test5DeleteUser() {
        try {
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Mr", "Mehdi", "Afifi",
                    "username", "password", "a@b.com", "09128971245", Boolean.TRUE)));

            userService.deleteUser(1L);
        } catch (ResourceNotFoundException ex) {
            TestCase.fail();
        }
    }

//    @Test(expected = ResourceNotFoundException.class)
//    public void test99GetExpectedException() throws ResourceNotFoundException {
//        try {
//            throw new ResourceNotFoundException("Fake Throws");
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw e;
//        }
//    }

}
