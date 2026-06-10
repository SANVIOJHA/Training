import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

public class VerifyJwt {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjExIiwicm9sZSI6IkNVU1RPTUVSIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTc3OTAzNTY3OSwiZXhwIjoxNzc5MDM5Mjc5fQ.-k_yUcsXumId2GUiM9RtNiscvBqDAQZgL4HNI8THUhg";
        String secret = "mysecretkeymysecretkeymysecretkeymysecretkey";
        
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token);
            System.out.println("VERIFIED SUCCESSFULLY");
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}
