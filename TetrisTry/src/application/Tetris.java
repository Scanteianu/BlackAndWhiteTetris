package application;

import java.awt.Point;

public class Tetris {
	public Tetris()
	{
		for(boolean[] row: board)
			for(boolean b:row)
				b=false;
		currentPieceType=Piece.getRandomPiece();
		currentPiecePoints=Piece.getShape(currentPieceType);
		currentPieceRoot=new Point(16,13);
	}
	public void checkBoard()
	{
		for(int j=0; j<GUIMaker.WINDOW_HEIGHT; j++)
		{
			boolean isFull = true;
    		for(int i=0; i<GUIMaker.WINDOW_WIDTH; i++)
    		{
    			if(!board[i][j])
    				isFull=false;
    		}
    		if(isFull)
    		{
    			for(int k=j+1; k<GUIMaker.WINDOW_HEIGHT;k++)
    			{
    				for(int i=0; i<GUIMaker.WINDOW_WIDTH; i++)
    	    		{
    	    			board[i][k-1]=board[i][k];
    	    		}
    			}
    			score++;
    			j--;
    		}
    		
		}
	}
	public boolean checkLose()
	{
		//boolean isFull = true;
		//check if in currentpiece
		setPiece(false);
		for(int j=0; j<GUIMaker.WINDOW_WIDTH; j++)
		{
			if(board[j][GUIMaker.WINDOW_HEIGHT-1])
				return false;
		}
		return true;
	}
	public boolean advance(int move)
	{
		//System.out.println(currentPieceRoot);
		//System.out.println(currentPiecePoints[1]);
		checkBoard();
		//System.out.println("cb");
		refreshCurrentPiece(move);
		//System.out.println("ref");
		setPiece(false);
		boolean lost= checkLose();//make sure curpiece is removed for this
		setPiece(true);
		//System.out.println("ret");
		
		return lost;
	}
	public boolean advance()
	{
		//System.out.println(currentPieceRoot);
		//System.out.println(currentPiecePoints[1]);
		checkBoard();
		//System.out.println("cb");
		refreshCurrentPiece();
		//System.out.println("ref");
		setPiece(false);
		boolean lost= checkLose();//make sure curpiece is removed for this
		setPiece(true);
		//System.out.println("ret");
		
		return lost;
	}
	private void refreshCurrentPiece(int move)
	{
		setPiece(false);
		if(currentPieceCanTurn(move))
		{
			currentPieceRoot.x+=move;
			//System.out.println("turn");
		}
		if(currentPieceCanMove())
		{
			//remove old piece
			 currentPieceRoot.y--;
			
			 //System.out.println("move");
		}
		else
		{
			//stop current piece, get a new random
			setPiece(true);
			currentPieceRoot.y=15;
			currentPieceRoot.x=(int)(2+Math.random()*13);
			currentPieceType=Piece.getRandomPiece();
			currentPiecePoints=Piece.getShape(currentPieceType);
		}
		setPiece(true);
		
	}
	public void slideCurrentPiece(int move)
	{
		setPiece(false);
		if(currentPieceCanTurn(move))
		{
			currentPieceRoot.x+=move;
			//System.out.println("turn");
		}
		setPiece(true);
	}
	public void rotateCurrentPiece()
	{
		setPiece(false);
		if(currentPieceCanRotate())
		{
			currentPieceRotation++;
			currentPiecePoints=Piece.getShape(currentPieceType,currentPieceRotation);
		}
		setPiece(true);
	}
	private void refreshCurrentPiece()
	{
		
		if(currentPieceCanMove())
		{
			//remove old piece
			 currentPieceRoot.y--;
			 //System.out.println("move");
		}
		else
		{
			//stop current piece, get a new random
			setPiece(true);
			currentPieceRoot.y=15;
			currentPieceRoot.x=(int)(2+Math.random()*13);
			currentPieceType=Piece.getRandomPiece();
			currentPiecePoints=Piece.getShape(currentPieceType);
		}
		setPiece(true);
		
	}
	private void setPiece(boolean value)
	{
		for(Point p: currentPiecePoints)
		{
			board[p.x+currentPieceRoot.x][p.y+currentPieceRoot.y]=value;
		}
	}
	boolean currentPieceCanMove()
	{
		if(currentPieceRoot.y<=0)
			return false;
		for(Point p:currentPiecePoints)
		{
			if(board[p.x+currentPieceRoot.x][p.y+currentPieceRoot.y-1])
				return false;
		}
		return true;
	}
	boolean currentPieceCanTurn(int direction)
	{
		if(direction==0) return true;
		if(currentPieceRoot.x==0) return false;
		for(Point p:currentPiecePoints)
		{
			try
			{
			if(board[p.x+currentPieceRoot.x+direction][p.y+currentPieceRoot.y])
				return false;
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				return false;
			}
				
		}
		return true;
		
	}
	boolean currentPieceCanRotate()
	{
		Point[] newPiecePoints=Piece.getShape(currentPieceType, currentPieceRotation);
		for(Point p:newPiecePoints)
		{
			try
			{
			if(board[p.x+currentPieceRoot.x][p.y+currentPieceRoot.y])
				return false;
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				return false;
			}
				
		}
		return true;
		
	}
	
	public boolean[][] getBoard()
	{
		setPiece(true);
		return board;
	}
	boolean[][] board= new boolean[GUIMaker.WINDOW_WIDTH][GUIMaker.WINDOW_HEIGHT+10];
	Point currentPieceRoot;
	Point[] currentPiecePoints;
	Piece.Type currentPieceType;
	int currentPieceRotation=0;
	private int score=0;
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}
