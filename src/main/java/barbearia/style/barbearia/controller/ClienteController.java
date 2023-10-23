package barbearia.style.barbearia.controller;

import barbearia.style.barbearia.domain.cliente.Cliente;
import barbearia.style.barbearia.domain.cliente.ClienteRepository;
import barbearia.style.barbearia.domain.cliente.DadosAtualizacaoCliente;
import barbearia.style.barbearia.domain.cliente.DadosCadastroCliente;
import barbearia.style.barbearia.domain.cliente.DadosDetalhamentoCliente;
import barbearia.style.barbearia.domain.cliente.DadosListagemCliente;
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
@RequestMapping("/clientes")
public class ClienteController {
  @Autowired
  private ClienteRepository repository;

  @PostMapping
  @Transactional
  public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados,
      UriComponentsBuilder uribuilder) {
    var cliente = new Cliente(dados);
    repository.save(cliente);
    var uri = uribuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
    return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
  }

  @GetMapping
  public ResponseEntity<Page<DadosListagemCliente>> listar(
      @PageableDefault(page = 0, sort = {"nome"}, size = 10) Pageable paginacao) {
    var page = repository.findByAtivoTrue(paginacao).map(DadosListagemCliente::new);
    return ResponseEntity.ok(page);
  }
 @PutMapping
 @Transactional
 public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados){
    var cliente = repository.getReferenceById(dados.id());
    cliente.atualizarInfomacoes(dados);
    return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
  }
  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity inativar(@PathVariable Long id) {
    var cliente = repository.getReferenceById(id);
    cliente.inativar();
    return ResponseEntity.noContent().build();
  }
  @GetMapping("/{id}")
  public ResponseEntity detalhar(@PathVariable Long id) {
    var cliente = repository.getReferenceById(id);
    return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
  }
}

