package chatBot_package;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class MessageByUser extends JLayeredPane {
	public MessageByUser(String text) {
		txt = new chatBot_package.MessageDesign();
		txt.setFont(new Font("Montserrat", Font.PLAIN, 10));
	    jLabel1 = new javax.swing.JLabel();
	    
	    txt.setEditable(false);
	    txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    txt.setBgColor(new java.awt.Color(255, 255, 255));
	    txt.setText(text);
	    jLabel1.setIcon(new ImageIcon(MessageByUser.class.getResource("/chatBot_package/user.png"))); // NOI18N

	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
	    			.addContainerGap(120, Short.MAX_VALUE)
	    			.addComponent(txt, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap())
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(txt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap())
	    );
	    this.setLayout(layout);
	}

	/**
	 * Create the panel.
	 */
	

private javax.swing.JLabel jLabel1;
private chatBot_package.MessageDesign txt;
}


