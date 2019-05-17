package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ImageAdjuster extends JPanel implements ChangeListener{
	
	private Picture wrappedP;
	private PictureView picture_view;
	private JSlider blurSlider;
	private JSlider brightSlider;
	private JSlider satSlider;
	private JLabel blurLabel;
	private JLabel brightLabel;
	private JLabel satLabel;
	private JPanel myPanel;
	
	private int blurIntensity;
	private int brightIntensity;
	private int satIntensity;
	private Picture newPicture;
	private Picture oldPicture;


	public ImageAdjuster(Picture picture) {
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		add(picture_view, BorderLayout.CENTER);
		
		JLabel title_label = new JLabel(picture.getCaption());
		add(title_label, BorderLayout.SOUTH);
		
		blurSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		brightSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		satSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		
		myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(3,1));
		myPanel.add(blurSlider, BorderLayout.NORTH);
		myPanel.add(brightSlider, BorderLayout.CENTER);
		myPanel.add(satSlider, BorderLayout.SOUTH);
		
		add(myPanel, BorderLayout.SOUTH);
		
		oldPicture = picture_view.getPicture();
		
		/*blurLabel = new JLabel("Blur: ");
		brightLabel = new JLabel("Brightness: ");
		satLabel = new JLabel("Saturation: ");*/
		
		blurSlider.setMajorTickSpacing(1);
		brightSlider.setMajorTickSpacing(25);
		satSlider.setMajorTickSpacing(25);
		
		brightSlider.setMinorTickSpacing(25);
		satSlider.setMinorTickSpacing(25);
		
		blurSlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Blur: "));
		brightSlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Brightness: "));
		satSlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Saturation: "));
		
		blurSlider.setPaintTicks(true);
		brightSlider.setPaintTicks(true);
		satSlider.setPaintTicks(true);
		
		blurSlider.setPaintLabels(true);
		brightSlider.setPaintLabels(true);
		satSlider.setPaintLabels(true);
		
		blurSlider.addChangeListener(this);
		brightSlider.addChangeListener(this);		
		satSlider.addChangeListener(this);
		
		
		/*blurSlider.addChangeListener(this);
		brightSlider.addChangeListener(this);
		satSlider.addChangeListener(this);*/


		/*add(blurSlider, BorderLayout.SOUTH);
		add(brightSlider, BorderLayout.SOUTH);
		add(satSlider, BorderLayout.SOUTH);
		add(blurLabel, BorderLayout.SOUTH);
		add(brightLabel, BorderLayout.SOUTH);
		add(satLabel, BorderLayout.SOUTH);*/
	//	blurLabel.add(blurSlider);
	//	add(blurLabel, BorderLayout.SOUTH);

		wrappedP = picture;

	}
	

	
	public MutablePixelArrayPicture clonePicture(Picture p) {
		Pixel[][] myPArray = new Pixel[oldPicture.getWidth()][oldPicture.getHeight()];
		
		for(int i = 0; i < oldPicture.getWidth(); i++) {
			for(int j = 0; j < oldPicture.getHeight(); j++) {
				myPArray[i][j]= oldPicture.getPixel(i, j);
			}
		}
		return new MutablePixelArrayPicture(myPArray, oldPicture.getCaption());
		//Picture newPicture = new PictureImpl(myPArray);
		//newPicture.createObservable();
	}
	
	public Picture blur(Picture p) {
		int value = blurSlider.getValue();
		Pixel[][] myPix = new Pixel[oldPicture.getWidth()][oldPicture.getHeight()];
		
		if(value == 0)
			return p;
		
		for(int i = 0; i < p.getWidth(); i++) {
			for(int j = 0; j < p.getHeight(); j++) {
				Pixel myPix2 = p.getPixel(i, j);
				double greens = 0;
				double blues = 0;
				double reds = 0;
				int counter = 0;

				for (int k = i - value; k < i + value; k++) {
					for (int l = j - value; l < j + value; l++) {
						try {
							Pixel myPix3 = p.getPixel(k, l);
							reds += myPix3.getRed();
							greens += myPix3.getGreen();
							blues += myPix3.getBlue();
							counter++;
						} catch (RuntimeException e) {
							
						}
					}
				}
				
				double averageReds = reds/counter;
				double averageGreens = greens/counter;
				double averageBlues = blues/counter;
				myPix2 = new ColorPixel(averageReds, averageGreens, averageBlues);
				
				myPix[i][j] = myPix2;
				p = newPicture.paint(i, j, myPix[i][j]);
			}
		}
		return p;
	}
	
	public Picture brighten(Picture p) {
		Pixel[][] myPix = new Pixel[oldPicture.getWidth()][oldPicture.getHeight()];
	
	/*
	  int value = brightSlider.getValue();

				Pixel temp;
				temp = newPicture.getPixel(j, i);	
	  
	  for(int i = 0; i < newPicture.getWidth(); i++) {
			for(int j = 0; j < newPicture.getHeight(); j++) {
				
				if(value < 0) {
					temp = temp.darken(value/-100);
				}
				if (value > 0) {
				
				temp = temp.lighten(value/100);
				}
			myPix[i][j] = temp;
			p = newPicture.paint(i, j, myPix[i][j]);
		}*/


			for(int i = 0; i < newPicture.getHeight(); i++) {
				for(int j = 0; j < newPicture.getWidth(); j++) {
					int value = brightSlider.getValue();

					Pixel temp;
					temp = newPicture.getPixel(j, i);
					if(value < 0) {
						temp = temp.darken(value/-100.0);
					}
					if (value > 0) {
					
					temp = temp.lighten(value/100.0);
					}
				myPix[j][i] = temp;
				p = newPicture.paint(j, i, myPix[j][i]);
			}
		}
		return p;
	}
	
	public Picture saturate(Picture p) {
		int value = satSlider.getValue();
		Pixel[][] myPix = new Pixel[oldPicture.getWidth()][oldPicture.getHeight()];
		if(value == 0)
			return p;
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j ++) {
				Pixel myPix2 = p.getPixel(i, j);
				
				double red = myPix2.getRed();
				double green = myPix2.getGreen();
				double blue = myPix2.getBlue();
				double saturIntensity = myPix2.getIntensity();
				double counter = 0;
				
				counter = Math.max(red, green);
				counter = Math.max(counter, blue);
				//counter = Math.max(red, blue);
				//counter = Math.max(green, red);
				double red2 = myPix2.getRed();
				double green2 = myPix2.getGreen();
				double blue2 = myPix2.getBlue();
				
				if(value < 0) {
					if(counter <= 0.01) {
						red2 = red;
						green2 = green;
						blue2 = blue;
					}
					else {
						red2 = red * (1.0 + (value/100.0)) - (saturIntensity * value / 100.0); 
						green2 = green * (1.0 + (value/100.0)) - (saturIntensity * value / 100.0);
						blue2 = blue * (1.0 + (value/100.0)) - (saturIntensity * value / 100.0); 
					}
				}
				if(value > 0) {
					red2 = red * ((counter + ((1.0 - counter) * ( value / 100.0))) / counter);
					green2 = green * ((counter + ((1.0 - counter) * ( value / 100.0))) / counter);
					blue2 = blue * ((counter + ((1.0 - counter) * ( value / 100.0))) / counter);
				}
				myPix[i][j] = new ColorPixel(red2, green2, blue2);
				p = newPicture.paint(i, j, myPix[i][j]);
			}
		}
		return p;
	}
		

	@Override
	public void stateChanged(ChangeEvent e) {
		
		newPicture = clonePicture(oldPicture);
        if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
        	newPicture = blur(newPicture);
        	newPicture = brighten(newPicture);
        	newPicture = saturate(newPicture);
            ObservablePicture myPicture = newPicture.createObservable();
            picture_view.setPicture(myPicture);
        }
	}
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		p.setCaption("KMP in Namibia");
		SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p);
		PixelInspector pixelInfo = new PixelInspector(p);
		ImageAdjuster adjustImg = new ImageAdjuster(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());

		top_panel.add(adjustImg, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);
		
		main_frame.pack();
		main_frame.setVisible(true);
	}
}
