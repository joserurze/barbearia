package barbearia.style.barbearia.domain.cliente;

import barbearia.style.barbearia.domain.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cliente")
@Table(name = "cliente")
public class Cliente implements Serializable {

  @Serial
  private static final long serialVersionUID=1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String email;
  private String cpf;
  private String telefone;

  private boolean ativo;

  @Embedded//referencia a entidade embutida
  private Endereco endereco;

  public Cliente(DadosCadastroCliente dados) {
    this.ativo=true;
    this.nome=dados.nome();
    this.email=dados.email();
    this.cpf=dados.cpf();
    this.telefone=dados.telefone();
    this.endereco = new Endereco(dados.endereco());
  }

  public void atualizarInfomacoes(DadosAtualizacaoCliente dados) {
    if(dados.nome()!=null){
      this.nome=dados.nome();
    }
    if(dados.telefone()!=null){
      this.telefone=dados.telefone();
    }
    if(dados.email()!=null){
      this.email=dados.email();
    }
    if(dados.endereco()!=null){
      this.endereco.atualizarInformacoes(dados.endereco());
    }



  }

  public void inativar() {
    this.ativo=false;
  }
}
