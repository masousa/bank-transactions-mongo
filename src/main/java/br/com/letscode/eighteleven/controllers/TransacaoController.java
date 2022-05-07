package br.com.letscode.eighteleven.controllers;

import br.com.letscode.eighteleven.payloads.CreateTransactionRequest;
import br.com.letscode.eighteleven.payloads.TransactionResponse;
import br.com.letscode.eighteleven.payloads.payment.CreatePaymentRequest;
import br.com.letscode.eighteleven.services.CreateTransactionService;
import br.com.letscode.eighteleven.services.GetListaTransacaoPorConta;
import br.com.letscode.eighteleven.services.GetSaldoContaService;
import br.com.letscode.eighteleven.services.RealizarPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction/v1")
@RequiredArgsConstructor
public class TransacaoController {
    private final CreateTransactionService createTransactionService;
    private final GetListaTransacaoPorConta getListaTransacaoPorConta;

    private final GetSaldoContaService getSaldoContaService;

    private final RealizarPagamentoService realizarPagamentoService;

    @PostMapping(path = "/", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody CreateTransactionRequest request) {
        createTransactionService.execute(request);

    }


    @GetMapping(path = "/extrato/{conta}", produces = "application/json")
    public List<TransactionResponse> listaTransacoesPorConta(@PathVariable("conta") String conta) {
        return getListaTransacaoPorConta.execute(conta);
    }


    @GetMapping(path = "/saldo/{conta}", produces = "application/json")
    public BigDecimal obtemSaldoPorConta(@PathVariable("conta") String conta) {
        return getSaldoContaService.execute(conta);
    }

    @PostMapping(value = "/pagamento", produces = "application/json")
    public TransactionResponse realizarPagamento(@RequestBody CreatePaymentRequest createPaymentRequest) {
        return realizarPagamentoService.execute(createPaymentRequest);
    }
}
