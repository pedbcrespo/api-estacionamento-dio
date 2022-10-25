package bootcamp.dio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class VeiculoExistenteException extends RuntimeException {
    public VeiculoExistenteException(String placa) {
        super("ERRO: placa ["+placa+"] ja registrada");
    }
}
