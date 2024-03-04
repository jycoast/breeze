package com.breeze.user.web.controller;


import com.breeze.user.web.entity.User;
import com.breeze.user.web.service.UserService;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GET
    @Path("/getUserById")
    private User getUserById(Long id) {
        return userService.getUserById(id);
    }
}
