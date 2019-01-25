package com.d2c.member.business.controller;

import com.d2c.member.business.model.User;
import com.d2c.member.business.service.UserService;
import com.d2c.member.elasticsearch.document.UserSearch;
import com.d2c.member.mongodb.document.UserMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "username", required = true) String username) {
        return userService.findByName(username);
    }

    @RequestMapping(value = "/api/user/cache", method = RequestMethod.GET)
    public User findCacheByName(@RequestParam(value = "username", required = true) String username) {
        return userService.findCacheByName(username);
    }

    @RequestMapping(value = "/api/user/mongo", method = RequestMethod.GET)
    public List<UserMongo> findMongoByName(@RequestParam(value = "username", required = true) String username) {
        return userService.findMongoByName(username);
    }

    @RequestMapping(value = "/api/user/search", method = RequestMethod.GET)
    public List<UserSearch> findSearchByName(@RequestParam(value = "username", required = true) String username) {
        return userService.findSearchByName(username);
    }

    @RequestMapping(value = "/api/user/update/{id}", method = RequestMethod.GET)
    public int updatePasswdById(@PathVariable(name = "id") Long id, @RequestParam(value = "password", required = true) String password) {
        return userService.updatePasswdById(id, password);
    }

}
