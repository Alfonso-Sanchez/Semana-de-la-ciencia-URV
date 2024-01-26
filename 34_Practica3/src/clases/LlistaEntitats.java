/*
 * ==== CLASE LLISTA ENTITATS ====
 * 
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package clases;
public class LlistaEntitats {
    
    private int numDades;
    private Entitat[] llista;

    /** Metode per obtenir la llista directament (utilitzat unica i expresament per a la part grafica del codi)
     * @return Llista entitats.
     */
    public Entitat[] getLlista() {
        return llista;
    }

    /** Constructor de la llistaEntitats
     * @param mida mida maxima de la llista.
     */
    public LlistaEntitats (int mida){
        numDades = 0;
        llista = new Entitat[mida];
    }

    /** Metode per afegir una entitat a la llista.
     * @param e Entitat a afegir a la llista.
     */
    public void afegirEntitat(Entitat e) {
        if (numDades < llista.length) {
            llista[numDades] = e.copia();
        }
        numDades++;
    }

    /**
     * Esborrar un element de la llista
     * @param posicio asumim que va desde 0 fins a nElem-1 (si posicio = nElem -1 la llista técnicament no es modifica però si que decrementa el nElem)
     */
    public void treureEntitat(int posicio) {
        if (posicio < numDades) {
            while (posicio < numDades-1) {
                llista[posicio] = llista[posicio+1];
                posicio++;
            }

            numDades--;
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < numDades; i++) {
            s += llista[i].toString();
        }
        return s;
    }

    /** Metode per obtenir el numero de elements que tenim a la llista.
     * @return El numero de elements que tenim a la llista.
     */
    public int getNElem() {
        return numDades;
    }

    /** Fa una recerca a partir del nom, per tractar de trobar la entitat a la llista.
     * @param nom Nom de la entitat a trobar.
     * @return Entitat trobada
     * @return NULL Entitat no trobada.
     */
    public Entitat buscarEntitat(String nom) {
        for (int i = 0; i < numDades; i++) {
            if (llista[i].getNom().equals(nom)) return llista[i];
        }
        return null;
    }

    /** Metode per obtenir el element en una posicio de la llista.
     * @param pos Posicio de la llista
     * @return Entitat almazenada en la llista o NULL si no hi ha res. 
     */
    public Entitat getEntitat(int pos) {
        return llista[pos];
    }

}

