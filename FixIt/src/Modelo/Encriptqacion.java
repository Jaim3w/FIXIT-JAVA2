
package Modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rdlfp
 */
public class Encriptqacion {
    
    //creo nla clase pra luego mandarlo a llamar en la class usuarios y meterlo en el set contraseña para que encripte
    public static String encriptar(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(texto.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar con SHA-256", e);
        }
    }
}
