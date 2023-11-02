package barbearia.style.barbearia.domain.agendamento;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
    Long id,
    Long idBarbeiro,

    Long idPaciente,

    LocalDateTime data
    ) {

    public DadosDetalhamentoAgendamento(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getBarbeiro().getId(),agendamento.getCliente().getId(),agendamento.getData());
    }
}
