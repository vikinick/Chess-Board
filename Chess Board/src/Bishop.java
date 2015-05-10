public class Bishop extends Piece{

	public Bishop(Board brd, String color, int x, int y) {
		super(brd, color, x, y);
		this.type = 'b';
	}

	public boolean move(int x, int y){
		if(super.move(x, y)){
			
			//for diagonals the distance moved must be equal horizontal
			if(Math.abs((x - this.x)) == Math.abs(y - this.y)){
				//check for pieces in the way
				//move up left
				if(x < this.x && y < this.y){
					for(int i = 1 ; i < Math.abs(this.x - x); i ++){
						if(brd.getPieceAt(this.x - i, this.y - i).getType() != 'I'){
							return false;
						}
					}
					
				//move down left
 				}else if(x < this.x && y > this.y){
					for(int i = 1 ;i < Math.abs(this.x - x); i ++){
						if(brd.getPieceAt(this.x - i, this.y + i).getType() != 'I'){
							return false;
						}
					}
				
				//move down right
				}else if(x > this.x && y > this.y){
					for(int i = 1 ;i < Math.abs(x - this.x); i ++){
						if(brd.getPieceAt(this.x + i, this.y + i).getType() != 'I'){
							return false;
						}
					}
					
				//move up right
				}else if(x > this.x && y < this.y){
					for(int i = 1 ;i < Math.abs(x - this.x); i ++){
						if(brd.getPieceAt(this.x + i, this.y - i).getType() != 'I'){
							return false;
						}
					}
				}
				
				brd.setPieceAt(this, x, y);
				brd.setPieceAt(new Piece(brd, "none", this.x, this.y), this.x, this.y);
				this.x = x;
				this.y = y;
			}else{
				return false;
			}
		}else{
			return false;
		}
		return(true);
	}
	
	public boolean hasValidMoves(){
		int xMoves[] = {this.x -1, this.x+1};
		int yMoves[] = {this.y -1, this.y+1};
		
		for(int f = 0; f<2; f++){
			mainLoop:
			for(int g = 0; g<2; g++){
				int x = xMoves[f];
				int y = yMoves[g];
				
				if(super.move(x, y)){
					if(Math.abs((x - this.x)) == Math.abs(y - this.y)){
						//check for pieces in the way
						//move up left
						if(x < this.x && y < this.y){
							for(int i = 1 ; i < Math.abs(this.x - x); i ++){
								if(brd.getPieceAt(this.x - i, this.y - i).getType() != 'I'){
									continue mainLoop;
								}
							}
							
						//move down left
		 				}else if(x < this.x && y > this.y){
							for(int i = 1 ;i < Math.abs(this.x - x); i ++){
								if(brd.getPieceAt(this.x - i, this.y + i).getType() != 'I'){
									continue mainLoop;
								}
							}
						
						//move down right
						}else if(x > this.x && y > this.y){
							for(int i = 1 ;i < Math.abs(x - this.x); i ++){
								if(brd.getPieceAt(this.x + i, this.y + i).getType() != 'I'){
									continue mainLoop;
								}
							}
							
						//move up right
						}else if(x > this.x && y < this.y){
							for(int i = 1 ;i < Math.abs(x - this.x); i ++){
								if(brd.getPieceAt(this.x + i, this.y - i).getType() != 'I'){
									continue mainLoop;
								}
							}
						}
						return true;
					}else{
						continue;
					}
				}else{
					continue;
				}
			}
		}
		return(false);
	}
	public String getIcon(){
		if(color.equals("black")){
			return("\u265D");
		}else{
			return("\u2657");
		}
	}
}
