package com.xlx.admin;

import com.xlx.system.entity.User;
import com.xlx.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShiroAdminApplicationTests {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void contextLoads() {
    
        User user = userService.getById(1L);
        System.out.println(user);
    }
    
}
