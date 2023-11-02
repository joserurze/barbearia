package barbearia.style.barbearia.controller;

import barbearia.style.barbearia.domain.agendamento.AgendamentoDeAtendimento;
import barbearia.style.barbearia.domain.agendamento.DadosAgendamento;
import barbearia.style.barbearia.domain.agendamento.DadosCancelamentoAgendamento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agendamentos")
public class ConsultaController {

    @Autowired
    private AgendamentoDeAtendimento agendamentoDeAtendimento;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados){
        var dto = agendamentoDeAtendimento.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoAgendamento dados) {
        agendamentoDeAtendimento.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
