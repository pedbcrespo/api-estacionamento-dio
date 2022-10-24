package bootcamp.dio.controller;

import bootcamp.dio.model.Veiculo;
import bootcamp.dio.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {
    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Veiculo>> getAll() {
        return veiculoService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Veiculo> getById(@PathVariable Long id) {
        return veiculoService.getById(id);
    }

    @GetMapping("/placa/{placa}")
    @ResponseBody
    public ResponseEntity<Veiculo> getByPlaca(@PathVariable String placa) {
        return veiculoService.getByPlaca(placa);
    }

    @PostMapping
    @ResponseStatus
    public ResponseEntity<Veiculo> post(@RequestBody Veiculo veiculo) {
        return veiculoService.save(veiculo);
    }

    @PutMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<Veiculo> update(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return veiculoService.updateSaida(id, veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<Veiculo> delete(@PathVariable Long id) {
        return veiculoService.delete(id);
    }
}
