package com.teamz.recipe.repository;

import com.teamz.recipe.domain.User;

import java.util.Optional;

public interface UserRepository {
    User register(User user);
    Optional<User> findById(String id);
    Optional<User> findByNickName(String nickname);


}
