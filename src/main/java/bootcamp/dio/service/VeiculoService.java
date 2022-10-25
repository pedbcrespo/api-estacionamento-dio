package bootcamp.dio.service;

import bootcamp.dio.dto.DTOVeiculo;
import bootcamp.dio.exceptions.CalculoValorException;
import bootcamp.dio.exceptions.VeiculoExistenteException;
import bootcamp.dio.exceptions.VeiculoInexistenteException;
import bootcamp.dio.model.Veiculo;
import bootcamp.dio.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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
        if(veiculoRepository.findByPlaca(veiculo.getPlaca()).size() != 0){
            throw new VeiculoExistenteException(veiculo.getPlaca());
        }
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(new DTOVeiculo(veiculoSalvo));
    }

    public ResponseEntity<DTOVeiculo> getByPlaca(String placa) {
        List<Veiculo> veiculos = veiculoRepository.findByPlaca(placa).stream().toList();
        if (veiculos.size() == 0) {
            throw new VeiculoInexistenteException(placa);
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
            throw new VeiculoInexistenteException(id);
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
        double valor = baseValorPorHora * resultado.toHours();
        if(valor < 0) {
            throw new CalculoValorException(dto);
        }
        dto.setValor(valor);
        return dto;
    }
}
