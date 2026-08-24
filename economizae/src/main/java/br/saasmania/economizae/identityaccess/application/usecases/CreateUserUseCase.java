package br.saasmania.economizae.identityaccess.application.usecases;

import br.saasmania.economizae.identityaccess.domain.models.Person;
import br.saasmania.economizae.identityaccess.domain.models.Tenant;
import br.saasmania.economizae.identityaccess.domain.models.User;
import br.saasmania.economizae.identityaccess.domain.repositories.ITenantRepository;
import br.saasmania.economizae.identityaccess.domain.repositories.IUserRepository;
import br.saasmania.economizae.identityaccess.domain.valueobjects.ContactInformation;
import br.saasmania.economizae.identityaccess.domain.valueobjects.Name;
import br.saasmania.economizae.identityaccess.domain.valueobjects.TenantId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserUseCase {

    private final ITenantRepository tenantRepository;
    private final IUserRepository userRepository;

    public CreateUserUseCase(ITenantRepository tenantRepository, IUserRepository userRepository) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public User execute(String tenantId, String username, String email, String passwordHash) {
        Tenant tenant = tenantRepository.findById(TenantId.from(tenantId))
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        Person person = new Person(
                null,
                ContactInformation.from(email),
                Name.from(username)
        );

        User user = tenant.registerUser(username, passwordHash, person);

        return userRepository.create(user);
    }
}