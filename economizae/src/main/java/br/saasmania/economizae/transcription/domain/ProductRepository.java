package br.saasmania.economizae.transcription.domain;

import java.sql.ResultSet;

import br.saasmania.economizae.transcription.domain.aux.IProductRepository;

public class ProductRepository implements IProductRepository {

    public ProductId nextIdentity(){
        long rawId = -1L;
        try {
            PreparedStatement ps = this.connection()
                .prepareStatement("update product_seq set next_val=LAST_INSERT_ID(next_val + 1)");

            ResultSet rs = ps.executeQuery();

            try{
                rs.next()
            }
        }
    }
}
