package wuziqi;
import java.awt.*;
public class chess {
Point poin;
Color color;
public chess(Point a, String c)
{
	this.poin=a;
	if(c.equals("black"))
	{
		color=color.black;
	}
	if(c.equals("white"))
	{
		color=color.white;
	}
}

public int getx()
{
	return poin.x;
}
public int gety()
{
	return poin.y;
}
public Color getColor()
{
	return color;
}
}
