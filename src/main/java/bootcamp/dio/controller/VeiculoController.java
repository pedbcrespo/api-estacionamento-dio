package bootcamp.dio.controller;

import bootcamp.dio.dto.DTOVeiculo;
import bootcamp.dio.model.Veiculo;
import bootcamp.dio.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DTOVeiculo>> getAll() {
        return veiculoService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DTOVeiculo> getById(@PathVariable Long id) {
        return veiculoService.getById(id);
    }

    @GetMapping("/placa/{placa}")
    @ResponseBody
    public ResponseEntity<DTOVeiculo> getByPlaca(@PathVariable String placa) {
        return veiculoService.getByPlaca(placa);
    }

    @PostMapping
    @ResponseStatus
    public ResponseEntity<DTOVeiculo> post(@RequestBody Veiculo veiculo) {
        return veiculoService.save(veiculo);
    }

    @PutMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<DTOVeiculo> update(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return veiculoService.updateSaida(id, veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<DTOVeiculo> delete(@PathVariable Long id) {
        return veiculoService.delete(id);
    }
}
