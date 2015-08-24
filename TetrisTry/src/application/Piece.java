package application;

import java.awt.Point;

public class Piece {
	public static Type getRandomPiece()
	{
		switch((int)(Math.random()*7))
		{
			case 1:
				return Type.SQUARE;

			case 2:
				return Type.LEFTL;

			case 3:
				return Type.RIGHTL;

			case 4:
				return Type.T;
			case 5:
				return Type.LINE;
			case 6:
				return Type.LEFTZ;
			case 0:
				return Type.RIGHTZ;
		}
		return null;
	}
	public enum Type{
		SQUARE,
		LINE,
		T,
		LEFTL,
		RIGHTL,
		LEFTZ,
		RIGHTZ
	};
	public static Point[] getShape(Type t)
	{
		Point[] points=new Point[4];
		switch(t)
				{
					case SQUARE:
						points[0]=new Point(0,0);
						points[1]=new Point(0,1);
						points[2]=new Point(1,0);
						points[3]=new Point(1,1);
						return points;

					case LINE:
						points[0]=new Point(0,0);
						points[1]=new Point(1,0);
						points[2]=new Point(2,0);
						points[3]=new Point(3,0);
						return points;


					case T:
						points[0]=new Point(0,0);
						points[1]=new Point(2,0);
						points[2]=new Point(1,0);
						points[3]=new Point(1,1);
						return points;


					case LEFTL:
						points[0]=new Point(0,0);
						points[1]=new Point(2,0);
						points[2]=new Point(1,0);
						points[3]=new Point(0,1);
						return points;

					case RIGHTL:
						points[0]=new Point(0,0);
						points[1]=new Point(2,0);
						points[2]=new Point(1,0);
						points[3]=new Point(2,1);
						return points;

					case LEFTZ:
						points[0]=new Point(0,1);
						points[1]=new Point(1,1);
						points[2]=new Point(1,0);
						points[3]=new Point(2,0);
						return points;

					case RIGHTZ:
						points[0]=new Point(0,0);
						points[1]=new Point(2,1);
						points[2]=new Point(1,0);
						points[3]=new Point(1,1);
						return points;

				}
		return null;
		
	}
	public static Point[] getShape(Type t, int rotation)
	{

		Point[] points=new Point[4];
		switch(t)
				{
					case SQUARE:
						points[0]=new Point(0,0);
						points[1]=new Point(0,1);
						points[2]=new Point(1,0);
						points[3]=new Point(1,1);
						return points;

					case LINE:
						if(rotation%2==0)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(1,0);
							points[2]=new Point(2,0);
							points[3]=new Point(3,0);
						}
						else
						{
							points[0]=new Point(0,0);
							points[1]=new Point(0,1);
							points[2]=new Point(0,2);
							points[3]=new Point(0,3);
						}
						return points;


					case T:
						if(rotation%4==0)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(2,0);
							points[2]=new Point(1,0);
							points[3]=new Point(1,1);
						}
						else if(rotation%4==1)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(0,1);
							points[2]=new Point(0,2);
							points[3]=new Point(1,1);
						}
						else if(rotation%4==2)
						{
							points[0]=new Point(0,1);
							points[1]=new Point(2,1);
							points[2]=new Point(1,1);
							points[3]=new Point(1,0);
						}
						else
						{
							points[0]=new Point(1,0);
							points[1]=new Point(1,1);
							points[2]=new Point(1,2);
							points[3]=new Point(0,1);
						}
						return points;


					case LEFTL:
						if(rotation%4==0)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(2,0);
							points[2]=new Point(1,0);
							points[3]=new Point(0,1);
						}
						else if(rotation%4==1)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(0,1);
							points[2]=new Point(0,2);
							points[3]=new Point(1,2);
						}
						else if(rotation%4==2)
						{
							points[0]=new Point(0,1);
							points[1]=new Point(2,1);
							points[2]=new Point(1,1);
							points[3]=new Point(2,0);
						}
						else
						{
							points[0]=new Point(1,0);
							points[1]=new Point(1,1);
							points[2]=new Point(1,2);
							points[3]=new Point(0,0);
						}
						return points;

					case RIGHTL:
						if(rotation%4==0)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(2,0);
							points[2]=new Point(1,0);
							points[3]=new Point(2,1);
						}
						else if(rotation%4==1)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(0,1);
							points[2]=new Point(0,2);
							points[3]=new Point(1,0);
						}
						else if(rotation%4==2)
						{
							points[0]=new Point(0,1);
							points[1]=new Point(2,1);
							points[2]=new Point(1,1);
							points[3]=new Point(0,0);
						}
						else
						{
							points[0]=new Point(1,0);
							points[1]=new Point(1,1);
							points[2]=new Point(1,2);
							points[3]=new Point(0,2);
						}
						return points;

					case LEFTZ:
						if(rotation%2==0)
						{
							points[0]=new Point(0,1);
							points[1]=new Point(1,1);
							points[2]=new Point(1,0);
							points[3]=new Point(2,0);
						}
						else
						{
							points[0]=new Point(0,1);
							points[1]=new Point(1,1);
							points[2]=new Point(0,0);
							points[3]=new Point(1,2);
						}
						return points;

					case RIGHTZ:
						if(rotation%2==0)
						{
							points[0]=new Point(0,0);
							points[1]=new Point(2,1);
							points[2]=new Point(1,0);
							points[3]=new Point(1,1);
						}
						else
						{
							points[0]=new Point(1,0);
							points[1]=new Point(0,2);
							points[2]=new Point(1,1);
							points[3]=new Point(0,1);
						}
						return points;

				}
		return null;
	}
}
