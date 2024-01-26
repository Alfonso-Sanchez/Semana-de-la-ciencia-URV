/*
 * ==== CLASE OPCIO 14 GRAFIC ====
 * 
 * Programador 4: clara.puig@estudiants.urv.cat
 */
package opcions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;

 public class AccioOpcio14 implements ActionListener {
    
    private LlistaActivitats llistaActivitats;
    private LlistaReserves llistaReserves;

    /** Constructor opcio 14
     * @param la LLista activitats
     * @param lr LLista reserves
     */
    public AccioOpcio14 (LlistaActivitats la, LlistaReserves lr) {
        llistaActivitats = la;
        llistaReserves = lr;
    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f  = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame("Eliminar un taller sense inscripcions");
        JPanel panelBotones = new JPanel();

        crearFrame(ventana, panelBotones);

        JLabel text = new JLabel("ID Taller ");
        JTextField idTaller = new JTextField("", 20);
        JButton bEliminar = new JButton("Eliminar");
        bEliminar.setName("Opcio3_boto_activitats");
        bEliminar.addActionListener(e -> {
            eliminarTaller(idTaller);
            idTaller.setText("");
        });
        JButton verTalleres = new JButton("Ver talleres");
        JButton atrasButton = new JButton("Atrás");
        
        panelBotones.add(bEliminar);
        panelBotones.add(text);
        panelBotones.add(idTaller);
        panelBotones.add(verTalleres);
        ventana.add(panelBotones); // Añadimos los botones a la ventana
        ventana.add(atrasButton, BorderLayout.SOUTH);

        atrasButton.addActionListener( e -> {
            ventana.dispose();
            f.setVisible(true);
        });

        verTalleres.addActionListener(e -> {
            mostrarTalleres();
        });
    } 


    // FUNCIONS PRIVATES PER GENERAR LA NOVA VENTANA 

    /** Creacio ventana inicial. 
     * @param ventana
     * @param panelBotones
     */
    private void crearFrame(JFrame ventana, JPanel panelBotones) {
       ventana.setSize(1280, 720);
       ventana.setLayout(new BorderLayout());
       ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ventana.setVisible(true);
       ventana.setLocationRelativeTo(null);

       JLabel titulo = new JLabel("Eliminar un taller sense inscripcions");
       titulo.setHorizontalAlignment(JLabel.CENTER);
       titulo.setFont(new Font("Arial", Font.BOLD, 30));    
       ventana.add(titulo);

       Log.logInfo(
                "Creación de la ventana de la opció 14 \n\t\t\t\t\t\t\t\t-> Eliminar taller sense inscripcions");
       }

       private void eliminarTaller(JTextField idTaller) {
        if (idTaller.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "No es pot deixar aquest camp buit", "Avis", JOptionPane.WARNING_MESSAGE);
        } else {
            String idTallerString = idTaller.getText();
            Taller t = llistaActivitats.buscarTaller(idTallerString);
            if (t == null) {
                JOptionPane.showMessageDialog(null, "No se ha trobat cap taller amb aquest ID", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (llistaReserves.numReservas(idTallerString) > 0 ) {
                JOptionPane.showMessageDialog(null, "Aquest taller te inscripcions i no es pot eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                llistaActivitats.treureActivitatNom(idTallerString);
                JOptionPane.showMessageDialog(null, "Se ha eliminat el taller correctament", "Informacio", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     *  Funcio que mostra tots els tallers per aixi poder vorer els codis, etc. Per facilitar la vida al usuari. Mostra tots els tallers en un tauler. 
     */
    private void mostrarTalleres() {
                int numTallers = 0;
                for (int i = 0; i < llistaActivitats.getNElem(); i++) {
                    if (llistaActivitats.getActivitat(i) instanceof Taller) {
                        numTallers++;
                    }
                }
                Object[][] datos = new Object[numTallers][10];
                int j=0;
                for (int i = 0; i < llistaActivitats.getNElem(); i++) {
            
                    if (llistaActivitats.getActivitat(i) instanceof Taller){
                    
                        Taller t = (Taller) llistaActivitats.getActivitat(i);
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
                String[] columnas = {"Codi", "Nom", "Nom entitat", "Codi Postal", "Dia", "Hora", "Durada", "Capacitat MAX", "Num inscripcions", "Mitjana Puntuacio"};
                // Creamos la tabla 
                DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                JTable tablaTallers = new JTable(modelo);

                // Creamos ahora el JScrollPane con la tabla
                JScrollPane scrollPane = new JScrollPane(tablaTallers);

                // Establecemos un tamaño acorde con los datos. 
                scrollPane.setPreferredSize(new java.awt.Dimension(1000, 500));
                // Mostramos la tabla como un popup
                JOptionPane.showMessageDialog(null, scrollPane, "Tallers actuals", JOptionPane.PLAIN_MESSAGE);
    }
}


