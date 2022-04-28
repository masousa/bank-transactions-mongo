package br.com.letscode.eighteleven.services;

import br.com.letscode.eighteleven.domains.TipoTransacao;
import br.com.letscode.eighteleven.domains.Transacao;
import br.com.letscode.eighteleven.payloads.CreateTransactionRequest;
import br.com.letscode.eighteleven.repositories.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionService {

    private final ObjectMapper objectMapper;
    private final TransacaoRepository transacaoRepository;
    public void execute(CreateTransactionRequest request){
        Transacao transacao = objectMapper.convertValue(request, Transacao.class);
        transacao.setProcessada(!TipoTransacao.PAGAMENTO.equals(request.getTipoTransacao()));
        transacaoRepository.save(transacao);

    }
}
