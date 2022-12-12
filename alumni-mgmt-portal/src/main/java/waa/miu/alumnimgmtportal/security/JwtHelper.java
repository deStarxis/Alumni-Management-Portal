package waa.miu.alumnimgmtportal.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtHelper {
    @Autowired
    private Environment env;
    private final String secret;
    private final long expiration;

    public JwtHelper(Environment env){
        this.env = env;
        secret = this.env.getProperty("jwt.secret");
        expiration = Long.parseLong(this.env.getProperty("jwt.expiration"));
    }

    public String generateToken(String email, Map<String, Object> claims){
        return Jwts.builder()
                .setSubject(email)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

//    public String generateRefreshToken

    public String getSubject(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            System.out.println(e.getMessage());
        }catch (MalformedJwtException e){
            System.out.println(e.getMessage());
        }catch (ExpiredJwtException e){
            System.out.println(e.getMessage());
        }catch (UnsupportedJwtException e){
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        String result = null;
        if(validateToken(token)){
            result = getSubject(token);
        }
        return  result;
    }
}
