package barbearia.style.barbearia.infra.security;

import barbearia.style.barbearia.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(Usuario usuario){
      try {
        var algoritimo = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer("API Barbearia Style")
            .withSubject(usuario.getLogin())
            .withExpiresAt(dataExpiracao())
            .sign(algoritimo);
      } catch (JWTCreationException exception){
        throw new RuntimeException("Erro ao gerar token jwt",exception);
      }
    }

    public String getSubject(String tokeJWT){
      try {
        var algoritimo = Algorithm.HMAC256(secret);
        return JWT.require(algoritimo)
            .withIssuer("API Barbearia Style")
            .build()
            .verify(tokeJWT)
            .getSubject();

      } catch (JWTCreationException exception){
        throw new RuntimeException("Token JWT invalido ou expirado");
      }
    }

    private Instant dataExpiracao() {
      return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
