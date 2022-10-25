package bootcamp.dio.exceptions;

import bootcamp.dio.dto.DTOVeiculo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CalculoValorException extends RuntimeException {
    public CalculoValorException(DTOVeiculo veiculo) {
        super("ERRO: registro id = "+veiculo.getId()+" placa = " +veiculo.getPlaca() +
                "diferença entre horarios gerando saldo negativo."+
                "\nhorario entrada = "+veiculo.getHorarioEntrada() +
                "\nhorario saida = "+veiculo.getHorarioSaida() +
                "\nsaldo = "+veiculo.getValor());
    }
}
