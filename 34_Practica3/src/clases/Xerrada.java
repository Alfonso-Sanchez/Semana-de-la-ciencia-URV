/*
 * ==== CLASE XERRADA  ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * 
 */

package clases;
public class Xerrada extends Activitat {
    private String nomPersona; //no creo que haga falta, ya que el nombre de la entidad que hace la actividad está guardada en "entitat"
    private static int hora = 17; //siempre es 17

    /** Constructor de la clase Xerrada. 
     * @param nomPersona nom de la persona que fa la xerrada. 
     * @param nom nom de la xerrada
     * @param codiPostal codi postal de la xerrada
     * @param dia dia que es fa la xerrada
     * @param entitat entitat que organitza la xerrada
     */
    public Xerrada(String nomPersona, String nom, int codiPostal, int dia, String entitat) {
        super(nom, codiPostal, dia, entitat);
        this.nomPersona = nomPersona; //dejo esto de prueba
    }

    /**
     * Constructor que solamente existe para que no se aumente el código
     */
    private Xerrada(String codi, String nom, int codiPostal, int dia, String nomEntitat, String nomPersona) {
        super(codi, nom, codiPostal, dia, nomEntitat);
        this.nomPersona = nomPersona;
    }

    /** Getter del nom de la persona que fa la xerrdada. 
     * @return Nom de la persona que fa la xerrda. 
     */
    public String getNomPersona() {
        return nomPersona;
    }

    /** Setter del nom de la persona que fa la xerrada. 
     * @param nomPersona Nom de la persona que fa la xerrada.
     */
    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }
   
    
    public String toString() {
        return (super.toString() + "\nNom del que fa la xerrada:  " + nomPersona + "\nHora: " + hora);
    }

    /** Metode copia de la clase xerrada. 
     * @return Objecte copia de xerrada. 
     */
    public Xerrada copia() {
        return new Xerrada(getCodi(), getNom(), getCodiPostal(), getDia(), getNomEntitat(), getNomPersona());
    }
    /** Metode per obtenir el codi de la xerrada. 
     * @return codi de la xerrada
     */
    public String getCodi() {
        return codi;
    }
}
