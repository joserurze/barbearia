package barbearia.style.barbearia.domain.cliente;

import barbearia.style.barbearia.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(
    @NotBlank
    String nome,
    @NotBlank
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{9,11}")
    String telefone,
    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
    String cpf,
    @NotNull
    @Valid
    DadosEndereco endereco
) {

}
