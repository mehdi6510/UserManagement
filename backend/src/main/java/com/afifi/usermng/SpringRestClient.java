package com.afifi.usermng;

import com.afifi.usermng.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpringRestClient {

    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users";
    private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users/{id}";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users";
    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users/{id}";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/usermanagement/api/users/{id}";

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();

        // Step1: first create a new user
        springRestClient.createUser();

        // Step 2: get new created user from step1
        springRestClient.getUserById();

        // Step3: get all users
        springRestClient.getUsers();

        // Step4: Update user with id = 1
        springRestClient.updateUser();

        // Step5: Delete user with id = 1
        springRestClient.deleteUser();
    }

    private void getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
                String.class);

        System.out.println(result);
    }

    private void getUserById() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");

        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, User.class, params);
        System.out.println(result);
    }

    private void createUser() {
        User newUser = new User("Ms", "Mehdi", "Afifi", "username", "password",
                "admin@gmail.com", "09128971245", Boolean.TRUE);

        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newUser, User.class);
        System.out.println(result);
    }

    private void updateUser() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        User updatedUser = new User("Miss", "Nahid", "Asghari", "username2", "password2",
                "admin2@gmail.com", "09133333333", Boolean.TRUE);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedUser, params);
    }

    private void deleteUser() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
    }
}
