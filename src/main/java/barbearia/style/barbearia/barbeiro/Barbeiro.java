package barbearia.style.barbearia.barbeiro;

import barbearia.style.barbearia.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="barbeiro")
@Entity(name="Barbeiro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Barbeiro implements Serializable {

  @Serial
  private static final long serialVersionUID=1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String telefone;
  @Enumerated
  private Especialidade especialidade;
  @Embedded
  private Endereco endereco;

  public Barbeiro(DadosCadastroBabeiro dados) {
    this.nome = dados.nome();
    this.email = dados.email();
    this.telefone=dados.telefone();
    this.especialidade=dados.especialidade();
    this.endereco=new Endereco(dados.endereco());
  }
}
