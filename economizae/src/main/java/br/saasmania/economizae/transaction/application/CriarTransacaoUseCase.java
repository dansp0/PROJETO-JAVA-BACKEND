package br.saasmania.economizae.transaction.application;

import org.springframework.stereotype.Service;

import br.saasmania.economizae.transaction.application.input.PersistTransactionInput;
import br.saasmania.economizae.transaction.application.output.TransactionOutput;
import br.saasmania.economizae.transaction.domain.ITransacaoRepository;
import br.saasmania.economizae.transaction.domain.Transacao;

@Service
public class CriarTransacaoUseCase {
    private final ITransacaoRepository repositorio;

    public CriarTransacaoUseCase(ITransacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public TransactionOutput executar(PersistTransactionInput input) {
        Transacao transacao = Transacao.registrar(
                input.valor(), input.categoria(), input.estabelecimento(),
                input.data(), input.tipo());

        repositorio.salvar(transacao);

        return new TransactionOutput(
                transacao.getId().asString(),
                transacao.getValor(),
                transacao.getCategoria(),
                transacao.getEstabelecimento(),
                transacao.getData(),
                transacao.getTipo());
    }
}