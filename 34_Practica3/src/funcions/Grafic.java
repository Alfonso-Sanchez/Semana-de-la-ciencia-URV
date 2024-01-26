/*
 * ==== APLICACIO PRINCIPAL DEL PROGRAMA GRAFIC ====
 * 
 * Programador 1: alfonso.sanchez@estudiants.urv.cat (sols editar la opcio corresponent)
 * Programador 2: eduard.vericat@estudiants.urv.cat (tota la resta de treball)
 * Programador 3: carlos.castanon@estudiants.urv.cat (sols editar la opcio corresponent)
 * Programador 4: clara.puig@estudiants.urv.cat (sols editar la opcio corresponent)
 * 
 */
package funcions;
import javax.swing.*;
import clases.*;

import java.awt.*;
import java.io.IOException;

import opcions.*;

public class Grafic extends JFrame{
    LlistaActivitats llistaActivitats;
    LlistaEntitats llistaEntitats;
    LlistaReserves llistaReserves;
    LlistaUsuaris llistaUsuaris;

    public Grafic(String titol, LlistaUsuaris lu, LlistaActivitats la, LlistaEntitats le, LlistaReserves lr){
        
        super(titol);
        llistaActivitats = la;
        llistaEntitats = le;
        llistaReserves = lr;
        llistaUsuaris = lu;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Log.logInfo("Creación de la pantalla principal");

        // Crear un panel con GridLayout (5 filas, 3 columnas, espaciado horizontal y vertical)
        JPanel panel = new JPanel(new GridLayout(6, 3, 10, 10));
        
        // Creem tots els botons
        crearBotons(panel, this);

        // Añadir el panel amb tots els botons al frame
        this.setContentPane(panel);
        // Hacer visible la ventana
        this.setVisible(true);
        // Assignar resolución a la ventana (1280x720)
        this.setSize(1280, 720);
        // Centrar la ventana al centro de la pantalla
        this.setLocationRelativeTo(null);
    }

    
    /*
     * Funcio que mostra una ventana emergent preguntant si vol guardar les dades. Activa guardar les dades si el usuari diu que si.
     */
    public static void popUpGuardar(LlistaActivitats llistaActivitats, LlistaEntitats llistaEntitats, LlistaUsuaris llistaUsuaris, LlistaReserves llistaReserves) throws IOException {
        
        int opcion = JOptionPane.showOptionDialog(
                null, // No hay componente padre
                "¿Quieres guardar los datos?", // Mensaje en el cuadro de diálogo
                "Guardar datos", // Título del cuadro de diálogo
                JOptionPane.YES_NO_OPTION, // Tipo de opciones (Sí o No)
                JOptionPane.QUESTION_MESSAGE, // Tipo de icono (pregunta en este caso)
                null, // Icono personalizado (null == predeterminado)
                new Object[]{"Sí", "No"}, // Opciones personalizadas
                "Sí" // Opción predeterminada
        );

        // Depenent de la elecció
        if (opcion == JOptionPane.YES_OPTION) {
            System.out.println("RESPUESTA DEL USUARIO: Sí");
            GuardarDades guardardades = new GuardarDades("src/files/entitats.csv", "src/files/usuaris.csv", "src/files/activitats.txt", "src/files/reserves.dat", "src/files/num_reserves.txt");
            guardardades.guardarEntitats(llistaEntitats);
            guardardades.guardarActivitats(llistaActivitats);
            guardardades.guardarUsuaris(llistaUsuaris);
            guardardades.guardarReserves(llistaReserves);
            
        } else if (opcion == JOptionPane.NO_OPTION) {
            System.out.println("RESPUESTA DEL USUARIO: No");
            // ACCIONS SI L'USUARI DIU QUE NO
           
        } else {
            System.out.println("El usuario ha cerrado el dialogo");
        }

        // Paramos la aplicación
        System.exit(0);
    }



    /**
     * Funció per crear els botons de la pantalla principal
     * @param panel es el panel on es guarden tots els botons
     * @param frame es el frame de la pantalla principal
     */
    public void crearBotons(JPanel panel, JFrame frame){

        JButton button0 = new JButton("Exercici PRAC 3");
            button0.setName("0");
            button0.addActionListener(new AccioOpcio0(llistaActivitats));
            panel.add(button0);

        JButton button1 = new JButton("Mostrar Dades de les LLISTES");
            button1.setName("1");
            button1.addActionListener(new AccioOpcio1(llistaUsuaris,llistaEntitats,llistaReserves, llistaActivitats));
            panel.add(button1);
        
        JButton button2 = new JButton("Mostrar ACTIVITATS ENTITAT CONCRETA");
            button2.setName("2");
            button2.addActionListener(new AccioOpcio2(llistaActivitats));
            panel.add(button2);
       

        JButton button3 = new JButton("Mostrar ACTIVITATS que es fan 1 dia");
            button3.setName("3");
            button3.addActionListener(new AccioOpcio3(llistaActivitats));
            panel.add(button3);
        
        JButton button4 = new JButton("Llista de TALLERS amb places DISPONIBLES");
            button4.setName("4");
            button4.addActionListener(new AccioOpcio4(llistaActivitats));
            panel.add(button4);

        JButton button5 = new JButton("Afegir ACTIVITAT");
            button5.setName("5");
            button5.addActionListener(new AccioOpcio5(llistaActivitats, llistaEntitats));
            panel.add(button5);

        JButton button6 = new JButton("Reservar TALLER");
            button6.setName("6");
            button6.addActionListener(new AccioOpcio6(llistaActivitats, llistaUsuaris, llistaReserves));
            panel.add(button6);

        JButton button7 = new JButton("Mostrar Usuaris TALLERS");
            button7.setName("7");
            button7.addActionListener(new AccioOpcio7(llistaReserves, llistaUsuaris, llistaActivitats));
            panel.add(button7);

        JButton button8 = new JButton("Usuari amb mes inscripcions a tallers");
            button8.setName("8");
            button8.addActionListener(new AccioOpcio8(llistaReserves));
            panel.add(button8);

        JButton button9 = new JButton("Registrar NOTA usuari apuntat a un TALLER");
            button9.setName("9");
            button9.addActionListener(new AccioOpcio9(llistaReserves, llistaActivitats, llistaUsuaris));
            panel.add(button9);

        JButton button10 = new JButton("Calcular NOTA MITJA d'un taller");
            button10.setName("10");
            button10.addActionListener(new AccioOpcio10(llistaActivitats));
            panel.add(button10);

        JButton button11 = new JButton("Taller amb més ÈXIT");
            button11.setName("11");
            button11.addActionListener(new AccioOpcio11(llistaActivitats));
            panel.add(button11);

         JButton button12 = new JButton("Mostrar LLISTA de VISITES per una entitat");
            button12.setName("12");
            button12.addActionListener(new AccioOpcio12(llistaActivitats, llistaEntitats));
            panel.add(button12); 

        JButton button13 = new JButton("Dades de les XERRADES");
            button13.setName("13");
            button13.addActionListener(new AccioOpcio13(llistaActivitats));
            panel.add(button13);

        JButton button14 = new JButton("Donar de BAIXA un TALLER");
            button14.setName("14");
            button14.addActionListener(new AccioOpcio14(llistaActivitats, llistaReserves));
            panel.add(button14);


        // Boton invisible para poder poner el boton de salir centrado. 

        JButton invisibleButton = new JButton("Invisible Button");
        invisibleButton.setVisible(false);

        panel.add(invisibleButton);

         JButton button15 = new JButton("SALIR");
            button15.setName("15");
            button15.addActionListener(e -> {
                try {
                popUpGuardar(llistaActivitats, llistaEntitats, llistaUsuaris, llistaReserves);
                } catch (IOException error) {
                    JOptionPane.showMessageDialog(null, "Error => "+error, "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            });
            panel.add(button15);
  
        Log.logInfo("Creación de los botones del menú principal");
    }
}

