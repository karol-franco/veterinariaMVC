package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.coordinador;
import modelo.vo.PersonaVo;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VentanaPersonas extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static final String PersonaVo = null;
	private JPanel contentPane;
	private JTextField txtDoc;
	private JTextField txtTelefono;
	private JTextField txtNombre;
	private coordinador miCoordinador;
	private JTextArea areaTexto;
	private JTable tablaPersonas;
	private JScrollPane scrollTabla;
	

	private JButton btnRegistrar, btnActualizar, btnEliminar, btnConsultar, btnConsultarLista ;

	public VentanaPersonas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 setContentPane(contentPane);
	
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPane.setLayout(null);
		
		JLabel lblGestionarPersonas = new JLabel("GESTIONAR PERSONAS");
		lblGestionarPersonas.setBounds(190, 11, 136, 15);
		lblGestionarPersonas.setFont(new Font("Verdana", Font.PLAIN, 11));
		contentPane.add(lblGestionarPersonas);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(35, 45, 77, 14);
		contentPane.add(lblDocumento);
		
		
		txtDoc = new JTextField();
		txtDoc.setBounds(109, 42, 121, 20);
		contentPane.add(txtDoc);
		txtDoc.setColumns(10);
	
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(295, 45, 68, 14);
		contentPane.add(lblTelefono);
		
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(373, 42, 86, 20);
		contentPane.add(txtTelefono);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(35, 70, 77, 14);
		contentPane.add(lblNombre);
		
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(109, 73, 350, 20);
		contentPane.add(txtNombre);
		
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(71, 104, 89, 34);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);

		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(176, 104, 90, 34);
		contentPane.add(btnActualizar);
		btnActualizar.addActionListener(this);
	
		
		btnEliminar =new JButton("Eliminar");
		btnEliminar.setBounds(373, 104, 86, 34);
		contentPane.add(btnEliminar);		btnEliminar.addActionListener(this);
		
		btnConsultarLista = new JButton("Consultar Lista");
		btnConsultarLista.setBounds(71, 149, 388, 35);
		contentPane.add(btnConsultarLista);
		btnConsultarLista.addActionListener(this);
	
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(277, 104, 86, 34);
		contentPane.add(btnConsultar);
		tablaPersonas = new JTable();
		scrollTabla = new JScrollPane(tablaPersonas);
		scrollTabla.setBounds(71, 195, 388, 120);
		contentPane.add(scrollTabla);

		
	}

	public void setCoordinador(coordinador miCoordinador) {
		this.miCoordinador= miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			PersonaVo personaVo= new PersonaVo();
			personaVo.setDocuemento(txtDoc.getText());
			personaVo.setNombre(txtNombre.getText());
			personaVo.setTelefono(txtTelefono.getText());
			if (e.getSource()==btnRegistrar) {
				miCoordinador.registrarPersona(personaVo);
			}
			if (e.getSource()==btnActualizar) {
				miCoordinador.ActualizarPersona(personaVo);
			}
			if (e.getSource()==btnEliminar) {
				miCoordinador.eliminarPersona(personaVo);
				
			}
			if (e.getSource() == btnConsultarLista) {
			List<PersonaVo> lista = miCoordinador.listaPersonas();
			StringBuilder sb = new StringBuilder();
			 DefaultTableModel modelo = new DefaultTableModel();
			    modelo.addColumn("Documento");
			    modelo.addColumn("Nombre");
			    modelo.addColumn("Teléfono");

			    for (PersonaVo p : lista) {
			        Object[] fila = { p.getDocumento(), p.getNombre(), p.getTelefono() };
			        modelo.addRow(fila);
			    }

			    tablaPersonas.setModel(modelo);
			}
			
		
	}
}
