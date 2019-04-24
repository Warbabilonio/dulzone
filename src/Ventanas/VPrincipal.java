package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;



public class VPrincipal extends JFrame implements ActionListener{
	
	private Container contenedor = null;
	private GridBagConstraints constraints= null;
	private JPanel panelCentral, panelNorte;
	private JScrollPane scrollSur;
	private JButtonF btnProveedores, btnAlmacen, btnPagos, btnEntrdas, btnSalidas, btnPedidos, btnProductos;
	private Iconos ic = new Iconos();;
	
	public VPrincipal() {
		init();
		format();
	
	}

	private void init() {
		fondoPantalla();
		contenedor = this.getContentPane();
		contenedor.setLayout(new BorderLayout());
		
		panelCentral = new JPanel(new GridBagLayout());
		panelCentral.setOpaque(false);
		panelCentral();
		//panelCentral.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.black));
		
		
		panelNorte = new JPanel(new FlowLayout());
		panelNorte.setOpaque(false);
		panelNorte.add(new JLabel("PRUEBA TITULO"));
		
		scrollSur = panelSur();
		
		
		
		contenedor.add(scrollSur, BorderLayout.SOUTH);
		contenedor.add(panelNorte, BorderLayout.NORTH);
		contenedor.add(panelCentral, BorderLayout.CENTER);
		
	}
	
	private JScrollPane panelSur() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 20, 20, 20);
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridwidth = 1;
		
		//botones
		btnProveedores = new JButtonF(" Proveedores ");
		btnAlmacen = new JButtonF("   Almacen   ");
		btnPagos = new JButtonF("    Pagos    ");
		btnSalidas = new JButtonF("   Salidas   ");
		btnEntrdas = new JButtonF("  Entradas  ");
		btnProductos = new JButtonF("  Productos  ");
		btnPedidos = new JButtonF("   Pedidos   ");
		
		//add icon to button
		btnEntrdas.setIcon(ic.icono("entradas"));
		btnSalidas.setIcon(ic.icono("salidas"));
		btnProveedores.setIcon(ic.icono("proveedores"));
		btnAlmacen.setIcon(ic.icono("almacen"));
		btnPagos.setIcon(ic.icono("pagos"));
		btnProductos.setIcon(ic.icono("productos"));
		btnPedidos.setIcon(ic.icono("pedidos"));
		
		//add actionlistener
		btnEntrdas.addActionListener(this);
		btnSalidas.addActionListener(this);
		btnProveedores.addActionListener(this);
		btnAlmacen.addActionListener(this);
		btnPagos.addActionListener(this);
		btnProductos.addActionListener(this);
		btnPedidos.addActionListener(this);
	
		//add to  panel	
		panel.add(btnEntrdas, constraints);
		panel.add(btnSalidas, constraints);
		panel.add(btnProveedores, constraints);
		panel.add(btnProductos, constraints);
		panel.add(btnAlmacen, constraints);
		panel.add(btnPedidos, constraints);
		panel.add(btnPagos, constraints);
		
		panel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.black));
		panel.setOpaque(false);
		JScrollPane scroll = new JScrollPane(panel);
		//scroll transparente
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		//scroll alto
		scroll.setPreferredSize(new Dimension(0,180));
		
		return scroll;
	}
	
	private void format() {
		this.setSize(1204,768);
		this.setMinimumSize(new Dimension(1204,768));
		this.setTitle("DULZONE");
		this.setLocationRelativeTo(null);
		//maximizado
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//
		
		this.setVisible(true);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		
	}
	
	private void refrescarPantalla() {
		
		this.paintAll(getGraphics());
	}
	
	private void fondoPantalla() {
		
		FondoVPrincipal p = new FondoVPrincipal("/img/logo.jpg");
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		this.setContentPane(p);
		
	}
	
	private void panelCentral() {
		
		GridBagConstraints cons= new GridBagConstraints();
		cons.insets = new Insets(20, 20, 20, 20);
		//cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 0.2;
		cons.weighty = 1.0;
		
		JPanel subPanel1 = new JPanel(new FlowLayout());
		JPanel subPanel2 = new JPanel(new FlowLayout());
		subPanel1.setBackground(Color.BLACK);
		subPanel2.setBackground(Color.RED);
		
		
		cons.gridwidth = 1;
		panelCentral.add(subPanel1, cons);
		cons.weightx = 0.8;
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		panelCentral.add(subPanel2, cons);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton boton = (JButton)e.getSource();
		
		if(boton == btnEntrdas) {
			
			panelCentral();
			refrescarPantalla();
			
		}
	}
	



}
