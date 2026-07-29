package br.saasmania.economizae.transaction.infrastructure.http;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.saasmania.economizae.transaction.application.ConsultarTransacoesPorCategoriaUseCase;
import br.saasmania.economizae.transaction.application.ConsultarTransacoesPorPeriodoUseCase;
import br.saasmania.economizae.transaction.application.dto.ConsultaTransacoesResult;
import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.Transacao;

@RestController
@RequestMapping("/api")
public class TransacaoConsultaController {
    private final ConsultarTransacoesPorPeriodoUseCase porPeriodoUseCase;
    private final ConsultarTransacoesPorCategoriaUseCase porCategoriaUseCase;

    public TransacaoConsultaController(
            ConsultarTransacoesPorPeriodoUseCase porPeriodoUseCase,
            ConsultarTransacoesPorCategoriaUseCase porCategoriaUseCase) {
        this.porPeriodoUseCase = porPeriodoUseCase;
        this.porCategoriaUseCase = porCategoriaUseCase;
    }

    @GetMapping("/consultar-transacoes")
    ConsultaTransacoesResponse consultar(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {

        ConsultaTransacoesResult resultado = porPeriodoUseCase.executar(inicio, fim);
        return new ConsultaTransacoesResponse(resultado.transacoes(), resultado.total());
    }

    @GetMapping("/consultar-transacoes/categoria")
    ConsultaTransacoesResponse consultarPorCategoria(@RequestParam CategoriaTransacao categoria) {
        ConsultaTransacoesResult resultado = porCategoriaUseCase.executar(categoria);
        return new ConsultaTransacoesResponse(resultado.transacoes(), resultado.total());
    }

    record ConsultaTransacoesResponse(List<Transacao> transacoes, BigDecimal total) {
    }
}