package barbearia.style.barbearia.controller;

import barbearia.style.barbearia.barbeiro.Barbeiro;
import barbearia.style.barbearia.barbeiro.BarbeiroRepository;
import barbearia.style.barbearia.barbeiro.DadosCadastroBabeiro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {
    @Autowired
    private BarbeiroRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroBabeiro dados){
      repository.save(new Barbeiro(dados));
    }
}
