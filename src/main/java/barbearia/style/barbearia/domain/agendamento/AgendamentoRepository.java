package barbearia.style.barbearia.domain.agendamento;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  Boolean existsByBarbeiroIdAndDataAndMotivoCancelamentoIsNull(Long idBarbeiro, LocalDateTime data);

  Boolean existsByClienteIdAndDataBetween(Long idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
