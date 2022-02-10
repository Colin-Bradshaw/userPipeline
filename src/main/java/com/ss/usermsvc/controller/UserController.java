package com.ss.usermsvc.controller;


import com.ss.usermsvc.entity.User;
import com.ss.usermsvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    // read
    @RequestMapping(path = "/lms/user/{id}", method = RequestMethod.GET)
    public List<User> getAirportByIATA(@PathVariable String id){
        try {
            Integer.parseInt(id);
            return userService.getUserByID(Integer.parseInt(id));
        } catch (NumberFormatException nf){
            return userService.getUsers(id);
        }
    }
    // create
    @RequestMapping(path = "/lms/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User u){
        try {
            userService.addUser(u);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // update
    @RequestMapping(path = "/lms/user", method = RequestMethod.PATCH)
    public void updateUser(@RequestBody User u){
        try {
            userService.updateUser(u);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //delete
    @RequestMapping(path = "/lms/user", method = {RequestMethod.DELETE})
    public void deleteUser(@RequestBody User u){
        try {
            userService.deleteUser(u);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
