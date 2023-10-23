package barbearia.style.barbearia.domain.barbeiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao);
}
