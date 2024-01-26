/*
 * ==== CLASE OPCIO 6 GRAFIC ====
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

public class AccioOpcio6 implements ActionListener {
    
    LlistaActivitats la;
    LlistaUsuaris lu;
    LlistaReserves lr;

    /** Constructor opcio 6
     * @param la Llista Activitats
     * @param lu Llista Usuaris
     * @param lr Llista Reserves 
     */
    public AccioOpcio6(LlistaActivitats la, LlistaUsuaris lu, LlistaReserves lr) {
        this.la = la;
        this.lu = lu;
        this.lr = lr;
    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame();
        crearFrame(ventana);

        JPanel botones = new JPanel();

        JLabel Lusuari = new JLabel("USUARI:");
        JTextField usuari = new JTextField("", 10);
        JLabel Ltaller = new JLabel("TALLER:");
        JTextField taller = new JTextField("", 10);

        JButton afegir = new JButton("AFEGIR");
        afegir.addActionListener(e -> {
            afegirReserva(botones);
        });
        JButton atrasButton = new JButton("Atrás");
        atrasButton.addActionListener( e -> {
            ventana.dispose();
            f.setVisible(true);
        });

        botones.add(Lusuari);
        botones.add(usuari);
        botones.add(Ltaller);
        botones.add(taller);
        botones.add(afegir);


        ventana.getContentPane().add(botones, BorderLayout.CENTER);
        ventana.add(atrasButton, BorderLayout.SOUTH);
    }


    /** Creacio de la ventana incial. 
     * @param ventana
     */
    private void crearFrame(JFrame ventana) {

        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Mostrando Talleres libres");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 5 \n\t\t\t\t\t\t\t\t-> Añadir ACTIVIDAD");

    }

    /** Metode per afegir la reserva, amb control de errors. 
     * @param botones
     */
    private void afegirReserva(JPanel botones) {
        Taller t = la.buscarTaller(((JTextField)botones.getComponent(3)).getText());
        Usuari u = lu.buscarUsuari(((JTextField)botones.getComponent(1)).getText());
        
        if (u == null || t == null) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario o el taller!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (lr.esUsuariApuntatATaller(u, t)) {
            JOptionPane.showMessageDialog(null, "El usuario no se puede volver a registrar al mismo taller!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Se ha afegit correctament la reserva!", "Informacio", JOptionPane.INFORMATION_MESSAGE);
            lr.afegirReserva(new Reserva(t, u));
            ((JTextField)botones.getComponent(1)).setText("");
            ((JTextField)botones.getComponent(3)).setText("");
        }
    }

    
}
