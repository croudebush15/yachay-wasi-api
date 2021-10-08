package com.pe.proyectotechnologico.Controller;



import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

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

    @GetMapping("/user")
    public ResponseEntity user(HttpServletRequest request) {
        User user = getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/classes")
    public ResponseEntity getClasses(HttpServletRequest request){
        return new ResponseEntity(HttpStatus.OK);
    }

    public User getUserFromRequest(HttpServletRequest request){
        String authToken = request.getHeader("Authorization");
        String userString =  new String(Base64.getDecoder()
                .decode(authToken));

        User user = new User();
        user.setUsername(userString.split(":")[0]);
        user.setPassword(userString.split(":")[1]);

        return service.userExists(user);
    }
}
