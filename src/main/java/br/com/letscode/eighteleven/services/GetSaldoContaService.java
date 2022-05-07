package br.com.letscode.eighteleven.services;

import br.com.letscode.eighteleven.domains.TipoTransacao;
import br.com.letscode.eighteleven.domains.Transacao;
import br.com.letscode.eighteleven.repositories.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSaldoContaService {

    private final TransacaoRepository transacaoRepository;

    public BigDecimal execute(String conta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        log.info("Iniciei as {}", LocalDateTime.now().format(formatter));
        BigDecimal response = BigDecimal.ZERO;
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Double creditos = transacaoRepository.sumByTipoTransacaoAndConta(TipoTransacao.CREDITO, conta);
        Double debitos = transacaoRepository.sumByTipoTransacaoAndConta(TipoTransacao.DEBITO, conta);
        log.info("Finalizei as {}", LocalDateTime.now().format(formatter));
        return response.add(BigDecimal.valueOf(creditos)).subtract(BigDecimal.valueOf(debitos));
    }

    private Double getValorTipoTransacao(List<Transacao> transacoesConta, TipoTransacao tipoTransacao) {
        return transacoesConta.stream().filter(transacao -> transacao.getTipoTransacao().equals(tipoTransacao))
                .map(Transacao::getValor).reduce(0D, Double::sum);
    }


}
