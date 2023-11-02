package barbearia.style.barbearia.domain.agendamento;

import barbearia.style.barbearia.domain.barbeiro.Barbeiro;
import barbearia.style.barbearia.domain.cliente.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="agendamento")
@Entity(name="Agendamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Agendamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "barbeiro_id")
  private Barbeiro barbeiro;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  private LocalDateTime data;
  @Enumerated(EnumType.STRING)
  @Column(name = "motivo_cancelamento")
  private MotivoCancelamento motivoCancelamento;
  public void cancelar(MotivoCancelamento motivo) {
    this.motivoCancelamento=motivo;
  }


//  public Agendamento(Long id, Barbeiro barbeiro, Cliente cliente, LocalDateTime data) {
//    this.id = id;
//    this.barbeiro = barbeiro;
//    this.cliente = cliente;
//    this.data = data;
//  }

}
