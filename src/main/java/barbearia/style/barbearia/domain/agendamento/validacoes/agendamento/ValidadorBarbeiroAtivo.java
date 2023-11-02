package barbearia.style.barbearia.domain.agendamento.validacoes.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import barbearia.style.barbearia.domain.barbeiro.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroAtivo implements ValidadorAgendamento {

    @Autowired
    private BarbeiroRepository repository;

    public void validar(DadosAgendamento dados) {
        //escolha do medico opcional
        if (dados.idBarbeiro() == null) {
            return;
        }

        var barbeiroEstaAtivo = repository.findAtivoById(dados.idBarbeiro());
        if (!barbeiroEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }

}
