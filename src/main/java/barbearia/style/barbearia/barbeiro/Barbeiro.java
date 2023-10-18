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
  private boolean ativo;

  public Barbeiro(DadosCadastroBabeiro dados) {
    this.ativo=true;
    this.nome = dados.nome();
    this.email = dados.email();
    this.telefone=dados.telefone();
    this.especialidade=dados.especialidade();
    this.endereco=new Endereco(dados.endereco());
  }

  public void atualizarInformacoes(DadosAtualizacaoBabeiro dados) {
      if(dados.nome()!=null){
        this.nome=dados.nome();
      }
      if(dados.telefone()!=null){
        this.telefone=dados.telefone();
      }
      if(dados.endereco()!=null){
        this.endereco.atualizarInformacoes(dados.endereco());

      }
  }

  public void excluir() {
    this.ativo=false;
  }
}
