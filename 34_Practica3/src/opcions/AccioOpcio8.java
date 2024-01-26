/*
 * ==== CLASE OPCIO 8 GRAFIC ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 */
package opcions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import clases.LlistaReserves;
import funcions.Funcions;

public class AccioOpcio8 implements ActionListener {
    private LlistaReserves llistaReserves;
    
    /** Constructor opcio 8
     * @param lr LLista Reserves 
     * @param evt
     */
    public AccioOpcio8(LlistaReserves lr) {
        this.llistaReserves = lr;
        
    }
    public void actionPerformed(ActionEvent evt) {
        Funcions f = new Funcions();
        String sortida = f.cas8(this.llistaReserves, true);
        if (sortida != null) {
            JOptionPane.showMessageDialog(null, "Usuari amb mes reserves\n\n" + sortida, "Informacio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hi han usuaris amb inscripcions", "Informacio", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
