package bootcamp.dio.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String placa;
    @NotNull
    private LocalDateTime horarioEntrada;

    private LocalDateTime horarioSaida;

    public Veiculo(Long id, String placa, LocalDateTime horarioEntrada) {
        this.id = id;
        this.placa = placa;
        this.horarioEntrada = horarioEntrada != null? horarioEntrada: LocalDateTime.now();
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
