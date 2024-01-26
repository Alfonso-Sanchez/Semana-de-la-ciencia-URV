/*
 * ==== "CLASE" GUARDAR DADES ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 * 
 */
package funcions;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import clases.*;

public class GuardarDades {
    String arxiuEntitats;
    String arxiuUsuaris;
    String arxiuActivitats;
    String arxiuReserves;
    String arxiuNumReserves;
    
    /** Constructor de la clase guardar dades 
     * @param arxiuE Arxiu entitats
     * @param arxiuU Arxiu Usuaris
     * @param arxiuA Arxiu Activitats
     * @param arxiuR Arxiu Reserves 
     * @param arxiuNR Arxiu Nombre de reserves 
     */
    public GuardarDades (String arxiuE, String arxiuU, String arxiuA, String arxiuR, String arxiuNR) {
            arxiuEntitats = arxiuE;
            arxiuUsuaris = arxiuU;
            arxiuActivitats = arxiuA;
            arxiuReserves = arxiuR;
            arxiuNumReserves = arxiuNR;
    }

    /** Metode per guardar les entitats rebudes per parametre en el arxiu indicat al constructor. 
     * @param lle LLista entitats 
     * @throws FileNotFoundException Si no troba el arxiu.. 
     */
    public void guardarEntitats (LlistaEntitats lle) throws FileNotFoundException {
            PrintWriter writerEntitats = new PrintWriter(arxiuEntitats);
            Entitat e;
            
            writerEntitats.println("NomEntitat; Telefon; CorreuElectronic");
            for (int i = 0; i < lle.getNElem(); i++) {
                e = lle.getEntitat(i);
                writerEntitats.println(e.getNom() + ";" + e.getTelefon()  + ";" + e.getCorreuElectronic());
            }
            writerEntitats.close();
    }

    /** Metode per guardar els usuaris al arxiu. 
     * @param llu llista usuaris
     * @throws FileNotFoundException Si no troba el arxiu... 
     */
    public void guardarUsuaris (LlistaUsuaris llu) throws FileNotFoundException {
            PrintWriter writerUsuaris = new PrintWriter(arxiuUsuaris);
            Usuari u;
            writerUsuaris.println("Alies; Correu; CodiPostal");
            for (int i = 0; i < llu.getNElem(); i++) {
                u = llu.getUsuari(i);
                writerUsuaris.println(u.getAlies() + ";" + u.getCorreuElectronic() + ";" + u.getCodiPostal() + ";" + u.getNumInscripcions());
            }
            writerUsuaris.close();
    }

    /** Metode per guardar les activitats al arxiu 
     * @param lla LLista activitats. 
     * @throws FileNotFoundException Si no troba el arxiu ... 
     */
    public void guardarActivitats (LlistaActivitats lla) throws FileNotFoundException {
            PrintWriter writerActivitats = new PrintWriter(arxiuActivitats);
            Xerrada x;
            Visita v;
            Taller t;
            for (int i = 0; i < lla.getNElem(); i++) {
                if (lla.getActivitat(i) instanceof Xerrada) {
                    x = (Xerrada) lla.getActivitat(i);
                    writerActivitats.println("Xerrada;" + x.getNomPersona()  + ";" + x.getNom() + ";" + x.getCodiPostal() + ";" + x.getDia() + ";" + x.getNomEntitat()); 
                } else if (lla.getActivitat(i) instanceof Visita) {
                    v = (Visita) lla.getActivitat(i);
                    writerActivitats.println("Visita;" + v.getNom() + ";" + v.getCodiPostal() + ";" + v.getDia() + ";" + v.getLocalitzacio() + ";" + v.teAudioguia() + ";" + v.esAdaptPerCecs() + ";" + v.getNomEntitat());
                } else if (lla.getActivitat(i) instanceof Taller) {
                    t = (Taller) lla.getActivitat(i);
                    writerActivitats.println("Taller;" + t.getNom() + ";" + t.getCodiPostal() + ";" + t.getDia() + ";" + t.getHora() + ";" + t.getDurada() + ";" + t.getCapacitatMax() + ";" + t.getNomEntitat() + ";" + t.getNumInscripcions() + ";" + t.getSumaPuntuaciones() + ";" + t.getNumPuntuaciones());
                }
            }
            writerActivitats.close();
    }

    /** Metode per guardar les reserves en el arxiu. 
     * @param llr LLista de reserves 
     * @throws IOException Si a l'hora de guardar les dades, dona error de IO... 
     */
    public void guardarReserves (LlistaReserves llr) throws IOException {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arxiuReserves));
            // Generamos el archivo .dat
            Reserva r;
            for (int i = 0; i < llr.getNElem(); i++) {
                r = llr.getReserva(i);
                oos.writeObject(r);
            }
            oos.close();
            PrintWriter writerNumReserves = new PrintWriter(arxiuNumReserves);
            writerNumReserves.println(llr.getNElem()); // Debemos saber cuantos elementos tenemos para asi poder generar los objetos en la lectura! 
            writerNumReserves.close();
    }
}
