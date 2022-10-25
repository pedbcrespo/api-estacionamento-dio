package bootcamp.dio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VeiculoInexistenteException extends RuntimeException {
    public VeiculoInexistenteException(Long id) {
        super("ERRO: veiculo "+id+" nao encontrado");
    }

    public VeiculoInexistenteException(String placa) {
        super("ERRO: veiculo "+placa+" nao encontrado");
    }
}
