package br.com.letscode.eighteleven.payloads.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentResponse {

    private String uuid;


    private String transaction;
    private Boolean processed;

    @JsonProperty("payment-date")
    private LocalDate paymentDate;
    @JsonProperty("due-date")
    private LocalDate dueDate;

    @JsonProperty("debitValue")
    private Double debitValue;
    @JsonProperty("paid-value")
    private Double paidValue;

    @JsonProperty("payment-info")
    private PaymentAccount paymentAccount;
}
