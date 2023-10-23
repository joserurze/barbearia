package barbearia.style.barbearia.domain.barbeiro;

import barbearia.style.barbearia.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoBabeiro(
    @NotNull // obrigatorio
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {

}
