/*
 * ==== CLASE OPCIO 9 GRAFIC ====
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

public class AccioOpcio9 implements ActionListener {

    private LlistaReserves lr;
    private LlistaActivitats la;
    private LlistaUsuaris lu;

    /** Constructor per a la opcio 9 
     * @param lr LLista Reserves 
     * @param la Llista Activitats
     * @param lu Llista Usuaris 
     */
    public AccioOpcio9(LlistaReserves lr, LlistaActivitats la, LlistaUsuaris lu) {
        this.lr = lr;
        this.la = la;
        this.lu = lu;
    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.setVisible(false);

        JFrame ventana = new JFrame();
        JPanel botones = new JPanel();

        crearFrame(ventana);

        JTextField usuari = new JTextField("", 15);
        JTextField taller = new JTextField("", 20);
        JTextField puntuacion = new JTextField("", 2);
        JLabel labelUsuari = new JLabel("USUARIO:");
        JLabel labelTaller = new JLabel("TALLER:");
        JLabel labelPuntuacion = new JLabel("PUNTUACION:");
        JButton boton = new JButton("AFEGIR PUNTUACIO");
        boton.addActionListener(e -> {
            afegirPuntuacio(usuari, taller, puntuacion);
        });

        botones.add(boton);
        botones.add(labelUsuari);
        botones.add(usuari);
        botones.add(labelTaller);
        botones.add(taller);
        botones.add(labelPuntuacion);
        botones.add(puntuacion);

        ventana.getContentPane().add(botones, BorderLayout.CENTER);

        JButton atrasButton = new JButton("Atrás");
        atrasButton.addActionListener( e -> {
            ventana.dispose();
            f.setVisible(true);
        });

        ventana.add(atrasButton, BorderLayout.SOUTH);
    }

    /** Creacio de la ventana inicial. 
     * @param ventana
     */
    public void crearFrame(JFrame ventana) {
        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Añadir puntuación de un usuario a un taller");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 9 \n\t\t\t\t\t\t\t\t-> Añadir puntuación de un usuario a un taller");
    }

    /** Metode per afegir la puntuacio amb control de errors. 
     * @param u Usuari (alies)
     * @param t Taller (idTaller)
     * @param p Puntuacio
     */
    public void afegirPuntuacio(JTextField u, JTextField t, JTextField p) {
        boolean valid = false;
        int pos = 0;
        while (pos < lr.getNElem() && !valid) {
            if (lr.getReserva(pos).getUsuari().getAlies().equals(u.getText()) && lr.getReserva(pos).getTaller().getCodi().equals(t.getText())) {
                valid = true;
            }
            pos++;
        }
        if (!valid) {
            JOptionPane.showMessageDialog(null, "Los datos introducidos no son validos" , "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Taller taller = la.buscarTaller(t.getText());
            la.afegirPuntuacio(Integer.parseInt(p.getText()), taller);
            Usuari usuari = lu.buscarUsuari(u.getText());
            lr.actualitzarPuntuacions(usuari, taller);
            JOptionPane.showMessageDialog(null, "Se ha añadido la puntuacion correctamente" , "Correcto", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
