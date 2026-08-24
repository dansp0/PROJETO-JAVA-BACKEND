package br.saasmania.economizae.identityaccess.presentation.controllers;

import br.saasmania.economizae.identityaccess.application.usecases.CreateUserUseCase;
import br.saasmania.economizae.identityaccess.domain.models.User;
import br.saasmania.economizae.identityaccess.presentation.dto.CreateUserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody CreateUserDto dto) {
        return createUserUseCase.execute(
                dto.tenantId(),
                dto.username(),
                dto.email(),
                dto.passwordHash()
        );
    }

    @GetMapping("/read-user")
    public void readUser() {
        // TODO: implement
    }
}