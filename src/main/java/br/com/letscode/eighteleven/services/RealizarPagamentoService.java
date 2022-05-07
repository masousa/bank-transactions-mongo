package br.com.letscode.eighteleven.services;

import br.com.letscode.eighteleven.clients.PaymentClient;
import br.com.letscode.eighteleven.domains.TipoTransacao;
import br.com.letscode.eighteleven.domains.Transacao;
import br.com.letscode.eighteleven.payloads.TransactionResponse;
import br.com.letscode.eighteleven.payloads.payment.CreatePaymentRequest;
import br.com.letscode.eighteleven.repositories.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealizarPagamentoService {

    private final TransacaoRepository transacaoRepository;
    private final PaymentClient paymentClient;

    private final ObjectMapper objectMapper;

    public TransactionResponse execute(CreatePaymentRequest request) {

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.DEBITO);
        transacao.setConta(request.getOriginAccount());
        transacao.setDataTransacao(request.getPaymentDate().atStartOfDay());
        transacao.setProcessada(false);
        transacao.setValor(request.getPaidValue());
        Transacao transacaoInserida = transacaoRepository.save(transacao);
        paymentClient.doPayment(request, transacao);
        log.info("Finalizado em {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return objectMapper.convertValue(transacaoInserida, TransactionResponse.class);

    }
}
