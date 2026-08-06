package br.saasmania.economizae.transaction.infrastructure.persistence.entity;

import br.saasmania.economizae.transaction.domain.CategoriaTransacao;
import br.saasmania.economizae.transaction.domain.TipoMovimento;
import br.saasmania.economizae.transaction.domain.Transacao;
import br.saasmania.economizae.transaction.domain.TransacaoId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoEntity {

    @Id
    private UUID id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private CategoriaTransacao categoria;

    private String estabelecimento;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipo;

    public static TransacaoEntity from(Transacao transacao) {
        return new TransacaoEntity(
                transacao.getId().valor(),
                transacao.getValor(),
                transacao.getCategoria(),
                transacao.getEstabelecimento(),
                transacao.getData(),
                transacao.getTipo());
    }

    public Transacao toDomain() {
        return Transacao.reconstruir(
                new TransacaoId(this.id),
                this.valor,
                this.categoria,
                this.estabelecimento,
                this.data,
                this.tipo);
    }
}