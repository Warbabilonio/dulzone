package Ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Formatos {
	private Formatos() {
		
	}

}

class JButtonF extends JButton {
	public JButtonF(String title) {
		super(title);
		setFont(new Font("Candice", Font.PLAIN, 30));
		setForeground(Color.DARK_GRAY);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		//setOpaque(false);
		//setContentAreaFilled(false);
		setBackground(new Color(194,194,192));
		setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.black));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
}