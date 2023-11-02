package barbearia.style.barbearia.domain.barbeiro;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
        select b from Barbeiro b
        where
        b.ativo = true
        and
        b.especialidade=:especialidade
        and
        b.id not in(
            select a.barbeiro.id from Agendamento a
            where
            a.data = :data
        )
        order by rand()
        limit 1
        
        """)
    Barbeiro escolherBabeiroAleatorio(Especialidade especialidade, LocalDateTime data);

    @Query("""
    select b.ativo
    from Barbeiro b
    where
    b.id = :id
    """)
    Boolean findAtivoById(Long id);
}
