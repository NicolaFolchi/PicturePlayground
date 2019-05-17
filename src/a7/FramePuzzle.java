package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePuzzle extends JPanel implements KeyListener, MouseListener{

	
	private List<PictureView> picViewList = new ArrayList();
	private Picture pictureSpace;
	private int space;
	private Picture pictureP;
	private int tempHeight;
	private Pixel whitePix;
	
	public FramePuzzle(Picture p) {
		setLayout(new GridLayout(5,5));
		
		pictureP = p;
		space = 24;
		tempHeight = 0;
		
		int newPictureWidth = pictureP.getWidth() / 5;
		int newPictureHeight = pictureP.getHeight() / 5;
		
		for(int i = 0; i < 5; i++) {
			int tempWidth = 0;
			for (int j = 0; j < 5; j++) {
				SubPicture mySubPicture = pictureP.extract(tempWidth, tempHeight, newPictureWidth, newPictureHeight);
				mySubPicture.setCaption("mySubPicture" + 1);
				
				picViewList.add(new PictureView(mySubPicture.createObservable()));
				
				tempWidth += newPictureWidth;
			}
			tempHeight += newPictureHeight;
		}
		
		whitePix = new ColorPixel(1,1,1);
		Pixel[][] myPix = new Pixel [newPictureWidth][newPictureHeight];
		
		for(int i = 0; i < myPix.length; i++) {
			for(int j = 0; j < myPix[0].length; j++) {
				myPix[i][j] = whitePix;
			}
		}
		
		pictureSpace = new MutablePixelArrayPicture(myPix, "myCaption");
		picViewList.get(space).setPicture(pictureSpace.createObservable());
		
		for (PictureView pv : picViewList) {
			pv.addMouseListener(this);
			pv.addKeyListener(this);
			this.add(pv);
		}
		
		setFocusable(true);
		this.grabFocus();
	}
	
	public FramePuzzle(boolean doubleBuffered) {
		super(doubleBuffered);
	}
	public FramePuzzle(LayoutManager l) {
		super(l);
	}
	public FramePuzzle(LayoutManager l, boolean doubleBuffered) {
		super(l, doubleBuffered);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int numberCounter = 0;
		
		for(PictureView pv : picViewList) {
			if(pv == (PictureView) e.getSource()) {
				
				if(space / 5 == numberCounter / 5) {
					
					if(space > numberCounter) {
						
						while(space > numberCounter) {
							
							picViewList.get(space).setPicture(picViewList.get(space - 1).getPicture());
							picViewList.get(space - 1).setPicture(pictureSpace.createObservable());
							
							space = space - 1;
						}
					} 
					
				/*	while(space < numberCounter + 1 ) {
						
						picViewList.get(space).setPicture(pictureSpace.createaObservable());
						picViewList.get(space + 1).setPicture(pictureSpace.createObservable());
						
						space = space + 1;
					}
				} */
					else {
						
						while(space < numberCounter) {
							
							picViewList.get(space).setPicture(picViewList.get(space + 1).getPicture());
							picViewList.get(space + 1).setPicture(pictureSpace.createObservable());
							
							space = space + 1;
						}
					}
				}
				if(space % 5 == numberCounter % 5) {
					
					if(space > numberCounter) {
						
						while(space > numberCounter) {
							
							picViewList.get(space).setPicture(picViewList.get(space - 5).getPicture());
							picViewList.get(space - 5 ).setPicture(pictureSpace.createObservable());
							
							space = space - 5;
						}
					} 
					else {
						/*	while(space < numberCounter + 1 ) {
						
						picViewList.get(space).setPicture(pictureSpace.createaObservable());
						picViewList.get(space + 1).setPicture(pictureSpace.createObservable());
						
						space = space + 1;
					}
				} */
						while (space < numberCounter) {
							
							picViewList.get(space).setPicture(picViewList.get(space + 5).getPicture());
							picViewList.get(space + 5).setPicture(pictureSpace.createObservable());
							
							space = space + 5;
							}
						}
					}
			}
			numberCounter++;
		}
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		/*
		 if(e.getKeyCode() == 37) {

		  if(space == 25 || space == 20 || space == 15 || space == 10 || space == 5) {
				
			} else {
				
				picViewList.get(space).setPicture(picViewList.get(space + 1).getPicture());
				picViewList.get(space + 1).setPicture(pictureSpace.createObservable());
				space = space + 1;
				}
		}
		 */
		
		if(e.getKeyCode() == 37) {
			if(space == 20 || space == 15 || space == 10 || space == 5 || space == 0) {}
			else {
				picViewList.get(space).setPicture(picViewList.get(space - 1).getPicture());
				picViewList.get(space - 1).setPicture(pictureSpace.createObservable());
				
				space = space - 1;
				}
		}
		
		if(e.getKeyCode() == 38) {
			if(space == 0 || space == 1 || space == 2 || space == 3 || space == 4) {}
			else {
				picViewList.get(space).setPicture(picViewList.get(space - 5).getPicture());
				picViewList.get(space - 5).setPicture(pictureSpace.createObservable());
				
				space = space - 5;
				}
		}
		
		if(e.getKeyCode() == 39) {
			if(space == 4 || space == 9 || space == 14 || space == 19 || space == 24) {}
			else {
				picViewList.get(space).setPicture(picViewList.get(space + 1).getPicture());
				picViewList.get(space + 1).setPicture(pictureSpace.createObservable());
				
				space = space + 1;
				}
		}
		
		if(e.getKeyCode() == 40) {
			if(space == 21 || space == 22 || space == 23 || space == 24 || space == 25) {}
			else {
				picViewList.get(space).setPicture(picViewList.get(space + 5).getPicture());
				picViewList.get(space + 5).setPicture(pictureSpace.createObservable());
				
				space = space + 5;
				}
		}
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		p.setCaption("KMP in Namibia");
		SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p);
		PixelInspector pixelInfo = new PixelInspector(p);
		ImageAdjuster adjustImg = new ImageAdjuster(p);
		FramePuzzle myKMPPuzzle = new FramePuzzle(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());

		top_panel.add(myKMPPuzzle, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);
		
		main_frame.pack();
		main_frame.setVisible(true);
	}

}
