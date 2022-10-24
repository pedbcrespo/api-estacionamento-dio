package bootcamp.dio.service;

import bootcamp.dio.dto.DTOVeiculo;
import bootcamp.dio.model.Veiculo;
import bootcamp.dio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    public ResponseEntity<List<Veiculo>> getAll() {
        List<Veiculo> resultado = veiculoRepository.findAll();
        return ResponseEntity.ok(resultado);
    }

    public ResponseEntity<Veiculo> getById(Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        return optionalVeiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Veiculo> save(Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(veiculoSalvo);
    }

    public ResponseEntity<Veiculo> getByPlaca(String placa) {
        Veiculo veiculoBuscado = veiculoRepository.findByPlaca(placa);
        return ResponseEntity.ok(veiculoBuscado);
    }

    public ResponseEntity<List<Veiculo>> getByDesc(Veiculo filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example<Veiculo> example = Example.of(filtro, matcher);
        List<Veiculo> listaVeiculos = veiculoRepository.findAll(example);
        return ResponseEntity.ok(listaVeiculos);
    }

    public ResponseEntity<Veiculo> delete(Long id) {
        Veiculo veiculoParaSerRemovido = getById(id).getBody();
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok(veiculoParaSerRemovido);
    }

    public ResponseEntity<Veiculo> updateSaida(Long id, Veiculo atualizacao) {
        LocalDateTime horarioSaida = LocalDateTime.now();
        if (atualizacao != null) {
            Objects.requireNonNull(atualizacao);
            horarioSaida = atualizacao.getHorarioSaida();
        }
        veiculoRepository.updateSaida(horarioSaida, id);
        Veiculo veiculoAtualizado = getById(id).getBody();
        return ResponseEntity.ok(veiculoAtualizado);
    }

    private Double calculaValorEstacionamento(Veiculo veiculo) {
        if(veiculo.getHorarioSaida() == null){
            return null;
        }
        final double baseValorPorHora = 5.0;
        LocalDateTime inicio = veiculo.getHorarioEntrada();
        LocalDateTime saida = veiculo.getHorarioSaida();
        Duration resultado = Duration.between(inicio, saida);
        return baseValorPorHora * resultado.toHours();
    }
}
