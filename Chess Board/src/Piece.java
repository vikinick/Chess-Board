public class Piece {

	char type;
	String color;
	int x;
	int y;
	
	Board brd;

	public Piece(Board brd, String color, int x, int y){
		this.color = color;
		this.type = 'I';
		this.x = x;
		this.y = y;
		this.brd = brd;
	}
	
	//copy constructor
	public Piece(Piece p){
		this.color = "none";
		this.type = 'I';
		this.x = p.getX();
		this.y = p.getY();
	}
	
	//in subclass use to check if the piece has any moves
	public boolean hasValidMoves(){
		return true;
	}
	public Board getBoard(){
		return brd;
	}
	
	public char getType(){
		return type;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean inCheck(){
		return false;
	}
	
	public boolean move(int x, int y){
		//contains checks for all cases in which no piece can move successfully independent of type
		
		//check for out of bounds moves
		if(x < 0 || y < 0 || x > 7 || y>7){
			return false;
		}
		
		//check for not moving
		if(x == this.x && y == this.y){
			//System.out.println("Same place");
			return(false);
		}
		
		//check for piece of own color at destination
		if(brd.getPieceAt(x, y).getColor().equals(this.color)){
			//System.out.println("Same Color");
			return(false);
		}
		return(true);
	}
	
	public String getColor(){
		return color;
	}
	
	public String getIcon(){
		return(" ");
	}
	
}
