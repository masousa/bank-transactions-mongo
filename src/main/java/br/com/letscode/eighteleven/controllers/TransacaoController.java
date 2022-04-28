package br.com.letscode.eighteleven.controllers;

import br.com.letscode.eighteleven.payloads.CreateTransactionRequest;
import br.com.letscode.eighteleven.services.CreateTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction/v1")
@RequiredArgsConstructor
public class TransacaoController {
    private final CreateTransactionService createTransactionService;

    @PostMapping(path = "/", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody CreateTransactionRequest request){
        createTransactionService.execute(request);

    }
}
