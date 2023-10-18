package barbearia.style.barbearia.barbeiro;

import barbearia.style.barbearia.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoBabeiro(
    @NotNull // obrigatorio
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {

}
