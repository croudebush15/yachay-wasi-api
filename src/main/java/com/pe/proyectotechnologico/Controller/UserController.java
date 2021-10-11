package com.pe.proyectotechnologico.Controller;


import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.TeacherService;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
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
    public ResponseEntity getAll(HttpServletRequest request){
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<User> users = service.findAll();
        if(users == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity getUser(HttpServletRequest request) {
        User user = service.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(HttpServletRequest request,
                               @RequestBody User user) {
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        //Username tiene que ser unico
        if (service.usernameExists(user)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        service.create(user);
        if(service.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(HttpServletRequest request,
                               @RequestBody User user) {
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        service.update(user);
        if(service.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity removeUser(HttpServletRequest request,
                                     @RequestBody User user) {
        //TODO: Desactivar user en vez de eliminar
        if (!service.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(service.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        service.delete(user.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/classes")
    public ResponseEntity getClasses(HttpServletRequest request){
        //TODO: Listar clases
        return new ResponseEntity(HttpStatus.OK);
    }


}
