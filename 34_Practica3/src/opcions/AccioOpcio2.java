/*
 * ==== CLASE OPCIO 2 GRAFIC ====
 * 
 * Programador 2: eduard.vericat@estudiants.urv.cat
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package opcions;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;

public class AccioOpcio2 implements ActionListener {

    private LlistaActivitats llistaActivitats;

    /** Constructor opcio 2 
     * @param llistaActivitats
     */
    public AccioOpcio2(LlistaActivitats llistaActivitats) {
        this.llistaActivitats = llistaActivitats;
    }

    public void actionPerformed(ActionEvent evt) {

        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.dispose();

        JFrame ventana = new JFrame("Cercar Activiats d'una Entitat concreta.");
        JPanel panelBotones = new JPanel();

        crearFrame(ventana, panelBotones);

        JLabel text = new JLabel("Nom de la entitat: ");
        JTextField nomText = new JTextField("", 20);
        JButton bActivitats = new JButton("Cercar");

        JLabel text1 = new JLabel("Tipus d'Activitat:");

        JComboBox<String> comboBox = new JComboBox<>(new String[] { "Visita", "Xerrada", "Taller" });

        bActivitats.addActionListener(e -> {
            mostrarXerrades(nomText, (String) comboBox.getSelectedItem());
        });

        JButton atrasButton = new JButton("Atrás");
        ventana.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
        });

        panelBotones.add(text);
        panelBotones.add(nomText);
        panelBotones.add(text1);
        panelBotones.add(comboBox);
        panelBotones.add(bActivitats);

        ventana.add(panelBotones);
    }


    // FUNCIONS PRIVATES PER GENERAR LA NOVA VENTANA
    /** Crecio de la ventana opcio 2 
     * @param ventana
     * @param panelBotones
     */
    private void crearFrame(JFrame ventana, JPanel panelBotones) {

        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Mostrant Activitats d'una Entitat concreta.");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 13 \n\t\t\t\t\t\t\t\t-> Mostrar Xerrades que fa una persona concreta");
    }

    /** Mostrar les xerrades en un tauler (amb control de excepcions)
     * @param nomEntitat
     * @param tipusActivitat
     */
    private void mostrarXerrades(JTextField nomEntitat, String tipusActivitat) {

        if (nomEntitat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "No es pot deixar aquest camp buit", "Avis",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String nomEntitatString = nomEntitat.getText();
            LlistaActivitats lreturn = llistaActivitats.entitatConcreta(nomEntitatString);
            if (lreturn == null) {
                JOptionPane.showMessageDialog(null, "No se ha trobat cap Entitat amb el nom de: " + nomEntitatString,
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                switch (tipusActivitat) {
                    case "Xerrada":

                        int numXerrades = 0;

                        for (int i = 0; i < lreturn.getNElem(); i++) {
                            if (lreturn.getActivitat(i) instanceof Xerrada) {
                                numXerrades++;
                            }
                        }

                        Object[][] datos = new Object[numXerrades][7];
                        int j = 0;

                        for (int i = 0; i < lreturn.getNElem(); i++) {

                            if (lreturn.getActivitat(i) instanceof Xerrada) {

                                Xerrada t = (Xerrada) lreturn.getActivitat(i);
                                datos[j][0] = t.getCodi();
                                datos[j][1] = t.getNom();
                                datos[j][2] = t.getCodiPostal();
                                datos[j][3] = t.getDia();
                                datos[j][4] = t.getNomEntitat();
                                datos[j][5] = t.getNomPersona();
                                datos[j][6] = 17;
                                j++;
                            }
                        }
                        // Crear el DefaultTableModel con las columnas
                        String[] columnas = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Nom Persona",
                                "Hora" };
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
                        JOptionPane.showMessageDialog(null, scrollPane, "Xerrades de " + nomEntitatString,
                                JOptionPane.PLAIN_MESSAGE);

                        break;
                    case "Visita":

                        int numVisites = 0;

                        for (int i = 0; i < lreturn.getNElem(); i++) {
                            if (lreturn.getActivitat(i) instanceof Visita) {
                                numVisites++;
                            }
                        }

                        Object[][] datos1 = new Object[numVisites][8];
                        int k = 0;
                        for (int i = 0; i < lreturn.getNElem(); i++) {

                            if (lreturn.getActivitat(i) instanceof Visita) {

                                Visita t = (Visita) lreturn.getActivitat(i);
                                datos1[k][0] = t.getCodi();
                                datos1[k][1] = t.getNom();
                                datos1[k][2] = t.getCodiPostal();
                                datos1[k][3] = t.getDia();
                                datos1[k][4] = t.getNomEntitat();
                                datos1[k][5] = t.getLocalitzacio();
                                datos1[k][6] = t.teAudioguia();
                                datos1[k][7] = t.esAdaptPerCecs();
                                k++;
                            }
                        }
                        // Crear el DefaultTableModel con las columnas
                        String[] columnas1 = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Localització",
                                "Audioguia", "Apte per Cecs" };
                        // Creamos la tabla
                        DefaultTableModel modelo1 = new DefaultTableModel(datos1, columnas1) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        JTable tablaVisites = new JTable(modelo1);

                        // Creamos ahora el JScrollPane con la tabla
                        JScrollPane scrollPane2 = new JScrollPane(tablaVisites);

                        // Establecemos un tamaño acorde con los datos.
                        scrollPane2.setPreferredSize(new java.awt.Dimension(800, 500));
                        // Mostramos la tabla como un popup
                        JOptionPane.showMessageDialog(null, scrollPane2, "Visites de " + nomEntitatString,
                                JOptionPane.PLAIN_MESSAGE);

                        break;
                    case "Taller":

                        int numTallers = 0;

                        for (int i = 0; i < lreturn.getNElem(); i++) {
                            if (lreturn.getActivitat(i) instanceof Taller) {
                                numTallers++;
                            }
                        }

                        Object[][] datos2 = new Object[numTallers][10];
                        int l = 0;
                        for (int i = 0; i < lreturn.getNElem(); i++) {

                            if (lreturn.getActivitat(i) instanceof Taller) {

                                Taller t = (Taller) lreturn.getActivitat(i);
                                datos2[l][0] = t.getCodi();
                                datos2[l][1] = t.getNom();
                                datos2[l][2] = t.getNomEntitat();
                                datos2[l][3] = t.getCodiPostal();
                                datos2[l][4] = t.getDia();
                                datos2[l][5] = t.getHora();
                                datos2[l][6] = t.getDurada();
                                datos2[l][7] = t.getCapacitatMax();
                                datos2[l][8] = t.getNumInscripcions();
                                datos2[l][9] = t.getMitjanaPuntuacion();
                                l++;
                            }
                        }

                        // Crear el DefaultTableModel con las columnas
                        String[] columnas2 = { "Codi", "Nom", "Nom Entitat", "Codi Postal", "Dia", "Hora", "Durada",
                                "Capacitat Max", "Num Incripcions,", "Mitjana Puntuacion" };
                        // Creamos la tabla
                        DefaultTableModel modelo2 = new DefaultTableModel(datos2, columnas2) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        JTable tablaTallers = new JTable(modelo2);

                        // Creamos ahora el JScrollPane con la tabla
                        JScrollPane scrollPane3 = new JScrollPane(tablaTallers);

                        // Establecemos un tamaño acorde con los datos.
                        scrollPane3.setPreferredSize(new java.awt.Dimension(800, 500));
                        // Mostramos la tabla como un popup
                        JOptionPane.showMessageDialog(null, scrollPane3, "Tallers de " + nomEntitatString,
                                JOptionPane.PLAIN_MESSAGE);

                        break;
                }
            }
        }
    }
}
