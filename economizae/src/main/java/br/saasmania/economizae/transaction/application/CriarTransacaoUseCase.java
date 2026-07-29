package br.saasmania.economizae.transaction.application;


import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.ITransacaoRepository;
import br.saasmania.economizae.transaction.domain.TipoMovimento;
import br.saasmania.economizae.transaction.domain.Transacao;

@Service
public class CriarTransacaoUseCase {
    private final ITransacaoRepository repositorio;

    public CriarTransacaoUseCase(ITransacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Transacao executar(BigDecimal valor, CategoriaTransacao categoria,
            String estabelecimento, LocalDate data, TipoMovimento tipo) {
                System.out.println("TIPO>>>>>>>>>>>" + tipo);
        Transacao transacao = Transacao.registrar(valor, categoria, estabelecimento, data, tipo);
        repositorio.salvar(transacao);
        return transacao;
    }
}