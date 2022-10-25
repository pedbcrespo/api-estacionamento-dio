package bootcamp.dio.repository;

import bootcamp.dio.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Veiculo v set v.horarioSaida = :saida where v.id = :id")
    void updateSaida(@Param("saida") LocalDateTime saida,  @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Veiculo v set v.placa = :placa, v.horarioEntrada = :entrada, v.horarioSaida = :saida where v.id = :id")
    void updateDados(@Param("id") Long id, @Param("placa") String placa, @Param("entrada") LocalDateTime entrada, @Param("saida") LocalDateTime saida);

    @Query(value="select * from veiculo where placa = :placa", nativeQuery = true)
    Collection<Veiculo> findByPlaca(@Param("placa") String placa);
}
