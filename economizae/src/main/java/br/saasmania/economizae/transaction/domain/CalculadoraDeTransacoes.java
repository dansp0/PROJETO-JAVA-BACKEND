package br.saasmania.economizae.transaction.domain;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraDeTransacoes {
    public BigDecimal somar(List<Transacao> transacoes) {
        return transacoes.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
