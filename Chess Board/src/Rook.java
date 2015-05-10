public class Rook extends Piece{

	public Rook(Board brd, String color, int x, int y) {
		super(brd, color, x, y);
		this.type = 'r';
	}
	
	public boolean move(int x, int y){
		if(super.move(x, y)){
			//rooks must move in a straight line therefore only one axis can be moved at a time
			if(x != this.x && y != this.y){
				return false;
			}else{
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
			}
		}else{
			return false;
		}
		
		return(true);
	}
	
	public boolean hasValidMoves(){
		int xMoves[] = {this.x - 1, this.x+1, this.x, this.x};
		int yMoves[] = {this.y, this.y, this.y-1, this.y + 1};
		mainLoop:
		for(int f = 0; f<4; f++){
			int x = xMoves[f];
			int y = yMoves[f];
			
			if(super.move(x, y)){
				if(x != this.x && y != this.y){
					continue;
				}else{
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
			return("\u265C");
		}else{
			return("\u2656");
		}
	}
}

