/*
 * ==== CLASE RESERVA ====
 * 
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package clases;
import java.io.Serializable;

public class Reserva implements Serializable{
    private static final long serialVersionUID = 1L; // Utilitzat per fer el serialitzat amb la seva clase pare Taller i Usuari.

    private static int codiReserva = 0;
    private String codi;
    private Taller taller;
    private Usuari usuari;

    /**
     * Constructor de Reserva
     * @param taller taller que reservamos
     * @param usuari usuario que reserva
     */
    public Reserva(Taller taller, Usuari usuari) {
       
        //this.taller.nuevaPlaza(); //aumentamos de plaza
        if (taller.getNumInscripcions() < taller.getCapacitatMax()) {
            codiReserva++;
            this.taller = taller.actualizarInscripcions();
            this.codi = taller.getCodi()+"R"+codiReserva;
            this.usuari = usuari.actualizar();
        } else {
            System.out.println("Tot ple"); // PASAR A EXCEPCION
        }
    }

    /** Metode per obtenir el codi de la reserva. 
     * @return String - Codi de la reserva. 
     */
    public String getCodiReserva() {
        return codi;
    }

    /** Aquest metode afegeix el grau de satisfaccio al taller indicat. 
     * @param num - Grau de satisfaccio 0-10.
     */
    public void grauSatisfaccio(int num) {
        taller.sumaPuntuacion(num);
    }

    public String toString() {
        return "Taller: " + taller.getNom() + "\nUsuari: " + usuari.getAlies() + "\nCodi Reserva: " + codi;
    }

    /** Getter per el Objecte Usuari de la reserva. 
     * @return Objecte usuari de la reserva. 
     */
    public Usuari getUsuari() {
        return usuari;
    }

    /** Getter per el Objecte Taller de la reserva.
     * @return Objecte Taller. 
     */
    public Taller getTaller() {
        return taller;
    }

    /** Setter per cambiar un taller de la reserva. 
     * @param t Objecte Taller. 
     */
    public void setTaller(Taller t) {
        this.taller = t;
    }

    /** Setter per cambiar un usuari de la reserva. 
     * @param u Objecte Usuari 
     */
    public void setUsuari(Usuari u) {
        this.usuari = u;
    }
}
