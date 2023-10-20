package barbearia.style.barbearia.cliente;

import barbearia.style.barbearia.endereco.Endereco;

public record DadosListagemCliente(

    Long id,
    String nome,
    String email,
    String telefone
) {

  public DadosListagemCliente(Cliente cliente) {
    this(cliente.getId(), cliente.getNome(),cliente.getEmail(),cliente.getTelefone());
  }
}
