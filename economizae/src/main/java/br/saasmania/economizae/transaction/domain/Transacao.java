package br.saasmania.economizae.transaction.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private final TransacaoId id;
    private final BigDecimal valor;
    private final CategoriaTransacao categoria;
    private final String estabelecimento;
    private final LocalDate data;
    private final TipoMovimento tipo;

     private Transacao(TransacaoId id, BigDecimal valor, CategoriaTransacao categoria,
                       String estabelecimento, LocalDate data, TipoMovimento tipo) {


        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser positivo");
        }
        

        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.estabelecimento = estabelecimento;
        this.data = data;
        this.tipo = tipo;
    }

    public static Transacao registrar(BigDecimal valor, CategoriaTransacao categoria,
                                       String estabelecimento, LocalDate data, TipoMovimento tipo) {
        return new Transacao(TransacaoId.novo(), valor, categoria, estabelecimento, data, tipo);
    }

    public static Transacao reconstruir(TransacaoId id, BigDecimal valor, CategoriaTransacao categoria,
                                     String estabelecimento, LocalDate data, TipoMovimento tipo) {
    return new Transacao(id, valor, categoria, estabelecimento, data, tipo);
}

    public TransacaoId getId() { return id; }
    public BigDecimal getValor() { return valor; }
    public CategoriaTransacao getCategoria() { return categoria; }
    public String getEstabelecimento() { return estabelecimento; }
    public LocalDate getData() { return data; }
    public TipoMovimento getTipo() { return tipo; }
}
