package wuziqi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class chessboard extends JFrame implements ActionListener
{
	
	private JButton confirm;
	private JMenuBar menueBar;
	private board boardd;
	private JPanel buttonPanel;
	private BorderLayout bly;
	private JMenu setting;
	private JMenu changeColor;
	private JMenuItem red,yellow,green;
	Container ctp;
	public chessboard()
	{
		setSize(600,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bly=new BorderLayout();
		ctp=getContentPane(); 
		menueBar=new JMenuBar();
		 setJMenuBar(menueBar);
		 JMenu setting = new JMenu("Setting");
		 JMenu changeColor=new JMenu("Change Color");
		 JMenuItem red=new JMenuItem("red");
		 red.setActionCommand("1");
		 JMenuItem yellow=new JMenuItem("yellow");
		 yellow.setActionCommand("2");
		 JMenuItem green=new JMenuItem("green");
		 green.setActionCommand("3");
		 JMenuItem brown=new JMenuItem("Brown");
		 brown.setActionCommand("4");
		 
		 red.addActionListener(this);
		 yellow.addActionListener(this);
		 green.addActionListener(this);
		 brown.addActionListener(this);
		 
		 setting.add(changeColor);
		 menueBar.add(setting);
		 changeColor.add(red);
		 changeColor.add(yellow);
		 changeColor.add(green);
		 changeColor.add(brown);
		 boardd=new board();
		 boardd.setSize(WIDTH, HEIGHT);
		 ctp.add(boardd,bly.CENTER);
		 boardd.setBackground(new Color(0xF3,0xD9,0x89));
		 buttonPanel=new JPanel();
		 buttonPanel.setBackground(Color.blue);
		 buttonPanel.setSize(1024,1024);
		 ctp.add(buttonPanel,bly.SOUTH);
		 confirm=new JButton("Restart");
		 confirm.addActionListener(this);
		 buttonPanel.add(confirm,new BorderLayout().CENTER);
		 
		 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==confirm)
		{
			boardd.restartgame();
			boardd.repaint();
		}
	    if(e.getActionCommand().charAt(0)=='1')
		{
			boardd.setBackground(Color.red);
		}
		
		else if(e.getActionCommand().charAt(0)=='2')
		{
			boardd.setBackground(Color.yellow);
		}
		else if(e.getActionCommand().charAt(0)=='3')
		{
			boardd.setBackground(Color.green);
		}
		else if(e.getActionCommand().charAt(0)=='4')
		{
			boardd.setBackground(new Color(0xF3,0xD9,0x89));
		}
	}
}