package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Security Manager. Handles security issues with JSON Web Tokens.
 *
 * @author Estácio Pereira.
 */
@Service
@Transactional
public class SecurityManagerBean implements SecurityManager {

    private static final int RSA_KEY_SIZE = 512;
    private Algorithm algorithm;

    private static final String ID_CLAIM = "id";
    private static final String EMAIL_CLAIM = "email";

    public SecurityManagerBean() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.algorithm = Algorithm.RSA256(publicKey, privateKey);
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            getDecodedJWT(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private DecodedJWT getDecodedJWT(String token) {
        JWTVerifier verifier = JWT.require(this.algorithm)
                .withIssuer("auth0")
                .build();
        return verifier.verify(token);
    }

    @Override
    public String generateToken(User user) {
        return JWT.create()
                .withClaim(ID_CLAIM, user.getId())
                .withClaim(EMAIL_CLAIM, user.getEmail())
                .sign(this.algorithm);
    }

    @Override
    public User getUserFromToken(String token) {
        DecodedJWT jwt = getDecodedJWT(token);
        Long id = jwt.getClaim(ID_CLAIM).asLong();
        String email = jwt.getClaim(EMAIL_CLAIM).asString();

        return new User(id, email);
    }

}
