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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

        mvc.perform(post(CREATE_URER_ENDPOINT_URL)
                .content(asJsonString(newUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void test12GetUserById_OK() throws Exception {
        mvc.perform(get(GET_URER_ENDPOINT_URL, 1)
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
        this.mvc.perform(get(GET_URERS_ENDPOINT_URL)
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

        mvc.perform(put(UPDATE_URER_ENDPOINT_URL, 1)
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
                .andDo(print())
                .andExpect(status().isOk());
    }

    /************************************************************************************
     *
     * Exceptional Paths
     *
     */

    /*
             Body = [
                 {
                     "timestamp":"2019-08-08T06:28:59.513+0000",
                     "message":"Cell Phone must be null or have atleast 11 characters",
                     "details":"cellPhone"
                 },
                 {
                     "timestamp":"2019-08-08T06:28:59.513+0000",
                     "message":"Title should have atleast 2 and characters",
                     "details":"title"
                 },
                 {
                     "timestamp":"2019-08-08T06:28:59.513+0000",
                     "message":"Email has invalid format",
                     "details":"email"
                 }
             ]
     */
    @Test
    public void test21CreateUser_BadRequest() throws Exception {
        User newUser = new User(null, "Mrrrrrrrrrr", "Mehdi", "Afifi", "username",
                "password", "mehdi%gmail.com", "091289712455555555", Boolean.TRUE);

        mvc.perform(post(CREATE_URER_ENDPOINT_URL)
                .content(asJsonString(newUser))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())

                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))

                .andExpect(jsonPath("$.[*].timestamp", is(notNullValue())))

                .andExpect(jsonPath("$.[*].details", hasItem("title")))
                .andExpect(jsonPath("$.[*].details", hasItem("email")))
                .andExpect(jsonPath("$.[*].details", hasItem("cellPhone")))

                .andExpect(jsonPath("$.[*].message", hasItem("Title should have atleast 2 and characters")))
                .andExpect(jsonPath("$.[*].message", hasItem("Email has invalid format")))
                .andExpect(jsonPath("$.[*].message", hasItem("Cell Phone must be null or have atleast 11 characters")));
    }

    @Test
    public void test22GetUserById_NotFound() throws Exception {
        mvc.perform(get(GET_URER_ENDPOINT_URL, 5)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.details", is("uri=/usermanagement/api/users/5")))
                .andExpect(jsonPath("$.message").value("User not found for this id :: 5"));
    }

    @Test
    public void test23GetAllUsers_IsEmpty() throws Exception {
        this.mvc.perform(get(GET_URERS_ENDPOINT_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    /*
         Body = [
             {
                 "timestamp":"2019-08-08T06:11:35.381+0000",
                 "message":"Cell Phone must be null or have atleast 11 characters",
                 "details":"cellPhone"
             },
             {
                 "timestamp":"2019-08-08T06:11:35.381+0000",
                 "message":"Title should have atleast 2 and characters",
                 "details":"title"
             }
         ]
     */
    @Test
    public void test24UpdateUser_BadRequest() throws Exception {
        User user4Update = new User(1L, "Misssssss", "Nahid", "Shahriyari", "n_s_user",
                "password", "nahid_shah@gmail.com", "09128971245111111", Boolean.TRUE);

        mvc.perform(put(UPDATE_URER_ENDPOINT_URL, 1)
                .content(asJsonString(user4Update))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$.[*].timestamp", is(notNullValue())))

                .andExpect(jsonPath("$.[*].details", hasItem("title")))
                .andExpect(jsonPath("$.[*].details", hasItem("cellPhone")))

                .andExpect(jsonPath("$.[*].message", hasItem("Title should have atleast 2 and characters")))
                .andExpect(jsonPath("$.[*].message", hasItem("Cell Phone must be null or have atleast 11 characters")));
    }

    @Test
    public void test25DeleteUser_NotFound() throws Exception {
        mvc.perform(delete(DELETE_URER_ENDPOINT_URL, 10)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.details", is("uri=/usermanagement/api/users/10")))
                .andExpect(jsonPath("$.message", is("User not found for this id :: 10")));
    }
}
