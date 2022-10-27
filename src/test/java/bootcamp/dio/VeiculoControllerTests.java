package bootcamp.dio;

import bootcamp.dio.dto.DTOVeiculo;
import bootcamp.dio.model.Veiculo;
import bootcamp.dio.repository.VeiculoRepository;
import bootcamp.dio.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;
@SpringBootTest
class VeiculoControllerTests {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    private List<Veiculo> listaVeiculos;

    @BeforeEach
    public void setUpTest() {
        listaVeiculos = new ArrayList<>();
        listaVeiculos.add(new Veiculo(1L, "asd1234", LocalDateTime.now()));
        listaVeiculos.add(new Veiculo(2L, "kci9647", LocalDateTime.now()));
        listaVeiculos.add(new Veiculo(3L, "qox6345", LocalDateTime.now()));
    }

    @Test
    public void testaBuscaPorId() {
        Long id = 1L;
        Mockito.when(veiculoRepository.findById(id)).thenReturn(findUsingFakeDB(id));
        ResponseEntity<DTOVeiculo> response = veiculoService.getById(id);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testaBuscaErradaPorId() {
        Long id = 4L;
        Mockito.when(veiculoRepository.findById(id)).thenReturn(findUsingFakeDB(id));
        ResponseEntity<DTOVeiculo> response = veiculoService.getById(id);
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void testaBuscaPorPlaca() {
        String placa = "kci9647";
        Mockito.when(veiculoRepository.findByPlaca(placa)).thenReturn(findUsingFakeDB(placa));
        ResponseEntity<DTOVeiculo> response = veiculoService.getByPlaca(placa);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    private Optional<Veiculo> findUsingFakeDB(Long id) {
        List<Veiculo> veiculosBuscado = listaVeiculos.stream().filter(veiculo -> Objects.equals(veiculo.getId(), id)).toList();
        return veiculosBuscado.size() > 0? Optional.of(veiculosBuscado.get(0)) : Optional.empty();
    }

    private Collection<Veiculo> findUsingFakeDB(String placa) {
        return listaVeiculos.stream()
                .filter(veiculo -> Objects.equals(veiculo.getPlaca(), placa))
                .toList();
    }
}
