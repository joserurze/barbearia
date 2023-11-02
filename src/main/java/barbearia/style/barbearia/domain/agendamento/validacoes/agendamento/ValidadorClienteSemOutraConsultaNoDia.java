package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.AgendamentoRepository;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteSemOutraConsultaNoDia implements ValidadorAgendamento {

    @Autowired
    private AgendamentoRepository repository;

    public void validar(DadosAgendamento dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByClienteIdAndDataBetween(dados.idCliente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Cliente já possui um horario agendada nesse dia");
        }
    }

}
