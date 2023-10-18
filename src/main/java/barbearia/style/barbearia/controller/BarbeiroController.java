package barbearia.style.barbearia.controller;

import barbearia.style.barbearia.barbeiro.Barbeiro;
import barbearia.style.barbearia.barbeiro.BarbeiroRepository;
import barbearia.style.barbearia.barbeiro.DadosAtualizacaoBabeiro;
import barbearia.style.barbearia.barbeiro.DadosCadastroBabeiro;
import barbearia.style.barbearia.barbeiro.DadosListagemBarbeiro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {
    @Autowired
    private BarbeiroRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroBabeiro dados){
      repository.save(new Barbeiro(dados));
    }

    @GetMapping
    public Page<DadosListagemBarbeiro> listar(@PageableDefault(size = 10,sort={"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemBarbeiro::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoBabeiro dados){
        var barbeiro = repository.getReferenceById(dados.id());
        barbeiro.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var barbeiro = repository.getReferenceById(id);
        barbeiro.excluir();
    }
}
