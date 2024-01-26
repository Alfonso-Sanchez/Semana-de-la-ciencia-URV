/*
 * ==== CLASE VISITA  ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * 
 */
package clases;
public class Visita extends Activitat {
    String localitzacio;
    boolean audioguia;
    boolean adaptPerCecs;

    /** Constructor de la clase Visita. 
     * @param nom Nom de la visita.
     * @param codiPostal Codi postal on es realitza la visita.
     * @param dia Dia de la visita
     * @param localitzacio Localitzacio de la visita
     * @param audioguia Te audioguia la visita?
     * @param adaptPerCecs La visita es adaptada per a cecs? 
     * @param entitat Entitat que organitza la visita. 
     */
    public Visita(String nom, int codiPostal, int dia, String localitzacio, boolean audioguia, boolean adaptPerCecs, String entitat) {
        super(nom, codiPostal, dia, entitat);
        this.localitzacio = localitzacio;
        this.audioguia = audioguia;
        this.adaptPerCecs = adaptPerCecs;
    }

    /** 
     * Constructor que se usa solamente para la copia, por eso es private
     */
    private Visita(String codi, String nom, int codiPostal, int dia, String nomEntitat, String localitzacio, boolean audioguia, boolean adaptPerCecs) {
        super(codi, nom, codiPostal, dia, nomEntitat);
        this.localitzacio = localitzacio;
        this.audioguia = audioguia;
        this.adaptPerCecs = adaptPerCecs;
    }

    /** Getter de la localitzacio de la visita.
     * @return Localitzacio de la visita. 
     */
    public String getLocalitzacio() {
        return localitzacio;
    }

    /**Getter de si la visita te audioGuia o no
     * @return True/False si te o no audioguia.
     */
    public boolean teAudioguia() {
        return audioguia;
    }

    /** Getter per obtenir si la visita es adaptada per a cecs. 
     * @return True/False si es o no adaptada per a cecs. 
     */
    public boolean esAdaptPerCecs() {
        return adaptPerCecs;
    }

    /** Setter per cambiar la localitzacio de la visita. 
     * @param localitzacio Localitzacio nova de la visita. 
     */
    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }

    /** Setter per cambiar si te audioguia la visita. 
     * @param audioguia Te audio guia? 
     */
    public void setAudioguia(boolean audioguia) {
        this.audioguia = audioguia;
    }

    /** Setter per cambiar si la visita esta adaptada per cecs. 
     * @param adaptPerCecs
     */
    public void setAdaptPerCecs(boolean adaptPerCecs) {
        this.adaptPerCecs = adaptPerCecs;
    }

    public String toString() {
        return (super.toString() + "\nLocalitzacio: " + localitzacio + ((audioguia) ? "\nTe audioguia" : "\nNo te audioguia") + 
        ((adaptPerCecs) ? "\nEs adapt per Cecs" : "\nNo es adapt per cecs"));
    }

    /** Metode per obtenir la copia de la visita. 
     * @return Objecte visita. 
     */
    public Visita copia() { 
        //String codi, String nom, int codiPostal, int dia, String nomEntitat, String localitzacio, boolean audioguia, boolean adaptPerCecs
        return new Visita(getCodi(), getNom(), getCodiPostal(), getDia(), getNomEntitat(), this.localitzacio, this.audioguia, this.adaptPerCecs);
    }
    /** Getter del codi de la visita. 
     * @return codi de la visita. 
     */
    public String  getCodi() {
        return codi;
    }
}
