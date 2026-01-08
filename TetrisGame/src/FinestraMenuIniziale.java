import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FinestraMenuIniziale extends JFrame{

	private PannelloScelteMenu menuIniziale;
	private PannelloCardLayout cardLayout;
	
	Image Minecraft = readImage(new File("Minecraft.png"));

	public FinestraMenuIniziale(String s) 
	{
		super(s);
		
		setLayout(new BorderLayout());
		
		//Funzioni ed oggetti da richiamare
		cardLayout = new PannelloCardLayout(Minecraft);
		menuIniziale = new PannelloScelteMenu(this,cardLayout);
		
		//Funzioni di inizializzazione della finestra
		add(menuIniziale,BorderLayout.LINE_START);
		add(cardLayout,BorderLayout.LINE_END);
		frameOption();
	}
	
	public void frameOption()
	{
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	protected BufferedImage readImage(File file) {
		try {
			if (file.canRead()) {
				return ImageIO.read(file);
			}
		} catch (IOException exc) {

		}
		return null;
	}
	
}
