package br.com.letscode.eighteleven.repositories;

import br.com.letscode.eighteleven.domains.TipoTransacao;
import br.com.letscode.eighteleven.domains.Transacao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends MongoRepository<Transacao, ObjectId> {

    public List<Transacao> findByConta(String conta);

    //@Query("Select Sum(t.valor) From Transacao t Where t.tipoTransacao= :tipoTransacao And t.conta = :conta")

    @Aggregation(pipeline = {"{'$match':{'tipoTransacao':?0, 'conta': ?1, 'processada':true}}", "{'$group':{'_id':null, 'total':{'$sum': '$valor'}}}"})
    public Double sumByTipoTransacaoAndConta(TipoTransacao tipoTransacao, String conta);

    // .aggregate([{$match: {tipoTransacao:"DEBITO"}} , {$group: {_id: null, total:{ $sum: "$valor"}}}])

    // SELECT SUM(VALOR) AS TOTAL FROM TRANSACAO WHERE TIPOTRANSACAO = DEBITO
}
