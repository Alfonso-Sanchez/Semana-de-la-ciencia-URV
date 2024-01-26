/*
 * ==== CLASE OPCIO 11 GRAFIC ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 */
package opcions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import clases.*;
import funcions.Funcions;

public class AccioOpcio11 implements ActionListener{

    private LlistaActivitats lla;
    
    /** Constructor opcio 11
     * @param la LLista Activitats
     */
    public AccioOpcio11 (LlistaActivitats la) {
        this.lla = la;
    }
    
    public void actionPerformed(ActionEvent evt) {
        Funcions f = new Funcions();
        String sortida = f.cas11(this.lla, true);
        if (sortida != null) {
            JOptionPane.showMessageDialog(null, "Taller amb mes inscripcions\n\n" + sortida, "Informacio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hi han tallers amb inscripcions" , "Informacio", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}


