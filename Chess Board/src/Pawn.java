public class Pawn extends Piece{

	public Pawn(Board brd, String color, int x, int y) {
		super(brd, color, x, y);
		this.type = 'p';
	}
	
	public boolean move(int x, int y){
		if(super.move(x, y)){
			if(color.equals("black")){
				//checks first moves that are never legal
				//moving up, moving 2 spaces left/right
				if(y<this.y || x>this.x+1 || x<this.x -1 || y > this.y+2){
					return(false);
				}
				
				//check for push
				//pawn cannot push if it is not on its starting line or if the move tries to move horizontally
				if(y>this.y + 1 && (this.y != 1 || x != this.x || brd.getPieceAt(x, y).getType() != 'I')){
					return(false);
				}

				//code for taking another piece
				//returns if there is no piece in the location or if the peice is the same color
				if((x != this.x) && (y == this.y+1)){
					if(brd.getPieceAt(x, y).getType() == 'I'){
						return(false);
					}
				}
				
				if(x != this.x && y!= this.y+1){
					return false;
				}
				if(y == this.y+1 && x == this.x){
					if(brd.getPieceAt(x, y).getType() != 'I'){
						return false;
					}
				}
				
				brd.setPieceAt(this, x, y);
				brd.setPieceAt(new Piece(brd, "none", this.x, this.y), this.x, this.y);
				this.x = x;
				this.y = y;
			}else{
				//same code as for black pieces
				//checks first moves that are never legal
				//moving up, moving 2 spaces left/right
				if(y>this.y || x>this.x+1 || x<this.x -1 || y < this.y-2){
					return(false);
				}
				
				//check for push
				//pawn cannot push if it is not on its starting line or if the move tries to move horizontally
				if(y<this.y - 1 && (this.y != 6 || x != this.x || brd.getPieceAt(x, y).getType() != 'I')){
					return(false);
				}

				//code for taking another piece
				//returns if there is no piece in the location or if the peice is the same color
				if((x != this.x) && (y == this.y-1)){
					if(brd.getPieceAt(x, y).getType() == 'I'){
						return(false);
					}
				}
				
				if(x != this.x && y!= this.y-1){
					return false;
				}
				if(y == this.y-1 && x == this.x){
					if(brd.getPieceAt(x, y).getType() != 'I'){
						return false;
					}
				}
				
				brd.setPieceAt(this, x, y);
				brd.setPieceAt(new Piece(brd, "none", this.x, this.y), this.x, this.y);
				this.x = x;
				this.y = y;
			}
			if(color.equals("white")){
				if(this.y == 0){
					PromotionSelector p = new PromotionSelector(brd, this);
					p.make(brd, this);
				}
			}else{
				if(this.y == 7){
					PromotionSelector p = new PromotionSelector(brd, this);
					p.make(brd, this);
				}
			}
		}else{
			return false;
		}
		return(true);
	}
	
	public boolean hasValidMoves(){
		
		int xMoves[] = {this.x -1, this.x, this.x+1};
		int y;
		if(color == "black"){
			y = this.y+1;
		}else{
			y = this.y-1;
		}
		
		for(int i = 0; i<3; i++){
			int x = xMoves[i];
			if(super.move(x, y)){
				if(color.equals("black")){
					//checks first moves that are never legal
					//moving up, moving 2 spaces left/right
					if(y<this.y || x>this.x+1 || x<this.x -1 || y > this.y+2){
						continue;
					}
					
					//check for push
					//pawn cannot push if it is not on its starting line or if the move tries to move horizontally
					if(y>this.y + 1 && (this.y != 1 || x != this.x || brd.getPieceAt(x, y).getType() == 'I')){
						continue;
					}

					//code for taking another piece
					//returns if there is no piece in the location or if the peice is the same color
					if((x != this.x) && (y == this.y+1)){
						if(brd.getPieceAt(x, y).getType() == 'I'){
							continue;
						}
					}
					if(x != this.x && y!= this.y+1){
						continue;
					}
					if(y == this.y+1 && x == this.x){
						if(brd.getPieceAt(x, y).getType() != 'I'){
							continue;
						}
					}
					return true;
				}else{
					//same code as for black pieces
					//checks first moves that are never legal
					//moving up, moving 2 spaces left/right
					if(y>this.y || x>this.x+1 || x<this.x -1 || y < this.y-2){
						continue;
					}
					
					//check for push
					//pawn cannot push if it is not on its starting line or if the move tries to move horizontally
					if(y<this.y - 1 && (this.y != 6 || x != this.x || brd.getPieceAt(x, y).getType() == 'I')){
						continue;
					}

					//code for taking another piece
					//returns if there is no piece in the location or if the peice is the same color
					if((x != this.x) && (y == this.y-1)){
						if(brd.getPieceAt(x, y).getType() == 'I'){
							continue;
						}
					}
					if(x != this.x && y!= this.y-1){
						continue;
					}
					if(y == this.y-1 && x == this.x){
						if(brd.getPieceAt(x, y).getType() != 'I'){
							continue;
						}
					}
					return true;
				}
			}else{
				continue;
			}
		}
		return false;
	}
	
	public String getIcon(){
		if(color.equals("black")){
			return("\u265F");
		}else{
			return("\u2659");
		}
	}
}
