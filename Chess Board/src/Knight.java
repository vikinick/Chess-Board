public class Knight extends Piece{

	public Knight(Board brd, String color, int x, int y) {
		super(brd, color, x, y);
		this.type = 'n';
	}
	
	public boolean move(int x, int y){
		if(super.move(x, y)){
			//checks from the viable moves for a knight
			if(((x == this.x+2 || x == this.x-2) && (y == this.y + 1 || y == this.y - 1)) || ((x == this.x+1 || x == this.x-1) && (y == this.y + 2 || y == this.y - 2))){
				brd.setPieceAt(this, x, y);
				brd.setPieceAt(new Piece(brd, " ", this.x, this.y), this.x, this.y);
				this.x = x;
				this.y = y;
			}else{
				return(false);
			}
		}else{
			return false;
		}
		return(true);
	}
	
	public boolean hasValidMoves(){
		int xMoves[] = {this.x - 2, this.x - 2, this.x+2, this.x +2, this.x + 1 ,this.x + 1, this.x - 1, this.x - 1};
		int yMoves[] = {this.y + 1, this.y - 1, this.y+1, this.y -1, this.y - 2, this.y + 2, this.y - 2, this.y + 2};
		
		for(int f = 0; f<8; f++){
				int x = xMoves[f];
				int y = yMoves[f];
				
				if(super.move(x, y)){
					if(((x == this.x+2 || x == this.x-2) && (y == this.y + 1 || y == this.y - 1)) || ((x == this.x+1 || x == this.x-1) && (y == this.y + 2 || y == this.y - 2))){
					}else{
						continue;
					}
				}else{
					continue;
				}
				return true;
				
		}
		return false;
	}
	public String getIcon(){
		if(color.equals("black")){
			return("\u265E");
		}else{
			return("\u2658");
		}
	}
}
