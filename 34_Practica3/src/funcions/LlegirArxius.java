/*
 * ==== "CLASE" LLEGIR ARXIUS ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 * 
 */
package funcions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LlegirArxius {
    String arxiuUsuaris;
    String arxiuEntitats;
    String arxiuActivitats;

    /** Constructor de la clase LlegirArxius 
     * @param aU Arxiu Usuaris
     * @param aE Arxiu Entitats
     * @param aA Arxiu Activitats
     */
    public LlegirArxius (String aU, String aE, String aA) {
        arxiuActivitats = aA;
        arxiuEntitats = aE;
        arxiuUsuaris = aU;
    }

    /** Metode per llegir tots els usuaris 
     * @return Llista STRING de tots els usuaris. 
     * @throws FileNotFoundException Si no troba el arxiu... 
     */
    public String[] llegirUsuaris() throws FileNotFoundException{
        String retorn[];
        Scanner f = new Scanner(new File(arxiuUsuaris));
        f.nextLine(); // Nos saltamos la cabecera.
        int nLinies = 0;
        while (f.hasNextLine()) {
            nLinies++;
            f.nextLine();
        }
        f.close();
        
        retorn = new String[nLinies];
        Scanner g = new Scanner(new File(arxiuUsuaris));
        g.nextLine(); // Volvemos a saltarnos la cabecera.
        for (int i = 0; i < nLinies; i++) {
          retorn[i] = g.nextLine(); 
        }
        return retorn;
    }

    /** Metode per llegir les entitats del arxiu 
     * @return LLista String de les entitats 
     * @throws FileNotFoundException Si no troba el arxiu... 
     */
    public String[] llegirEntitats() throws FileNotFoundException {
        String retorn[];
        Scanner f = new Scanner(new File(arxiuEntitats));
        f.nextLine(); // Ens saltem la capsalera
        int nLinies = 0;
        // Calculem cuantes linies te el arxius. 
        while (f.hasNextLine()) {
            nLinies++;
            f.nextLine();
        }
        f.close();
        
        retorn = new String[nLinies];
        Scanner g = new Scanner(new File(arxiuEntitats));
        g.nextLine(); // Volvemos a saltarnos la cabecera.
        for (int i = 0; i < nLinies; i++) {
          retorn[i] = g.nextLine(); 
        }
        return retorn;
    }

    /** Metode per llegir les activitats 
     * @return LLista String de les activitats llegides. 
     * @throws FileNotFoundException Si no troba el arxiu.. 
     */
    public String[] llegirActivitats() throws FileNotFoundException {
        String retorn[];
        Scanner f = new Scanner(new File(arxiuActivitats));
        int nLinies = 0;
        while (f.hasNextLine()) {
            nLinies++;
            f.nextLine();
        }
        f.close();
        
        retorn = new String[nLinies];
        Scanner g = new Scanner(new File(arxiuActivitats));
        for (int i = 0; i < nLinies; i++) {
          retorn[i] = g.nextLine(); 
        }
        return retorn;
    }
}
