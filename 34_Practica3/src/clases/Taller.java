/*
 * ==== CLASE TALLER  ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * 
 */
package clases;
import java.util.Objects;

public class Taller extends Activitat {
    private static final long serialVersionUID = 1L; // Utilitzat per fer el serialitzat de reserva. Utilitza el mateix codi que la seva clase pare ACTIVITAT.

    private int hora;
    private int durada; //en minutos, así no se tiene que hacer un float
    private int capacitatMax;
    private int numInscripcions;
    private int sumaPuntuaciones = 0;
    private int numPuntuaciones = 0;

    /** Constructor per al taller. 
     * @param nom Nom del taller
     * @param codiPostal Codi postal on es realitza el taller
     * @param dia Dia que es fara el taller.
     * @param hora Hora que es fara el taller
     * @param durada Durada en minuts que tindra el taller.
     * @param capacitatMax Capacitat maxima que tindra el taller.
     * @param entitat Entitat que organitza el taller
     * @param numInscripcions Num de inscripcions que te el taller. (SI ES NOU POSAR A 0!!)
     * @param sumaPuntuaciones Suma de puntuacions que te el taller. (SI ES NOU POSAR A 0!!)
     * @param numPuntuaciones Nombre de puntuacions que te el taller. (SI ES NOU POSAR A 0!!)
     */
    public Taller(String nom, int codiPostal, int dia, int hora, int durada, int capacitatMax, String entitat, int numInscripcions, int sumaPuntuaciones, int numPuntuaciones) {
        super(nom, codiPostal, dia, entitat);
        this.hora = hora;
        this.durada = durada;
        this.capacitatMax = capacitatMax;
        this.numInscripcions = numInscripcions;
        this.sumaPuntuaciones = sumaPuntuaciones;
        this.numPuntuaciones = numPuntuaciones;
    }


    /** Metode per actualitzar el nombre de inscripcions que te el taller. Utilitzat per llistaReserves. 
     * @return Objecte Taller actualitzat! 
     */
    public Taller actualizarInscripcions() {
        this.numInscripcions++;
        return this;
    }

    /** Getter del nombre de inscripicions que te el taller. 
     * @return Numero de inscripcions del taller.
     */
    public int getNumInscripcions() {
        return numInscripcions;
    }

    /** Getter de la hora que es realitza el taller. 
     * @return Hora en que es fa el taller. 
     */
    public int getHora() {
        return hora;
    }

    /** Getter per obtenir la durada del taller en minuts.
     * @return Durada del taller.
     */
    public int getDurada() {
        return durada;
    }

    /** Getter per obtenir la capacitat maxima del taller.
     * @return Capacitat del taller. 
     */
    public int getCapacitatMax() {
        return capacitatMax;
    }
 
    /** Getter per obtenir la suma de puntuacions que te el taller.
     * @return Suma de puntuacions del taller. 
     */
    public int getSumaPuntuaciones() { 
        return sumaPuntuaciones;
    }

    /** Getter per obtenir el nombre de puntuacions que te un taller.
     * @return Nombre de puntuacions del taller. 
     */
    public int getNumPuntuaciones() {
        return numPuntuaciones;
    }

    /** Setter per cambiar la hora del taller. 
     * @param hora Hora nova del taller. 
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /** Setter per cambiar la durada del taller. 
     * @param durada Durada nova en minuts.
     */
    public void setDurada(int durada) {
        this.durada = durada;
    }

    /** Setter per cambiar la capacitat maxima del taller. 
     * @param capacitatMax Capacitat nova del taller. 
     */
    public void setCapacitatMax(int capacitatMax) {
        this.capacitatMax = capacitatMax;
    }

    /** Metode per sumar la puntuacio al taller.
     * Las puntuaciones NO se podrán cambiar fijamente, si no que un usuario simplemente da una puntuación del taller y ya
     * @param num Puntuacio entre el 0-10 a afegir.
     */
    public void sumaPuntuacion(int num) {
        sumaPuntuaciones += num;
        numPuntuaciones++;
    }

    /** Metode per obtenir la mitjana de puntuacio de un taller. 
     * @return Mitjana de puntuacio del taller. 
     */
    public float getMitjanaPuntuacion() {
        float mitjana = 0;
        if (numPuntuaciones != 0) {
            mitjana = (float)sumaPuntuaciones/numPuntuaciones;
        }
        return mitjana;
    }

    public String toString() {
        return (super.toString() + "\nHora: " + hora + "\nDurada (minutos): " + durada + "\nCapacitad Maxima: " + capacitatMax + "\nNum de inscripcions:"+ numInscripcions + ( (numPuntuaciones != 0) ? 
        ("\nSuma de puntuaciones: " + sumaPuntuaciones + " " + "\nMitjana de puntuaciones: " + getMitjanaPuntuacion()) : ""));
    }

    /** Metode copia del taller.
     * @return Copia del taller. 
     */
    public Taller copia() {
        return this;
    }

    /** Getter per obtenir el codi del taller. 
     * @return codi del taller. 
     */
    public String  getCodi() {
        return codi;
    }


    /** Metode per obtenir la proporcio de ocupacio del taller. 
     * @return
     */
    public float getProporcioOcupacio() {
        return (numInscripcions * 1.0f)/(capacitatMax * 1.0f); // Convertim els ints a float, per a un correcte us de les dades. 
    }

    /*
    * Necesitamos un metodo equals para que permita comprobar si el ID del taller es igual al ID que nos entra y asi confirmar que 
    * el taller es el mismo. Ya que el metodo equals en java compara por referencia, y nos es util para algunos casos.
    */

    /** Comproba a partir del codi del taller si son iguals o no.
    * @return true  = iguals
    * @return false = no iguals
    * 
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Taller otroTaller = (Taller) obj;
        return Objects.equals(codi, otroTaller.codi);
    } 
}
