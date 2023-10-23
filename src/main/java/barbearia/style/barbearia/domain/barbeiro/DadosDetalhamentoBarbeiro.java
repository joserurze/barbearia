package barbearia.style.barbearia.domain.barbeiro;

import barbearia.style.barbearia.domain.endereco.Endereco;

public record DadosDetalhamentoBarbeiro(
    Long id,

    String nome,

    String email,

    String telefone,

    Especialidade especialidade,
    Endereco endereco
) {

  public DadosDetalhamentoBarbeiro(Barbeiro barbeiro) {
    this(barbeiro.getId(), barbeiro.getNome(),barbeiro.getEmail(),barbeiro.getTelefone(),barbeiro.getEspecialidade(),barbeiro.getEndereco());
  }
}
