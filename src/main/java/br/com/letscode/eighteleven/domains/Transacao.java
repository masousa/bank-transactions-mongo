package br.com.letscode.eighteleven.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "transacao")
public class Transacao {
    @Id
    private ObjectId id;
    @JsonProperty("tipo-transacao")
    private TipoTransacao tipoTransacao;
    private String conta;

    private Double valor;
    private Boolean processada;
    @JsonProperty("data-transacao")
    private LocalDateTime dataTransacao = LocalDateTime.now();
    private String uuid = UUID.randomUUID().toString();
    private DadosTransacao dadosTransacao;
}
