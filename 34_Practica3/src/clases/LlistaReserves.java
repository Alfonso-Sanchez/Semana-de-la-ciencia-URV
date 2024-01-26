/*
 * ==== CLASE LLISTA RESERVES ====
 * 
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package clases;
/*Clara */
public class LlistaReserves {
    
    private int nElem;
    private Reserva[] llista;

    
    /** Constructor amb una mida definida
     * @param mida
     */
    public LlistaReserves(int mida) {
        nElem = 0;
        llista = new Reserva[mida];
    }

    
    /**
     * Constructor amb una mida no definida, posem per defecte 100 reserves. 
     */
    public LlistaReserves() {
        nElem = 0;
        llista = new Reserva[100];
    }

    /** Metode per obtenir el numero de reserves que te un taller utilitzant com a cerca el codi de taller. 
     * @param codiTaller
     * @return Numero de reserves trobades en el taller indicat per parametre
     */
    public int numReservas(String codiTaller) {
        int num = 0;
        for (int i = 0; i < nElem; i++) {
            if (llista[i].getTaller().getCodi().equals(codiTaller)) num++;
        }
        return num;
    }


    /** Metode per afegir una reserva a la llista. 
     * @param r
     */
    public void afegirReserva(Reserva r) {
        if (nElem < llista.length) {
            llista[nElem] = r;
            nElem++;
        }
    } 


    public String toString() {
        String s = "";
        for (int i = 0; i < nElem; i++) {
            s += llista[i].toString() + "\n";
        }
        return s;
    }

    /** Metode per obtenir el numero de elements de la llista.
     * @return El numero de elements de la llista.
     */
    public int getNElem() {
        return nElem;
    }

    /** Metode per obtenir el usuari amb mes inscripcions. 
     * Es realitza en llista reserves, per que volem mirar tots el usuaris que al menys tinguin 1 inscripcio! 
     * @return Retorna el objecte usuari amb mes inscripcions. 
     */
    public Usuari usuariAmbMesInscripcions() {
        int maxInscripcions = 0;
        int posicio = 0;
        for (int i = 0; i < nElem; i++) {
            if (llista[i].getUsuari().getNumInscripcions() > maxInscripcions) {
                maxInscripcions = llista[i].getUsuari().getNumInscripcions();
                posicio = i;
            }
        }
        if (maxInscripcions > 0) { 
            return llista[posicio].getUsuari();
        } else {
            return null; // Si no existeix cap usuari, o cap reserva... 
        }
    }

    /** Obtenim una reserva en una posicio especifica de la llista.
     * @param pos Posicio de la llista que vols obtenir. 
     * @return Reserva en la posicio indicada.
     */
    public Reserva getReserva(int pos) {
        return llista[pos];
    }

    /** Comproba si un usuari es apuntat a un taller o no.
     * @param u
     * @param t
     * @return true / false segons si esta o no apuntat al taller. 
     */
    public boolean esUsuariApuntatATaller(Usuari u, Taller t) {
        boolean esApuntat = false;
        int i = 0;
        while (!esApuntat && i < nElem) {
           if (llista[i].getTaller().equals(t)  && llista[i].getUsuari().equals(u)) {
            esApuntat = true;
           }
           i++;
        }
        return esApuntat;
    }
    

    /** Genera una llista de usuaris apuntats a un taller pasat per parametre. 
     * @param u
     * @param t
     * @return LlistaUsuaris
     */
    public LlistaUsuaris usuarisApuntatsATaller(LlistaUsuaris u, Taller t) {
        LlistaUsuaris llistaRetorn = new LlistaUsuaris(t.getNumInscripcions());
        
        for (int i = 0; i < u.getNElem(); i++) {
            if (esUsuariApuntatATaller(u.getUsuari(i), t)) {
                llistaRetorn.afegirUsuari(u.getUsuari(i));
            }
        }
        return llistaRetorn;
    }
    
    /** Si safegeix una puntuacio es te que actualitzar tambe en la llista de reserves. 
     * @param u
     * @param t
     */
    public void actualitzarPuntuacions(Usuari u, Taller t) {
        int posicioTrobada = trobarReserva(u, t);
        if (posicioTrobada != -1) {
           Reserva r = llista[posicioTrobada];
           r.setTaller(t);
           llista[posicioTrobada] = r;
        }
    }

    /** Metode privat per trobar una reserva en la llista. 
     * @param u
     * @param t
     * @return posicio de la llista on es troba
     */
    private int trobarReserva(Usuari u, Taller t) {

        int posicioTrobada = -1;
        boolean noTrobat = true;
        int i = 0;
        while (noTrobat) { // Aquesta funcio solament es crida si la reserva es valida, per tant, sempre estara a la llista.
            if (llista[i].getTaller().equals(t) && llista[i].getUsuari().equals(u)) {
                posicioTrobada = i;
                noTrobat = false;
            }
            i++;
        }
        return posicioTrobada;
    }
}
