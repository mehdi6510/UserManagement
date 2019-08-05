package com.afifi.usermng.controller;

import com.afifi.usermng.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerMockMvcTest {

    private static final String GET_URERS_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users";
    private static final String GET_URER_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users/{id}";
    private static final String CREATE_URER_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users";
    private static final String UPDATE_URER_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users/{id}";
    private static final String DELETE_URER_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users/{id}";

    @Autowired
    private MockMvc mvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test11CreateUser_OK() throws Exception {
        User newUser = new User(null, "Mr", "Mehdi", "Afifi", "username",
                "password", "a@b.com", "09128971245", Boolean.TRUE, "user", new Date(),
                "user", new Date());

        mvc.perform(MockMvcRequestBuilders
                .post(CREATE_URER_ENDPOINT_URL)
                .content(asJsonString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void test12GetUserById_OK() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(GET_URER_ENDPOINT_URL, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mehdi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Afifi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"));
    }

    @Test
    public void test13GetAllUsers_OK() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get(GET_URERS_ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    public void test14UpdateUser_OK() throws Exception {
        User user4Update = new User(1L, "Mr", "Mehdi-2", "Afifi-2", "username-2",
                "password", "a@b.com", "09128971245", Boolean.TRUE,
                "user", new Date(), "user", new Date());

        mvc.perform(MockMvcRequestBuilders
                .put(UPDATE_URER_ENDPOINT_URL, 1)
                .content(asJsonString(user4Update))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mehdi-2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Afifi-2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username-2"));
    }

    @Test
    public void test15DeleteUser_OK() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete(DELETE_URER_ENDPOINT_URL, 1))
                .andExpect(status().isOk());
    }

    //@MockBean
//    private UserRepository mockRepository;
//    @Before
//    public void init() {
//        User user = new User(null, "Ms", "Nahid", "Safari", "nahid_user",
//                "nahid_pass", "nahid@yahoo.com", "09128971245", Boolean.TRUE,
//                "user", new Date(), "user", new Date());
//
//        when(mockRepository.findById(10L)).thenReturn(Optional.of(user));
//    }

//    @Test
//    public void test21CreateUser() throws Exception {
//        User newUser = new User(null, "Mrrrrrrrrrr", "Mehdi", "Afifi", "username",
//                "password", "mehdi%gmail.com", "091289712455555555", Boolean.TRUE, "user",
//                new Date(), "user", new Date());
//
//        mvc.perform(MockMvcRequestBuilders
//                .post(CREATE_URER_ENDPOINT_URL)
//                .content(asJsonString(newUser))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//    }
//
//    @Test
//    public void test22GetUserById() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get(GET_URER_ENDPOINT_URL, 1)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mehdi"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Afifi"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"));
//    }
//
//    @Test
//    public void test23GetAllUsers() throws Exception {
//        this.mvc.perform(MockMvcRequestBuilders
//                .get(GET_URERS_ENDPOINT_URL)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
//    }
//
//    @Test
//    public void test24UpdateUser() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .put(UPDATE_URER_ENDPOINT_URL, 1)
//                .content(asJsonString(new User(1L, "Mr", "Mehdi-2", "Afifi-2", "username-2",
//                        "password", "a@b.com", "09128971245", Boolean.TRUE,
//                        "user", new Date(), "user", new Date())))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mehdi-2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Afifi-2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username-2"));
//    }
//
//    @Test
//    public void test25DeleteUser() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .delete(DELETE_URER_ENDPOINT_URL, 1))
//                .andExpect(status().isOk());
//    }
}
