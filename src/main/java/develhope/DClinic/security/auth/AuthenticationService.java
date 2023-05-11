package develhope.DClinic.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import develhope.DClinic.security.config.JwtService;
import develhope.DClinic.security.token.Token;
import develhope.DClinic.security.token.TokenRepository;
import develhope.DClinic.security.token.TokenType;
import develhope.DClinic.user.domain.entities.User;
import develhope.DClinic.user.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  private final UserRepository repository;
  @Autowired
  private final TokenRepository tokenRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final JwtService jwtService;
  @Autowired
  private final AuthenticationManager authenticationManager;

  private Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);


  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .fiscalCode(request.getFiscalCode())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getFiscalCode(),
                    request.getPassword()
            )
    );
    var user = repository.findByFiscalCode(request.getFiscalCode())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userFiscalCode;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userFiscalCode = jwtService.extractUsername(refreshToken);
    if (userFiscalCode != null) {
      var user = this.repository.findByFiscalCode(userFiscalCode)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }

  /*public User userByToken(HttpServletRequest request){
    if(HttpHeaders.AUTHORIZATION == null) {
      LOGGER.error("----   ----    ----    ----    ----");
      LOGGER.error("THE USER HAS NOT BEEN AUTHENTICATED");
      LOGGER.error("----   ----    ----    ----    ----");
    }
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String token = authHeader.substring(7);
    final String userFiscalCode = jwtService.extractUsername(token);
    var user = this.repository.findByFiscalCode(userFiscalCode)
            .orElseThrow();
    return user;
  }*/
}
