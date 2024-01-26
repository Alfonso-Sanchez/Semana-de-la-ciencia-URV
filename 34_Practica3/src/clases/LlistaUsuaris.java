/*
 * ==== CLASE LLISTA USUARIS ====
 * 
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package clases;
public class LlistaUsuaris {

    private int nElem;
    private Usuari[] llista;

    /** Constructor LlistaUsuaris amb una mida definida per parametre. 
     * @param mida Mida de la llista.
     */
    public LlistaUsuaris(int mida) {
        llista = new Usuari[mida];
        nElem = 0;
    }

    /**
     * Constructor LlistaUsuaris sense mida definida per parametre. 
     * Mida per defecte 100 usuaris. 
     */
    public LlistaUsuaris() {
        llista = new Usuari[100];
        nElem = 0;
    }

    /**
     * Esborrar un element de la llista
     * @param posicio asumim que va desde 0 fins a nElem-1 (si posicio = nElem -1 la llista técnicament no es modifica però si que decrementa el nElem)
     */
    public void afegirUsuari(Usuari u) {
        if (nElem < llista.length) {
            llista[nElem] = u.copia();
            nElem++;
        }
    }

    
    /** Metode per eliminar un usuari en una posicio concreta. Utilitzada especialment per altres clases. 
     * @param posicio
     */
    /* public void treureUsuari(int posicio) {
        if (posicio < nElem) {
            while (posicio < nElem-1) {
                llista[posicio] = llista[posicio+1];
                posicio++;
            }

            nElem--;
        }
    } */

    public String toString() {
        String s = "";
        for (int i = 0; i < nElem; i++) {
            s += llista[i].toString() + "\n";
        }
        return s;
    }

    /** Metode per obtenir el nombre de elements de la llista.
     * @return Nombre de elements en la llista.
     */
    public int getNElem() {
        return nElem;
    }

    /** Metode per buscar un usuari per el seu alies i obtenir el Objecte Usuari corresponent si existeix. 
     * @param alies
     * @return NULL = No existeix eixe usuari / Objecte Usuari = Existeix el usuari per el alies indicat. 
     */
    public Usuari buscarUsuari(String alies) {
        for (int i = 0; i < nElem; i++) {
            if (llista[i].getAlies().equals(alies)) return llista[i];
        }
        return null;
    }

    /** Metode per obtenir un element de la llista en una posicio indicada per parametre. 
     * @param pos Posicio de la llista a obtenir. 
     * @return Objecte Usuari en la posicio de la llista indicada.
     */
    public Usuari getUsuari(int pos) {
        return llista[pos];
    }    
}
