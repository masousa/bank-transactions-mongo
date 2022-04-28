package br.com.letscode.eighteleven.repositories;

import br.com.letscode.eighteleven.domains.Transacao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends MongoRepository<Transacao, ObjectId> {
}
