/*
 * ==== CLASE OPCIO 4 GRAFIC ====
 * 
 * Programador 3: carlos.castanon@estudiants.urv.cat [Estructura i funcionalitat del codi]
 * Programador 1: alfonso.sanchez@estudiants.urv.cat [Millora diseny grafic]
 */
package opcions;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;

public class AccioOpcio4 implements ActionListener {

    private LlistaActivitats llistaActivitats;

    /** Constructor opcio 4 
     * @param la
     */
    public AccioOpcio4(LlistaActivitats la) {
        llistaActivitats = la;
    }

    public void actionPerformed(ActionEvent evt) {
        mostrarTallers(llistaActivitats.tallersLliures());
        Log.logInfo("Accionat opcio 4 --> Mostrar tallers lliures");
    }


    /** Mostra un tauler de la llista de entrada 
     * @param llistaEntrada Llista activitats on sols hi ha els tallers lliures. 
     */
    public void mostrarTallers(LlistaActivitats llistaEntrada) {
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

        JOptionPane.showMessageDialog(null, scrollPane, "Tallers amb plaçes disponibles" , JOptionPane.PLAIN_MESSAGE);
    }
}
