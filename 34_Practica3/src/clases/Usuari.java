/*
 * ==== CLASE USUARI  ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * 
 */
package clases;
import java.io.Serializable;
import java.util.Objects;

public class Usuari implements Serializable{
    private static final long serialVersionUID = 1L;

    private String alies;
    private String correuElectronic;
    private int codiPostal;
    private int numInscripcions;


    /** Constructor del Usuari
     * @param alies Alies del usuari
     * @param correuElectronic Correu electronic del usuari
     * @param codiPostal Codi postal del usuari
     * @param numInscripcions Nombre de inscripcions del usuari. (si es nou posar a 0!)
     */
    public Usuari(String alies, String correuElectronic, int codiPostal, int numInscripcions) {
        this.alies = alies;
        this.correuElectronic = correuElectronic;
        this.codiPostal = codiPostal;
        this.numInscripcions = numInscripcions;
    }

    /** Metode copia del usuari.
     * @return Copia del Objecte Usuari
     */
    public Usuari copia() {
       return this;
    }

    /** Metode per actualitzar el nombre de inscripcions del usuari utilitzat per llista reserves. 
     * @return Objecte Usuari actualizat. 
     */
    public Usuari actualizar() {
        numInscripcions++;
        return this;
    }

    /**
     * Getter del numero d'inscripcions
     * @return numInscripcions
     */
    public int getNumInscripcions() {
        return numInscripcions;
    }

    /**
     * Setter del numero d'Inscripcions
     * @param numInscripcions
     */
    public void setNumInscripcions(int numInscripcions) {
        this.numInscripcions = numInscripcions;
    }

    /**
     * Getter del alies
     * @return alies
     */
    public String getAlies() {
        return alies;
    }

    /**
     * Setter del alies
     * @param alies
     */
    public void setAlies(String alies) {
        this.alies = alies;
    }

    /**
     * Getter del correu electronic
     * @return correuElectronic
     */
    public String getCorreuElectronic() {
        return correuElectronic;
    }

    /**
     * Setter del correu electronic
     * @param correuElectronic
     */
    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    /**
     * Getter del codi postal
     * @return CodiPosta
     */
    public int getCodiPostal() {
        return codiPostal;
    }

    /**
     * Setter del codi postal
     * @param codiPostal
     */
    public void setCodiPostal(int codiPostal) {
        this.codiPostal = codiPostal;
    }

    public String toString() {
        return "Alies: " + alies + "\nCorreu Electronic: " + correuElectronic + "\nCodi Postal: " + codiPostal + "\nNombre d'Inscripcions: " + numInscripcions;
    }

    /** 
     * Metode per actualizar el nombre de inscripcions del usuari. 
     */
    public void augmentarInscripcions() {
        numInscripcions++;
    }

    /**
     * Metode per reduir el nombre de inscripcions del usuari.
     */
    public void reduirInscripcions() {
        if (numInscripcions > 0) {
            numInscripcions--;
        }
    }
    /** Comproba a partir del alies del usuari si son iguals o no.
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
        Usuari otroUsuari = (Usuari) obj;
        return Objects.equals(alies, otroUsuari.alies);
    } 

}
