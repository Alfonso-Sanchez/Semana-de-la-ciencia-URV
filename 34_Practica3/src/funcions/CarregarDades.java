/*
 * ==== "CLASE" CARREGAR DADES ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 * 
 */
package funcions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import clases.*;

public class CarregarDades {
    String[] dataActivitats, dataEntitats, dataUsuaris;

    /** Constructor de la clase CarregarDades
     * @param dataActivitatsInput Dades de activitats a carregar en memoria. 
     * @param dataEntitatsInput Dades de entitats a carregar en memoria. 
     * @param dataUsuarisInput Dades de usuaris a carregar en memoria. 
     */
    public CarregarDades (String[] dataActivitatsInput, String[] dataEntitatsInput, String [] dataUsuarisInput) {
        dataActivitats = dataActivitatsInput;
        dataEntitats = dataEntitatsInput;
        dataUsuaris = dataUsuarisInput;
    }

    /** Metode per carregar en memoria totes les activitats en una llista. 
     * @return LListaActivitats generada. 
     */
    public LlistaActivitats carregarLlistaActivitats () {
        String[] dataPartidaActivitats = new String[11]; // Agafem la mida maxima de columnes de dades (taller cols = 9 + 1 identificador de objecte)
        // Generem ara tots els objectes y llistes pertinents. 
        LlistaActivitats llistaActivitats = new LlistaActivitats(dataActivitats.length + 100);

        for (int i = 0; i < dataActivitats.length; i++) {
            dataPartidaActivitats = dataActivitats[i].split(";");
            if (dataPartidaActivitats[0].equals("Xerrada")) {
                int codPost = Integer.parseInt(dataPartidaActivitats[3]);
                short dia = Short.parseShort(dataPartidaActivitats[4]);
                llistaActivitats.afegirActivitat(new Xerrada(dataPartidaActivitats[1], dataPartidaActivitats[2], codPost, dia, dataPartidaActivitats[5]));
            } else if (dataPartidaActivitats[0].equals("Visita")) {
                int codPost = Integer.parseInt(dataPartidaActivitats[2]);
                short dia = Short.parseShort(dataPartidaActivitats[3]);
                boolean audiGuia = Boolean.parseBoolean(dataPartidaActivitats[5]);
                boolean adaptatCecs = Boolean.parseBoolean(dataPartidaActivitats[6]);
                llistaActivitats.afegirActivitat(new Visita(dataPartidaActivitats[1], codPost, dia, dataPartidaActivitats[4], audiGuia, adaptatCecs, dataPartidaActivitats[7]));
            } else if (dataPartidaActivitats[0].equals("Taller")) {
                int codPost = Integer.parseInt(dataPartidaActivitats[2]);
                int dia = Integer.parseInt(dataPartidaActivitats[3]);
                int hora = Integer.parseInt(dataPartidaActivitats[4]);
                int duracio = Integer.parseInt(dataPartidaActivitats[5]);
                int capacitat = Integer.parseInt(dataPartidaActivitats[6]);
                int numInscripcions = Integer.parseInt(dataPartidaActivitats[8]);
                int sumaPuntuaciones = Integer.parseInt(dataPartidaActivitats[9]);
                int numPuntuaciones = Integer.parseInt(dataPartidaActivitats[10]);
                llistaActivitats.afegirActivitat(new Taller(dataPartidaActivitats[1], codPost, dia, hora, duracio, capacitat, dataPartidaActivitats[7], numInscripcions, sumaPuntuaciones, numPuntuaciones));
            }
        }
        return llistaActivitats;
    }

    /** Metode per carregar en memoria tots els usuaris. 
     * @return LlistaUsuaris carregada en memoria.
     */
    public LlistaUsuaris carregarLlistaUsuaris() {
        Usuari u;
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(dataUsuaris.length+20);
        String[] dataPartidaUsuaris = new String[5];
        for (int i = 0; i < dataUsuaris.length; i++) {
            dataPartidaUsuaris = dataUsuaris[i].split(";");
            int codPost = Integer.parseInt(dataPartidaUsuaris[2]);
            int numInscripcions = Integer.parseInt((dataPartidaUsuaris[3]));
            u = new Usuari(dataPartidaUsuaris[0], dataPartidaUsuaris[1], codPost, numInscripcions);
            llistaUsuaris.afegirUsuari(u);
        }
        return llistaUsuaris;
    }

    /** Metode per carregar en memoria totes les Entitats. 
     * @return LLista de totes les entitats carregades a memoria. 
     */
    public LlistaEntitats carregarLlistaEntitats() {
        Entitat e;
        LlistaEntitats llistaEntitats = new LlistaEntitats(dataEntitats.length+20);
        String[] dataPartidaEntitats = new String[5];
        for (int i = 0; i < dataEntitats.length; i++) {
            dataPartidaEntitats = dataEntitats[i].split(";");
            e = new Entitat(dataPartidaEntitats[0], dataPartidaEntitats[1], dataPartidaEntitats[2]);
            llistaEntitats.afegirEntitat(e);
        }
        return llistaEntitats;
    }

    /** Metode per carregar totes les reserves. A partir del serialitzat. 
     * @param numReservesArxiu Archiu on es guarda el nombre de reserves guardades en el ultim guardat. 
     * @param reservesDat Archiu serialitzat on es guarda les reserves. 
     * @return LLista Reserves carregat a memoria. 
     * @throws ClassNotFoundException Excepcio si no troba la clase Reserva o alguna subclase com taller o usuari. 
     * @throws IOException Excepcio si no troba algun arxiu indicat per parametre. 
     */
    public LlistaReserves carregarLlistaReserves(String numReservesArxiu, String reservesDat) throws ClassNotFoundException, IOException  {
            Reserva r;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(reservesDat));
            Scanner f = new Scanner(new File(numReservesArxiu));
            int numReserves = Integer.parseInt(f.nextLine());
            LlistaReserves llistaReserves = new LlistaReserves(numReserves+120);
            for (int i = 0; i < numReserves; i++) {
                r =  (Reserva) ois.readObject();
                llistaReserves.afegirReserva(r);
            } 
            ois.close();
            f.close();

            return llistaReserves;
    }
}