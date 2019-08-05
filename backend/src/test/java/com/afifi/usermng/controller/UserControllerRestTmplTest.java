package com.afifi.usermng.controller;

import com.afifi.usermng.BackendApplication;
import com.afifi.usermng.model.User;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerRestTmplTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/usermanagement/api";
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void test11CreateUser() {
        User newUser = new User("Mr", "Mehdi", "Afifi", "username", "password",
                "a@b.com", "09128971245", Boolean.TRUE);

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", newUser, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertThat(postResponse.getBody().getId(), is(equalTo(1L)));
        assertThat(postResponse.getBody().getFirstName(), is(equalTo("Mehdi")));
        assertThat(postResponse.getBody().getLastName(), is(equalTo("Afifi")));
    }

    @Test
    public void test12GetUserById() {
        int id = 1;
        ResponseEntity<User> response = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody().getId(), is(equalTo(1L)));
        assertThat(response.getBody().getId(), not(equalTo(2L)));
        assertThat(response.getBody().getFirstName(), is(equalTo("Mehdi")));
        assertThat(response.getBody().getLastName(), is(equalTo("Afifi")));
    }

    @Test
    public void test13GetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List> response = restTemplate.exchange(getRootUrl() + "/users/", HttpMethod.GET, entity, List.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody().size(), is(equalTo(1)));
        assertThat(((Map) response.getBody().get(0)).get("id"), is(equalTo(1)));
        assertThat(((Map) response.getBody().get(0)).get("firstName"), is(equalTo("Mehdi")));
        assertThat(((Map) response.getBody().get(0)).get("lastName"), is(equalTo("Afifi")));
    }

    @Test
    public void test14UpdateUser() {
        int id = 1;
        ResponseEntity<User> response = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody().getId(), is(equalTo(1L)));

        User userDetails = response.getBody();
        userDetails.setFirstName("Mehdi-2");
        userDetails.setLastName("Afifi-2");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(userDetails, headers);
        ResponseEntity<User> updateResponse = restTemplate.exchange(getRootUrl() + "/users/" + id, HttpMethod.PUT, entity, User.class);

        assertNotNull(updateResponse);
        assertNotNull(updateResponse.getBody());
        assertThat(updateResponse.getBody().getId(), is(equalTo(1L)));
        assertThat(updateResponse.getBody().getFirstName(), is(equalTo("Mehdi-2")));
        assertThat(updateResponse.getBody().getLastName(), is(equalTo("Afifi-2")));

        ResponseEntity<User> response4UpdatedUser = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(response4UpdatedUser);
        assertNotNull(response4UpdatedUser.getBody());

        assertThat(response4UpdatedUser.getBody(), samePropertyValuesAs(updateResponse.getBody()));
        assertThat(response4UpdatedUser.getBody().getFirstName(), is(equalTo("Mehdi-2")));
        assertThat(response4UpdatedUser.getBody().getLastName(), is(equalTo("Afifi-2")));
    }

    @Test
    public void test15DeleteUser() {
        int id = 1;
        ResponseEntity<User> response = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody().getId(), is(equalTo(1L)));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, Boolean>> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Map> deleteResponse = restTemplate.exchange(getRootUrl() + "/users/" + id, HttpMethod.DELETE, entity, Map.class);

        assertNotNull(deleteResponse);
        assertNotNull(deleteResponse.getBody());
        assertThat(deleteResponse.getBody().get("deleted"), is(equalTo(Boolean.TRUE)));
        assertThat(deleteResponse.getBody().get("deleted"), not(equalTo(Boolean.FALSE)));
    }

    /************************************************************************************
     *
     * Exceptional Paths
     *
     */

    @Test(expected = RestClientException.class)
    public void test21CreateUser_throwsRestClientException() {
        User newUser = new User("Mrrrrrrrrr", "mehdi", "Affffffffffffiiiiiiiiiiiiiifffffffffffiiiiiiiiiiiiiiiii",
                "username", "password", "a%b.com", "0912897124599999", Boolean.TRUE);

        restTemplate.postForEntity(getRootUrl() + "/users", newUser, User.class);
        TestCase.fail();
    }

    @Test
    public void test22GetUserById_returnStatusCode404() {
        int id = 100;
        ResponseEntity<User> response = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);

        assertNotNull(response);
        assertThat(response.getStatusCodeValue(), is(404));
    }

    @Test
    public void test23GetAllUsers_returnEmotyList() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List> response = restTemplate.exchange(getRootUrl() + "/users/", HttpMethod.GET, entity, List.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody().size(), is(equalTo(0)));
    }

    @Test
    public void test24UpdateUser_returnHttpStatus404() {
        int id = 20;
        User user = new User(20L, "Mr", "Mehdi", "Afifi", "username1",
                "password1", "a1@gmail.com", "09128971241", Boolean.TRUE);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<User> putResponse = restTemplate.exchange(getRootUrl() + "/users/" + id, HttpMethod.PUT, entity, User.class);

        assertNotNull(putResponse);
        assertThat(putResponse.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
    }

    @Test
    public void test25DeleteUser_returnUserNotFound() {
        int id = 10;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, Boolean>> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Map> response = restTemplate.exchange(getRootUrl() + "/users/" + id, HttpMethod.DELETE, entity, Map.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getBody().get("message"), is(equalTo("User not found for this id :: 10")));
    }

}
