package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void create(User user) {
        repository.save(user);
    }

    public Boolean userExists(User userLogin){
        User user = repository.findByUsername(userLogin.getUsername());
        if (Objects.equals(user.getPassword(), userLogin.getPassword()))
            return true;
        else return false;
    }
}
