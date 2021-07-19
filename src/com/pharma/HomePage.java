package com.pharma;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class HomePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 729, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSunPharmaStores = new JLabel("SUN PHARMA STORES");
		lblSunPharmaStores.setForeground(new Color(255, 165, 0));
		lblSunPharmaStores.setHorizontalAlignment(SwingConstants.CENTER);
		lblSunPharmaStores.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 21));
		lblSunPharmaStores.setBounds(43, 49, 543, 96);
		frame.getContentPane().add(lblSunPharmaStores);
		
		JButton btnAddData = new JButton("ADD DATA");
		btnAddData.setForeground(new Color(0, 0, 0));
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details det = new Details();
				det.main(null);
				frame.setVisible(false);
				
			}
		});
		btnAddData.setBackground(new Color(255, 255, 255));
		btnAddData.setBounds(91, 264, 106, 35);
		frame.getContentPane().add(btnAddData);
		
		JButton btnShowReports = new JButton("SHOW REPORTS");
		btnShowReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Gui run = new Gui();
			     run.createGui();
			}
		});
		btnShowReports.setBackground(Color.WHITE);
		btnShowReports.setBounds(448, 264, 138, 35);
		frame.getContentPane().add(btnShowReports);
		
		JLabel lblNewLabel = new JLabel("New label");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/sunn4.png"));
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(0, 0, 175, 174);
		frame.getContentPane().add(lblNewLabel);
		
	}
}
