package com.alex.develop.SpringRestApiExample.store;

import com.alex.develop.SpringRestApiExample.dal.User;

import java.util.*;

public class UserInMemoryStore implements Store<User> {

    private Map<Long, User> users = new LinkedHashMap<>();

    @Override
    public Optional<Long> create(User user) {
        users.put(user.getId(), user);
        return Optional.of(user.getId());
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> read(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(users.replace(user.getId(), user));
    }

    @Override
    public Optional<User> delete(long id) {
        return Optional.ofNullable(users.remove(id));
    }
}
