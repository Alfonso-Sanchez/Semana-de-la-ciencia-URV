/*
 * ==== CLASE OPCIO 13 GRAFIC ====
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

public class AccioOpcio13 implements ActionListener{

    private LlistaActivitats lla;
    
    /** Constructor cas 13
     * @param la LLista activitats 
     */
    public AccioOpcio13 (LlistaActivitats la) {
        this.lla = la;
    }
    
    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f  = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame("Cercar Xerrades que fa una persona concreta.");
        JPanel panelBotones = new JPanel();

        crearFrame(ventana, panelBotones);

        JLabel text = new JLabel("Nom de la persona: ");
        JTextField nomText = new JTextField("", 20);
        JButton Cercar = new JButton("Cercar");
        Cercar.setName("Opcio3_boto_activitats");
        Cercar.addActionListener(e -> {
            mostrarXerrades(nomText);
        });
        JButton atras = new JButton("Atras");
        panelBotones.add(Cercar);
        panelBotones.add(text);
        panelBotones.add(nomText);
        ventana.add(panelBotones);
        ventana.add(atras, BorderLayout.SOUTH);
        

        atras.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
            Log.logInfo("Se ha salido de la opcion 13");
        });
    }

    // FUNCIONS PRIVATES PER GENERAR LA NOVA VENTANA
    /** Creacio ventana inicial .
     * @param ventana
     * @param panelBotones
     */
    private void crearFrame(JFrame ventana, JPanel panelBotones) {

        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Mostrant Xerrades que fa una persona concreta.");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 13 || Mostrar Xerrades que fa una persona concreta");
    }
    
    /** Mostra un tauler de les xerrades que fa la persona rebuda per parametre. 
     * @param nomPersona
     */
    private void mostrarXerrades(JTextField nomPersona) {
        if (nomPersona.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "No es pot deixar aquest camp buit", "Avis", JOptionPane.WARNING_MESSAGE);
        } else {
            String nomPersonaString = nomPersona.getText();
            LlistaActivitats lreturn = lla.mostrarXerradesPersona(nomPersonaString);
            if (lreturn.getNElem() == 0) {
                JOptionPane.showMessageDialog(null, "No se ha trobat cap Xerrada amb el autor " + nomPersonaString, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Object[][] datos = new Object[lreturn.getNElem()][7];
                int j=0;
                for (int i = 0; i < lreturn.getNElem(); i++) {
            
                    if (lreturn.getActivitat(i) instanceof Xerrada){
                    
                        Xerrada x = (Xerrada)lreturn.getActivitat(i);
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
                JOptionPane.showMessageDialog(null, scrollPane, "Xerrades de "+nomPersonaString, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}