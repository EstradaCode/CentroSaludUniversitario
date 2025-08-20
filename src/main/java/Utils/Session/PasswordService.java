package Utils.Session;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {

    // cost 12 → ajustá a 13–14 si el server lo permite
    private final int cost = Integer.parseInt(
            System.getenv().getOrDefault("BCRYPT_COST", "12")
    );

    /** Genera un hash seguro */
    public String hash(char[] raw) {
        try {
            return BCrypt.withDefaults().hashToString(cost, raw);
        } finally {
            java.util.Arrays.fill(raw, '\0'); // limpia el char[] de memoria
        }
    }

    /** Verifica password contra hash almacenado */
    public boolean verify(String hash, char[] raw) {
        try {
            BCrypt.Result result = BCrypt.verifyer().verify(raw, hash);
            return result.verified;
        } finally {
            java.util.Arrays.fill(raw, '\0');
        }
    }

    public boolean needsRehash(String hash) {
        int current = extractCost(hash);      // p.ej. "$2b$12$..." -> 12
        return current > 0 && current < cost; // rehash si subió el cost
    }

    /** Extrae el work factor de un hash bcrypt estándar "$2a$12$..." */
    private int extractCost(String hash) {
        if (hash == null || hash.length() < 7) return -1;
        try {
            // posiciones 4-6 tienen el cost en 2 dígitos (04..31)
            return Integer.parseInt(hash.substring(4, 6));
        } catch (Exception e) {
            return -1;
        }
    }
}

