package util;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Classe que contém diversos métodos prontos que serão utilizados durante todo o projeto
 * @author rodolfo
 */
public class Utils {
    /**
     * Função responsável por converter a data em string para timestamp
     * @param data
     * @return 
     */
    public static Timestamp converterData(String data) {
        if (data == null) {
            return null;
        }

        try {
            Instant instant = Instant.parse(data);

            return Timestamp.from(instant);
        } catch (Exception e) {
            return null;
        }
    }
}
