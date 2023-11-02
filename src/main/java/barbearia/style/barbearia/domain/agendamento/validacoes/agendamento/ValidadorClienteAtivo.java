package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import barbearia.style.barbearia.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorAgendamento {

    @Autowired
    private ClienteRepository repository;

    public void validar(DadosAgendamento dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idCliente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Horario não pode ser agendado com cliente excluído");
        }
    }
}
