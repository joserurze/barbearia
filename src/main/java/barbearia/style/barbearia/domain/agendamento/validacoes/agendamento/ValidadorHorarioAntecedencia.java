package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento{


  public void validar(DadosAgendamento dados){
    var dataConsulta = dados.data();
    var agora = LocalDateTime.now();
    var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

    if(diferencaEmMinutos<30){
      throw new ValidacaoException("Agendamento deve ser agendada com antecedencia minima de 30 minutos");
    }
  }

}
