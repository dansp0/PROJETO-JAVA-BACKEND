package br.saasmania.economizae.identityaccess.presentation.controllers;

import br.saasmania.economizae.identityaccess.application.usecases.CreateTenantUseCase;
import br.saasmania.economizae.identityaccess.domain.models.Tenant;
import br.saasmania.economizae.identityaccess.presentation.dto.CreateTenantDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class TenantController {
    private final CreateTenantUseCase createTenantUseCase;

    public TenantController(CreateTenantUseCase createTenantUseCase) {
        this.createTenantUseCase = createTenantUseCase;
    }
 
    @PostMapping("/create-tenant")
    @ResponseStatus(HttpStatus.CREATED)
    public Tenant createTenant(@Valid @RequestBody CreateTenantDto dto) {
        return createTenantUseCase.execute(dto.name(), dto.plan());
    }
}