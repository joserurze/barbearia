package barbearia.style.barbearia.domain.agendamento;

import barbearia.style.barbearia.domain.barbeiro.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamento(

    Long idBarbeiro,
    @NotNull
    Long idCliente,

    @NotNull
    @Future// agendamento tem que ser no futuro
    LocalDateTime data,
    Especialidade especialidade
) {

}
