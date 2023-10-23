package barbearia.style.barbearia.controller;

import barbearia.style.barbearia.domain.barbeiro.Barbeiro;
import barbearia.style.barbearia.domain.barbeiro.BarbeiroRepository;
import barbearia.style.barbearia.domain.barbeiro.DadosAtualizacaoBabeiro;
import barbearia.style.barbearia.domain.barbeiro.DadosCadastroBabeiro;
import barbearia.style.barbearia.domain.barbeiro.DadosDetalhamentoBarbeiro;
import barbearia.style.barbearia.domain.barbeiro.DadosListagemBarbeiro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {

  @Autowired
  private BarbeiroRepository repository;

  @PostMapping
  @Transactional
  public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroBabeiro dados,
      UriComponentsBuilder uriBuilder) {
    var barbeiro = new Barbeiro(dados);

    repository.save(barbeiro);
    var uri = uriBuilder.path("/barbeiros/{id}").buildAndExpand(barbeiro.getId()).toUri();

    return ResponseEntity.created(uri).body(new DadosDetalhamentoBarbeiro(barbeiro));
  }

  @GetMapping
  public ResponseEntity<Page<DadosListagemBarbeiro>> listar(
      @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
    var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemBarbeiro::new);
    return ResponseEntity.ok(page);
  }

  @PutMapping
  @Transactional
  public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoBabeiro dados) {
    var barbeiro = repository.getReferenceById(dados.id());
    barbeiro.atualizarInformacoes(dados);
    return ResponseEntity.ok(new DadosDetalhamentoBarbeiro(barbeiro));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity excluir(@PathVariable Long id) {
    var barbeiro = repository.getReferenceById(id);
    barbeiro.excluir();
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity detalhar(@PathVariable Long id){

    var barbeiro = repository.getReferenceById(id);
    return ResponseEntity.ok(new DadosDetalhamentoBarbeiro(barbeiro));
  }
}
