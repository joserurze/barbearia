package barbearia.style.barbearia.barbeiro;

import barbearia.style.barbearia.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroBabeiro(
    @NotBlank //verifica se não é nulo e nem vazio
    String nome,
    @NotBlank
    @Email //verifica se tem os padroes de email
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{9,11}")
    String telefone,

    @NotNull
    Especialidade especialidade,
    @NotNull
    @Valid
    DadosEndereco endereco

) {

}
