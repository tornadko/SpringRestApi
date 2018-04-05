package com.alex.develop.SpringRestApiExample;

import com.alex.develop.SpringRestApiExample.dal.User;
import com.alex.develop.SpringRestApiExample.exception.UserNotFoundException;
import com.alex.develop.SpringRestApiExample.grpc.GreetingClient;
import com.alex.develop.SpringRestApiExample.store.Store;
import com.alex.develop.SpringRestApiExample.store.UserInMemoryStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserApiController {

    private final Store<User> store = new UserInMemoryStore();

    @RequestMapping(value = "/api/user/greet", method = RequestMethod.POST)
    public ResponseEntity<?> greet(@RequestBody User user) {
        return GreetingClient
                .greet(user.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> users() {
        return store.readAll();
    }

    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET)
    public User userById(@PathVariable String userId) {
        return store
                .read(Long.valueOf(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String userId) {
        return store.delete(Long.valueOf(userId))
                .map(user -> ResponseEntity.noContent().build())
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @RequestMapping(path = "/api/users", method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody User input) {
        return store
                .create(input)
                .map(userId -> {
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .buildAndExpand(userId).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(path = "/api/users/{userId}", method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody User input) {
        return store
                .update(input)
                .map(user -> ResponseEntity.ok(input))
                .orElse(ResponseEntity.noContent().build());
    }
}
