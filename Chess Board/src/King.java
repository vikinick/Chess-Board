public class King extends Piece{

    public King(Board brd, String color, int x, int y) {
        super(brd, color, x, y);
        this.type = 'k';
    }
    
    public boolean move(int x, int y){
        if(super.move(x, y)){
	        if(x > this.x + 1 || y >this.y + 1 || x < this.x - 1 || y < this.y - 1){
	            return false;
	        }else{
	        	brd.setPieceAt(this, x, y);
	        	brd.setPieceAt(new Piece(brd, "none", this.x, this.y), this.x, this.y);
	            this.x = x;
	            this.y = y;
	        }
        }else{
        	return(false);
        }
	    return(true);
    }
    
    public boolean inCheck(){
        //check for knights in an L shape around the king
        if(this.x - 1 >= 0 && this.y + 2 < 8){ //make sure array doesn't check out of bounds
        	if(brd.getPieceAt(this.x - 1, this.y + 2).getType() ==  'n' && !brd.getPieceAt(this.x - 1, this.y + 2).getColor().equals(this.getColor())){
        		
        		return true;
        	}
        }
        
        if(this.x - 2 >= 0 && this.y -1 >= 0){
			if(brd.getPieceAt(this.x - 2, this.y - 1).getType() ==  'n' && !brd.getPieceAt(this.x - 2, this.y - 1).getColor().equals(this.getColor())){
				return true;
			}
	    }
        
        if(this.x - 1 >= 0 && this.y - 2 >= 0){
        	if(brd.getPieceAt(this.x - 1, this.y - 2).getType() ==  'n' && !brd.getPieceAt(this.x - 1, this.y - 2).getColor().equals(this.getColor())){
        		return true;
	        }
	    }
        
        if(this.x +1 < 8 && this.y - 2 >= 0){
        	if((brd.getPieceAt(this.x + 1, this.y - 2).getType() ==  'n' && !brd.getPieceAt(this.x + 1, this.y - 2).getColor().equals(this.getColor()))){
        		return true;
        	}
        }
        
        if(this.x +2 < 8 && this.y - 1 >= 0){
        	if(brd.getPieceAt(this.x + 2, this.y - 1).getType() ==  'n' && !brd.getPieceAt(this.x + 2, this.y - 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        	
        if(this.x -2 >= 2 && this.y + 1 < 8){
        	if(brd.getPieceAt(this.x - 2, this.y + 1).getType() ==  'n' && !brd.getPieceAt(this.x - 2, this.y + 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        if(this.x + 2 < 8 && this.y + 1 <8){
        	if((brd.getPieceAt(this.x + 2, this.y + 1).getType() ==  'n' && !brd.getPieceAt(this.x + 2, this.y + 1).getColor().equals(this.getColor()))){
        		return true;
        	}
        }
        
        if(this.x + 1 <8 && this.y +2 < 8){
        	if(brd.getPieceAt(this.x + 1, this.y + 2).getType() ==  'n' && !brd.getPieceAt(this.x + 1, this.y + 2).getColor().equals(this.getColor())){
        		return true;
        	}
        } 
        
    	if(this.x - 1 >= 0 && this.y - 1 >= 0){
    		if(brd.getPieceAt(this.x - 1, this.y - 1).getType() == 'p' && !brd.getPieceAt(this.x - 1, this.y - 1).getColor().equals(this.getColor())){
    			return true;
    		}
    	}
    	if(this.x + 1 < 8 && this.y -1 >= 0){
    		if(brd.getPieceAt(this.x + 1, this.y - 1).getType() == 'p' && !brd.getPieceAt(this.x + 1, this.y - 1).getColor().equals(this.getColor()) && this.color.equals("black")){
    	           return true;
    		}
    	}
    		
        if(this.x -1 >= 0 && this.y+1 < 8){
        	if((brd.getPieceAt(this.x - 1, this.y + 1).getType() == 'p' && !brd.getPieceAt(this.x - 1, this.y + 1).getColor().equals(this.getColor()))){
        		return true;
        	}
        }
        
        if(this.x + 1 < 8 && this.y + 1 < 8){
        	if(brd.getPieceAt(this.x + 1, this.y + 1).getType() == 'p' && !brd.getPieceAt(this.x + 1, this.y + 1).getColor().equals(this.getColor()) && this.color.equals("white")){
                return true;
        	}
        }
      
        //check for kings one space around the king
        if(this.x - 1 >= 0 && this.y -1 >= 0){
        	if(brd.getPieceAt(this.x - 1, this.y - 1).getType() ==  'k' && !brd.getPieceAt(this.x - 1, this.y - 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.x - 1 >= 0){
        	if(brd.getPieceAt(this.x - 1, this.y).getType() ==  'k' && !brd.getPieceAt(this.x - 1, this.y).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.x - 1 >= 0 && this.y +1 < 8){
        	if(brd.getPieceAt(this.x - 1, this.y + 1).getType() ==  'k' & !brd.getPieceAt(this.x - 1, this.y + 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.y - 1 >= 0){
        	if(brd.getPieceAt(this.x, this.y - 1).getType() ==  'k' && !brd.getPieceAt(this.x, this.y - 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.y + 1 < 8){
        	if(brd.getPieceAt(this.x, this.y + 1).getType() ==  'k' && !brd.getPieceAt(this.x, this.y + 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.x + 1 < 8 && this.y -1 >= 0){
        	if(brd.getPieceAt(this.x + 1, this.y - 1).getType() ==  'k' && !brd.getPieceAt(this.x + 1, this.y - 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.x + 1 <8){
        	if(brd.getPieceAt(this.x + 1, this.y).getType() ==  'k' && !brd.getPieceAt(this.x + 1, this.y).getColor().equals(this.getColor())){
        		return true;
        	}
        }
        
        if(this.x + 1 < 8 && this.y +1 < 8){
        	if(brd.getPieceAt(this.x + 1, this.y + 1).getType() ==  'k' && !brd.getPieceAt(this.x + 1, this.y + 1).getColor().equals(this.getColor())){
        		return true;
        	}
        }          
                        
        //check for rooks / bishops / queens    
            //horizontal checking
        
            for(int i = this.x - 1; i >= 0; i--){
            	if(brd.getPieceAt(i, this.y).getType() != 'I' && 
            			!(brd.getPieceAt(i, this.y).getType() =='r' || brd.getPieceAt(i, this.y).getType() == 'q')){
                    break;
                }else if((brd.getPieceAt(i, this.y).getType() == 'r' || brd.getPieceAt(i, this.y).getType() == 'q') && !brd.getPieceAt(i, this.y).getColor().equals(this.getColor())){
                    return true;
                }
                
            }
            for(int i = this.x + 1; i < 8; i++){
            	if(brd.getPieceAt(i, this.y).getType() != 'I' && 
            			!(brd.getPieceAt(i, this.y).getType() =='r' || brd.getPieceAt(i, this.y).getType() == 'q')){
            		break;
                }else if((brd.getPieceAt(i, this.y).getType() =='r' || brd.getPieceAt(i, this.y).getType() == 'q') && !brd.getPieceAt(i, this.y).getColor().equals(this.getColor())){
            		return true;
            	}
            	
                
            }

            //vertical checking
            for(int i = this.y - 1; i >= 0; i--){
            	if(brd.getPieceAt(this.x, i).getType() != 'I' && 
            			!(brd.getPieceAt(this.x, i).getType() =='r' || brd.getPieceAt(this.x, i).getType() == 'q')){
            		break;
                }else if((brd.getPieceAt(this.x, i).getType() =='r' || brd.getPieceAt(this.x, i).getType() == 'q')&& !brd.getPieceAt(this.x, i).getColor().equals(this.getColor())){
                	return true;
                }
                
            }
            
            for(int i = this.y + 1; i < 8; i++){
            	if(brd.getPieceAt(this.x, i).getType() != 'I' &&
            			!(brd.getPieceAt(this.x, i).getType() =='r' || brd.getPieceAt(this.x, i).getType() == 'q')){
            		break;
                }else if((brd.getPieceAt(this.x, i).getType() =='r' || brd.getPieceAt(this.x, i).getType() == 'q')&& !brd.getPieceAt(this.x, i).getColor().equals(this.getColor())){
                	return true;
                }
            }

            //diagonal checking
            if(this.x>this.y){
                //bot right diagonal
                for(int i = 1; i < 8 - this.x; i++){
                	if(this.x + i < 8 && this.y + i <8){
                		if(brd.getPieceAt(this.x + i, this.y + i).getType() != 'I' && !(brd.getPieceAt(this.x + i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y + i).getType() == 'q')){
                			break;
                		}else if((brd.getPieceAt(this.x + i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y + i).getType() == 'q')&& !brd.getPieceAt(this.x + i, this.y + i).getColor().equals(this.getColor())){
                			return true;
		                }
                		
                	}
                }

                //top left diagonal
                for(int i = 1; i <= this.y; i++){
                	if(this.x - i >= 0 && this.y -1 >= 0){
                		if(brd.getPieceAt(this.x - i, this.y - i).getType() != 'I' && !(brd.getPieceAt(this.x - i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y - i).getType() == 'q')){
                			break;
                		}else if((brd.getPieceAt(this.x - i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y - i).getType() == 'q')&& !brd.getPieceAt(this.x - i, this.y - i).getColor().equals(this.getColor())){
                			return true;
	                    }
                		
                	}
                }

                //top right diagonal
                for(int i = 1; i <= this.y; i++){
                	if(this.x + i < 8 && this.y - i >= 0){
                		if(brd.getPieceAt(this.x + i, this.y - i).getType() != 'I' && !(brd.getPieceAt(this.x + i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y - i).getType() == 'q')){
                			break;
                        }else if((brd.getPieceAt(this.x + i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y - i).getType() == 'q')&& !brd.getPieceAt(this.x + i, this.y - i).getColor().equals(this.getColor())){
                            return true;
	                    }
	                    
                	}
                }

                //bot left diagonal
                for(int i = 1; i < 8 - this.x; i++){
                	if(this.x - i >= 0 && this.y + i < 8){
                		if(brd.getPieceAt(this.x - i, this.y + i).getType() != 'I' && !(brd.getPieceAt(this.x - i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y + i).getType() == 'q')){
                			break;
                        }else if((brd.getPieceAt(this.x - i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y + i).getType() == 'q')&& !brd.getPieceAt(this.x - i, this.y + i).getColor().equals(this.getColor())){
                            return true;
	                    }
                		
                	}
                }
            }else{

                //bot right diagonal
                for(int i = 1; i < 8 - this.y; i++){
                	if(this.x + i < 8 && this.y + i < 8){
                		if(brd.getPieceAt(this.x + i, this.y + i).getType() != 'I' && !(brd.getPieceAt(this.x + i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y + i).getType() == 'q')){
                            break;
                        }else if((brd.getPieceAt(this.x + i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y + i).getType() == 'q')&& !brd.getPieceAt(this.x + i, this.y +i).getColor().equals(this.getColor())){
	                    	return true;
	                    }
	                    
                	}
                }

                //top left diagonal
                for(int i = 1; i <= this.x; i++){
                	if(this.x - i >= 0 && this.y - i >=0){
                		if(brd.getPieceAt(this.x - i, this.y - i).getType() != 'I' && !(brd.getPieceAt(this.x - i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y - i).getType() == 'q')){
                            break;
                        }else if((brd.getPieceAt(this.x - i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y - i).getType() == 'q')&& !brd.getPieceAt(this.x - i, this.y -1).getColor().equals(this.getColor())){
                			return true;
	                    }
                	}
                }

                //top right diagonal
                for(int i = 1; i <= this.x; i++){
                	if(this.x + i < 8 && this.y - i >=0){
                		if(brd.getPieceAt(this.x + i, this.y - i).getType() != 'I' && !(brd.getPieceAt(this.x + i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y - i).getType() == 'q')){
                            break;
                        }else if((brd.getPieceAt(this.x + i, this.y - i).getType() == 'b' || brd.getPieceAt(this.x + i, this.y - i).getType() == 'q')&& !brd.getPieceAt(this.x + i, this.y - i).getColor().equals(this.getColor())){
                			return true;
                		}
                		
                	}
                    
                }
                //bot left diagonal
                for(int i = 1; i < 8 - this.y; i++){
                	if(this.x - i >=0 && this.y + i < 8){
                		if(brd.getPieceAt(this.x - i, this.y + i).getType() != 'I' && !(brd.getPieceAt(this.x - i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y + i).getType() == 'q')){
                            break;
                        }else if((brd.getPieceAt(this.x - i, this.y + i).getType() == 'b' || brd.getPieceAt(this.x - i, this.y + i).getType() == 'q') && !brd.getPieceAt(this.x - i, this.y + i).getColor().equals(this.getColor())){
	                    	return true;
	                    }
                	}
                }
            }
        return false;
    }
    public String getIcon(){
        if(color.equals("black")){
            return("\u265A");
        }else{
            return("\u2654");
        }
    }
    
    public boolean hasValidMoves(){
        int xMoves[] = {this.x -1, this.x, this.x+1};
        int yMoves[] = {this.y -1, this.y, this.y+1};
        
        for(int f = 0; f<3; f++){
            for(int g = 0; g<3; g++){
                int x = xMoves[f];
                int y = yMoves[g];
                
                if(super.move(x, y)){
                    if(x > this.x + 1 || y >this.y + 1 || x < this.x - 1 || y < this.y - 1){
                        continue;
                    }else{
                        return true;
                    }
                }else{
                    continue;
                }
            }
        }
        return false;
    }
}