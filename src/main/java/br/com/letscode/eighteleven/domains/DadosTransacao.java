package br.com.letscode.eighteleven.domains;

import lombok.Data;

@Data
public class DadosTransacao {
    private TipoOrigem origem;
    private String observacoes;
    private String identificador;
}
