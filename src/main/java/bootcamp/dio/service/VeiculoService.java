package bootcamp.dio.service;

import bootcamp.dio.dto.DTOVeiculo;
import bootcamp.dio.model.Veiculo;
import bootcamp.dio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    public ResponseEntity<List<DTOVeiculo>> getAll() {
        List<Veiculo> resultado = veiculoRepository.findAll();
        List<DTOVeiculo> resultadoEmDTO = resultado.stream().map(this::calculaValorEstacionamento).toList();
        return ResponseEntity.ok(resultadoEmDTO);
    }

    public ResponseEntity<DTOVeiculo> getById(Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if(optionalVeiculo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        DTOVeiculo dto = new DTOVeiculo(optionalVeiculo.get());
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<DTOVeiculo> save(Veiculo veiculo) {
        if(getByPlaca(veiculo.getPlaca())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(new DTOVeiculo(veiculoSalvo));
    }

    public ResponseEntity<DTOVeiculo> getByPlaca(String placa) {
        List<Veiculo> veiculos = veiculoRepository.findByPlaca(placa).stream().toList();
        if (veiculos.size() == 0) {
            return null;
        }
        return ResponseEntity.ok(new DTOVeiculo(veiculos.get(0)));
    }

    public ResponseEntity<DTOVeiculo> delete(Long id) {
        DTOVeiculo veiculoParaSerRemovido = getById(id).getBody();
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok(veiculoParaSerRemovido);
    }

    public ResponseEntity<DTOVeiculo> updateSaida(Long id, Veiculo atualizacao) {
        if(!veiculoRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LocalDateTime horarioSaida = atualizacao != null ? atualizacao.getHorarioSaida() != null ?
                atualizacao.getHorarioSaida() : LocalDateTime.now() :
                LocalDateTime.now();
        veiculoRepository.updateSaida(horarioSaida, id);
        DTOVeiculo veiculoAtualizado = getById(id).getBody();
        return ResponseEntity.ok(veiculoAtualizado);
    }

    private DTOVeiculo calculaValorEstacionamento(Veiculo veiculo) {
        DTOVeiculo dto = new DTOVeiculo(veiculo);
        if (veiculo.getHorarioSaida() == null) {
            return dto;
        }
        final double baseValorPorHora = 5.0;
        LocalDateTime inicio = veiculo.getHorarioEntrada();
        LocalDateTime saida = veiculo.getHorarioSaida();
        Duration resultado = Duration.between(inicio, saida);
        Double valor = baseValorPorHora * resultado.toHours();
        dto.setValor(valor);
        return dto;
    }
}
