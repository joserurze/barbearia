package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import org.springframework.stereotype.Component;

@Component

public interface ValidadorAgendamento  {

  void validar(DadosAgendamento dados);


}
