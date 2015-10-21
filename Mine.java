import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.*;
import java.util.*;

public class Mine implements ActionListener{
	Random rand = new Random();
	int grid[][]=new int[8][8];
	JButton bgrid[][]=new JButton[8][8];
	final int BOMB=9;
	final int BLANK=0;
	int score = 0;
	JFrame win;
	ImageIcon numbers[]={
		new ImageIcon("0.png"),
		new ImageIcon("1.png"),
		new ImageIcon("2.png"),
		new ImageIcon("3.png"),
		new ImageIcon("4.png"),
		new ImageIcon("5.png"),
		new ImageIcon("b.png"),
		new ImageIcon("b.png"),
		new ImageIcon("b.png"),
		new ImageIcon("b.png")
	};


	public void buildGUI(){
		win = new JFrame("Mine");
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(8,8));
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				bgrid[i][j].setActionCommand(""+i+","+j);
				bgrid[i][j].addActionListener(this);
				body.add(bgrid[i][j]);

			}
		}
		win.add(body);
		win.pack();
		win.setVisible(true);
		win.setResizable(false);
		win.setSize(300,300);
	}


	public void actionPerformed(ActionEvent e){
		int r1=0;
		int c1=0;
		
		String line = e.getActionCommand();
		System.out.println("Line: "+line);

		int position = line.indexOf(",");
		String part1 = line.substring(0,position);
		String part2 = line.substring(position+1);
		int r = Integer.parseInt(part1);
		int c = Integer.parseInt(part2); 
		//bgrid[r][c].setText(""+grid[r][c]);
		bgrid[r][c].setIcon(numbers[grid[r][c]]);
		if(grid[r][c]==BOMB){
			JOptionPane.showMessageDialog(null, "Sorry you lose!");
			System.exit(0);
		}else{
			score++;
			System.out.println(score);
			if(score==55){

				JOptionPane.showMessageDialog(null, "You are a winner!");
				System.exit(0);

			} 
		}
		if(grid[r][c]==BLANK){
			check(r,c);
		}
		
	}

	public void check(int r, int c){
		int originalR = r;
		int originalC = c;
		int rr;
		int cc;
		if(r-1 > -1){
			clear(r-1, c);
			rr=r-1;
			while(grid[rr][c]==BLANK){
				clear(rr,c);
				rr=rr-1;
				if(rr-1 < -1) return;
			}

			rr = originalR;
		} 

		if(r+1 < 8){
			clear(r+1, c);
			rr=r+1;
			while(grid[rr][c]==BLANK){
				clear(rr,c);
				rr=rr+1;
				if(rr+1 > 8) return;
			}
			rr = originalR;
		}
		if(c-1 > -1) {
			clear(r, c-1);
			cc=c-1;
			while(grid[r][cc]==BLANK){
				clear(r,cc);
				cc=cc-1;
				if(cc-1 < -1) return;
			}
			cc = originalC;
		}
		if(c+1 < 8){
			clear (r, c+1);
			cc=c+1;
			while(grid[r][cc]==BLANK){
				clear(r,cc);
				cc=cc+1;
				if(cc+1 > 8) return;
			}
			cc = originalC;
		}
 		if(r-1 > -1 && c-1 > -1){
 			clear (r-1, c-1);
 			rr=r-1;
 			cc=c-1;
			while(grid[rr][cc]==BLANK){
				clear(rr,cc);
				rr=rr-1;
				cc=cc-1;
				if(cc-1 < -1||rr-1 < -1) return;
			}
			rr = originalR;
			cc = originalC;
 		}
 		if(r+1 < 8 && c+1 < 8){
 			clear (r+1, c+1);
 			rr=r+1;
 			cc=c+1;
			while(grid[rr][cc]==BLANK){
				clear(rr,cc);
				rr=rr+1;
				cc=cc+1;
				if(cc+1 > 8||rr+1 > 8) return;
			}
			rr = originalR;
			cc = originalC;
 		}
 		if(r-1 > -1 && c+1 < 8){
 			clear (r-1, c+1);
 			rr=r-1;
 			cc=c+1;
			while(grid[rr][cc]==BLANK){
				clear(rr,cc);
				rr=rr-1;
				cc=cc+1;
				if(cc+1 > 8||rr-1 < -1) return;
			}
			rr = originalR;
			cc = originalC;
 		}
 		if(r+1 < 8 && c-1 > -1){
 			clear (r+1, c-1);
 			rr=r+1;
 			cc=c-1;
			while(grid[rr][cc]==BLANK){
				clear(rr,cc);
				rr=rr+1;
				cc=cc-1;
				if(cc-1 < -1||rr+1 > 8) return;
			}
			rr = originalR;
			cc = originalC;
 		}
	}

	public void clear(int r, int c){
		if(grid[r][c]!=BLANK) return;
		if(getIcon(grid[r][c])==numbers[0]) {
			bgrid[r][c].setIcon(numbers[0]);
		}
		if(grid[r][c]==BLANK){
			bgrid[r][c].setIcon(numbers[0]);
		}
	}

	public ImageIcon getIcon(int poop){
		System.out.println("getgetgetget");
		//i
		//hate
		//seth
		
		return numbers[poop];


	}

	public void init(){
		for(int i = 0;i<8;i++){
			for (int j = 0; j<8; j++) {
				grid[i][j]= BLANK;
				bgrid[i][j]= new JButton();
			}
		}
		//first 10
		int counter = 0;
		for(int i = 0; i<8; i++){
			for (int j = 0; j<8; j++) {
				if(counter > 9) break;
				grid[i][j]=BOMB;
				counter++;
			}
		}
		shuffle();
		check4Bombs();
		printGrid();
		buildGUI();

	}

	public static void main(String args[]){
		new Mine();
	}

	public void printGrid(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.print("|"+grid[i][j]+"|");
			}
			System.out.println("");
		}
	}

	public Mine(){
		init();
	}

	

	void swap(int x1, int y1, int x2, int y2){
		int temp = grid[x1][x2];
		grid[x1][x2]=grid[x2][y2];
		grid[x2][y2]=temp;
	}

	void shuffle(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				int x = rand.nextInt(8);
				int y = rand.nextInt(8);
				swap(i,j,x,y);
			}
		}
	}

	void check4Bombs(){
		int bombcounter=0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(grid[i][j]!= BOMB){
					if(i-1>=0&&i+1<=7&&j-1>=0&&j+1<=7){
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==0&&j==0){
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
					}
					else if(i==0&&j==7){
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==7&&j==7){
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
					}
					else if(i==7&&j==0){
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==0){
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
					}
					else if(j==0){
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//bottom right
						if(grid[i+1][j+1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
					}
					else if(j==7){
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//bottom left
						if(grid[i+1][j-1]==BOMB){
						bombcounter++;
						}
						//bottom
						if(grid[i+1][j]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					else if(i==7){
						//top left
						if(grid[i-1][j-1]==BOMB){
						bombcounter++;
						}
						//left
						if(grid[i][j-1]==BOMB){
						bombcounter++;
						}
						//right
						if(grid[i][j+1]==BOMB){
						bombcounter++;
						}
						//top right
						if(grid[i-1][j+1]==BOMB){
						bombcounter++;
						}
						//top
						if(grid[i-1][j]==BOMB){
						bombcounter++;
						}
					}
					grid[i][j]=bombcounter;
					
					//System.out.println(bombcounter);
					bombcounter=0;
				}
			}
		}
	}
	

	
}



