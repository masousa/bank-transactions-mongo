package br.com.letscode.eighteleven.exceptions;

public class ClientException extends RuntimeException {

    public ClientException() {
        super("Erro ao tentar realizar operação no cliente");
    }

    public ClientException(String message) {
        super(message);
    }


}
