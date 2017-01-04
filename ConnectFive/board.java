package wuziqi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ArrayList.*;

public class board extends JPanel implements ActionListener, MouseListener{

	int x, y;
	final int sizeOfQiZi = 20;
	boolean switchPlayer;
	int winner = 0;
	int[][] Qizis;
	JLabel steps,result;
	int stepCounter=0;
	boolean isgameover;
	boolean needToRestart;
	ArrayList<chess> al;				//array to store all the point will be drawn on the panel
	public board() {
		al=new ArrayList();
		needToRestart=false;
		Qizis=new int[15][15];
		isgameover=false;
		setLayout(new BorderLayout());
		switchPlayer = true; // true mean black go first
		addMouseListener(this);
		steps=new JLabel("Step count: "+stepCounter,JLabel.CENTER);
		result=new JLabel("",JLabel.CENTER);
		add(result,new BorderLayout().NORTH);
		add(steps,new BorderLayout().SOUTH);
		
	}

	public Point checklocation(int x, int y) {
		int i = 0;
		int j = 0;
		int xleft = 0;
		int xright = 0;
		int yup = 0;
		int ydown = 0;
		Point p = new Point();
		while (true) // check if the cursor click on the right range for x
		{
			xleft = getWidth() / 16+i * getWidth() / 16 - 10;
			xright = getWidth() / 16+i*getWidth()/16 + 10;
			if (x >= xleft && x <= xright)
				break;
			if (x < xleft && x < xright)
				return null;
			i++;
		}
		while (true) // check if the cursor click on the right range for y
		{
			yup = getHeight() / 16+j * getHeight() / 16 - 10;
			ydown =getHeight() / 16+ j * getHeight() / 16 + 10;
			if (y >= yup && y <= ydown)
				break;
			if (y < yup && x < ydown)
				return null;
			j++;
		}
		if (Qizis[j][i] == 1 || Qizis[j][i] == 2) {
			return null;
		}
		if (switchPlayer == true)
			Qizis[j][i] = 1;
		else
			Qizis[j][i] = 2;
		stepCounter++;                //count steps 
		System.out.println(stepCounter);
		checkWin2(j, i);
		p.x = xleft;
		p.y = yup;
		return p;
	}
//	public void checkWin(int j, int i) {
//		
//		if ((checkhorizontal(j, i) == true)||(checkvertically(j, i)==true)||(checkLP45degree(j,i)==true)||(checkRP45degree(j,i)==true)) {
//			if (switchPlayer == true) {
//				System.out.println("Black won");
//				winner = 1;
//			} else
//				System.out.println("white won");
//			winner = 2;
//		}
//
//	}
	
	public void checkWin2(int x, int y){
		//1 = black; 2 = white
		int currentColor = Qizis[x][y];
		String currentColorStr = "";
		if(currentColor == 1){
			currentColorStr = "black";
		}else if(currentColor == 2){
			currentColorStr = "white";
		}else{
			return; //never seen this color, something wrong
		}
		//check horizontal
		int line1 = checkNext(x,y,-1,0,currentColor) + checkNext(x,y,1,0,currentColor) - 1;
		//check 45 degree
		int line2 = checkNext(x,y,-1,-1,currentColor) + checkNext(x,y,1,1,currentColor) - 1;
		//check 90 degree
		int line3 = checkNext(x,y,0,-1,currentColor) + checkNext(x,y,0,1,currentColor) - 1;
		//check 90 degree
		int line4 = checkNext(x,y,1,-1,currentColor) + checkNext(x,y,-1,1,currentColor) - 1;
		if(line1 >= 5 || line2 >= 5 || line3 >= 5 || line4 >=5){
			result.setText(currentColorStr + " won");
			winner = currentColor;
			isgameover=true;
		}
		
	}
	
	private int checkNext(int x, int y, int directionX, int directionY, int currentColor){
		if(x < 0 || y < 0 || x>=Qizis.length || y>=Qizis[x].length){
			return 0; //touch boundary
		}
		if(Qizis[x][y] == currentColor){
			return 1 + checkNext(x+directionX,y+directionY,directionX,directionY,currentColor);
		}else{
			return 0;
		}
		
	}
//	public boolean checkvertically(int j,int i)
//	{
//		int isFiveOfTheSame = 1;
//		// check for up indirection
//		for (int u = j - 1; u > 0; u--) {
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (u < 0)
//				break;
//			if (u >14)
//				break;
//			if (Qizis[u][i] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//		}
//		// check for down indirection
//		for (int u = j + 1; u < 15; u++)
//		{
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (u > 14)
//				break;
//			if (u < 0)
//				break;
//			if (Qizis[u][i] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//		}
//		return false;
//	}
//	public boolean checkLP45degree(int j,int i)
//	{
//		//check for up-left to down-right 45 degree direction 
//		int isFiveOfTheSame = 1;
//		int leftcount=1;
//		int rightcount=1;
//		int tempx;
//		int tempy;
//		while(true)           //check for up-left
//		{
//			 tempx=i-leftcount;
//			 tempy=j-leftcount;
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (tempy <0)
//				break;
//			if (tempx < 0)
//				break;
//			if (Qizis[tempy][tempx] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//			leftcount++;
//		}
//		while(true)           //check for down-right
//		{
//			 tempx=i+rightcount;
//			 tempy=j+rightcount;
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (tempy >14)
//				break;
//			if (tempx >14)
//				break;
//			if (Qizis[tempy][tempx] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//			rightcount++;
//		}
//		return false;
//	}
//	public boolean checkRP45degree(int j,int i)
//	{
//		//check for up-right to down-left 45 degree direction 
//		int isFiveOfTheSame = 1;
//		int leftcount=1;
//		int rightcount=1;
//		int tempx;
//		int tempy;
//		while(true)           //check for up-right
//		{
//			 tempx=i+rightcount;
//			 tempy=j-rightcount;
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (tempy <0)
//				break;
//			if (tempx > 14)
//				break;
//			if (Qizis[tempy][tempx] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//			rightcount++;
//		}
//		while(true)           //check for down-right
//		{
//			 tempx=i-leftcount;
//			 tempy=j+leftcount;
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (tempy >14)
//				break;
//			if (tempx <0)
//				break;
//			if (Qizis[tempy][tempx] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//			leftcount++;
//		}
//		return false;
//	}
//	public boolean checkhorizontal(int j, int i) {
//		System.out.println(j + " " + i + " " + Qizis[j][i]);
//		int isFiveOfTheSame = 1;
//		// check the left horizontally with i
//		for (int u = i - 1; u >= 0; u--) {
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (u < 0)
//				break;
//			if (Qizis[j][u] == Qizis[j][i]) {
//				System.out.println(Qizis[j][i] + " to " + Qizis[j][u]);
//				isFiveOfTheSame++;
//			} else
//				break;
//		}
//		//// check the right horizontally with i
//		for (int u = i + 1; u < 17; u++) {
//			if (isFiveOfTheSame == 5)
//				return true;
//			if (u > 14)
//				break;
//			if (Qizis[j][u] == Qizis[j][i])
//				isFiveOfTheSame++;
//			else
//				break;
//		}
//		return false;
//	}
	public void restartgame()
	{
		stepCounter=0;
		Qizis = new int[15][15];
		al=new ArrayList();
		result.setText("");
		steps.setText("Step count: ");
		isgameover=false;
		needToRestart=false;
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 1; i <=15; i++) {
			g.drawLine(getWidth() / 16, i * getHeight() / 16, getWidth() * 15 / 16, i * getHeight() / 16);
			g.drawLine( i * getWidth() / 16, getHeight() / 16,
					i* getWidth() / 16, 15 * getHeight() / 16);
		}
		if(!al.isEmpty())
		{
			java.util.Iterator<chess> at=al.iterator();
			while(at.hasNext())
			{
				chess p=at.next();
				g.setColor(p.color);
				g.fillOval(p.getx(), p.gety(), sizeOfQiZi, sizeOfQiZi);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(needToRestart==false)
		{
		x = e.getX();
		y = e.getY();
		Point temp = checklocation(x, y);
		String color=switchPlayer?"black":"white";
		if (temp == null)
			return;
		al.add(new chess(temp,color));			//add each point into the arraylist
		repaint();
		if(isgameover==true)
		{
		JOptionPane.showMessageDialog(null, result.getText());
		needToRestart=true;
		return;
		}
		switchPlayer = !switchPlayer;
		steps.setText("Step count: "+stepCounter);
		}

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
