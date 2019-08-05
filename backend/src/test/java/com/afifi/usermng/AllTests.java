package com.afifi.usermng;

import com.afifi.usermng.app.AppSmokeTest;
import com.afifi.usermng.controller.UserControllerMockMvcTest;
import com.afifi.usermng.controller.UserControllerRestTmplTest;
import com.afifi.usermng.service.UserServiceMockitoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AppSmokeTest.class,
        UserControllerRestTmplTest.class,
        UserControllerMockMvcTest.class,
        UserServiceMockitoTest.class
})
public class AllTests {
}
