package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import java.time.DayOfWeek;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioFuncionamentoBarbearia implements ValidadorAgendamento {
    public void validar(DadosAgendamento dados){

      var dataConsulta = dados.data();

      var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
      var antesDaAbertura = dataConsulta.getHour()<9;
      var depoisDoEncerramento = dataConsulta.getHour()>18;

      if(domingo||antesDaAbertura||depoisDoEncerramento){
        throw new ValidacaoException("Agendamento fora do horario de atendimento");
      }


    }
}
