/*
 * ==== CLASE OPCIO 12 GRAFIC ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 */
package opcions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;
public class AccioOpcio12 implements ActionListener {

    private LlistaActivitats llistaActivitats;
    private LlistaEntitats llistaEntitats;

    /** Constructor opcio 12
     * @param la LLista Activitats
     * @param le Llista Entitats
     */
    public AccioOpcio12 (LlistaActivitats la, LlistaEntitats le) {
        llistaActivitats = la;
        llistaEntitats = le;
    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame("Cercar visites que fa una entitat, amb requisits");
        JPanel panelBotones = new JPanel();

        crearFrame(ventana, panelBotones);

        JLabel text = new JLabel("Nom de la entitat: ");
        JTextField nomEntitat = new JTextField("", 20);
        JCheckBox perCecs = new JCheckBox("Adaptat per Cecs");
        JCheckBox audioGuia = new JCheckBox("Te audioguia");
        JButton Cercar = new JButton("Cercar");
        Cercar.setName("Opcio12_boto_activitats");
        Cercar.addActionListener(e -> {
            mostrarVisites(nomEntitat, audioGuia, perCecs);
        });
        JButton atras = new JButton("Atras");
        
        panelBotones.add(Cercar);
        panelBotones.add(text);
        panelBotones.add(nomEntitat);
        panelBotones.add(perCecs);
        panelBotones.add(audioGuia);
        ventana.add(panelBotones);
        ventana.add(atras, BorderLayout.SOUTH);

        atras.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
            Log.logInfo("Se ha salido de la opcion 12");
        });
    }

    // Funcions privates per a aquesta accio. 

    /** Creacio de la ventana inicial.
     * @param ventana
     * @param panelBotones
     */
    private void crearFrame(JFrame ventana, JPanel panelBotones) {
        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Mostrar les visites de una entitat concreta");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo);


        Log.logInfo("Se ha creado la ventana de la opcion 12 || Mostrar visitas entidad concreta");
    }

    /** Mostra les visites segons el indicat per parametre en un tauler. 
     * @param entidad
     * @param audioGuiaChBox
     * @param perCecsChBox
     */
    private void mostrarVisites(JTextField entidad, JCheckBox audioGuiaChBox, JCheckBox perCecsChBox) {
        if (entidad.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "No es pot deixar aquest camp buit", "Avis", JOptionPane.WARNING_MESSAGE);
        } else {
            String entidadString = entidad.getText();
            Entitat e = llistaEntitats.buscarEntitat(entidadString);
            LlistaActivitats llreturn = llistaActivitats.mostrarVisitesEntitat(entidadString, audioGuiaChBox.isSelected(), perCecsChBox.isSelected()); 
            if (e == null) {
                JOptionPane.showMessageDialog(null, "No se ha trobat cap entitat amb aquest nom", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (llreturn.getNElem() == 0) {
                JOptionPane.showMessageDialog(null, "No se ha trobat cap visita amb la entitat i condicions indicades", "Informacio", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object[][] datos = new Object[llreturn.getNElem()][8];
                for (int i = 0; i < llreturn.getNElem(); i++) {
            
                    
                    Visita v= (Visita)llreturn.getActivitat(i);
                    datos[i][0] = v.getCodi();
                    datos[i][1] = v.getNom();
                    datos[i][2] = v.getCodiPostal();
                    datos[i][3] = v.getDia();
                    datos[i][4] = v.getNomEntitat();
                    datos[i][5] = v.getLocalitzacio();
                    datos[i][6] = v.teAudioguia();
                    datos[i][7] = v.esAdaptPerCecs();
                }
                    // Crear el DefaultTableModel con las columnas
                String[] columnas = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Localització", "Audioguia", "Apte per Cecs"};
                // Creamos la tabla 
                DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                JTable tablaVisites = new JTable(modelo);

                // Creamos ahora el JScrollPane con la tabla
                JScrollPane scrollPane = new JScrollPane(tablaVisites);

                // Establecemos un tamaño acorde con los datos. 
                scrollPane.setPreferredSize(new java.awt.Dimension(800, 500));
                // Mostramos la tabla como un popup
                JOptionPane.showMessageDialog(null, scrollPane, "Visites trobades", JOptionPane.PLAIN_MESSAGE);
            }    
        }
    }
}




