package barbearia.style.barbearia.domain.cliente;

import barbearia.style.barbearia.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
    @NotNull//obrigatório
    Long id,
    String nome,
    String telefone,
    String email,
    @Valid
    DadosEndereco endereco
) {

}
