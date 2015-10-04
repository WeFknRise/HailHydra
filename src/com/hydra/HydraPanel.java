package com.hydra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Method;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HydraPanel extends JFrame {

	private JLabel lbl;
	private JPanel pnl;
	private Random r;
	private int red, green, blue;

	public HydraPanel() {

		this.setTitle("Hail Hydra!!!");

		setVisible(true);
		setSize(300, 300);
		setResizable(false);

		r = new Random();
		red = r.nextInt(255);
		green = r.nextInt(255);
		blue = r.nextInt(255);

		getContentPane().setBackground(new Color(red, green, blue));

		pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		lbl = new JLabel("Hail Hydra");
		lbl.setHorizontalAlignment(SwingConstants.CENTER); // I've also tried JPanel.CENTER
		lbl.setVerticalAlignment(SwingConstants.CENTER); // I've also tried JPanel.CENTER
		lbl.setHorizontalTextPosition(SwingConstants.CENTER); // I've also tried JPanel.CENTER
		lbl.setVerticalTextPosition(SwingConstants.CENTER); // I've also tried JPanel.CENTER
		add(lbl);

		WindowHandler wh = new WindowHandler();
		this.addWindowListener(wh);
	}

	public static void positionFrameRandomly(final Window frame) {
		positionFrameOnScreen(frame, Math.random(), Math.random());
	}

	public static Rectangle getMaximumWindowBounds() {
		final GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			final Method method = GraphicsEnvironment.class.getMethod("getMaximumWindowBounds", (Class[]) null);
			return (Rectangle) method.invoke(localGraphicsEnvironment, (Object[]) null);
		}
		catch (Exception e) {
		}

		final Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		return new Rectangle(0, 0, s.width, s.height);
	}

	public static void positionFrameOnScreen(final Window frame, final double horizontalPercent, final double verticalPercent) {

		final Rectangle s = getMaximumWindowBounds();
		final Dimension f = frame.getSize();
		final int w = Math.max(s.width - f.width, 0);
		final int h = Math.max(s.height - f.height, 0);
		final int x = (int) (horizontalPercent * w) + s.x;
		final int y = (int) (verticalPercent * h) + s.y;
		frame.setBounds(x, y, f.width, f.height);

	}

	private class WindowHandler implements WindowListener {

		public void windowActivated(WindowEvent e) {

		}

		public void windowClosed(WindowEvent e) {

		}

		public void windowClosing(WindowEvent e) {
			for (int i = 0; i < 3; i++) {
				HydraPanel h = new HydraPanel();
				positionFrameRandomly(h);
			}
		}

		public void windowDeactivated(WindowEvent e) {

		}

		public void windowDeiconified(WindowEvent e) {

		}

		public void windowIconified(WindowEvent e) {

		}

		public void windowOpened(WindowEvent e) {

		}
	}
}
