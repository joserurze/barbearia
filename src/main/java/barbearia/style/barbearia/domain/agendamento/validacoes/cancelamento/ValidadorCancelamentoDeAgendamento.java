package barbearia.style.barbearia.domain.agendamento.validacoes.cancelamento;

import barbearia.style.barbearia.domain.agendamento.DadosCancelamentoAgendamento;
import org.springframework.stereotype.Component;

@Component
public interface ValidadorCancelamentoDeAgendamento {

    void validar(DadosCancelamentoAgendamento dados);

}
