package com.portfolio.portfolio_backend.repository;

import java.util.Optional;

import com.portfolio.portfolio_backend.model.User;

public interface IUserRepository {
    Optional<User> findByUsername(String username);
}
