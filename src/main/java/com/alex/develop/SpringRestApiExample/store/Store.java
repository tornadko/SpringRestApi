package com.alex.develop.SpringRestApiExample.store;


import com.alex.develop.SpringRestApiExample.dal.User;

import java.util.List;
import java.util.Optional;

public interface Store<Entity> {

    Optional<Long> create(User user);

    List<Entity> readAll();

    Optional<Entity> read(long id);

    Optional<Entity> update(Entity item);

    Optional<Entity> delete(long id);
}