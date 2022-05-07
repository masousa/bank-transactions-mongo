package br.com.letscode.eighteleven.payloads.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreatePaymentRequest {

    @JsonProperty("account")
    private String originAccount;
    private String transaction;

    @JsonProperty("payment-date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate paymentDate;
    @JsonProperty("due-date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;

    @JsonProperty("debitValue")
    private Double debitValue;
    @JsonProperty("paid-value")
    private Double paidValue;

    @JsonProperty("payment-info")
    private PaymentAccount paymentAccount;
}
