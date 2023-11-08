package barbearia.style.barbearia.domain.agendamento.validacoes.cancelamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.AgendamentoRepository;
import barbearia.style.barbearia.domain.agendamento.DadosCancelamentoAgendamento;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeAgendamento{

  @Autowired
  private AgendamentoRepository repository;

  @Override
  public void validar(DadosCancelamentoAgendamento dados) {
      var agendamento = repository.getReferenceById(dados.idAgendamento());
      var agora = LocalDateTime.now();
      var diferencaEmHoras = Duration.between(agora, agendamento.getData()).toHours();

    if(diferencaEmHoras < 24){
      throw new ValidacaoException("Agendamento somente pode ser cancelado com antecedencia de 24 horas");
    }
  }
}
