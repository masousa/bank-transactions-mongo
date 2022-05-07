package br.com.letscode.eighteleven.payloads.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentAccount {
    private String agency;
    @JsonProperty("account-number")
    private String accountNumber;
    private String identifier;
}
