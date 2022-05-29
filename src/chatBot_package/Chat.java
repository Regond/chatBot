package chatBot_package;

import javax.swing.JPanel;
import java.awt.Color;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Chat extends JPanel {
	
	
	int verticalScrollBarMaximumValue;
	JPanel chatArea = new JPanel();
	JToggleButton soundBtn = new JToggleButton("");
	JTextArea messageBox = new JTextArea();
	JToggleButton languageButton = new JToggleButton("EN");
	JButton sendButton = new JButton("Send message!");
	String name = "";
	Bot bot = new Bot();
	
	public Chat() {
		setBorder(null);
		setBackground(new java.awt.Color(187, 191, 202));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(new Color(51, 51, 51));
		
		
		messageBox.setWrapStyleWord(true);
		messageBox.setFont(new Font("Montserrat Light", Font.PLAIN, 24));
		messageBox.setBorder(new EmptyBorder(3, 3, 3, 3));
		messageBox.setBackground(new Color(232, 232, 232));
		
		
		soundBtn.setSelectedIcon(new ImageIcon(Chat.class.getResource("/chatBot_package/off.png")));
		soundBtn.setIcon(new ImageIcon(Chat.class.getResource("/chatBot_package/on.png")));
		soundBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		
		languageButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		
		sendButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		sendButton.setBackground(SystemColor.menu);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 687, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(messageBox, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(soundBtn, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(42)
									.addComponent(languageButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(messageBox, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(soundBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(languageButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		
		
		verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
		scrollPane.getVerticalScrollBar().addAdjustmentListener(
        e -> {
            if ((verticalScrollBarMaximumValue - e.getAdjustable().getMaximum()) == 0)
                return;
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        });
		
		chatArea.setBackground(new Color(51, 51, 51));
		scrollPane.setViewportView(chatArea);
		chatArea.setLayout(new MigLayout("fillx", "[]", "[]"));
		
		setLayout(groupLayout);
		
		callAllFunctions();
	}
	
	public void callAllFunctions() {
		enter();
		language();
		btnClick();
		bgSound();
		mute();
	}
	
	public void setText(String text) {
		MessageByUser item = new MessageByUser(text);
		chatArea.add(item, "wrap, w 50%, al right");
		chatArea.revalidate();
		chatArea.repaint();
	}
	//відображення на панельці відповіді бота
	public void answerText(String text) {
			MessageByBot item3 = new MessageByBot(text);
			chatArea.add(item3, "wrap, w 50%");
			chatArea.repaint();
			chatArea.revalidate();
			messageBox.setText("");	
			sendSound();
	}
	
	public void language() {
		languageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(languageButton.isSelected()) {
					languageButton.setText("UA");
					sendButton.setText("Відправити!");
					JOptionPane.showMessageDialog(null, "Chat will be cleared");
					chatArea.removeAll();
					chatArea.updateUI();
				}
				else if(!languageButton.isSelected()){
					languageButton.setText("EN");
					sendButton.setText("Send message!");
					JOptionPane.showMessageDialog(null, "Чат буде очищено!");
					chatArea.removeAll();
					chatArea.updateUI();
				}
			}
		});
	}
	
	public void enter() {
		messageBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String text = messageBox.getText();
					if (!languageButton.isSelected()) {
						if(text.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Enter the text!");
						}
						else {
							setText(text);
							answerText(bot.chatting(text));
						}
						
					}
					else if(languageButton.isSelected()){
						if(text.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Введіть текст");
						}
						else {
							setText(text);
							answerText(bot.chatting_ua(text));
						}
					}
					e.consume();
				}
			}
		});
	}
	public void btnClick() {
		sendButton.setBackground(SystemColor.control);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = messageBox.getText().trim();
				if (!languageButton.isSelected()) {
					if(text.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Enter the text!");
					}
					else {
						setText(text);
						answerText(bot.chatting(text));
					}
				}
				else if(languageButton.isSelected()){
					if(text.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Введіть текст");
					}
					else {
						setText(text);
						answerText(bot.chatting_ua(text));
					}
				}
				
	  }
	});
	}
	//звук відправки повідомлення
	public void sendSound() {
		try {
		File sound = new File("notification.wav");
		Clip c = AudioSystem.getClip();
		c.open(AudioSystem.getAudioInputStream(sound));
		c.start();
		}
		catch(Exception e) {}
	}
	
	public void mute() {
		soundBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(soundBtn.isSelected()) {
					stopClip();
				}
				else if(!soundBtn.isSelected()){
					playClip();
				}
			}
		});
	}
	
	Clip c;
	//грати музику
	public void playClip() {
		c.start();
		c.loop(999);
	}
	//зупинити музику
	public void stopClip() {
		c.stop();
	}
	//фонова музика
	public void bgSound() {
		try {
			File sound = new File("background.wav");
			c = AudioSystem.getClip();
			c.open(AudioSystem.getAudioInputStream(sound));
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(0);
			c.start();
			c.loop(999);
			}
			catch(Exception e) {}
	}
}
