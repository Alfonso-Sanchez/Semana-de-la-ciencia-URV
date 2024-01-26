/*
 * ==== CLASE OPCIO 7 GRAFIC ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat [Estructura i funcionalitat del codi]
 * Programador 1: alfonso.sanchez@estudiants.urv.cat [Millora diseny grafic]
 */
package opcions;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;

public class AccioOpcio7 implements ActionListener {
    
    private LlistaReserves lr;
    private LlistaUsuaris lu;
    private LlistaActivitats la; 

    /** Constructor opcio 7
     * @param lr Llista Reserves
     * @param lu Llista Usuaris
     * @param la LLista Activitats
     */
    public AccioOpcio7(LlistaReserves lr, LlistaUsuaris lu, LlistaActivitats la) {
        this.lr = lr;
        this.lu = lu;
        this.la = la;

    }

    public void actionPerformed(ActionEvent evt) {
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.dispose();

        JFrame ventana = new JFrame("");
        JPanel botones = new JPanel();

        crearFrame(ventana);

        JLabel text = new JLabel("TALLER:");
        JTextField field = new JTextField("", 20);
        JButton buscar = new JButton("BUSCAR");
        buscar.addActionListener(e -> {
            mostrarUsuarisTaller(field);
            field.setText("");
        });
        JButton atras = new JButton("Atras");
        atras.addActionListener(e -> {
            ventana.dispose();
            f.setVisible(true);
            Log.logInfo("Se ha salido de la opcion 13");
        });
        botones.add(buscar);
        botones.add(text);
        botones.add(field);
        JPanel atrasPanel = new JPanel();
        atrasPanel.add(atras);

        ventana.getContentPane().add(botones, BorderLayout.CENTER);
        ventana.getContentPane().add(atrasPanel, BorderLayout.SOUTH);

    }

    /** Metode per genera la ventana inicial de aquesta opcio 
     * @param ventana
     */
    private void crearFrame(JFrame ventana) {
        ventana.setSize(1280, 720);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Mostrando Usarios Apuntado a un Taller");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        ventana.add(titulo, BorderLayout.NORTH);

        Log.logInfo(
                "Creación de la ventana de la opció 7 \t-> Mostrar Usuarios apuntados a un Taller");
    }

    /** Metode que mostra els usuaris apuntats al taller en un tauler. 
     * @param field
     */
    private void mostrarUsuarisTaller(JTextField field) {
        LlistaUsuaris llistaUsuaris = lr.usuarisApuntatsATaller(lu, la.buscarTaller(field.getText()));
        Object[][] datos = new Object[llistaUsuaris.getNElem()][4];

        for (int i = 0; i < llistaUsuaris.getNElem(); i++) {
            datos[i][0] = llistaUsuaris.getUsuari(i).getAlies();
            datos[i][1] = llistaUsuaris.getUsuari(i).getCorreuElectronic();
            datos[i][2] = llistaUsuaris.getUsuari(i).getCodiPostal();
            datos[i][3] = llistaUsuaris.getUsuari(i).getNumInscripcions();
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Alias", "Correo Electrónico", "Código Postal", "Número de Inscripciones" };

        // Crear el modelo de la tabla sin que se pueda editar
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        JTable tablaUsuarios = new JTable(modelo);

        // Crear un JScrollPane para agregar la tabla con barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);

        JOptionPane.showMessageDialog(null, scrollPane, "Usuaris inscrits al taller " + field.getText(), JOptionPane.PLAIN_MESSAGE);
    }
}
