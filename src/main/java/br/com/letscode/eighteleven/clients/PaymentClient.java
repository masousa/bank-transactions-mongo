package br.com.letscode.eighteleven.clients;

import br.com.letscode.eighteleven.domains.DadosTransacao;
import br.com.letscode.eighteleven.domains.TipoOrigem;
import br.com.letscode.eighteleven.domains.Transacao;
import br.com.letscode.eighteleven.exceptions.ClientException;
import br.com.letscode.eighteleven.payloads.payment.CreatePaymentRequest;
import br.com.letscode.eighteleven.payloads.payment.PaymentResponse;
import br.com.letscode.eighteleven.repositories.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentClient {
    @Value("${business.payments.endpoint}")
    private String paymentEndpoint;

    private final TransacaoRepository transacaoRepository;

    @Async
    public void doPayment(CreatePaymentRequest createPaymentRequest, Transacao transacao) {
        try {
            var restTemplate = new RestTemplate();
            ResponseEntity<PaymentResponse> responseEntity =
                    restTemplate.postForEntity(paymentEndpoint, createPaymentRequest, PaymentResponse.class);
            if (responseEntity.getStatusCode().isError()) {
                throw new ClientException("Erro ao tentar realizar um pagamento");
            }

            var paymentResponse = Optional.ofNullable(responseEntity.getBody())
                    .orElseThrow(ClientException::new);

            boolean processed = paymentResponse.getProcessed();
            transacao.setProcessada(processed);
            var dadosTransacao = new DadosTransacao();
            dadosTransacao.setOrigem(TipoOrigem.PAGAMENTO);
            dadosTransacao.setIdentificador(paymentResponse.getUuid());
            transacao.setDadosTransacao(dadosTransacao);
            transacaoRepository.save(transacao);
            log.info("Finalizado em {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        } catch (RestClientException restClientException) {
            final var messageError = "Erro ao tentar conectar no cliente";
            log.error(messageError, restClientException);
            throw new ClientException(messageError);
        }
    }
}
