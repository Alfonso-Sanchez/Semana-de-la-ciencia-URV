/*
 * ==== Clase Activitat ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * 
 */

package clases;
import java.io.Serializable;

abstract class Activitat implements Serializable {
    private static final long serialVersionUID = 1L; // Utilitzat per fer el serialitzat de la clase activitat i les seves subclases.
    
    private static int codiNum = 100; //se le suma 1 cada vez que se crea una actividad de cualquier tipo
    protected String codi;
    private String nom;
    private int codiPostal;
    private int dia;
    private String nomEntitat;
    //nota: esto necesita la clase "entiat" hecha
    //private Entitat entitat;

    /**
     * Constructor
     * @param nomEntitat nomEntitat, 
     * @param nom
     * @param codiPostal
     * @param dia
     * @param entitat
     */
    public Activitat(String nom, int codiPostal, int dia, String entitat) {
        codi = entitat.substring(0,3) + String.valueOf(codiNum);
        this.nom = nom;
        this.codiPostal = codiPostal;
        this.dia = dia;
        this.nomEntitat = entitat;
        codiNum++; //se aumenta el código
    }

    /**
     * Constructor que se usa SOLAMENTE copiar una activitat, para así evitar incrementar el static codiNum
     * Para diferenciarlo, en vez de pasar Entitat entitat se pasa String nomEntitat y además se pasa el codi
     * Este constructor NO se debería de usar fuera de hacer copias, existe solamente para evitar el incremento
     * de codiNum. El constructor es public para que las subclases de Activitat puedan usarlo sin incrementar codiNum.
     */
    public Activitat(String codi, String nom, int codiPostal, int dia, String nomEntitat) {
        this.codi = codi;
        this.nom = nom;
        this.codiPostal = codiPostal;
        this.dia = dia;
        this.nomEntitat = nomEntitat;
    }

    /** Metode per obtenir el codi.
     * @return codi
     */
    public String getCodi() {
        return codi;
    }

    /** Metode per obtenir el nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /** Metode per obtenir el codi postal. 
     * @return codiPostal
     */
    public int getCodiPostal() {
        return codiPostal;
    }

    /** Metode per obtenir el dia.
     * @return dia
     */
    public int getDia() {
        return dia;
    }

    /** Metode per posar el nom, al nom de entrada per parametre. 
     * @param nom El nom que vols posar.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** Metode per posar el codi postal, al codi de entrada per parametre. 
     * @param codiPostal El codi postal que vols posar. 
     */
    public void setCodiPostal(int codiPostal) {
        this.codiPostal = codiPostal;
    }

    /** Metode per posar el dia, al dia de entrada per parametre. 
     * @param dia El nou dia que vols posar a la activitat.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /** Metode per obtenir el nom de la entitat que fa la activitat. 
     * @return nomEntitat.
     */
    public String getNomEntitat() {
        return nomEntitat;
    }

    /** Metode per posar el nom de la entitat, al rebut per parametre. 
     * @param e Entitat a la que vols asignar aquesta activitat.
     */
    public void setNomEntitat(Entitat e) {
        this.nomEntitat = e.getNom();
    }

    public String toString() {
        //String s = "Codi: " + codi + "\nNom: " + nom + "\nCodi Postal: " + codiPostal + "\nDia: " + dia;
        return "Codi: " + codi + "\nNom: " + nom + "\nCodi Postal: " + codiPostal + "\nDia: " + dia + "\nOrganitzador: " + nomEntitat;
    }

}
