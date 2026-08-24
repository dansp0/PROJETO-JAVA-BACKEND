package br.saasmania.economizae.identityaccess.infrastructure.persistence.repositories;

import br.saasmania.economizae.identityaccess.domain.models.User;
import br.saasmania.economizae.identityaccess.domain.repositories.IUserRepository;
import br.saasmania.economizae.identityaccess.domain.valueobjects.UserId;
import br.saasmania.economizae.identityaccess.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final SpringDataUserRepository springDataRepository;

    public UserRepositoryImpl(SpringDataUserRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public User create(User user) {
        UserEntity entity = UserEntity.from(user);
        UserEntity saved = springDataRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public User save(User user) {
        UUID id = UUID.fromString(user.getId().value());
        UserEntity entity = springDataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        entity.setUsername(user.getUsername());
        entity.setName(user.getPerson().getName().value());
        if (user.getPerson().getContactInformation() != null) {
            entity.setEmail(user.getPerson().getContactInformation().email().address());
        }
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());
        entity.setSignMessages(user.isSignMessages());
        entity.setTokenVersion(user.getTokenVersion());
        entity.setSuper(user.isSuper());
        entity.setOnline(user.isOnline());
        entity.setWhatsappId(user.getWhatsappId());

        UserEntity saved = springDataRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<User> findById(UserId id) {
        return springDataRepository.findById(UUID.fromString(id.value()))
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByName(String name) {
        return springDataRepository.findByName(name)
                .map(UserEntity::toDomain);
    }
}