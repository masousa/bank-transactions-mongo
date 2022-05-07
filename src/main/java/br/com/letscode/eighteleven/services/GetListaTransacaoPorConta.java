package br.com.letscode.eighteleven.services;

import br.com.letscode.eighteleven.domains.Transacao;
import br.com.letscode.eighteleven.payloads.TransactionResponse;
import br.com.letscode.eighteleven.repositories.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetListaTransacaoPorConta {

    private final TransacaoRepository transacaoRepository;

    private final ObjectMapper objectMapper;

    public List<TransactionResponse> execute(String conta) {
        
        return transacaoRepository.findByConta(conta).stream()
                .map(this::mapToTrasactionResponse).collect(Collectors.toList());
    }

    private TransactionResponse mapToTrasactionResponse(Transacao transacao) {
        return objectMapper.convertValue(transacao, TransactionResponse.class);
    }
}
