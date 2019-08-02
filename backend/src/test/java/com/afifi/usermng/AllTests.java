package com.afifi.usermng;

import com.afifi.usermng.app.AppSmokeTest;
import com.afifi.usermng.controller.UserControllerMockMVCTest;
import com.afifi.usermng.controller.UserControllerTest;
import com.afifi.usermng.service.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AppSmokeTest.class,
        UserControllerTest.class,
        UserControllerMockMVCTest.class,
        UserServiceTest.class
})
public class AllTests {
}
