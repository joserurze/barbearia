package barbearia.style.barbearia.infra.exceptions;

import barbearia.style.barbearia.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity tratarErro404(){

    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)//Essa é a exceção que o bean validation lança quando há campo inválido.
  public ResponseEntity tratarErro400(MethodArgumentNotValidException ex ){

    var erros = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
  }

  @ExceptionHandler(ValidacaoException.class)//Essa é a exceção que o bean validation lança quando há campo inválido.
  public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex ){

    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  public record DadosErroValidacao(String campo, String mensagem){

    public DadosErroValidacao(FieldError field) {
      this(field.getField(),field.getDefaultMessage());
    }
  }
}
