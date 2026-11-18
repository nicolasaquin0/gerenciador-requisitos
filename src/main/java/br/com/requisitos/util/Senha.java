package br.com.requisitos.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class Senha {

    private static final SecureRandom RANDOM = new SecureRandom();

    private Senha() {
    }

    public static String gerarSalt() {
        byte[] bytes = new byte[16];
        RANDOM.nextBytes(bytes);
        return HexFormat.of().formatHex(bytes);
    }

    public static String gerarHash(String senha, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-256");
            byte[] hash = md.digest((salt + senha).getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean confere(String senha, String salt, String hash) {
        return MessageDigest.isEqual(gerarHash(senha, salt).getBytes(StandardCharsets.UTF_8),
                hash.getBytes(StandardCharsets.UTF_8));
    }
}
