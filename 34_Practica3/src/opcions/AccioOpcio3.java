/*
 * ==== CLASE OPCIO 3 GRAFIC ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat [Estructura i funcionalitat del codi]
 * Programador 1: alfonso.sanchez@estudiants.urv.cat [Millora diseny grafic]
 */
package opcions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;

public class AccioOpcio3 implements ActionListener {
    private LlistaActivitats llistaActivitats;

    public AccioOpcio3(LlistaActivitats llistaActivitats) {
        this.llistaActivitats = llistaActivitats;
    }

    public void actionPerformed(ActionEvent evt) {

        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame("Mostrando actividades de 1 dia");
        JPanel panelBotones = new JPanel();

        crearFrame(ventana, panelBotones);

        JLabel text = new JLabel("DIA:");
        JTextField diaText = new JTextField("1", 2); //se inicializa con un dia para que no empiece vacio.
        JButton bActivitats = new JButton("ACTIVITATS");
        bActivitats.setName("opcion3_boton_activitats");
        
        JButton atras = new JButton("Atras");
        atras.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
            Log.logInfo("Se ha salido de la opcion 13");
        });
        JPanel atrasPanel = new JPanel();
        atrasPanel.add(atras);
        panelBotones.add(bActivitats);
        panelBotones.add(text);
        panelBotones.add(diaText);
        

        ventana.add(panelBotones, BorderLayout.CENTER);
        ventana.add(atrasPanel, BorderLayout.SOUTH);

        bActivitats.addActionListener(e -> {
             mostrarOpciones(ventana, bActivitats, Integer.parseInt(diaText.getText()));
        });
    }

    /** Creacio de la ventana opcio3 
     * @param ventana
     * @param panelBotones
     */
    private void crearFrame(JFrame ventana, JPanel panelBotones) {

        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Mostrando actividades de 1 dia");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 3 \n\t\t\t\t\t\t\t\t-> Mostrar ACTIVITATS que es fan 1 dia");
    }


    /** Mostra una ventana nova amb les opcions disponibles, si no esta disponible, apareix en vermell. 
     * @param ventana
     * @param botoAccionat
     * @param dia
     */
    private void mostrarOpciones(JFrame ventana, JButton botoAccionat, int dia) {
        boolean hiHaXerrades, hiHaTallers, hiHaVisites;

        ventana.setVisible(false);
        JFrame ventana2 = new JFrame();
        ventana2.setSize(1280, 720);
        ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana2.setVisible(true);
        ventana2.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JPanel panelAtras = new JPanel();

        LlistaActivitats llistaDiaConcret = llistaActivitats.diaConcret(dia);
        hiHaVisites = comprobarExistencia(1, llistaDiaConcret);
        hiHaXerrades = comprobarExistencia(2, llistaDiaConcret);
        hiHaTallers = comprobarExistencia(3, llistaDiaConcret);
        
        JButton tallers = new JButton("TALLERS");
        JButton xerrades = new JButton("XERRADES");
        JButton visites = new JButton("VISITES");
        if (!hiHaVisites) visites.setBackground(new Color(245, 95, 95));
        if (!hiHaXerrades) xerrades.setBackground(new Color(245, 95, 95));
        if (!hiHaTallers) tallers.setBackground(new Color(245, 95, 95));


        xerrades.addActionListener(e -> {
            if (hiHaXerrades) {
                mostrarXerrades(llistaDiaConcret, dia);
            } else {
                JOptionPane.showMessageDialog(null, "No hi han xerrades per aquest dia", "Avis", JOptionPane.WARNING_MESSAGE);
            }
            
        });
        
        
        visites.addActionListener(e -> {
            if (hiHaVisites) {
                mostrarVisites(llistaDiaConcret, dia);
            } else {
                JOptionPane.showMessageDialog(null, "No hi han visites per aquest dia", "Avis", JOptionPane.WARNING_MESSAGE);
            }
        });

        tallers.addActionListener(e -> {
            if (hiHaTallers) {
                mostrarTallers(llistaDiaConcret, dia);
            } else {
                JOptionPane.showMessageDialog(null, "No hi han tallers per aquest dia", "Avis", JOptionPane.WARNING_MESSAGE);
            }

        });

        JButton atras = new JButton("Atras");

        atras.addActionListener(e -> {
            ventana2.dispose();
            ventana.setVisible(true);
            Log.logInfo("Se ha salido de la consulta de el dia "+dia);
        });

        panel.add(tallers);
        panel.add(xerrades);
        panel.add(visites);
        panelAtras.add(atras);
        ventana2.getContentPane().add(panel, BorderLayout.NORTH);
        ventana2.add(panelAtras, BorderLayout.SOUTH);
    }

    /** Comproba la existencia de al menys una activitat del tipus indicat 
     * @param tipus
     * @param la LLista activitats. 
     * @return
     */
    public boolean comprobarExistencia(int tipus, LlistaActivitats la) {
        boolean existeix = false;
        int i = 0;
        switch (tipus) {
            case 1: // visita
                while (!existeix && i < la.getNElem()) {
                    if(la.getActivitat(i) instanceof Visita) existeix = true;
                    i++;
                }
                break;
            case 2: // xerrada
                while (!existeix && i < la.getNElem()) {
                    if(la.getActivitat(i) instanceof Xerrada) existeix = true;
                    i++;
                }
                break;
            case 3: // taller
                while (!existeix && i < la.getNElem()) {
                    if(la.getActivitat(i) instanceof Taller) existeix = true;
                    i++;
                }
                break;
            default:
                break;
        }
        return existeix;
    }

    /** Mostra les xerrades en un tauler. 
     * @param llistaEntrada LLista de activitats on sols te les xerrades. 
     * @param dia
     */
    public void mostrarXerrades(LlistaActivitats llistaEntrada, int dia) {
        int numXerrades = 0; 
        for (int i = 0; i < llistaEntrada.getNElem(); i++) {
            if (llistaEntrada.getActivitat(i) instanceof Xerrada) {
                numXerrades++;
            }
        }
        Object[][] datos = new Object[numXerrades][7];
            int j=0;
            for (int i = 0; i < llistaEntrada.getNElem(); i++) {
                if (llistaEntrada.getActivitat(i) instanceof Xerrada){
                    Xerrada x = (Xerrada)llistaEntrada.getActivitat(i);
                    datos[j][0] = x.getCodi();
                    datos[j][1] = x.getNom();
                    datos[j][2] = x.getCodiPostal();
                    datos[j][3] = x.getDia();
                    datos[j][4] = x.getNomEntitat();
                    datos[j][5] = x.getNomPersona();
                    datos[j][6] = 17;
                    j++;
                }
            }
            // Crear el DefaultTableModel con las columnas
            String[] columnas = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Nom Persona", "Hora"};
            // Creamos la tabla 
            DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            JTable tablaXerrades = new JTable(modelo);

            // Creamos ahora el JScrollPane con la tabla
            JScrollPane scrollPane = new JScrollPane(tablaXerrades);

            // Establecemos un tamaño acorde con los datos. 
            scrollPane.setPreferredSize(new java.awt.Dimension(800, 500));
            // Mostramos la tabla como un popup
            JOptionPane.showMessageDialog(null, scrollPane, "Xerrades de el dia" + dia, JOptionPane.PLAIN_MESSAGE);
    }

    /** Mostra un tauler de les visites. 
     * @param llistaEntrada LListaActivitats on sols te les visites. 
     * @param dia
     */
    public void mostrarVisites(LlistaActivitats llistaEntrada, int dia) {
        int numVisites = 0;
        for (int i = 0; i < llistaEntrada.getNElem(); i++) {
            if (llistaEntrada.getActivitat(i) instanceof Visita) {
                numVisites++;
            }
        }

        Object[][] datos = new Object[numVisites][8];
        int j = 0;
        for (int i = 0; i < llistaEntrada.getNElem(); i++) {

            if (llistaEntrada.getActivitat(i) instanceof Visita) {

                Visita v = (Visita) llistaEntrada.getActivitat(i);
                datos[j][0] = v.getCodi();
                datos[j][1] = v.getNom();
                datos[j][2] = v.getCodiPostal();
                datos[j][3] = v.getDia();
                datos[j][4] = v.getNomEntitat();
                datos[j][5] = v.getLocalitzacio();
                datos[j][6] = v.teAudioguia();
                datos[j][7] = v.esAdaptPerCecs();
                j++;
            }
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Localització", "Audioguia",
                "Apte per Cecs" };

        // Crear el modelo de la tabla sin que se pueda editar
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        JTable tablaVisitas = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tablaVisitas);

        // Establecemos un tamaño acorde con los datos. 
        scrollPane.setPreferredSize(new java.awt.Dimension(800, 500));

        JOptionPane.showMessageDialog(null, scrollPane, "Visites de el dia" + dia, JOptionPane.PLAIN_MESSAGE);
    }

    /** Mostra els tallers en un tauler. 
     * @param llistaEntrada
     * @param dia
     */
    public void mostrarTallers(LlistaActivitats llistaEntrada, int dia) {
        int numTallers = 0;
         // Miramos el numeros de Tallers que hay en la lista de Actividades
         for (int i = 0; i < llistaEntrada.getNElem(); i++) {
            if (llistaEntrada.getActivitat(i) instanceof Taller) {
                numTallers++;
            }
        }

        Object[][] datos = new Object[numTallers][10];
        int j = 0;
        for (int i = 0; i < llistaEntrada.getNElem(); i++) {

            if ((llistaEntrada.getActivitat(i) instanceof Taller) && (j < numTallers)) {

                Taller t = (Taller) llistaEntrada.getActivitat(i);
                datos[j][0] = t.getCodi();
                datos[j][1] = t.getNom();
                datos[j][2] = t.getNomEntitat();
                datos[j][3] = t.getCodiPostal();
                datos[j][4] = t.getDia();
                datos[j][5] = t.getHora();
                datos[j][6] = t.getDurada();
                datos[j][7] = t.getCapacitatMax();
                datos[j][8] = t.getNumInscripcions();
                datos[j][9] = t.getMitjanaPuntuacion();
                j++;
            }
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Codi", "Nom", "Nom Entitat", "Codi Postal", "Dia", "Hora", "Durada", "Capacitat Max",
                "Num Incripcions,", "Mitjana Puntuacion" };

        // Crear el modelo de la tabla sin que se pueda editar
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        JTable tablaTaller = new JTable(modelo);

        // Crear un JScrollPane para agregar la tabla con barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaTaller);

         // Establecemos un tamaño acorde con los datos. 
        scrollPane.setPreferredSize(new java.awt.Dimension(800, 500));

        JOptionPane.showMessageDialog(null, scrollPane, "Talleres de el dia" + dia, JOptionPane.PLAIN_MESSAGE);
    }
    
}
