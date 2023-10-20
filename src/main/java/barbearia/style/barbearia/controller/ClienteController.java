package barbearia.style.barbearia.controller;

import barbearia.style.barbearia.cliente.Cliente;
import barbearia.style.barbearia.cliente.ClienteRepository;
import barbearia.style.barbearia.cliente.DadosAtualizacaoCliente;
import barbearia.style.barbearia.cliente.DadosCadastroCliente;
import barbearia.style.barbearia.cliente.DadosListagemCliente;
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
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteRepository repository;

  @PostMapping
  @Transactional
  public void cadastrar(@RequestBody @Valid DadosCadastroCliente dados){
    repository.save(new Cliente(dados));
  }

  @GetMapping
  public Page<DadosListagemCliente> listar(@PageableDefault(page = 0, sort={"nome"},size=10) Pageable paginacao){
    return repository.findByAtivoTrue(paginacao).map(DadosListagemCliente::new);
  }

  @PutMapping
  @Transactional
  public void atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados){
      var cliente = repository.getReferenceById(dados.id());
      cliente.atualizarInfomacoes(dados);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void inativar(@PathVariable Long id){
    var cliente = repository.getReferenceById(id);
    cliente.inativar();
  }



}


