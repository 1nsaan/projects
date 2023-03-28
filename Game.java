
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
class Game extends MouseAdapter implements ActionListener{
    JFrame frame;
    JButton button;
    JButton score;
    int clicks=0,successful_clicks=0;
    int x,y,key,height=100;
    Random r;

    Game(){

	//creating components
        frame =new JFrame("Catch me");
        score= new JButton("Score: 0");
        button=new JButton("Start");


	//configuring components
	UIManager.getLookAndFeelDefaults().put("defaultFont",new Font("Arial",Font.BOLD,14));
	button.setFocusPainted(false);
	button.setBounds(800,800,300,150);
	score.setBounds(800,400,300,150);
	score.setBackground(Color.green);
	score.setForeground(Color.white);
	score.setFont(new Font("Arial",Font.BOLD,30));
	r=new Random();

	//this key decides with how many clicks should the size of the button change
	key=r.nextInt(5)+1;

	// adding components to frame
        frame.add(button);
        frame.add(score);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
        frame.addMouseListener(this);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setLayout(null);
	frame.getContentPane().setBackground(Color.black);

	//setting visibility of components
	score.setVisible(false);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){

	//gets triggered when button is clicked
	clicks+=1;
        successful_clicks+=1;
         if(clicks==1) {
		button.setText("Catch me!");
		score.setVisible(false);
		height=100;
		}

	//randomizing the position of button with each click
        x=r.nextInt(800);
        y=r.nextInt(800);

	//the below conditional is for resizing the button
	if(clicks%key==0 && clicks!=0)height=(int)(height>=15?height/3:100);


        button.setBounds(x,y,height,height);
        }


    public void mouseClicked(MouseEvent e){

	  clicks+=1;
	  //the below conditional checks for the end-of-game condition
          if((clicks-successful_clicks)==1)
	 {
	    score.setVisible(true);
	    score.setText("Score: "+(successful_clicks-1));
 	    successful_clicks=0;
	    clicks=0;
	    button.setBounds(800,800,300,150);
	    score.setBounds(800,400,300,150);
            button.setText("START");
	}
	else
	score.setText("Score: "+successful_clicks);
	  
	
    }
    public static void main(String args[]){
        new Game();
    }

}