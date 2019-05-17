package a7;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimplePictureViewWidget extends JPanel implements MouseListener {

	private PictureView picture_view;
	
	/*private int x;
	private int y;
	private double red;
	private double green;
	private double blue;
	private double brightness;
	protected JLabel infoX = new JLabel("<html>X: " + x 
			+ "<br>Y: " + y
			+ "<br>Red: " + red
			+ "<br>Green: " + green
			+ "<br>Blue: " + blue
			+ "<br>Brightness: " + brightness + "</html>");
	protected JLabel infoY = new JLabel("Y: " + y);
	protected JLabel infoRed = new JLabel("Red: " + red);
	protected JLabel infoGreen = new JLabel("Green: " + green);
	protected JLabel infoBlue = new JLabel("Blue: " + blue);
	protected JLabel infoBright = new JLabel("Brightness: " + brightness);*/

	
	public SimplePictureViewWidget(Picture picture) {
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);
		
		JLabel title_label = new JLabel(picture.getCaption());
		add(title_label, BorderLayout.SOUTH);
		//add(infoX, BorderLayout.WEST);
		
		
		/*add(infoY, BorderLayout.AFTER_LAST_LINE);
		add(infoRed, BorderLayout.AFTER_LAST_LINE);
		add(infoGreen, BorderLayout.AFTER_LAST_LINE);
		add(infoBlue, BorderLayout.AFTER_LAST_LINE);
		add(infoBright, BorderLayout.AFTER_LAST_LINE);*/

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());

		
	//	Pixel myPixel;
		
	//	pixelToRGB(myPixel);
		
		
	
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}
