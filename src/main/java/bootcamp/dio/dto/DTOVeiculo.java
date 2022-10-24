package bootcamp.dio.dto;

import bootcamp.dio.model.Veiculo;

import java.time.LocalDateTime;

public class DTOVeiculo {
    private Long id;
    private String placa;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private Double valor;

    public DTOVeiculo(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.placa = veiculo.getPlaca();
        this.horarioEntrada = veiculo.getHorarioEntrada();
        this.horarioSaida = veiculo.getHorarioSaida();
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
