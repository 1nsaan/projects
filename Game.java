import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
class CatchMe  implements ActionListener{
	JFrame f;
    JButton button;
    JButton score;
    int clicks=0,successful_clicks=0;
    int x,y,height=25;
    Random r;

    CatchMe(){

	//creating components
		f=new JFrame("CatchMe");
        score= new JButton("Score: 0");
        button=new JButton("Start");


	//configuring components
	//UIManager.getLookAndFeelDefaults().put("defaultFont",new Font("Arial",Font.BOLD,14));
	button.setFocusPainted(false);
	button.setBounds(600,400,300,150);
	score.setBounds(600,400,300,150);
	score.setBackground(Color.green);
	score.setForeground(Color.white);
	score.setFont(new Font("Arial",Font.BOLD,30));
	r=new Random();

	//this key decides with how many clicks should the size of the button change

	// adding components to frame
        f.add(button);
        f.add(score);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
        f.addMouseListener(new MouseAdapter(){
	public void mouseClicked(MouseEvent e){
	   clicks+=1;
	   checkGameStatus();
	}}
);
    f.addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
			clicks+=1;
			if(x>=me.getX()&& x<(me.getX()+100) && y==me.getY()){
			  successful_clicks+=1;
			  
			}
			System.out.println(x+" "+me.getX()+" "+y+" "+me.getY());
			checkGameStatus();
		}
	});
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
	f.getContentPane().setBackground(Color.white);
	f.setLayout(null);
	//setting visibility of components
	score.setVisible(false);
	f.setSize(600,600);
	f.setVisible(true);
	
    }
    public void actionPerformed(ActionEvent e){

	//gets triggered when button is clicked
	clicks+=1;
        successful_clicks+=1;
         if(clicks==1) {
		button.setText("Catch me!");
		score.setVisible(false);
		}

	//randomizing the position of button with each click
        x=r.nextInt(600);
        y=r.nextInt(600);

	//the below conditional is for repositioning the button(temporary-thread based in next)
        button.setBounds(x,y,90,height);
		
        }

    void checkGameStatus(){
		  //the below conditional checks for the end-of-game condition
          if((clicks-successful_clicks)==1)
	 {
	    score.setVisible(true);
	    score.setText("Score: "+(successful_clicks-1));
 	    successful_clicks=0;
	    clicks=0;
	    button.setBounds(600,600,300,150);
        button.setText("START");
	}
	else
	score.setText("Score: "+successful_clicks);
	}
    public static void main(String args[]){
        new CatchMe();
    }

}
