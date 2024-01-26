/*
 * ==== CLASE OPCIO 1 GRAFIC ====
 * 
 * Programador 2: eduard.vericat@estudiants.urv.cat
 * Programador 4: clara.puig@estudiants.urv.cat
 * 
 */
package opcions;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.*;
import funcions.Log;

public class AccioOpcio1 implements ActionListener {

    private LlistaUsuaris llistaUsuaris;
    private LlistaEntitats llistaEntitats;
    private LlistaActivitats llistaActivitats;
    private LlistaReserves llistaReserves;

    /** Constructor de la opcio 1 
     * @param llistaUsuaris
     * @param llistaEntitats
     * @param llistaReserves
     * @param llistaActivitats
     */
    public AccioOpcio1(LlistaUsuaris llistaUsuaris, LlistaEntitats llistaEntitats, LlistaReserves llistaReserves,
            LlistaActivitats llistaActivitats) {
        this.llistaUsuaris = llistaUsuaris;
        this.llistaEntitats = llistaEntitats;
        this.llistaReserves = llistaReserves;
        this.llistaActivitats = llistaActivitats;
    }

    public void actionPerformed(ActionEvent evt) {

        // Cojemos el frame des de donde se ha ejecutado y lo cerramos
        JButton b = (JButton) evt.getSource();
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(b);
        f.dispose();

        // Definimos el frame y el panel y creamos la ventana
        JFrame ventanaBotonListas = new JFrame("Mostrar Dades de les LLISTES");
        JPanel panelBotones = new JPanel();

        // Definimos un tipo de fuente que utilizaremos para los titulos
        Font fuente_titulos = new Font("Arial", Font.BOLD, 30);

        // Finalmente creamos la ventana
        crearFrame(ventanaBotonListas, panelBotones, fuente_titulos);

        // ** ACTIVITATS **
        JButton buttonActivitats = new JButton("ACTIVITATS");
        buttonActivitats.setName("opcion1_boton_activitats");
        buttonActivitats.addActionListener(e -> {
            mostrarListaActividades(ventanaBotonListas, fuente_titulos);
        });

        panelBotones.add(buttonActivitats);

        // ** ENTITATS **
        JButton buttonEntitats = new JButton("ENTITATS");
        buttonEntitats.setName("opcion1_boton_entitats");
        buttonEntitats.addActionListener(e -> {
            mostrarListaEntidades(ventanaBotonListas, fuente_titulos);
        });

        panelBotones.add(buttonEntitats);

        // ** RESERVES **
        JButton buttonReserves = new JButton("RESERVES");
        buttonReserves.setName("opcion1_boton_reserves");
        buttonReserves.addActionListener(e -> {
            mostrarListaReservas(ventanaBotonListas, fuente_titulos);
        });

        panelBotones.add(buttonReserves);

        // ** USUARIS **
        JButton buttonUsuarios = new JButton("USUARIOS");
        buttonUsuarios.setName("opcion1_boton_usuarios");
        buttonUsuarios.addActionListener(e -> {
            mostrarListaUsuarios(ventanaBotonListas, fuente_titulos);
        });

        panelBotones.add(buttonUsuarios);

        // ** BOTÓN ATRAS **
        JButton button_retroceder = new JButton("ATRAS");
        button_retroceder.setName("opcion1_boton_atras");
        button_retroceder.addActionListener(e -> {
            // Hacemos visible la pantalla principal
            f.setVisible(true);
            // Cerramos completamente liberando memoria la ventana de los botones
            ventanaBotonListas.dispose();
            Log.logInfo("Se ha retrocedido hasta la pantalla principal (Botón ATRÁS)");
        });

        panelBotones.add(button_retroceder);

        // Añadimos todos los Botones
        ventanaBotonListas.getContentPane().add(panelBotones);
    }

    /** Creacio de la ventana inicial per demanar al usuari quina llista vol mostrar. 
     * @param ventanaBotonListas
     * @param panelBotones
     * @param fuente_titulos
     */
    private void crearFrame(JFrame ventanaBotonListas, JPanel panelBotones, Font fuente_titulos) {

        // Generamos la ventana con los 4 botones
        ventanaBotonListas.setSize(1280, 720);
        ventanaBotonListas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonListas.setVisible(true);
        ventanaBotonListas.setLocationRelativeTo(null);

        // Creamos un titulo
        JLabel tituloPantallaListas = new JLabel("Selecciona que lista quieres mostrar:");
        tituloPantallaListas.setHorizontalAlignment(JLabel.CENTER);
        tituloPantallaListas.setFont(fuente_titulos);
        ventanaBotonListas.add(tituloPantallaListas, BorderLayout.NORTH);

        // Panel central para organizar los botones
        panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));

        Log.logInfo(
                "Creación de la ventana de la opció 1 \n\t\t\t\t\t\t\t\t-> Mostrar los datos de cualquiera lista que esté definida");

    }

    /** Metode per mostrar la llista activitats en un tauler. 
     * @param ventanaBotonActivitats
     */
    private void mostrarLlistaVisita(JFrame ventanaBotonActivitats) {

        int numVisites = 0;

        // Escondemos la ventana de las listas
        ventanaBotonActivitats.setVisible(false);

        JFrame ventanaBotonVisita = new JFrame("Llista RESERVES");
        ventanaBotonVisita.setSize(1280, 720);
        ventanaBotonVisita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonVisita.setVisible(true);
        ventanaBotonVisita.setLocationRelativeTo(null);

        JLabel tituloVisites = new JLabel("LLISTA VISITES");
        tituloVisites.setHorizontalAlignment(JLabel.CENTER);
        // tituloVisites.setFont(fuente_titulos);
        ventanaBotonVisita.add(tituloVisites, BorderLayout.NORTH);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonVisita.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonVisita.dispose();
            ventanaBotonActivitats.setVisible(true);

        });

        // Miramos el numeros de Tallers que hay en la lista de Actividades
        for (int i = 0; i < llistaActivitats.getNElem(); i++) {
            if (llistaActivitats.getActivitat(i) instanceof Visita) {
                numVisites++;
            }
        }

        Object[][] datos = new Object[numVisites][8];
        int j = 0;
        for (int i = 0; i < llistaActivitats.getNElem(); i++) {

            if (llistaActivitats.getActivitat(i) instanceof Visita) {

                Visita t = (Visita) llistaActivitats.getActivitat(i);
                datos[j][0] = t.getCodi();
                datos[j][1] = t.getNom();
                datos[j][2] = t.getCodiPostal();
                datos[j][3] = t.getDia();
                datos[j][4] = t.getNomEntitat();
                datos[j][5] = t.getLocalitzacio();
                datos[j][6] = t.teAudioguia();
                datos[j][7] = t.esAdaptPerCecs();
                j++;
            }
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Localització", "Audioguia",
                "Apte per Cecs" };

        // Crear el modelo de la tabla sin que se pueda editar
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        JTable tablaReservas = new JTable(modelo);

        // Crear un JScrollPane para agregar la tabla con barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        ventanaBotonVisita.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /** Metode per mostrar totes les xerrades en un tauler. 
     * @param ventanaBotonActivitats
     */
    private void mostrarLlistaXerrada(JFrame ventanaBotonActivitats) {

        int numXerrades = 0;

        ventanaBotonActivitats.setVisible(false);

        JFrame ventanaBotonXerrades = new JFrame("Llista XERRADES");
        ventanaBotonXerrades.setSize(1280, 720);
        ventanaBotonXerrades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonXerrades.setVisible(true);
        ventanaBotonXerrades.setLocationRelativeTo(null);

        JLabel tituloXerrades = new JLabel("LLISTA XERRADES");
        tituloXerrades.setHorizontalAlignment(JLabel.CENTER);
        // tituloVisites.setFont(fuente_titulos);
        ventanaBotonXerrades.add(tituloXerrades, BorderLayout.NORTH);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonXerrades.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonXerrades.dispose();
            ventanaBotonActivitats.setVisible(true);

        });

        // Miramos el numeros de Tallers que hay en la lista de Actividades
        for (int i = 0; i < llistaActivitats.getNElem(); i++) {
            if (llistaActivitats.getActivitat(i) instanceof Xerrada) {
                numXerrades++;
            }
        }

        Object[][] datos = new Object[numXerrades][7];
        int j = 0;
        for (int i = 0; i < llistaActivitats.getNElem(); i++) {

            if (llistaActivitats.getActivitat(i) instanceof Xerrada) {

                Xerrada t = (Xerrada) llistaActivitats.getActivitat(i);
                datos[j][0] = t.getCodi();
                datos[j][1] = t.getNom();
                datos[j][2] = t.getCodiPostal();
                datos[j][3] = t.getDia();
                datos[j][4] = t.getNomEntitat();
                datos[j][5] = t.getNomPersona();
                datos[j][6] = 17;
                j++;
            }
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Codi", "Nom", "Codi Postal", "Dia", "Nom Entitat", "Nom Persona", "Hora" };

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
        ventanaBotonXerrades.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /** Metode per mostrar tots els tallers en un tauler. 
     * @param ventanaBotonActivitats
     */
    private void mostrarLlistaTallers(JFrame ventanaBotonActivitats) {

        int numTallers = 0;

        ventanaBotonActivitats.setVisible(false);

        JFrame ventanaBotonTallers = new JFrame("Llista TALLERS");
        ventanaBotonTallers.setSize(1280, 720);
        ventanaBotonTallers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonTallers.setVisible(true);
        ventanaBotonTallers.setLocationRelativeTo(null);

        JLabel tituloTallers = new JLabel("LLISTA TALLERS");
        tituloTallers.setHorizontalAlignment(JLabel.CENTER);
        // tituloVisites.setFont(fuente_titulos);
        ventanaBotonTallers.add(tituloTallers, BorderLayout.NORTH);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonTallers.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonTallers.dispose();
            ventanaBotonActivitats.setVisible(true);

        });

        // Miramos el numeros de Tallers que hay en la lista de Actividades
        for (int i = 0; i < llistaActivitats.getNElem(); i++) {
            if (llistaActivitats.getActivitat(i) instanceof Taller) {
                numTallers++;
            }
        }

        Object[][] datos = new Object[numTallers][10];
        int j = 0;
        for (int i = 0; i < llistaActivitats.getNElem(); i++) {

            if ((llistaActivitats.getActivitat(i) instanceof Taller) && (j < numTallers)) {

                Taller t = (Taller) llistaActivitats.getActivitat(i);
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
        JTable tablaUsuarios = new JTable(modelo);

        // Crear un JScrollPane para agregar la tabla con barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        ventanaBotonTallers.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /** Metode per cambiar a una altra ventana amb 3 botons (tallers, xerrades, visites)
     * @param ventanaBotonListas
     * @param fuente_titulos
     */
    private void mostrarListaActividades(JFrame ventanaBotonListas, Font fuente_titulos) {

        // Escondemos la ventana de las listas
        ventanaBotonListas.setVisible(false);

        JFrame ventanaBotonActivitats = new JFrame("Llista ACTIVITATS");
        ventanaBotonActivitats.setSize(1280, 720);
        ventanaBotonActivitats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonActivitats.setVisible(true);
        ventanaBotonActivitats.setLocationRelativeTo(null);

        JLabel tituloActivitats = new JLabel("LLISTA ACTIVITATS");
        tituloActivitats.setHorizontalAlignment(JLabel.CENTER);
        tituloActivitats.setFont(fuente_titulos);
        ventanaBotonActivitats.add(tituloActivitats, BorderLayout.NORTH);

        JPanel panel = new JPanel(new FlowLayout());

        JButton visitasButton = new JButton("Visites");
        visitasButton.addActionListener(e -> {
            mostrarLlistaVisita(ventanaBotonActivitats);
        });

        panel.add(visitasButton);

        JButton tallerButton = new JButton("Tallers");
        tallerButton.addActionListener(e -> {
            mostrarLlistaTallers(ventanaBotonActivitats);
        });
        panel.add(tallerButton);

        JButton xerradaButton = new JButton("Xerrades");
        xerradaButton.addActionListener(e -> {
            mostrarLlistaXerrada(ventanaBotonActivitats);
        });
        panel.add(xerradaButton);

        ventanaBotonActivitats.add(panel, BorderLayout.CENTER);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonActivitats.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonActivitats.dispose();
            ventanaBotonListas.setVisible(true);
        });

        JLabel titulo = new JLabel("Selecciona qui tipus de llista vols mostrar");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        ventanaBotonActivitats.add(titulo, BorderLayout.NORTH);

        Log.logInfo("Mostrar ventana lista Actividades");

    }

    /** Metode que mostra un tauler de la llista entitats. 
     * @param ventanaBotonListas
     * @param fuente_titulos
     */
    private void mostrarListaEntidades(JFrame ventanaBotonListas, Font fuente_titulos) {

        // Escondemos la ventana de las listas
        ventanaBotonListas.setVisible(false);

        JFrame ventanaBotonEntitats = new JFrame("Llista ENTITATS");
        ventanaBotonEntitats.setSize(1280, 720);
        ventanaBotonEntitats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonEntitats.setVisible(true);
        ventanaBotonEntitats.setLocationRelativeTo(null);

        JLabel tituloEntitats = new JLabel("LLISTA ENTITATS");
        tituloEntitats.setHorizontalAlignment(JLabel.CENTER);
        tituloEntitats.setFont(fuente_titulos);
        ventanaBotonEntitats.add(tituloEntitats, BorderLayout.NORTH);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonEntitats.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonEntitats.dispose();
            ventanaBotonListas.setVisible(true);

        });

        Object[][] datos = new Object[llistaEntitats.getNElem()][3];

        for (int i = 0; i < llistaEntitats.getNElem(); i++) {
            datos[i][0] = llistaEntitats.getEntitat(i).getNom();
            datos[i][1] = llistaEntitats.getEntitat(i).getTelefon();
            datos[i][2] = llistaEntitats.getEntitat(i).getCorreuElectronic();
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Nom", "Telefon", "Correu Electronic" };

        // Crear el modelo de la tabla sin que se pueda editar
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        JTable tablaEntidades = new JTable(modelo);

        // Crear un JScrollPane para agregar la tabla con barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaEntidades);
        ventanaBotonEntitats.getContentPane().add(scrollPane);

        Log.logInfo("Mostrar ventana lista de Entidades");

    }

    /** Metode que mostra una llista de reserves en un tauler. 
     * @param ventanaBotonListas
     * @param fuente_titulos
     */
    private void mostrarListaReservas(JFrame ventanaBotonListas, Font fuente_titulos) {

        // Escondemos la ventana de las listas
        ventanaBotonListas.setVisible(false);

        JFrame ventanaBotonReservas = new JFrame("Llista RESERVES");
        ventanaBotonReservas.setSize(1280, 720);
        ventanaBotonReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonReservas.setVisible(true);
        ventanaBotonReservas.setLocationRelativeTo(null);

        JLabel tituloReservas = new JLabel("LLISTA RESERVES");
        tituloReservas.setHorizontalAlignment(JLabel.CENTER);
        tituloReservas.setFont(fuente_titulos);
        ventanaBotonReservas.add(tituloReservas, BorderLayout.NORTH);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonReservas.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonReservas.dispose();
            ventanaBotonListas.setVisible(true);

        });

        Object[][] datos = new Object[llistaReserves.getNElem()][4];

        for (int i = 0; i < llistaReserves.getNElem(); i++) {
            datos[i][0] = llistaReserves.getReserva(i).getCodiReserva();
            datos[i][1] = llistaReserves.getReserva(i).getUsuari().getAlies();
            datos[i][2] = llistaReserves.getReserva(i).getTaller().getCodi();
            datos[i][3] = llistaReserves.getReserva(i).getTaller().getNom();
        }

        // Crear el DefaultTableModel con las columnas
        String[] columnas = { "Codi Reserva", "Usuari", "Codi Taller", "Taller" };

        // Crear el modelo de la tabla sin que se pueda editar
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        JTable tablaReservas = new JTable(modelo);

        // Crear un JScrollPane para agregar la tabla con barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        ventanaBotonReservas.getContentPane().add(scrollPane);

        Log.logInfo("Mostrar ventana lista Reservas");

    }

    /** Mostra la llista de usuaris en un tauler. 
     * @param ventanaBotonListas
     * @param fuente_titulos
     */
    private void mostrarListaUsuarios(JFrame ventanaBotonListas, Font fuente_titulos) {

        // Escondemos la ventana de las listas
        ventanaBotonListas.setVisible(false);

        JFrame ventanaBotonUsuarios = new JFrame("Llista USUARIS");
        ventanaBotonUsuarios.setSize(1280, 720);
        ventanaBotonUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBotonUsuarios.setVisible(true);
        ventanaBotonUsuarios.setLocationRelativeTo(null);

        JLabel tituloUsuarios = new JLabel("LLISTA USUARIS");
        tituloUsuarios.setHorizontalAlignment(JLabel.CENTER);
        tituloUsuarios.setFont(fuente_titulos);
        ventanaBotonUsuarios.add(tituloUsuarios, BorderLayout.NORTH);

        JButton atrasButton = new JButton("Atrás");
        ventanaBotonUsuarios.add(atrasButton, BorderLayout.SOUTH);
        atrasButton.addActionListener(e -> {
            ventanaBotonUsuarios.dispose();
            ventanaBotonListas.setVisible(true);

        });

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
        ventanaBotonUsuarios.getContentPane().add(scrollPane);
        Log.logInfo("Mostrar ventana lista Usuarios");

    }

    
}