/*
 * ==== "CLASE" LOG ====
 * 
 * Programador 2: eduard.vericat@estudiants.urv.cat
 * 
 */
package funcions;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// CLASE UTITLITZADA SOLAMENT EN MODE GRAFIC. 
public class Log {

    private static final Logger logger = Logger.getLogger(Log.class.getName());
    private static FileHandler fileHandler;
    private static LocalDateTime currentDateTime = LocalDateTime.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static String formattedDateTime = currentDateTime.format(formatter);

    static {
        try {
            // Configurar el manejador de archivo para escribir en un archivo llamado "log.txt"
            fileHandler = new FileHandler("log.txt", true);
            logger.addHandler(fileHandler);

            // Utilizar el formateador personalizado
            CustomFormatter formatter = new CustomFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Constructor per fer un missage de informacio al log. 
     * @param message
     */
    public static void logInfo(String message) {
        logger.info(formattedDateTime + "  " + message);
    }

    
    /** Constructor per fer un missage de warning al log. 
     * @param message
     */
    public static void logWarning(String message) {
        logger.warning(formattedDateTime + "  " + message);
    }
    
    /** Constructor per fer un missage de error al log. 
     * @param message
     */
    public static void logError(String message) {
        logger.severe(formattedDateTime + "  " + message);
    }

    /**
     * Metode per tancar el logfile. 
     */
    public static void closeLogFile() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }

    private static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            // Formato personalizado: solo mostrar la fecha y el mensaje
            return record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }
}


