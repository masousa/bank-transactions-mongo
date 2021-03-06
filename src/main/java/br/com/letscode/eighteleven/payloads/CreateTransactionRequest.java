package br.com.letscode.eighteleven.payloads;

import br.com.letscode.eighteleven.domains.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTransactionRequest {

    @JsonProperty("tipo-transacao")
    private TipoTransacao tipoTransacao;
    private String conta;
    private Double valor;
}
