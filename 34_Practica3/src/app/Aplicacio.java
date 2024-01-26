/*
 * ==== APLICACIO PRINCIPAL DEL PROGRAMA ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 * Programador 2: eduard.vericat@estudiants.urv.cat
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */

package app;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import clases.LlistaActivitats;
import clases.LlistaEntitats;
import clases.LlistaReserves;
import clases.LlistaUsuaris;
import funcions.CarregarDades;
import funcions.Consola;
import funcions.Grafic;
import funcions.LlegirArxius;

public class Aplicacio {
    
    public static void main(String[] args) {

        LlistaUsuaris llistaUsuaris;
        LlistaEntitats llistaEntitats;
        LlistaActivitats llistaActivitats;
        LlistaReserves llistaReserves;
       
        /*
        * Carga los archivos `reserves.dat`, `activitats.txt`, `entitats.csv` y `usuaris.csv` en memoria.
        *
        * Los archivos `reserves.dat` contiene los datos de las reservas.
        *
        * Los datos se cargan en memoria para que puedan ser utilizados por el resto del programa.
        */
        try {
            // Llegim tots els arxius
            LlegirArxius llegirArxius = new LlegirArxius("src/files/usuaris.csv", "src/files/entitats.csv", "src/files/activitats.txt"); 
            String[] dataEntitats = llegirArxius.llegirEntitats();
            String[] dataUsuaris = llegirArxius.llegirUsuaris();
            String[] dataActivitats = llegirArxius.llegirActivitats();

            // Carreguem les dades a memoria.
            CarregarDades carregarDades = new CarregarDades(dataActivitats, dataEntitats, dataUsuaris);
            
            llistaEntitats = carregarDades.carregarLlistaEntitats();
            llistaUsuaris = carregarDades.carregarLlistaUsuaris();
            llistaActivitats = carregarDades.carregarLlistaActivitats();
            llistaReserves = carregarDades.carregarLlistaReserves("src/files/num_reserves.txt", "src/files/reserves.dat");
            

            /*
            * Inicio de la aplicacion. Aqui ya se realizan las funciones de la aplicacion.
            */
            if (popUpModoGrafic()) {
                new Grafic("Practica 3",llistaUsuaris, llistaActivitats, llistaEntitats, llistaReserves);
            } else {
                new Consola(llistaUsuaris, llistaEntitats, llistaActivitats, llistaReserves);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Error, no se ha trobat el archiu! => ["+e+"]");
        } catch (IOException e) {
            System.out.println("Error, no se ha pogut generar el arxiu reserves.dat! => ["+e+"]");
        } catch (ClassNotFoundException e) {
            System.out.println("Error, no se ha trobat la clase per carregar les dades! => ["+e+"]");
        }             
    }


    /** Preguntamos si queremos el modo grafico o el modo consola! :)
     * @return true / false
     */
    public static boolean popUpModoGrafic(){

        boolean opcio = false;
        // Mostrar el cuadro de diálogo
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Quieres iniciar la aplicación en modo Consola o en modo Gráfico?",
                "Selecciona el Modo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Consola", "Gráfico"},
                "Consola");

        if (opcion == JOptionPane.YES_OPTION) {
            System.out.println("Iniciando en modo Consola");

        } else if (opcion == JOptionPane.NO_OPTION) {
            System.out.println("Iniciando en modo Gráfico");
            opcio = true;
        }
        return opcio;
    }
}
