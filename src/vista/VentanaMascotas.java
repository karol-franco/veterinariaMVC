package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.coordinador;
import modelo.vo.MascotaVo;
import modelo.vo.PersonaVo;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class VentanaMascotas extends JFrame implements  ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private coordinador miCoordinador;
	private JTextField txtIdDueño;
	private JTextField txtNombre;
	private JTextField txtRaza;
	private JTextField txtSexo;
	JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnConsultarLista; 
	private JTextArea areaTexto;
	private JTable tablaMascotas;
	private JScrollPane scrollTabla;
	private JLabel lblNombreDueno;
	
	public VentanaMascotas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		iniciarComponentes();
		
	}

	private void iniciarComponentes() {
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("GESTIONAR MASCOTAS");
		lblTitulo.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblTitulo.setBounds(171, 11, 173, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblidDueño = new JLabel("Id dueño :");
		lblidDueño.setBounds(40, 39, 76, 14);
		contentPane.add(lblidDueño);
		
		txtIdDueño = new JTextField();
		txtIdDueño.setBounds(106, 36, 129, 20);
		contentPane.add(txtIdDueño);
		txtIdDueño.setColumns(10);
		
		JLabel lblRaza = new JLabel("Raza :");
		lblRaza.setBounds(314, 39, 76, 14);
		contentPane.add(lblRaza);
		
		JLabel lblNombreMasco = new JLabel("Nombre :");
		lblNombreMasco.setBounds(40, 74, 76, 14);
		contentPane.add(lblNombreMasco);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(106, 71, 129, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtRaza = new JTextField();
		txtRaza.setBounds(381, 36, 86, 20);
		contentPane.add(txtRaza);
		txtRaza.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo :");
		lblSexo.setBounds(314, 74, 46, 14);
		contentPane.add(lblSexo);
		
		txtSexo = new JTextField();
		txtSexo.setBounds(381, 71, 86, 20);
		contentPane.add(txtSexo);
		txtSexo.setColumns(10);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setBounds(20, 113, 107, 34);
		contentPane.add(btnRegistrar);
		btnRegistrar.addActionListener(this);
	
		
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(146, 113, 105, 34);
		contentPane.add(btnConsultar);
		btnConsultar.addActionListener(this);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setBounds(261, 113, 118, 34);
		contentPane.add(btnActualizar);
		btnActualizar.addActionListener(this);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(389, 113, 105, 34);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		
		btnConsultarLista = new JButton("CONSULTAR LISTA");
		btnConsultarLista.setBounds(20, 154, 474, 28);
		contentPane.add(btnConsultarLista);
		btnConsultarLista.addActionListener(this);
		
		lblNombreDueno = new JLabel("Dueño: ");
		lblNombreDueno.setBounds(71, 193, 300, 20);
		contentPane.add(lblNombreDueno);
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		JScrollPane scrollTexto = new JScrollPane(areaTexto);
		scrollTexto.setBounds(71, 320, 388, 100);
		contentPane.add(scrollTexto);


		tablaMascotas = new JTable();
		scrollTabla = new JScrollPane(tablaMascotas);
		scrollTabla.setBounds(71, 209, 388, 100);
		contentPane.add(scrollTabla);

		
		
	}
	public void setCoordinador(coordinador miCoordinador) {
		this.miCoordinador= miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MascotaVo miMascotaVo = new MascotaVo();
		String documento = txtIdDueño.getText();
		String nombreDueno = miCoordinador.obtenerNombreDueno(documento);
		lblNombreDueno.setText("Dueño: " + nombreDueno);
		miMascotaVo.setDocumentoDueno(txtIdDueño.getText());
		miMascotaVo.setNombre(txtNombre.getText());
		miMascotaVo.setRaza(txtRaza.getText());
		miMascotaVo.setSexo(txtSexo.getText());
		
		if (e.getSource()== btnRegistrar) {
			miCoordinador.registrarMascota(miMascotaVo);
		}
		if (e.getSource() == btnActualizar) {
			miCoordinador.ActualizarMascota(miMascotaVo);
		}
		if (e.getSource() == btnEliminar) {
			miCoordinador.EliminarMascota(miMascotaVo);
		}
		if (e.getSource() == btnConsultar) {
			List<MascotaVo> ConsultarMascota = miCoordinador.ConsultarMascota(documento);
			    lblNombreDueno.setText("Dueño: " + nombreDueno);
			if (ConsultarMascota.isEmpty()) {
			    areaTexto.setText("No hay mascotas registradas para el documento: " + documento);

			    DefaultTableModel modeloVacio = new DefaultTableModel();
			    tablaMascotas.setModel(modeloVacio);

			} else {
			    String[] columnas = {"Nombre", "Raza", "Sexo"};
			    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

			    for (MascotaVo m : ConsultarMascota) {
			        Object[] fila = {
			            m.getNombre(),
			            m.getRaza(),
			            m.getSexo()
			        };
			        modelo.addRow(fila);
			    }

			    tablaMascotas.setModel(modelo);
			    areaTexto.setText("");
			    
			}
			//areaTexto.setText(sb.toString());
			
			
		}if (e.getSource()== btnConsultarLista) {
			List<MascotaVo> lista = miCoordinador.listaMascotas();
			StringBuilder sb = new StringBuilder();
			 DefaultTableModel modelo = new DefaultTableModel();
			    modelo.addColumn("Documento");
			    modelo.addColumn("Nombre");
			    modelo.addColumn("Teléfono");

			    for (MascotaVo p : lista) {
			        Object[] fila = { p.getNombre(), p.getRaza(), p.getSexo() };
			        modelo.addRow(fila);
			    }

			    tablaMascotas.setModel(modelo);
			}
			
		}
		
	
}
