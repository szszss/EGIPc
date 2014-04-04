package edu.ccmu.egipc.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class VWFrame extends JFrame {

	public static final VWFrame INSTANCE = new VWFrame();
	private JPanel contentPane;
	private Image offscreen;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(offscreen, 0, 0, 560, 252, this);
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
		//canvas.getGraphics().drawImage(offscreen, 0, 0, 560, 252, this);
	}

	/**
	 * Create the frame.
	 */
	public VWFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 560, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try {
			String path = this.getClass().getClassLoader().getResource("variowatch.jpg").getPath();
			File file = new File(path.substring(1));
			offscreen = ImageIO.read(file);
		} catch (IOException e) {
			 e.printStackTrace();
		}
		//FileImageInputStream fiis = new FileImageInputStream(new File(""));
		//fiis.
		//Image image = canvas.createImage(new InputStr);
	}

}
