package chatBot_package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class StartFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	int verticalScrollBarMaximumValue;
	Chat chat = new Chat();
	
	String name = "";
	JButton startButton = new JButton("Start");
	public StartFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartFrame.class.getResource("/chatBot_package/icon-256x256.png")));
		setTitle("ChatBot");
		setResizable(false);
		JPanel contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 551);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(null);
		menuPanel.setBackground(new java.awt.Color(51, 51, 51));
		contentPane.add(menuPanel);
		
		JLabel greetLabel = new JLabel("Welcome!");
		greetLabel.setFont(new Font("Piximisa", Font.PLAIN, 72));
		greetLabel.setForeground(SystemColor.inactiveCaptionBorder);
		
		startButton.setSelected(true);
		startButton.setBorderPainted(false);
		startButton.setBackground(UIManager.getColor("Table.selectionBackground"));
		startButton.setForeground(new Color(0, 0, 0));
		startButton.setFont(new Font("Piximisa", Font.PLAIN, 35));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(chat);
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Powered by Tkach Vasyl");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(219)
					.addComponent(greetLabel, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
					.addGap(197))
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(278)
					.addComponent(startButton, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addGap(256))
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addContainerGap(304, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(276))
		);
		gl_menuPanel.setVerticalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(41)
					.addComponent(greetLabel)
					.addGap(85)
					.addComponent(startButton, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(174)
					.addComponent(lblNewLabel)
					.addGap(44))
		);
		menuPanel.setLayout(gl_menuPanel);

		}
}
