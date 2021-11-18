package com.pe.proyectotechnologico.Controller;


import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        User loggedUser = service.userExists(user);
        if(loggedUser == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(loggedUser.getTeacher(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity getAllActiveUsers(HttpServletRequest request){
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<User> users = service.findByStatus(true);
        if(users == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/users/inactive")
    public ResponseEntity getAllInactiveUsers(HttpServletRequest request){
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<User> users = service.findByStatus(false);
        if(users == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity getUser(HttpServletRequest request) {
        User user = service.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(user.getTeacher(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(HttpServletRequest request,
                               @RequestBody User user) {
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        //Username tiene que ser unico
        if (service.usernameExists(user)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        service.create(user);
        if(service.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(HttpServletRequest request,
                               @RequestBody User user) {
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(service.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        service.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity removeUser(HttpServletRequest request,
                                     @PathVariable Integer id) {
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(service.findById(id) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/user/restore")
    public ResponseEntity restoreUser(HttpServletRequest request,
                                      @RequestBody User user) {
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(service.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        service.restoreUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
