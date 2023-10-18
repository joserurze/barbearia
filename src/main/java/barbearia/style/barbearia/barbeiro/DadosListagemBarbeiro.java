package barbearia.style.barbearia.barbeiro;

import jakarta.validation.constraints.NotNull;

public record DadosListagemBarbeiro(
    Long id,
    @NotNull
    String nome,
    String email,
    String telefone,
    Especialidade especialidade
) {

  public DadosListagemBarbeiro(Barbeiro barbeiro) {
      this(barbeiro.getId(), barbeiro.getNome(),barbeiro.getEmail(),barbeiro.getTelefone(),barbeiro.getEspecialidade());
//    thisbarbeiro.getNome();
//    this.email=barbeiro.getEmail();
//    this.telefone=barbeiro.getTelefone();
//    this.especialidade=barbeiro.getEspecialidade();
  }
}
