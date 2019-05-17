package a7;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspector extends SimplePictureViewWidget {
	
	private Picture picture_view;
	private int x;
	private int y;
	private double red;
	private double green;
	private double blue;
	private double brightness;
	protected JLabel pixelInfo = new JLabel("<html>X: " + x 
			+ "<br><br><br><br><br>Y: " + y
			+ "<br><br><br><br><br>Red: " + red
			+ "<br><br><br><br><br>Green: " + green
			+ "<br><br><br><br><br>Blue: " + blue
			+ "<br><br><br><br><br>Brightness: " + brightness + "</html>");


	public PixelInspector(Picture picture) {
		super(picture);
		add(pixelInfo, BorderLayout.WEST);
		picture_view = picture;
	}	


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
		
		pixelInfo.setText("<html>X: " + e.getX()
				+ "<br><br><br><br><br>Y: " + e.getY()
				+ "<br><br><br><br><br>Red: " + (df.format(picture_view.getPixel(e.getX(), e.getY()).getRed()))
				+ "<br><br><br><br><br>Green: " + (df.format(picture_view.getPixel(e.getX(), e.getY()).getGreen()))
				+ "<br><br><br><br><br>Blue: " + (df.format(picture_view.getPixel(e.getX(), e.getY()).getBlue()))
				+ "<br><br><br><br><br>Brightness: " + (df.format(picture_view.getPixel(e.getX(), e.getY()).getIntensity())) 
				+ "</html>");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		p.setCaption("KMP in Namibia");
		SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p);
		PixelInspector pixelInfo = new PixelInspector(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(pixelInfo, BorderLayout.WEST);
		main_frame.setContentPane(top_panel);
		
		main_frame.pack();
		main_frame.setVisible(true);
	}
}

