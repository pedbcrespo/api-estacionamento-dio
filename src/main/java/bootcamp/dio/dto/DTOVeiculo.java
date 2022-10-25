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
        this.valor = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalDateTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
