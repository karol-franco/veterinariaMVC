package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controlador.coordinador;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

    public JButton btnPersonas;
    public JButton btnMascotas;
    private coordinador miCoordinador;
    private FondoPanel fondo;
    VentanaPersonas miVentanaPersonas;
   
    public VentanaPrincipal() {
        setTitle("Ventana Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        fondo = new FondoPanel();
        fondo.setBorder(new EmptyBorder(5, 5, 5, 5));
        fondo.setLayout(null);
        setContentPane(fondo); 

        JLabel lblTitulo = new JLabel("SISTEMA VETERINARIA ABC");
        lblTitulo.setBounds(183, 11, 300, 24);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); 
        fondo.add(lblTitulo); 
        
        btnPersonas = new JButton("Gestionar personas");
        btnPersonas.setBounds(60, 248, 152, 51);
        btnPersonas.addActionListener(this);
        fondo.add(btnPersonas);  

        btnMascotas = new JButton("Gestionar mascotas");
        btnMascotas.setBounds(325, 248, 152, 51);
        btnMascotas.addActionListener(this);
        fondo.add(btnMascotas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPersonas) {
            if (miCoordinador != null) {
                miCoordinador.mostrarVentanaPersonas();
            } else {
                System.err.println("Error: Coordinador no asignado.");
            }
        }

        if (e.getSource() == btnMascotas) {
            if (miCoordinador != null) {
                miCoordinador.mostrarVentanaMascotas();
            } else {
                System.err.println("Error: Coordinador no asignado.");
            }
        }
    }

    public void setCoordinador(coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}
