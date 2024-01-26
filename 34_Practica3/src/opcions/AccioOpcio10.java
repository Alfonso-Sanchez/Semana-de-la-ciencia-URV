/*
 * ==== CLASE OPCIO 10 GRAFIC ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat
 */
package opcions;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import clases.*;
import funcions.Log;

public class AccioOpcio10 implements ActionListener {
    
    private LlistaActivitats la;

    /** Constructor opcio 10
     * @param la Llista activitats
     */
    public AccioOpcio10(LlistaActivitats la) {
        this.la = la;
    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame();
        JPanel botones = new JPanel();
        crearFrame(ventana);

        JTextField taller = new JTextField("", 20);
        JLabel tallerLabel = new JLabel("TALLER:");
        JButton buscar = new JButton("BUSCAR");
        buscar.addActionListener(e -> {
            mostrarNotaMitja(taller);
        });
        JButton atras = new JButton("Atras");

        atras.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
            Log.logInfo("Se ha salido de la opcion 10");
        });

        botones.add(atras);
        botones.add(tallerLabel);
        botones.add(taller);
        botones.add(buscar);

        ventana.add(botones);
    }

    /** Creacio de la ventana inicial
     * @param ventana
     */
    public void crearFrame(JFrame ventana) {
        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Obtener nota media de un taller");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 10 \t-> Obtener nota media de un taller");
    }

    /** Mostra la nota mitjana del taller indicat per parametre. 
     * @param taller ID del taller
     */
    public void mostrarNotaMitja(JTextField taller) {
        boolean trobat = false;
        for (int i = 0; i < la.getNElem(); i++) {
            if (la.getActivitat(i) instanceof Taller && ((Taller)la.getActivitat(i)).getCodi().equals(taller.getText())) {
                JOptionPane.showMessageDialog(null, "Puntuacio Mitjana del taller " +taller.getText()+" es: "+ String.valueOf(((Taller)la.getActivitat(i)).getMitjanaPuntuacion()), "Informacio", JOptionPane.INFORMATION_MESSAGE);
                trobat = true;
                i = la.getNElem(); //se para el bucle
            }
        }
        if (!trobat) {
            JOptionPane.showMessageDialog(null, "Aquest taller no existeix" , "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
