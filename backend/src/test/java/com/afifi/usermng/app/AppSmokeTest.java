package com.afifi.usermng.app;

import com.afifi.usermng.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppSmokeTest {

    @Autowired
    private UserController controller;

    @Test
    public void contexLoads() {
        assertThat(controller).isNotNull();
    }

}
