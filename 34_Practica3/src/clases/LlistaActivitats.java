/*
 * ==== CLASE LLISTA ACTIVITATS ====
 * 
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package clases;
public class LlistaActivitats {

    private int nElem;
    private Activitat[] llista;

    /** Constructor de la lista activitats amb parametre. 
     * @param Mida Mida de la llista de activitats.
     */
    public LlistaActivitats(int mida) {
        llista = new Activitat[mida];
        nElem = 0;
    }

    /** Constructor de la llista activitats sense parametre. 
     * 
     */
    public LlistaActivitats() {
        llista = new Activitat[100];
        nElem = 0;
    }

    /** Metode per afegir una nova activitat a la llista.
     * @param a Activitat a afegir a la llista.
     */
    public void afegirActivitat(Activitat a) {
        if (nElem < llista.length) {
            if (a instanceof Taller) llista[nElem] = ((Taller)a).copia();
            else if (a instanceof Xerrada) llista[nElem] = ((Xerrada)a).copia();
            else if (a instanceof Visita) llista[nElem] = ((Visita)a).copia();
            nElem++;
        }
    }

    /**
     * Esborrar un element de la llista
     * @param posicio asumim que va desde 0 fins a nElem-1 (si posicio = nElem -1 la llista técnicament no es modifica però si que decrementa el nElem)
     */
    public void treureActivitat(int posicio) {
        if (posicio < nElem) {
            while (posicio < nElem-1) {
                llista[posicio] = llista[posicio+1];
                posicio++;
            }
            nElem--;
        }
    }

    /**
     * esborrar una activitat especifica, però es pasa el nom i no la posició
     * @param idActivitat de la activitat a esborrar
     */
    public void treureActivitatNom(String idActivitat) {
        boolean trobat = false;
        for (int i = 0; i < nElem && !trobat; i++) {
            if (llista[i].getCodi().equals(idActivitat)) {
                treureActivitat(i);
                trobat = true;
            }
        }
    }

    /** Metode per afegir una puntuacio al taller.
     * @param puntuacio Puntuacio a afegir
     * @param t Objecte taller al que vols afegir la puntuacio.
     */
    public void afegirPuntuacio(int puntuacio, Taller t) {
        for (int i = 0; i < nElem; i++) {
            if (llista[i].getCodi().equals(t.getCodi())) {
                if (llista[i] instanceof Taller) ((Taller)llista[i]).sumaPuntuacion(puntuacio);
                i = nElem; //per parar el bucle
            }
        }
    }

    public String toString() {

        String x = "===== Xerrades ====== \n";
        String t = "====== Tallers ====== \n";
        String v = "====== Visites ====== \n";

        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Taller) {
                t += llista[i].toString() + "\n";
            } else if (llista[i] instanceof Visita) {
                v += llista[i].toString()+"\n";
            } else if (llista[i] instanceof Xerrada) {
                x += llista[i].toString()+"\n";
            }
        }

        return x+t+v;
    }

    /** Getter del NElements
     * @return Numero de elements
     */
    public int getNElem() {
        return nElem;
    }

    //FUNCIONES PARA LOS EJERCICIOS
    /** Metode per obtenir una llista de activitats que fa una entitat concreta.
     * @param nom Nom de la entitat a utilitzar per a la cerca
     * @return LLista de totes les activitats de eixa entitat.
     */
    public LlistaActivitats entitatConcreta(String nom) {
        LlistaActivitats l2 = new LlistaActivitats(nElem);
        for (int i = 0; i < nElem; i++) {
            if (llista[i].getNomEntitat().equals(nom)) l2.afegirActivitat(llista[i]);
        }
        return l2;
    }

    /** Metode per obtenir una llista de activitats que es fa en un dia concret. 
     * @param dia Dia a utilitzar per a la cerca
     * @return LLista de totes les activitats de eixe dia.
     */
    public LlistaActivitats diaConcret(int dia) {
        LlistaActivitats l2 = new LlistaActivitats(nElem);
        for (int i = 0; i < nElem; i++) {
            if (llista[i].getDia() == dia) l2.afegirActivitat(llista[i]);
        }
        return l2;
    }


    /** Metode per fer una cerca de un taller a partir del seu codi en la llista de activitats.
     * @param idTaller Codi del taller per fer la cerca.
     * @return Objecte Taller.
     */
    public Taller buscarTaller(String idTaller) {
        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Taller && llista[i].getCodi().equals(idTaller)) return (Taller)llista[i];
        }
        return null;
    }

     /** Metode que a partir dels requisits de la practica calcula el taller amb mes exit de totes les activitats en la llista.
     * @return Objecte Taller que te mes exit segons requisits.
     */
    public Taller mesExit() {
        float maxProporcio = Float.MIN_VALUE;
        int posicio = 0;
        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Taller) {
                if (((Taller)llista[i]).getProporcioOcupacio() > maxProporcio) {
                    maxProporcio = ((Taller)llista[i]).getProporcioOcupacio();
                    posicio = i;
                }
            }
        }
        if (maxProporcio == 0.000000) { // Si la proporcio es 0.0000 no hi han incripcions en cap taller!
            return null;
        } else {
            return (Taller)llista[posicio];
        }
        
    }

    /** Metode per obtenir totes les visites que te una entitat, usant alguns parametres de entrada per la cerca.
     * @param nom Nom de la entitat que fa la activitat o activitats.
     * @param audio True / False (segons si vols que les visites de la cerca tinguin audioguia o no)
     * @param perCecs True / False (Segons si vols que els visites de la cerca estiguin adatptades per a cecs o no)
     * @return
     */
    public LlistaActivitats mostrarVisitesEntitat(String nom, boolean audio, boolean perCecs) {
        LlistaActivitats retorn; 
        Visita v;
        int numVisites = 0; 
        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Visita) {
                v = (Visita) llista[i];
                if (v.getNomEntitat().equals(nom) && v.adaptPerCecs == perCecs && v.audioguia == audio) {
                    numVisites++;
                }
            }
        }
        retorn = new LlistaActivitats(numVisites);

        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Visita) {
                v = (Visita) llista[i];
                if (v.getNomEntitat().equals(nom) && v.adaptPerCecs == perCecs && v.audioguia == audio) {
                    retorn.afegirActivitat(v);
                }
            }
        }

        return retorn;
    }

    /** Metode de cerca que retorna una llista de totes les xerrades que fa una persona concreta rebuda per parametre. 
     * @param nom Nom de la persona que fa la xerrada.
     * @return LListaActivitats amb totes les Xerrades que fa eixia persona. 
     * @return NULL si no existeix ninguna Xerrada per a eixa persona. 
     */
    public LlistaActivitats mostrarXerradesPersona(String nom) {
        Xerrada x;
        int numXerradesTrobades = 0;

        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Xerrada) {
                x = (Xerrada) llista[i];
                if (x.getNomPersona().equals(nom)) {
                    numXerradesTrobades++;
                }
            }
        }
        
        
        LlistaActivitats retorn = new LlistaActivitats(numXerradesTrobades);
        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Xerrada) {
                x = (Xerrada) llista[i];
                if (x.getNomPersona().equals(nom)) {
                    retorn.afegirActivitat(x);
                }
            }
        }
        return retorn;
    }

    /** Metode per obtenir un valor directament de la llista, independent del tipus. (util per guardar arxius, o mostrar dades en taules en grafics)
     * @param pos Posicio de la llista a obtindre
     * @return Activitat en la posicio de la llista. 
     */
    public Activitat getActivitat (int pos) {
        return llista[pos];
    }

    /** Metode que retorna una llista de Activitats de tots els tallers lliures.
     * @return LlistaActivitats de tallers lliures.
     */
    public LlistaActivitats tallersLliures() {
        LlistaActivitats retorn = new LlistaActivitats(nElem);
        for (int i = 0; i < nElem; i++) {
            if (llista[i] instanceof Taller){
                Taller t = (Taller)llista[i];
                if (t.getNumInscripcions() < t.getCapacitatMax()) {
                    retorn.afegirActivitat(t);
                }
            }
        }   
        return retorn;
    }
}
