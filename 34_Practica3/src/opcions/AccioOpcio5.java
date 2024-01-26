/*
 * ==== CLASE OPCIO 5 GRAFIC ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat [Estructura i funcionalitat del codi]
 * Programador 1: alfonso.sanchez@estudiants.urv.cat [Millora diseny grafic]
 */
package opcions;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import clases.*;
import funcions.Log;

public class AccioOpcio5 implements ActionListener {
    
    int opcio = 0; //Cada valor determina que tipo de actividad se guarda: 1 = taller, 2 = xerrada, 3 = visita
    private LlistaActivitats la;
    private LlistaEntitats le;

    /** Constructor opcio 4
     * @param la llistaactivitats
     * @param le llistaentitats
     */
    public AccioOpcio5(LlistaActivitats la, LlistaEntitats le) {
        this.la = la;
        this.le = le;
    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);;

        JFrame ventana = new JFrame("Añadiendo Actividad");
        crearFrame(ventana);

        JPanel panel = new JPanel();
        JPanel botones = new JPanel();

        /*dato muy gracioso: si haces panel.setVisible(false) y luego panel.getComponent(i).setVisible(true), el componente no se hace visible
        ya que todo el panel es invisible. asi que tengo que, uno por uno, hacer cada componente invisible.*/

        //activitat
        JLabel Lnom = new JLabel("NOM:");
        JTextField nom = new JTextField(5);
        JLabel LcodiPostal = new JLabel("CODI POSTAL:");
        JTextField codiPostal = new JTextField(3);
        JLabel Ldia = new JLabel("DIA:");
        JTextField dia = new JTextField(2);
        JLabel Lentitat = new JLabel("ENTITAT:");
        JTextField entitat = new JTextField(10);
        Lnom.setVisible(false);
        nom.setVisible(false);
        LcodiPostal.setVisible(false); // Posem tots els camps invisibles, per despres activarlos quan el usuari indiqui el tipus de activitat a afegir.
        codiPostal.setVisible(false);
        Ldia.setVisible(false);
        dia.setVisible(false);
        Lentitat.setVisible(false);
        entitat.setVisible(false);


        //taller (numInscripcions, sumaPuntuaciones y numPuntuaciones se pasan a 0)
        JLabel Lhora = new JLabel("HORA:");
        Lhora.setVisible(false);
        JTextField hora = new JTextField(2);
        hora.setVisible(false);
        JLabel Ldurada = new JLabel("DURADA:");
        Ldurada.setVisible(false);
        JTextField durada = new JTextField(2);
        durada.setVisible(false);
        JLabel LcapacitatMax = new JLabel("CAPACITAT MAX:");
        LcapacitatMax.setVisible(false);
        JTextField capacitatMax = new JTextField(2);
        capacitatMax.setVisible(false);

        //xerrada
        JLabel LnomPersona = new JLabel("NOM PERSONA:");
        LnomPersona.setVisible(false);
        JTextField nomPersona = new JTextField(10);
        nomPersona.setVisible(false);

        //visita
        JLabel Llocalitzacio = new JLabel("LOCALITZACIO:");
        Llocalitzacio.setVisible(false);
        JTextField localitzacio = new JTextField(10);
        localitzacio.setVisible(false);
        JLabel Laudioguia = new JLabel("AUDIOGUIA:");
        Laudioguia.setVisible(false);
        JCheckBox audioguia = new JCheckBox();
        audioguia.setVisible(false);
        JLabel LadaptPerCecs = new JLabel("ADAPT PER CECS:");
        LadaptPerCecs.setVisible(false);
        JCheckBox adaptPerCecs = new JCheckBox();
        adaptPerCecs.setVisible(false);

        JButton taller = new JButton("TALLER");
        taller.addActionListener(e -> {
            opcio = 1;
            mostrarActivitat(opcio, panel);
        });
        JButton xerrada = new JButton("XERRADA");
        xerrada.addActionListener(e -> {
            opcio = 2;
            mostrarActivitat(opcio, panel);
        });
        JButton visita = new JButton("VISITA");
        visita.addActionListener(e -> {
            opcio = 3;
            mostrarActivitat(opcio, panel); 
        });
        JButton afegir = new JButton("AFEGIR");
        afegir.addActionListener(e -> {
            if (comprobarDades(opcio, panel)) afegirActivitat(opcio, panel);
        });
        JButton atras = new JButton("Atras");
        atras.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
        });

        //añadir todo...>_>
        //codigos: 0-7 (estos NO se tocan, todas las actividades lo usan)
        panel.add(Lnom);
        panel.add(nom);
        panel.add(LcodiPostal);
        panel.add(codiPostal);
        panel.add(Ldia);
        panel.add(dia);
        panel.add(Lentitat);
        panel.add(entitat);
        
        //codigos: 8-13
        panel.add(Lhora);
        panel.add(hora);
        panel.add(Ldurada);
        panel.add(durada);
        panel.add(LcapacitatMax);
        panel.add(capacitatMax);

        //codigos: 14-15
        panel.add(LnomPersona);
        panel.add(nomPersona);

        //codigos: 16-21
        panel.add(Llocalitzacio);
        panel.add(localitzacio);
        panel.add(Laudioguia);
        panel.add(audioguia);
        panel.add(LadaptPerCecs);
        panel.add(adaptPerCecs);

        botones.add(taller);
        botones.add(xerrada);
        botones.add(visita);
        botones.add(afegir);
        botones.add(atras);

        ventana.getContentPane().add(panel, BorderLayout.CENTER);
        ventana.getContentPane().add(botones, BorderLayout.SOUTH);
    }

    /** Creacio de la ventana inicial. 
     * @param ventana
     */
    private void crearFrame(JFrame ventana) {

        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Afegir una activitat");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 5 \n\t\t\t\t\t\t\t\t-> Añadir ACTIVIDAD");
    }

    /** Cambia els botons i fields segons el tipus de activitat. 
     * @param opcio
     * @param panel
     */
    private void mostrarActivitat(int opcio, JPanel panel) {
        
        // Activamos los botones permanentes para todas las acitivades. (solo se hace la primera vez)
        if (!panel.getComponent(0).isVisible()) {
            for (int i = 0; i < 8; i++) {
                panel.getComponent(i).setVisible(true);
            } 
        } 
        
        //para facilitarme la vida, es mejor hacer todo invisible primero y luego poner los correspondientes visible

        for (int i = 8; i < 22; i++) panel.getComponent(i).setVisible(false);
        switch(opcio) {
            case 1:
                for (int i = 8; i < 14; i++) panel.getComponent(i).setVisible(true);
                break;
            case 2:
                for (int i = 14; i < 16; i++) panel.getComponent(i).setVisible(true);
                break;
            case 3:
                for (int i = 16; i < 22; i++) panel.getComponent(i).setVisible(true);
                break;
        }
    }
    
    /** Metode per afegir la activitat corresponent, amb el tipus seleccionat i amb control de errors 
     * @param opcio
     * @param panel
     */
    private void afegirActivitat(int opcio, JPanel panel) {
        //estos valores son necesarios para todos los tipos de activitats
        String nom = ((JTextField)panel.getComponent(1)).getText();
        int codiPostal = Integer.valueOf(((JTextField)panel.getComponent(3)).getText());
        int dia = Integer.valueOf(((JTextField)panel.getComponent(5)).getText());
        String entitat = ((JTextField)panel.getComponent(7)).getText();

        ((JTextField)panel.getComponent(1)).setText("");
        ((JTextField)panel.getComponent(3)).setText("");
        ((JTextField)panel.getComponent(5)).setText("");
        ((JTextField)panel.getComponent(7)).setText("");

        switch(opcio) {
            case 1: //tallers
                int hora = Integer.valueOf(((JTextField)panel.getComponent(9)).getText());
                int durada = Integer.valueOf(((JTextField)panel.getComponent(11)).getText());
                int capacitatMax = Integer.valueOf(((JTextField)panel.getComponent(13)).getText());
                Taller t = new Taller(nom, codiPostal, dia, hora, durada, capacitatMax, entitat, 0, 0, 0);
                la.afegirActivitat(t);
                ((JTextField)panel.getComponent(9)).setText("");
                ((JTextField)panel.getComponent(11)).setText("");
                ((JTextField)panel.getComponent(13)).setText("");
                JOptionPane.showMessageDialog(null, "Se ha afegit la activitat correctament", "Avis", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2: //xerrades
                String nomPersona = ((JTextField)panel.getComponent(15)).getText();
                Xerrada x = new Xerrada(nomPersona, nom, codiPostal, dia, entitat);
                la.afegirActivitat(x);
                ((JTextField)panel.getComponent(15)).setText("");
                JOptionPane.showMessageDialog(null, "Se ha afegit la activitat correctament", "Avis", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3: //visites
                String localitzacio = ((JTextField)panel.getComponent(17)).getText();
                Boolean audioguia = ((JCheckBox)panel.getComponent(19)).isSelected();
                Boolean adaptPerCecs = ((JCheckBox)panel.getComponent(21)).isSelected();
                Visita v = new Visita(nom, codiPostal, dia, localitzacio, audioguia, adaptPerCecs, entitat);
                la.afegirActivitat(v);
                ((JTextField)panel.getComponent(17)).setText("");
                ((JCheckBox)panel.getComponent(19)).setSelected(false);
                ((JCheckBox)panel.getComponent(21)).setSelected(false);
                JOptionPane.showMessageDialog(null, "Se ha afegit la activitat correctament", "Avis", JOptionPane.INFORMATION_MESSAGE);
                break;
            default: //esto puede llegar a ocurrir si el usuario nunca ha puesto un dato
                break;
        }
    }

    /** Comprueba si el string input es sols numeric o te altres caracters.
     * @param input
     * @return true/false
     */
    private boolean esNumero(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) { // si no es un numero dona error, el captem per que no peti el programa i diem que no es un numero retornant fals.
            return false;
        }
    }

    
    /** Realitza una comprobacio de tots els camps abans de afegir la activitat per evitar introduccions erronies.
     * @param opcio
     * @param panel
     * @return true/false
     */
    private boolean comprobarDades(int opcio, JPanel panel) {
        boolean totCorrecte = true;
        int codiPostal = -1, dia = -1; // Inicialitzacio per defecte! Si les dades introduides no estan buides i tenen sols numeros, es sustiurian per les introduides.
        //estos valores son necesarios para todos los tipos de activitats
        if (esNumero(((JTextField)panel.getComponent(3)).getText())) { 
            codiPostal = Integer.valueOf(((JTextField)panel.getComponent(3)).getText());
        } else {
            totCorrecte = false;
            JOptionPane.showMessageDialog(null, "El codi postal no es pot deixar buit o tindre caracters no numerics", "Avis", JOptionPane.WARNING_MESSAGE);
        } 
        if (esNumero(((JTextField)panel.getComponent(5)).getText()) && totCorrecte) {
            dia = Integer.valueOf(((JTextField)panel.getComponent(5)).getText());
        } else {
            totCorrecte = false;
            JOptionPane.showMessageDialog(null, "El dia no es pot deixar buit o tindre caracters no numerics", "Avis", JOptionPane.WARNING_MESSAGE);
        }
        String nomEntitat = ((JTextField)panel.getComponent(7)).getText();
        Entitat comprobacioEntitat = le.buscarEntitat(nomEntitat);
        String nom = ((JTextField)panel.getComponent(1)).getText();
        // VERIFICACIONES DATOS PERMANENTES PARA TODOS LOS TIPOS 
        if (codiPostal <= 0 && totCorrecte) {
            totCorrecte = false;
            JOptionPane.showMessageDialog(null, "El codi postal no pot ser negatiu.", "Avis", JOptionPane.WARNING_MESSAGE);
        } else if ((dia < 0 || dia > 31 ) && totCorrecte) { // si la anterior comprobacion no fue correcta, ya no sigas comprobando el resto.
            totCorrecte = false;
            JOptionPane.showMessageDialog(null, "El dia ha de estar entre el dia 1 y el 31.", "Avis", JOptionPane.WARNING_MESSAGE);
        } else if (comprobacioEntitat == null && totCorrecte) {
            totCorrecte = false;
            JOptionPane.showMessageDialog(null, "La entitat indicada, no existeix", "Avis", JOptionPane.WARNING_MESSAGE);
        } else if (nom.equals("") && totCorrecte) {
            JOptionPane.showMessageDialog(null, "El nom de la activitat no pot estar buit", "Avis", JOptionPane.WARNING_MESSAGE);
        }
        // Entendemos que deberia haber un else pero no es posible para este caso, por que son comprobaciones con requisitos, no hay un default.
        
        // VERIFICACIONES PARA DATOS ESPECIFICOS DE CADA TIPO.

        if (opcio == 1) {
                int hora = -1;
                int durada = -1; 
                int capacitatMax = -1;
                
                if (esNumero(((JTextField)panel.getComponent(9)).getText()) && totCorrecte) {
                    hora = Integer.valueOf(((JTextField)panel.getComponent(9)).getText());
                } else {
                    totCorrecte = false;
                    JOptionPane.showMessageDialog(null, "No es pot deixar la hora buida o amb caracters no numerics", "Avis", JOptionPane.WARNING_MESSAGE);
                }
                if (esNumero(((JTextField)panel.getComponent(11)).getText()) && totCorrecte) {
                    durada = Integer.valueOf(((JTextField)panel.getComponent(11)).getText());
                } else {
                    totCorrecte = false;
                    JOptionPane.showMessageDialog(null, "No es pot deixar la duracio buida o amb caracters no numerics", "Avis", JOptionPane.WARNING_MESSAGE);
                }
                if (esNumero(((JTextField)panel.getComponent(13)).getText()) && totCorrecte) {
                    capacitatMax = Integer.valueOf(((JTextField)panel.getComponent(13)).getText());
                } else {
                    totCorrecte = false; 
                    JOptionPane.showMessageDialog(null, "No es pot deixar la capacitat maxima buida o amb caracters no numerics", "Avis", JOptionPane.WARNING_MESSAGE);
                }
                if (opcio == 1 && totCorrecte) {
                    // VERIFIQUEM LES DADES DE TALLERS (COM totCorrecte) HA PASAT VERIFICACIONS, MIREM SI ES TRUE PER EVITAR VERIFICACION INNECESARIES. 
                    if ((hora < 0 || hora > 23) && totCorrecte) {
                        totCorrecte = false;
                        JOptionPane.showMessageDialog(null, "La hora te que ser 0 - 23 ", "Avis", JOptionPane.WARNING_MESSAGE);
                    } else if ((durada < 30 || durada > 500) && totCorrecte) { // Anem a dir que la durada dels tallers ha de ser minim 30 minuts y maxim 500 minuts.
                        totCorrecte = false;
                        JOptionPane.showMessageDialog(null, "La duracio del taller ha de ser minim 30m", "Avis", JOptionPane.WARNING_MESSAGE);
                    } else if ((capacitatMax < 10 || capacitatMax > 180) && totCorrecte) { // Direm que la capacitat ha de estar entre 10 y 300
                        totCorrecte = false;
                        JOptionPane.showMessageDialog(null, "La capacitat ha de ser minim de 10 persones y maxim de 180 persones", "Avis", JOptionPane.WARNING_MESSAGE);
                    }
                }
        } else if (opcio == 2) { // Verificaciones Xerrades
            String nomPersona = ((JTextField)panel.getComponent(15)).getText();
            if (nomPersona.equals("") && totCorrecte) {
                totCorrecte = false;
                JOptionPane.showMessageDialog(null, "El nom de la persona no pot estar buit", "Avis", JOptionPane.WARNING_MESSAGE);
            }
        } else if (opcio == 3) { // Verificaciones visitas
            String localitzacio = ((JTextField)panel.getComponent(17)).getText();
            if (localitzacio.equals("") && totCorrecte) {
                totCorrecte = false;
                JOptionPane.showMessageDialog(null, "La localitzacio de la visita no pot estar buida", "Avis", JOptionPane.WARNING_MESSAGE);
            }
        }
        return totCorrecte;
    }
}
