package Ventanas;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class FondoVPrincipal extends javax.swing.JPanel {
	
	private String ruta = "";
	
	public FondoVPrincipal(String ruta) {
	
			this.ruta = ruta;
			this.setSize(400,280);
			
		}

@Override
	public void paintComponent (Graphics g){
			Dimension tamanio = getSize();
			ImageIcon imagenFondo = new ImageIcon(getClass().getResource(ruta));
			g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	
}
