package com.afifi.usermng.controller;

import com.afifi.usermng.BackendApplication;
import com.afifi.usermng.model.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void test1CreateUser() {
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
    public void test2GetUserById() {
        int id = 1;
        ResponseEntity<User> getResponse = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);

        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertThat(getResponse.getBody().getId(), is(equalTo(1L)));
        assertThat(getResponse.getBody().getId(), not(equalTo(2L)));
        assertThat(getResponse.getBody().getFirstName(), is(equalTo("Mehdi")));
        assertThat(getResponse.getBody().getLastName(), is(equalTo("Afifi")));
    }

    @Test
    public void test3GetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List> getResponse = restTemplate.exchange(getRootUrl() + "/users/", HttpMethod.GET, entity, List.class);

        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertThat(getResponse.getBody().size(), is(equalTo(1)));
        assertThat(((Map) getResponse.getBody().get(0)).get("id"), is(equalTo(1)));
        assertThat(((Map) getResponse.getBody().get(0)).get("firstName"), is(equalTo("Mehdi")));
        assertThat(((Map) getResponse.getBody().get(0)).get("lastName"), is(equalTo("Afifi")));
    }

    @Test
    public void test4UpdateUser() {
        int id = 1;
        ResponseEntity<User> getResponse = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertThat(getResponse.getBody().getId(), is(equalTo(1L)));

        User userDetails = getResponse.getBody();
        userDetails.setFirstName("Mehdi-2");
        userDetails.setLastName("Afifi-2");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(userDetails, headers);
        ResponseEntity<User> putResponse = restTemplate.exchange(getRootUrl() + "/users/" + id, HttpMethod.PUT, entity, User.class);

        assertNotNull(putResponse);
        assertNotNull(putResponse.getBody());
        assertThat(putResponse.getBody().getId(), is(equalTo(1L)));
        assertThat(putResponse.getBody().getFirstName(), is(equalTo("Mehdi-2")));
        assertThat(putResponse.getBody().getLastName(), is(equalTo("Afifi-2")));

        ResponseEntity<User> getResponse4UpdatedUser = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(getResponse4UpdatedUser);
        assertNotNull(getResponse4UpdatedUser.getBody());

        assertThat(getResponse4UpdatedUser.getBody(), samePropertyValuesAs(putResponse.getBody()));
        assertThat(getResponse4UpdatedUser.getBody().getFirstName(), is(equalTo("Mehdi-2")));
        assertThat(getResponse4UpdatedUser.getBody().getLastName(), is(equalTo("Afifi-2")));
    }

    @Test
    public void test5DeleteUser() {
        int id = 1;
        ResponseEntity<User> getResponse = restTemplate.getForEntity(getRootUrl() + "/users/" + id, User.class);
        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertThat(getResponse.getBody().getId(), is(equalTo(1L)));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, Boolean>> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Map> deleteResponse = restTemplate.exchange(getRootUrl() + "/users/" + id, HttpMethod.DELETE, entity, Map.class);

        assertNotNull(deleteResponse);
        assertNotNull(deleteResponse.getBody());
        assertThat(deleteResponse.getBody().get("deleted"), is(equalTo(Boolean.TRUE)));
        assertThat(deleteResponse.getBody().get("deleted"), not(equalTo(Boolean.FALSE)));
    }

}
