package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Models.dto.RoleDTO;
import com.example.demo.services.RoleService;

@SpringBootTest
class DemoApplicationTests {
    private final RoleService roleService;

    @Autowired
    public DemoApplicationTests(RoleService roleService) {
        this.roleService = roleService;
    }
 
	@Test
	void contextLoads() {
		RoleDTO roledto = new RoleDTO();
		roledto.setName("admin");

		boolean result = roleService.save(roledto);
		
		assertEquals(true, result);
	}

}
