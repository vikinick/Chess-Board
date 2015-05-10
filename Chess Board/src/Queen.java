public class Queen extends Piece{

	public Queen(Board brd, String color, int x, int y) {
		super(brd, color, x, y);
		this.type = 'q';
	}
	
	public boolean move(int x, int y){
		if(super.move(x, y)){
			if(x == this.x || y == this.y){
				//check for pieces in way
				//uses rook algorithm
				if(x < this.x){
					for(int i = this.x - 1; i > x; i--){
						if(brd.getPieceAt(i, y).getType() != 'I'){
							return false;
						}
					}
				}else if(x > this.x){
					for(int i = this.x + 1; i < x; i++){
						if(brd.getPieceAt(i, y).getType() != 'I'){
							return false;
						}
					}
				}else if (y < this.y){
					for(int i = this.y - 1; i > y; i--){
						if(brd.getPieceAt(x, i).getType() != 'I'){
							return false;
						}
					}
				}else if(y > this.y){
					for(int i = this.y + 1; i < y; i++){
						if(brd.getPieceAt(x, i).getType() != 'I'){
							return false;
						}
					}
				}
				brd.setPieceAt(this, x, y);
				brd.setPieceAt(new Piece(brd, " ", this.x, this.y), this.x, this.y);
				this.x = x;
				this.y = y;
			}else if(Math.abs((x - this.x)) == Math.abs(y - this.y)){
				//check for pieces in way
				//same as algorithm for bishop
				
					//move up left
					if(x < this.x && y < this.y){
						for(int i = 1 ;i < Math.abs(this.x - x); i ++){
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
		return true;
	}
	
	public boolean hasValidMoves(){
		int xMoves[] = {this.x -1, this.x, this.x+1};
		int yMoves[] = {this.y -1, this.y, this.y+1};
		
		for(int f = 0; f<3; f++){
			mainLoop:
			for(int g = 0; g<3; g++){
				int x = xMoves[f];
				int y = yMoves[g];
				
				if(super.move(x, y)){
					if(x == this.x || y == this.y){
						//check for pieces in way
						//uses rook algorithm
						if(x < this.x){
							for(int i = this.x - 1; i > x; i--){
								if(brd.getPieceAt(i, y).getType() != 'I'){
									continue mainLoop;
								}
							}
						}else if(x > this.x){
							for(int i = this.x + 1; i < x; i++){
								if(brd.getPieceAt(i, y).getType() != 'I'){
									continue mainLoop;
								}
							}
						}else if (y < this.y){
							for(int i = this.y - 1; i > y; i--){
								if(brd.getPieceAt(x, i).getType() != 'I'){
									continue mainLoop;
								}
							}
						}else if(y > this.y){
							for(int i = this.y + 1; i < y; i++){
								if(brd.getPieceAt(x, i).getType() != 'I'){
									continue mainLoop;
								}
							}
						}
						return true;
					}else if(Math.abs((x - this.x)) == Math.abs(y - this.y)){
						//check for pieces in way
						//same as algorithm for bishop
						
							//move up left
							if(x < this.x && y < this.y){
								for(int i = 1 ;i < Math.abs(this.x - x); i ++){
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
						}else{
							continue;
						}
				}else{
					continue;
				}
			}
		}
		return false;
	}
	
	
	public String getIcon(){
		if(color.equals("black")){
			return("\u265B");
		}else{
			return("\u2655");
		}
	}
}

