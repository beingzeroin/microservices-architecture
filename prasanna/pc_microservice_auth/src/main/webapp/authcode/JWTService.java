


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class JWTService {
    private static Key signingKey;
    private static String cherlkey= "thisismnfke845whflefshdfasifhslafhdlksgdfkgsjdysecre2342342tk2eyforjwt3422";

    static{
        String base64Key = DatatypeConverter.printBase64Binary(cherlkey.getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Key);
        signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public static String createJWT(String id, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject("authservice demo")
                .setIssuer("prasannachereddy@bz")
                .signWith(SignatureAlgorithm.HS256, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static String decodeJWT(String jwt) {
        String response = "valid";
        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(jwt).getBody();
        }catch(Exception e) {
            e.printStackTrace();
            response = "invalid due to: " + e.getMessage();
        }
        return response;
    }


}
