import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panneau extends JPanel{
	private String service = "Pharmacie";
	
public void paintComponent(Graphics g){
	System.out.println("Je suis exécutée !");
	int x1 = this.getWidth()/3;
	int y1 = this.getHeight()/2;
	
	
	
	//Texte
	Font font = new Font("Arial",Font.CENTER_BASELINE,20);
	g.setFont(font);
	g.setColor(Color.blue);
	
	System.out.println("Service reçu2 par la classe panneau : " + this.service );
	g.drawString("Futur planning de " + this.service,x1,y1);	
	
	
}

public void setPlanning(String planning){
	this.service = planning;
	System.out.println("Service reçu par la classe panneau : " + planning ); 
}
}
