/*
 * ==== FUNCIONS CONSOLA Y PART DEL GRAFIC ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 * Programador 2: eduard.vericat@estudiants.urv.cat
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package funcions;
import java.util.Scanner;

import clases.*;
/*
 * En compte de tenir totes les funcions en el main utilitzem una clase Funcions per tindre tots els casos de la practica. 
 */
public class Funcions {
    
    static Scanner s = new Scanner(System.in); //scanner global
    public Funcions() {
        // Solament necesitem un objecte funcions buit, ja que aquesta clase nomes es per funcions
        // de la aplicacio. 
    }

    /** Metode per mostrar el menu en pantalla.
     * 
     */
    public void menu() {
        System.out.println("1. Mostrar les dades de totes les llistes.");
        System.out.println("2. Mostrar activitats d'una entitat en concret.");
        System.out.println("3. Mostrar activitats d'un dia en concret.");
        System.out.println("4. Mostrar llista de tallers amb places disponibles.");
        System.out.println("5. Afegir una nova activitat.");
        System.out.println("6. Afegir una reserva a un taller.");
        System.out.println("7. Mostrar usuaris apuntats a un taller.");
        System.out.println("8. Mostrar usuari amb més reserves");
        System.out.println("9. Registrar la nota de satisfacción d'un usuari apuntat a un taller.");
        System.out.println("10. Mostrar nota mitjana del taller.");
        System.out.println("11. Mostrar taller amb més èxit.");
        System.out.println("12. Mostrar visites ofertes per una Entitat en concret.");
        System.out.println("13. Mostrar xerrades d'una persona en concret.");
        System.out.println("14. Eliminar taller on no n'hi hagi persones apuntades.");
        System.out.println("15. Sortir");
    }

    /** Mostrar les dades de qualsevol llista que tingueu definida.
     * @param la LLista Activitats
     * @param lu LLista Usuaris 
     * @param lr LLista reserves 
     * @param le llista entitats
     */
    public void cas1(LlistaActivitats la, LlistaUsuaris lu, LlistaReserves lr, LlistaEntitats le) {
        System.out.println("======== Llista d'activitats ========\n" + la + "\n======== Llista d'usuaris ========\n" + lu + "\n======== Llista de reserves ========\n" + lr + "\n======== Llista entitats ========" + le);
    }

    /** Obtenir i mostrar la llista d’activitats que ofereix una entitat concreta.
     * @param la LLista activitats
     */
    public void cas2(LlistaActivitats la) {
        System.out.println("Introdueix el nom de l'entitat que vols buscar:");
        String nom = s.nextLine();
        LlistaActivitats lTemp = la.entitatConcreta(nom);
        if (lTemp.getNElem() == 0) {
            System.out.println("No se ha trobat activitats per aquesta entitat o entitat erronea.");
        } else {
            System.out.println(lTemp);
        }
    }

    /** Obtenir i mostrar la llista de les activitats que es duen a terme en un dia indicat per teclat.
     * @param la LLista activitats
     */
    public void cas3(LlistaActivitats la) {
        System.out.println("Introdueix el dia pel qual vols buscar:");
        try {
        int dia = Integer.parseInt(s.nextLine());
        System.out.println(la.diaConcret(dia));
        } catch (NumberFormatException e) {
            System.out.println("Has d'introduir un número!");
        }
    }

    /** Obtenir i mostrar la llista dels tallers que tenen places disponibles. 
     * @param la Llista activitats
     */
    public void cas4(LlistaActivitats la) {
        System.out.println(la.tallersLliures());
    }

    /** Afegir una nova activitat
     * @param la Llista activitats
     * @param le LLista entitats
     */
    public void cas5(LlistaActivitats la, LlistaEntitats le) {
        boolean tipus_correcte = false;
        String tipus = "";
        while (!tipus_correcte) {
            System.out.println("Quin tipus de activitat vol afegir? Visita | Taller | Xerrada ");
            tipus = s.nextLine();
            if (tipus.equals("Visita") || tipus.equals("Taller") || tipus.equals("Xerrada")) {
                tipus_correcte = true;
            }
        }
        tipus_correcte = false;
        
        if (tipus.equals("Visita")) {
            try {
                String nom, localitzacio, nomEntitat;
                int codPostal, dia;
                boolean audioguia, adaptPerCecs;
                String audioguiaString ="", adapString ="";

                System.out.println("Nom de la visita");
                nom = s.nextLine();
                System.out.println("Codi postal de la localitzacio de la visita");
                codPostal = Integer.parseInt(s.nextLine());
                while (codPostal < 0 || codPostal > 99999) {
                    System.out.println("El codi postal no pot ser inferior a 0 ni major a 99999, intenta una altra vegada");
                    codPostal = Integer.parseInt(s.nextLine());
                }
                System.out.println("Dia de la visita (posa sols el dia, no la data completa)");
                dia = Integer.parseInt(s.nextLine());
                while (dia < 1 && dia > 31) {
                    System.out.println("El dia ha de estar entre 1 y 31, intenta una altra vegada");
                    dia = Integer.parseInt(s.nextLine());
                }
                System.out.println("Localitzacio de la visita");
                localitzacio = s.nextLine();
                
                while (!tipus_correcte) {
                    System.out.println("Es la visita amb audioguia? SI | NO"); // Verifiquem que el usuari pose SI o NO y no una altra dada.
                    audioguiaString = s.nextLine();
                    if(audioguiaString.equals("SI") || audioguiaString.equals("NO")){
                        tipus_correcte = true;
                    }
                }
                if (audioguiaString.equals("SI")) {
                    audioguia = true;
                } else {
                    audioguia = false;
                }

                tipus_correcte = false;
                while (!tipus_correcte) {
                    System.out.println("Es la visita adaptada per cecs? SI | NO"); // Verifiquem que el usuari pose SI o NO y no una altra dada. 
                    adapString = s.nextLine();
                    if(adapString.equals("SI") || adapString.equals("NO")) {
                        tipus_correcte = true;
                    }
                }
                if (adapString.equals("SI")) {
                    adaptPerCecs = true;
                } else {
                    adaptPerCecs = false;
                }
                System.out.println("Enitat asociada a la vista");
                nomEntitat = s.nextLine();
                while (le.buscarEntitat(nomEntitat) == null) { // Verificacio de que la entrada del usuari sigui una entitat valida
                    System.out.println("Entitat no trobada, esta el nom ben posat? Torna a provar");
                    nomEntitat = s.nextLine();
                }
                la.afegirActivitat(new Visita(nom, codPostal, dia, localitzacio, audioguia, adaptPerCecs, nomEntitat));
            } catch(NumberFormatException e) {
                System.out.println("Has introduit un valor invalid!");
            }


        } 
        else if (tipus.equals("Taller")) {
            try {
                String nom, nomEntitat;
                int codPostal, dia, hora, durada, capacitatMax;
                Entitat entitat;
                
                System.out.println("Digues el nom del taller");
                nom = s.nextLine();
                System.out.println("Digues el codi postal de la ubicacio del taller");
                codPostal = Integer.parseInt(s.nextLine());
                while (codPostal < 0 || codPostal > 99999) {
                    System.out.println("El codi postal ha de estar entre 1 y 99999, intentalo una altra vegada");
                    codPostal = Integer.parseInt(s.nextLine());
                }
                System.out.println("Digues el dia del taller (sols el dia, no format complet)");
                dia = Integer.parseInt(s.nextLine());
                while (dia < 0 || dia > 31) {
                    System.out.println("El dia ha de estar entre 1 y 31! Intenta altra vegada");
                    dia = Integer.parseInt(s.nextLine());
                }
                System.out.println("Digues la hora en que es fara el taller");
                hora = Integer.parseInt(s.nextLine());
                while (hora < 1 || hora > 24) {
                    System.out.println("La hora debe estar entre 1 y 24, intenta una altra vegada");
                    hora = Integer.parseInt(s.nextLine());
                }
                System.out.println("Digues la durada que tindra el taller (Expresat en minuts sense cap lletra final EX => 60)");
                durada = Integer.parseInt(s.nextLine());
                while (durada < 30 || durada > 360) {
                    System.out.println("La durada del taller ha de ser entre 30 minuts y maxim 360 minuts, intentalo altra vegada.");
                    durada = Integer.parseInt(s.nextLine());
                }
                System.out.println("Digues la capacitat maxima que tindra el taller");
                capacitatMax = Integer.parseInt(s.nextLine());
                while (capacitatMax < 10 || capacitatMax > 200) {
                    System.out.println("La capacitat maxima ha de ser minim de 10 persones y maxim de 200, intentalo altra vegada.");
                    durada = Integer.parseInt(s.nextLine());
                }
                System.out.println("Entitat asociada al taller");
                nomEntitat = s.nextLine();
                while (le.buscarEntitat(nomEntitat) == null) {
                    System.out.println("Entitat no trobada, es segur que el nom esta ben posat? Torna a provar");
                    nomEntitat = s.nextLine();
                }
                entitat = le.buscarEntitat(nomEntitat);
                nomEntitat = entitat.getNom();
                la.afegirActivitat(new Taller(nom, codPostal, dia, hora, durada, capacitatMax, nomEntitat, 0, 0, 0));
            } catch (NumberFormatException e) {
                System.out.println("Has introduit un valor invalid!");
            }
        } 
        else if (tipus.equals("Xerrada")) {
            try {
                String nomPersona, nom, nomEntitat;
                int codiPostal, dia;
                Entitat entitat;

                System.out.println("Quina sera la persona que fara la xerrada? (nom)");
                nomPersona = s.nextLine();
                System.out.println("Sobre que fara la xerrada?");
                nom = s.nextLine();
                System.out.println("Codi postal del lloc de la xerrada");
                codiPostal = Integer.parseInt(s.nextLine());
                while (codiPostal < 0 || codiPostal > 99999) {
                    System.out.println("El codi postal ha de estar entre 1 y 99999, intentalo una altra vegada");
                    codiPostal = Integer.parseInt(s.nextLine());
                }
                System.out.println("Dia que es fara la xerrada (sols el dia)");
                dia = Integer.parseInt(s.nextLine());
                while (dia < 0 || dia > 31) {
                    System.out.println("El dia ha de estar entre 1 y 31! Intenta altra vegada");
                    dia = Integer.parseInt(s.nextLine());
                }
                System.out.println("Nom de la entitat asociada a la xerrada");
                nomEntitat = s.nextLine();
                while (le.buscarEntitat(nomEntitat) == null) {
                    System.out.println("Es el nom de la entitat correcte? Prova una altra vegada");
                    nomEntitat = s.nextLine();
                }
                entitat = le.buscarEntitat(nomEntitat);
                nomEntitat = entitat.getNom();
                la.afegirActivitat(new Xerrada(nomPersona, nom, codiPostal, dia, nomEntitat));
            } catch(NumberFormatException e) {
                System.out.println("Has introduit un valor invalid!");
            }
        }
    }

    /** Registrar la petició d’un usuari per reservar un taller.
     * @param lu Llista usuaris
     * @param lr LLista reserves
     * @param la LLista activitats
     */
    public void cas6(LlistaUsuaris lu, LlistaReserves lr, LlistaActivitats la) {
        System.out.println("Introdueix l'alias del seu usuari:");
        String alies = s.nextLine();
        Usuari u = lu.buscarUsuari(alies);
        if (u == null) {
            System.out.println("No s'ha trobat l'usuari, disculpes.");
        }
        else {
            System.out.println("Introdueixi el nom el taller que vol trobar.");
            String taller = s.nextLine();
            Taller t = la.buscarTaller(taller);
            if (t == null) {
                System.out.println("No s'ha trobat el taller, disculpes.");
            }
            else {
                lr.afegirReserva(new Reserva(t, u));
                System.out.println("S'ha afegit la reserva!");
            }
        }
    }

    /** Mostrar els usuaris que s’han apuntat a un taller.
     * @param la LLista activitats
     * @param lu Llista usuaris
     * @param lr Llista reserves
     */
    public void cas7(LlistaActivitats la, LlistaUsuaris lu, LlistaReserves lr) {
        System.out.println("Digues el codi del taller: ");
        String taller = s.nextLine();
        Taller t = la.buscarTaller(taller);
        if (t == null) {
            System.out.println("No se ha trobat el taller");
        } else {
            LlistaUsuaris llistaInscripcions = lr.usuarisApuntatsATaller(lu, t);
            System.out.println(llistaInscripcions);
        }
        
    }

    /** Calcular l’usuari que s’ha apuntat a més tallers.
     * @param lr LLista reserves 
     * @param modeGrafic True = No dona sortida per pantalla / False = dona sortida per pantalla
     * @return String usuari amb mes inscripcions. 
     */
    public String cas8(LlistaReserves lr, boolean modeGrafic) {
        if (!modeGrafic) System.out.println(lr.usuariAmbMesInscripcions());
        return lr.usuariAmbMesInscripcions().toString();
    }

    /** Registrar la nota que un usuari que s’ha apuntat a un taller li dona un cop s’ha fet.
     * @param la LLista activitats
     * @param lr LLista reserves
     * @param lu LLista usuaris
     */
    public void cas9(LlistaActivitats la, LlistaReserves lr, LlistaUsuaris lu) {
        String aliasUsuari, idTaller;
        int puntuacio;
        Taller t;
        Usuari u;
        System.out.println("Digues el alies del usuari");
        aliasUsuari = s.nextLine();
        while (lu.buscarUsuari(aliasUsuari) == null) { // Forcem a que el usuari indicat sigui existent.
            System.out.println("No se ha trobat el usuari, proba una altra vegada");
            System.out.println("Digues el alies del usuari");
            aliasUsuari = s.nextLine();
        }
        u = lu.buscarUsuari(aliasUsuari);
        System.out.println("Digues el codi del taller al que el usuari esta inscrit");
        idTaller = s.nextLine();
        while (la.buscarTaller(idTaller) == null) { // Forcem a que el taller indicat sigui existent.
            System.out.println("No se ha trobat el taller, proba una altra vegada");
            System.out.println("Digues el ID del taller al que el usuari esta inscrit"); 
            idTaller = s.nextLine();
        }
        t = la.buscarTaller(idTaller);
        System.out.println("Comprobant si existeix reserva amb el usuari i taller proporcionat....");
        if (lr.esUsuariApuntatATaller(u, t)) { // Si el usuari no esta apuntat a aquest taller... 
            System.out.println("Introdueix una puntuacio entre 0-10");
            try {
                puntuacio = Integer.parseInt(s.nextLine());
                while (puntuacio < 0 || puntuacio > 10) { // per evitar al menys excepcions numeriques, fem un petit control.
                    System.out.println("Puntuacio incorrecta! Prova una altra vegada");
                    System.out.println("Introdueix una puntuacio entre 0-10");
                    puntuacio = Integer.parseInt(s.nextLine());
                }
                la.afegirPuntuacio(puntuacio, t); // t queda actualitzat amb la puntuacio.
                lr.actualitzarPuntuacions(u, t); // Actualitzem el taller en la reserva (necesari per poder comprobar si un usuari es a un taller concret)
            } catch(NumberFormatException e) {
                System.out.println("No has introduit un número!");
            }
            
        } else {
            System.out.println("No existeix reserva amb aquest taller y usuari");
        }
    }

    /** Calcular la nota mitja que ha rebut un taller.
     * @param la Llista activitats
     */
    public void cas10(LlistaActivitats la) {
        System.out.println("Introdueix el ID del taller:");
        String idTaller = s.nextLine();
        while (la.buscarTaller(idTaller) == null) {
            System.out.println("Introdueix el ID del taller:");
            idTaller = s.nextLine();
        }
        System.out.println("Nota mitjana: " + la.buscarTaller(idTaller).getMitjanaPuntuacion());
    }

    /** . Quin és el taller que ha tingut més èxit? Calcularem l’èxit segons el taller que ha tingut una ocupació més alta en proporció a les places que oferia
     * @param la LList activitats 
     * @param modeGrafic True = No dona sortida per pantalla / False = dona sortida per pantalla
     * @return String del taller amb mes exit 
     */
    public String cas11(LlistaActivitats la, boolean modeGrafic) {
        String sortida = la.mesExit().toString();
        if (!modeGrafic) {
            if (sortida == null) {
                System.out.println("No hi han inscripcions en cap taller");
            } else {
                System.out.println(sortida);
            }
        }   
        if (sortida == null) {
            return null;
        } else {
            return sortida;
        }
    }

    /** . Obtenir i mostrar les dades de la llista de visites ofertes per una entitat (per teclat s’indicarà si es vol audioguia o si cal que estigui adaptada per persones cegues).
     * @param la LLista activitats
     * @param le LLista entitats
     */
    public void cas12(LlistaActivitats la, LlistaEntitats le) {
        System.out.println("Introdueix el nom de l'entitat: ");
        String entitat = s.nextLine();
        while (le.buscarEntitat(entitat) == null) {
            System.out.println("No se ha trobat la entitat indicada!");
            System.out.println("Introdueix el nom de l'entitat: ");
            entitat = s.nextLine();
        }
        boolean audioGuia;
        boolean esPerCecs;
        String input;
        System.out.println("Vols la visita adaptada per a cecs? Y | N");
        input = s.nextLine();
        if (input.equals("Y")) {
            esPerCecs = true;
        } else { // Con esto ya controlamos todas las excepciones, si el usuario mete otros datos, se interpreta como false directamente. 
            esPerCecs = false;
        }
        System.out.println("Vols la visita amb audioguia? Y | N");
        input = s.nextLine();
        if (input.equals("Y")) {
            audioGuia = true;
        } else { // Con esto ya controlamos todas las excepciones, si el usuario mete otros datos, se interpreta como false directamente. 
            audioGuia = false;
        }
        System.out.println(la.mostrarVisitesEntitat(entitat, audioGuia, esPerCecs));
    }

    /** Mostrar les dades de les xerrades que farà una persona concreta.
     * @param la LLista de xerrades que fa una persona concreta. 
     */
    public void cas13(LlistaActivitats la) {
        System.out.println("Introdueix el nom de la persona: ");
        String nomPersona = s.nextLine();
        System.out.println(la.mostrarXerradesPersona(nomPersona));
    }

    /** Donar de baixa un taller sempre que no hi hagi usuaris apuntats.
     * @param la LLista activitats
     * @param lr Llista reserves 
     */
    public void cas14(LlistaActivitats la, LlistaReserves lr) {
        System.out.println("Introdueix el ID del taller a esborrar.");
        String idTaller = s.nextLine();
        Taller t = la.buscarTaller(idTaller);
        if (t == null) {
            System.out.println("No s'ha trobat el taller.");
        } else if (lr.numReservas(idTaller) > 0) {
            System.out.println("No s'ha pogut borrar el taller degut a que n'hi han places ocupades.");
        } else {
            la.treureActivitatNom(idTaller);
        }
    }
}
