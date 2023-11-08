package barbearia.style.barbearia.domain.agendamento;

import barbearia.style.barbearia.domain.ValidacaoException;
import barbearia.style.barbearia.domain.agendamento.validacoes.agendamento.ValidadorAgendamento;
import barbearia.style.barbearia.domain.agendamento.validacoes.cancelamento.ValidadorCancelamentoDeAgendamento;
import barbearia.style.barbearia.domain.barbeiro.Barbeiro;
import barbearia.style.barbearia.domain.barbeiro.BarbeiroRepository;
import barbearia.style.barbearia.domain.cliente.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoDeAtendimento {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private BarbeiroRepository barbeiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private List<ValidadorAgendamento> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeAgendamento> validadorCancelamentoDeAgendamentos;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados){

        if(!clienteRepository.existsById(dados.idCliente())){
            throw new ValidacaoException("Id do cliente informado não existe");
        }

        if(dados.idBarbeiro()!=null && !barbeiroRepository.existsById(dados.idBarbeiro())){
            throw new ValidacaoException("Id do barbeiro informado não existe");
        }

        validadores.forEach(v->v.validar(dados));

        var barbeiro = escolherBarbeiro(dados);
        if(barbeiro==null){
            throw new ValidacaoException("Não existe barbeiro disponivel nessa data");
        }
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var agendamento= new Agendamento(null, barbeiro, cliente, dados.data(),null);
        agendamentoRepository.save(agendamento);

        return new DadosDetalhamentoAgendamento(agendamento);
    }

    private Barbeiro escolherBarbeiro(DadosAgendamento dados) {
        if(dados.idBarbeiro()!=null){
            return barbeiroRepository.getReferenceById(dados.idBarbeiro());
        }
        if(dados.especialidade()==null){
            throw new ValidacaoException("Especialidade é obrigatoria");
        }
        return barbeiroRepository.escolherBabeiroAleatorio(dados.especialidade(),dados.data());
    }

    public void cancelar(DadosCancelamentoAgendamento dados) {
        if (!agendamentoRepository.existsById(dados.idAgendamento())) {
            throw new ValidacaoException("Id do agendamento informado não existe!");
        }

        validadorCancelamentoDeAgendamentos.forEach(v -> v.validar(dados));

        var agendamento = agendamentoRepository.getReferenceById(dados.idAgendamento());
        agendamento.cancelar(dados.motivo());
    }
}
