package com.pe.proyectotechnologico.Service;

import com.pe.proyectotechnologico.Model.User;
import com.pe.proyectotechnologico.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements CrudService<User,Integer> {

    final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public void update(User user) {
        User oldUser = findById(user.getId());
        user.setPassword(oldUser.getPassword());
        repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = repository.findById(id).orElse(null);
        if (user != null) user.getTeacher().setStatus(false);
        repository.save(user);
    }

    public void restoreUser(Integer id){
        User user = repository.findById(id).orElse(null);
        if (user != null) user.getTeacher().setStatus(true);
        repository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<User> users = repository.findAll();
        users.forEach(user -> {
            user.setPassword("");
        });

        return users;
    }

    public User userExists(User userLogin){
        User user = repository.findByUsername(userLogin.getUsername());
        if (user != null){
            if (Objects.equals(user.getPassword(), userLogin.getPassword()))
                return user;
            else return null;
        }
        else return null;
    }

    public Boolean usernameExists(User user){
        if (repository.findByUsername(user.getUsername()) != null) return true;
        else return false;
    }

    public User getUserFromRequest(HttpServletRequest request){
        String authToken = request.getHeader("Authorization");
        if (authToken == null) return null;
        String userString =  new String(Base64.getDecoder()
                .decode(authToken));
        User user = new User(userString.split(":")[0],userString.split(":")[1]);
        return userExists(user);
    }

    public Boolean isUserAdmin(HttpServletRequest request){
        User user = getUserFromRequest(request);
        if (user != null && user.getTeacher().getRole().equals("ADMIN")) return true;
        else return false;
    }

    public List<User> findByStatus(Boolean isActive){
        return repository.findAllByTeacher_Status(isActive);
    }
}
