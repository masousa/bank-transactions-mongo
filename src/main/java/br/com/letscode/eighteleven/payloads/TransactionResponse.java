package br.com.letscode.eighteleven.payloads;

import br.com.letscode.eighteleven.domains.DadosTransacao;
import br.com.letscode.eighteleven.domains.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionResponse {
    @JsonProperty("tipo-transacao")
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;
    private DadosTransacao dadosTransacao;
}
