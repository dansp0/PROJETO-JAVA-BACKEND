package br.saasmania.economizae.identityaccess.domain.repositories;

import br.saasmania.economizae.identityaccess.domain.models.User;
import br.saasmania.economizae.identityaccess.domain.valueobjects.UserId;

import java.util.Optional;

public interface IUserRepository {
    User create(User tenant);
    User save(User tenant);
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}