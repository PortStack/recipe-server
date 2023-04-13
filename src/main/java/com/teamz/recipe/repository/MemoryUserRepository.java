package com.teamz.recipe.repository;

import com.teamz.recipe.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository{

    private static Map<String, User> store = new HashMap<>();
    @Override
    public User register(User user) {
        store.put(user.getId(),user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByNickName(String nickname) {
        return store.values().stream()
                .filter(user -> user.getNickname().equals(nickname))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}
