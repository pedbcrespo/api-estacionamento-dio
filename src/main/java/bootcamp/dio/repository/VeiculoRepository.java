package bootcamp.dio.repository;

import bootcamp.dio.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Modifying
    @Query(value = "update veiculo v set v.horario_saida = :saida where v.id = :id", nativeQuery = true)
    void updateSaida(@Param("saida") LocalDateTime saida,  @Param("id") Long id);

    @Modifying
    @Query(value="select * from veiculo where placa = :placa", nativeQuery = true)
    Collection<Veiculo> findByPlaca(@Param("placa") String placa);
}
