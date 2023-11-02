package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.AgendamentoRepository;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroComOutraConsultaNoMesmoHorario implements ValidadorAgendamento {

    @Autowired
    private AgendamentoRepository repository;

    public void validar(DadosAgendamento dados) {
        var barbeiroPossuiOutraConsultaNoMesmoHorario = repository.existsByBarbeiroIdAndDataAndMotivoCancelamentoIsNull(dados.idBarbeiro(), dados.data());
        if (barbeiroPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
