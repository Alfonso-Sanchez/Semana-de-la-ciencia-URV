/*
 * ==== APLICACIO EN CONSOLA ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat
 * Programador 2: eduard.vericat@estudiants.urv.cat
 * Programador 3: carlos.castanon@estudiants.urv.cat
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package funcions;
import clases.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Consola {

    private LlistaUsuaris llistaUsuaris;
    private LlistaEntitats llistaEntitats;
    private LlistaActivitats llistaActivitats;
    private LlistaReserves llistaReserves;

    public Consola (LlistaUsuaris lu, LlistaEntitats le, LlistaActivitats la, LlistaReserves lr) {
        llistaUsuaris = lu;
        llistaActivitats = la;
        llistaReserves = lr;
        llistaEntitats = le;
        
        boolean sortir = false;
        Scanner s = new Scanner(System.in);
        while(!sortir) {
        String opcio = "0";
        Funcions funcions = new Funcions(); // Per millor estructura, tenim una clase funcions que te tots els casos i funcions de aplicacio.
        funcions.menu();
        System.out.println("---------");
        System.out.println("Siusplau indiqui la seva opció.");
        opcio = s.nextLine();
        switch(opcio) { // Los casos estan en orden al enunciado de la practica. 
            case "1":
                funcions.cas1(llistaActivitats, llistaUsuaris, llistaReserves, llistaEntitats);
                break;
            case "2":
                funcions.cas2(llistaActivitats);
                break;
            case "3":
                funcions.cas3(llistaActivitats);
                break;
            case "4":
                funcions.cas4(llistaActivitats);
                break;
            case "5":
                funcions.cas5(llistaActivitats, llistaEntitats);
                break;
            case "6":
                funcions.cas6(llistaUsuaris, llistaReserves, llistaActivitats);
                break;
            case "7":
                funcions.cas7(llistaActivitats, llistaUsuaris, llistaReserves);
                break;
            case "8":
                funcions.cas8(llistaReserves, false);
                break;
            case "9":
                funcions.cas9(llistaActivitats, llistaReserves, llistaUsuaris);
                break;
            case "10":
                funcions.cas10(llistaActivitats);
                break;
            case "11":
                funcions.cas11(llistaActivitats, false);
                break;
            case "12":
                funcions.cas12(llistaActivitats, llistaEntitats);
                break;
            case "13":
                funcions.cas13(llistaActivitats);
                break;
            case "14":
                funcions.cas14(llistaActivitats, llistaReserves);
                break;
            case "15":
                System.out.println("Vols guardar les dades? Y / N");
                String guardarDades = s.nextLine();
                if (guardarDades.equals("Y")) {
                    System.out.println("Guardant dades");
                    GuardarDades guardardades = new GuardarDades("src/files/entitats.csv", "src/files/usuaris.csv", "src/files/activitats.txt", "src/files/reserves.dat", "src/files/num_reserves.txt");
                    try {
                        guardardades.guardarEntitats(llistaEntitats);
                        guardardades.guardarActivitats(llistaActivitats);
                        guardardades.guardarUsuaris(llistaUsuaris);
                        guardardades.guardarReserves(llistaReserves);
                    } catch (FileNotFoundException e) {
                        System.out.println("Error, no se ha trobat el archiu! => ["+e+"]");
                    } catch (IOException e) {
                        System.out.println("Error, no se ha pogut generar el arxiu reserves.dat! => ["+e+"]");
                    }
                } 
                System.out.println("Adeu!");
                sortir = true;
                break;
            default:
                System.out.println("Has d'introducir un número del 1 al 15!");
                break;
            }
        }
        s.close();
    }

                

}
