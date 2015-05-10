import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;







import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.*;
import javax.swing.*;

public class Board implements ActionListener {
	Piece[][] pieces = new Piece[8][8]; //Holds each piece
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    JButton[][] chessBoardSquares = new JButton[8][8]; //holds the squares
    private JPanel chessBoard; //hold each square
    private static final String COLS = "ABCDEFGH"; //the column labels
    private Color blackSquare = new Color(250,0,0); //the color of the "black" square
    private Color whiteSquare = new Color(250,250,250); //the color of the "white" square
	private int origX = -1; //used to determine original place of piece
	private int origY = -1; //used to determine original place of piece
	private int destX = -1; //used ot determine destination of piece
	private int destY = -1; //used ot determine destination of piece
	private boolean clicked = false; //tells if a piece has been selected
	private boolean whiteTurn = true; //turn control
	private boolean gameOver = false; //tells if the game is over
	private char whitePlayer = 'P'; //P = Human, H = HardAI, E = EasyAI
	private char blackPlayer = 'P'; //P = Human, H = HardAI, E = EasyAI
	private int[] whiteKingPos = new int[2]; // keeps location of the king, [0] is x and [1] is y
	private int[] blackKingPos = new int[2]; //keeps location of the king, [0] is x and [1] is y
	
	public static NewJFrame j; //options window
	public static Music m; //plays music
	public char whoWon = '0'; //holds who won
	
    public static void main(String[] args){
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Board board = new Board(); //construct the board
                board.initializeGui(); //set up the GUI
                board.setupBoard(); //set up the board on the gui
                
		        m = new Music(board);		     //play the music
		        m.begin();
            	JFrame f = new JFrame(); //create a new JFrame
            	f = board.Initialize(board); //initialize the Jframe

            }
        });
    }
    
    public JFrame Initialize(Board board){

        JFrame f = new JFrame("Chess by GK KA NG NV"); //name of the window
        f.setResizable(false); //do not allow the window to be resized
        f.add(board.getGui()); //add the chessboard
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //set X to close
        f.setLocationByPlatform(true); // chose where the window opens
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
        j = new NewJFrame(board); //open the options window
        j.make();
        return f;
    }
    
    public void setupBoard(){ //set up all of the pieces
		pieces[0][0] = new Rook(this,"black",0,0); //back row of black
		pieces[1][0] = new Knight(this,"black",1,0);
		pieces[2][0] = new Bishop(this,"black",2,0);
		pieces[3][0] = new Queen(this,"black",3,0);
		pieces[4][0] = new King(this,"black",4,0);
		pieces[5][0] = new Bishop(this,"black",5,0);
		pieces[6][0] = new Knight(this,"black",6,0);
		pieces[7][0] = new Rook(this,"black",7,0);
		for(int i = 0; i < 8; i++){ //row of black pawns
			pieces[i][1] = new Pawn(this,"black",i,1);
		}
		for(int i = 2; i < 6; i++){ //blank pieces
			for(int j = 0; j < 8; j++){
				pieces[j][i] = new Piece(this,"none",j,i);
			}
		}
		for(int i = 0; i < 8; i++){ //white pawns
			pieces[i][6] = new Pawn(this,"white",i,6);
		}
		pieces[0][7] = new Rook(this,"white",0,7); //back row of white
		pieces[1][7] = new Knight(this,"white",1,7);
		pieces[2][7] = new Bishop(this,"white",2,7);
		pieces[3][7] = new Queen(this,"white",3,7);
		pieces[4][7] = new King(this,"white",4,7);
		pieces[5][7] = new Bishop(this,"white",5,7);
		pieces[6][7] = new Knight(this,"white",6,7);
		pieces[7][7] = new Rook(this,"white",7,7);
		whiteKingPos[0] = 4; //set the white King's position X
		whiteKingPos[1] = 7; //set the white King's position Y
		blackKingPos[0] = 4; //set the black King's position X
		blackKingPos[1] = 0; //set the black King's position Y
		
		for (int i = 0; i < 8; i++){ //set the icon for the two top rows
        	for(int j = 0; j< 2; j++){
        		chessBoardSquares[i][j].setText(pieces[i][j].getIcon());
        	}
        }
        for(int i = 0; i < 8; i++){ //set the icon for the middle rows
        	for(int j = 2; j < 6; j++){
        		chessBoardSquares[i][j].setText(" ");
        	}
        }
        
        for (int i = 0; i < 8; i++){ //set the icon for the lower two rows
        	for(int j = 6; j < 8; j++){
        		chessBoardSquares[i][j].setText(pieces[i][j].getIcon());
        	}
        }
        whiteTurn = true; //set it so it is white's turn
	}
	
   	public final void initializeGui() {
        // set up the main GUI
   		Button options = new Button("Options"); //options button
   		options.setActionCommand("options");
   		options.addActionListener(this);
   		
   		Button playOptions = new Button("Player Options"); //options dealing with the player button
   		playOptions.setActionCommand("playoptions");
   		playOptions.addActionListener(this);
   		
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar(); //make a toolbar
        tools.setFloatable(false); //make the toolbar so it cannot move
        tools.add(options); //add the options button
        tools.add(playOptions); //addthe player options button
        gui.add(tools, BorderLayout.PAGE_START); //add it all to the gui

        chessBoard = new JPanel(new GridLayout(0, 9)); //make a 9X9 grid/
        chessBoard.setBorder(new LineBorder(Color.BLACK)); //set the border color to black
        gui.add(chessBoard); //add the chessboard to the gui

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0); //margins should be 0 on each side
        for (int j = 0; j < 8; j++) { //set up each button
            for (int i = 0; i < 8; i++) { 
                JButton b = new JButton(); //make a new button
                b.setHorizontalTextPosition(SwingConstants.CENTER); //center the icon
                b.setFont(new Font("SanSerif",Font.BOLD,40)); //set the text to SansSerif and the font to bold size 40
                b.setMargin(buttonMargin); //set the margin
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)); //set the size to 64X64 pixels
                b.setIcon(icon); //set the icon to blank
                if ((i % 2 == 1 && j % 2 == 1) //color the squares
                        //) {
                        || (i % 2 == 0 && j % 2 == 0)) {
                    b.setBackground(whiteSquare);
                } else {
                    b.setBackground(blackSquare);
                }
                chessBoardSquares[i][j] = b; //add the button to the board
            }
        }
    	for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				String holder = new String( "0" + i + "0" + j + "a");
				chessBoardSquares[i][j].setActionCommand(holder); //add a actioncommand to each button
				chessBoardSquares[i][j].addActionListener(this); //add an actionlistener to each button
			}
		}
        
        //fill the chess board with the buttons
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int i = 0; i < 8; i++) {
            chessBoard.add(
                    new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }
    }
   	
   	public boolean movePiece(){
   		if(pieces[origX][origY].move(destX,destY)){ //check if the move is possible, also does the move
   			System.out.println("Move has been done in background");
   			clicked = false; //sets the "chosen" boolean to false
   			chessBoardSquares[destX][destY].setText(chessBoardSquares[origX][origY].getText()); //set the button text to the original piece's button text
   			chessBoardSquares[origX][origY].setText(" "); //set the original button's text to blank
   			pieces[origX][origY] = new Piece(this,"none", origX, origY); //create a new blank piece at the original location
   			return true; //return true if happened
   		}else{
   			System.out.println("Move is invalid"); //move is invalid
	   		destX = -1; //set all of the vars dealing with movement to -1
	   		destY = -1;
	   		origX = -1;
	   		origY = -1;
	   		clicked = false; //change the "clicked" boolean to false
	   		return false; //returns false if failed
   		}
   	}
   	
public Piece getRandomPiece(String color){
        Random rx = new Random(); //create random seed for X
        Random ry = new Random(); //create random seed for Y
        
        int x = rx.nextInt(8); //random number between 0 and 7
        int y = ry.nextInt(8); //random number betwen 0 and 7
        
        while(pieces[x][y].getType() == 'I' || !pieces[x][y].getColor().equals(color) || !pieces[x][y].hasValidMoves()){ //select a new location for the piece
            x = rx.nextInt(8); //select a new X
            y = ry.nextInt(8); //select a new Y
        }
        return pieces[x][y]; //return the piece that can move
    }
    
    public void moveRandomPiece(String color){
        System.out.println("choosing piece"); 
        Piece p = getRandomPiece(color); //choose a random piece
        
        int oldX = p.getX(); //set oldX,oldY to location of the old piece
        int oldY = p.getY(); 
        Random rx = new Random(); //new random seed for X
        Random ry = new Random(); //new random seed for Y
        
        int x = rx.nextInt(8); //select a number between 0 and 7 for X
        int y = ry.nextInt(8); //select a number between 0 and 7 for Y
        
        while(!p.move(x, y)){ //while the piece cannot move select a new piece
            x = rx.nextInt(8); 
            y = ry.nextInt(8);
        }
        chessBoardSquares[x][y].setText(p.getIcon()); //change the text of the new piece's location to the text of the old location
        chessBoardSquares[oldX][oldY].setText(" "); //set the text to blank for the original location
        if(pieces[x][y].getType() == 'k'){ //if the piece is a king
        	if(pieces[x][y].getColor() == "white"){ //if the piece is white
        		System.out.println("White king is now at x = " + x + " y=" + y);
        		whiteKingPos[0] = x; //change the location of the pointer to the white king
        		whiteKingPos[1] = y;
        	}else{ //if the king is black
        		System.out.println("Black king is now at x = " + x + " y=" + y);
        		blackKingPos[0] = x; //change the location of the pointer to the black king
        		blackKingPos[1] = y;
        	}
        }
        destX = x; //change the destination variables to the destination that the piece is going to
        destY = y;
        System.out.println("piece at " + oldX + " " + oldY + " moved to " + x + " " + y);
    }
   	
   	public void changeSquareColorImplement(){ //chang the color of the squares
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
            		if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) { //loop through the whole board
            			chessBoardSquares[i][j].setBackground(whiteSquare);
            		} else {
            			chessBoardSquares[i][j].setBackground(blackSquare);
            		}
            }
        }
   	}
   	
	public void setBlack(Color c){ //change the color of the black square
		blackSquare = c;
		changeSquareColorImplement();
	}
	
	public void setWhite(Color c){ //change the color of the white square
		whiteSquare = c;
		changeSquareColorImplement();
	}

	public final JComponent getChessBoard() { //return the chessBoard
        return chessBoard;
    }
    
	public Piece getPieceAt(int x, int y){ //return the piece at the location
		return pieces[x][y];
	}

    public void setPieceAt(Piece p, int x, int y){ //set the piece at the location to the new piece
    	pieces[x][y] = p;
    }
    
    public boolean checkKing(){ //check if the kings are in check
    	System.out.println("Checking Kings");
    	return(this.checkBlackKing() || this.checkWhiteKing());
    }
    
    public boolean checkBlackKing(){ //check if the black king is in check
    	System.out.println("Checking Black King");
    	return(pieces[this.getBlackX()][this.getBlackY()].inCheck());
    }
    
    public boolean checkWhiteKing(){ //check if the white king is in check
    	System.out.println("Checking White King");
    	return pieces[this.getWhiteX()][this.getWhiteY()].inCheck();
    }
    
    public int getWhiteX(){ //return the location for X of the white king
    	return whiteKingPos[0];
    }
    
    public int getWhiteY(){ //return the location for Y of the white king
    	return whiteKingPos[1];
    }
    
    public int getBlackX(){ //return the location for X of the black king
    	return blackKingPos[0];
    }
    
    public int getBlackY(){ //return the location for Y of the black king
    	return blackKingPos[1];
    }

    public final JComponent getGui() { //return the gui
        return gui;
    }
    
    public void setWhitePlayer(char a){ //choose who plays, the AI or a human for white
    	whitePlayer = a;
    	System.out.println("White player is now " + a );
    }
    
    public void setBlackPlayer(char a){//choose who plays, the AI or a human for black
    	blackPlayer = a;
    	System.out.println("Black player is now " + a);
    }
    
    public char getWhitePlayer(){ //see who is playing white
    	return whitePlayer;
    }
    
    public char getBlackPlayer(){ //see who is playing black
    	return blackPlayer;
    }

	public void actionPerformed(ActionEvent e) { //if a button is pressed
		if(e.getActionCommand().equals("options")){
			new OptionsWindow(this).setVisible(true); //set the options window to visible
		}else if(e.getActionCommand().equals("playoptions")){ //show the options for players
			j = new NewJFrame(this);
		    j.make();
		}else if(whiteTurn && whitePlayer =='P' || !whiteTurn && blackPlayer =='P'){ //if chessboard is clicked
			if(!clicked){ //set selected boolean to true
				origX = Integer.valueOf(e.getActionCommand().substring(0,2)); //get the X of the selected button
				origY = Integer.valueOf(e.getActionCommand().substring(2,4)); //get the Y of the selected button
				clicked = true; //set the clicked boolean to true
				if(whiteTurn && pieces[origX][origY].getColor() != "white"){ //if a white piece is not selected on a white turn
					origX = -1;
					origY = -1;
					clicked = false;
				}else if(!whiteTurn && pieces[origX][origY].getColor() != "black"){ //if a black piece is not selected on a black turn
					origX = -1;
					origY = -1;
					clicked = false;
				}else if(pieces[origX][origY].getType() == 'I'){ //if a blank piece is selected
					origX = -1;
					origY = -1;
					clicked = false;
				}
				System.out.println("\n \n");
				System.out.println(origX + " " + origY);
			}else{ //if the button has been clicked
				System.out.println(pieces[origX][origY].getColor() +" " + pieces[origX][origY].getType());
				destX = Integer.valueOf(e.getActionCommand().substring(0,2)); //select the X destination of the piece
				destY = Integer.valueOf(e.getActionCommand().substring(2,4)); //select the Y destination of the piece
				
				if(this.movePiece()){ //if the piece can move to the location
					if(pieces[destX][destY].getType() == 'k'){ //if the piece can move
						if(pieces[destX][destY].getColor().equals("white")){
							whiteKingPos[0] = destX; //set the white king's position
							whiteKingPos[1] = destY;
						}else{
							blackKingPos[0] = destX;
							blackKingPos[1] = destY;
						}
					}
					
					System.out.println("Moving piece to " + destX + destY + " from " + origX + origY);
					whiteTurn = !whiteTurn; //change whose turn it is
					if(this.checkKing()){ //call the king checking function
						System.out.println("Something is in check");
						if(this.checkBlackKing()){ //check if the black king is in check
							System.out.println("Black is in check");
							JOptionPane.showConfirmDialog(null, "Black is in check", "In check!", JOptionPane.DEFAULT_OPTION); //display that Black is in check
						}
						if(this.checkWhiteKing()){ //check if the white king is in check
							System.out.println("White is in check");
							JOptionPane.showConfirmDialog(null, "White is in check", "In check!", JOptionPane.DEFAULT_OPTION); //display that White is in check
						}
					}
					System.out.println("White King location " + whiteKingPos[0] + whiteKingPos[1]);
					System.out.println("Black King location " + blackKingPos[0] + blackKingPos[1]);
				} else {
					System.out.println("Not moving the piece");
				}
			}
		}else{ //AI movement handling
			System.out.println("Computer is taking a turn");
			if(!whiteTurn){ //if it is black's turn
				moveRandomPiece("black");
			}else{ //if it is white's turn
				moveRandomPiece("white");
			}
			whiteTurn = !whiteTurn; //change whose turn it is
		}
		
		if(destX == whiteKingPos[0] && destY == whiteKingPos[1] && pieces[destX][destY].getColor().equals("black")){ //check if the black king is taken
			whoWon = 'b'; //set black to win
			System.out.println("Black won!");
			if(JOptionPane.showConfirmDialog(null, "Black won! \nPlay Again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ //display "play again?" popup
				setupBoard();
			}
		}else if(destX == blackKingPos[0] && destY == blackKingPos[1] && pieces[destX][destY].getColor().equals("white")){ //check if black king is taken
			whoWon = 'w'; //set white to win
			System.out.println("White won!");
			if(JOptionPane.showConfirmDialog(null, "White won! \nPlay Again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ //display "play again?" popup
				setupBoard();
			}
		}
	}
	
}
