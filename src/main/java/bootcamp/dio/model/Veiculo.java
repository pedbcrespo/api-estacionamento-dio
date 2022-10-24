package bootcamp.dio.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String placa;

    private LocalDateTime horarioEntrada;

    private LocalDateTime horarioSaida = LocalDateTime.now();

    public Veiculo(Long id, String placa, LocalDateTime horarioEntrada) {
        this.id = id;
        this.placa = placa;
        this.horarioEntrada = horarioEntrada;
    }

    public Veiculo() {
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }
}