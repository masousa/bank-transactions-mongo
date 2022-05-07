package br.com.letscode.eighteleven.payloads;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GenericEntityResponse {
    private String message;
    private String httpStatusCode;
}
