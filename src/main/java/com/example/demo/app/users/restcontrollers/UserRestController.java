package com.example.demo.app.users.restcontrollers;


import com.example.demo.app.users.models.User;
import com.example.demo.app.users.services.UserService;
import com.example.demo.utils.Router;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Router.USERS)
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        try {
            return userService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody User user) {
        try {
            return userService.update(id, user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
