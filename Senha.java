import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Armazena somente salt e hash. A senha digitada nunca vai para o arquivo. */
public final class Senha {
    private static final int ITERACOES = 600_000;
    private final byte[] salt;
    private final byte[] hash;

    public Senha(String texto) {
        if (texto == null || !texto.matches("^(?=.*[A-Z])(?=.*\\d).{12,}$")) {
            throw new IllegalArgumentException("Senha: mínimo de 12 caracteres, uma maiúscula e um número.");
        }
        salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        hash = calcular(texto, salt);
    }

    private Senha(byte[] salt, byte[] hash) {
        this.salt = salt.clone();
        this.hash = hash.clone();
    }

    public boolean confere(String tentativa) {
        return tentativa != null && MessageDigest.isEqual(hash, calcular(tentativa, salt));
    }

    public String paraArmazenamento() {
        return "pbkdf2-sha256$" + ITERACOES + "$" + Base64.getEncoder().encodeToString(salt)
                + "$" + Base64.getEncoder().encodeToString(hash);
    }

    public static Senha doArmazenamento(String registro) {
        String[] partes = registro.split("\\$", -1);
        if (partes.length != 4 || !partes[0].equals("pbkdf2-sha256")
                || !partes[1].equals(String.valueOf(ITERACOES))) {
            throw new IllegalArgumentException("Registro de senha inválido.");
        }
        byte[] salt = Base64.getDecoder().decode(partes[2]);
        byte[] hash = Base64.getDecoder().decode(partes[3]);
        if (salt.length != 16 || hash.length != 32) {
            throw new IllegalArgumentException("Registro de senha inválido.");
        }
        return new Senha(salt, hash);
    }

    private static byte[] calcular(String texto, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(texto.toCharArray(), salt, ITERACOES, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Não foi possível processar a senha.", e);
        } finally {
            spec.clearPassword();
        }
    }
}
