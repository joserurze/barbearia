package barbearia.style.barbearia.cliente;

import barbearia.style.barbearia.endereco.Endereco;

public record DadosListagemCliente(
    String nome,
    String email,
    String telefone,
    Endereco endereco
) {

  public DadosListagemCliente(Cliente cliente) {
    this(cliente.getNome(),cliente.getEmail(),cliente.getTelefone(),cliente.getEndereco());
  }
}
