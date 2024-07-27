package Game;


import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SnakeAndLadder extends JFrame {
	int player1val = 0;
	int player2val = 0;
	static int[] stopval = new int[] {99,95,89,66,54,43,40,27};
	static int[] slowval = new int[] {41,77,53,45,31,18,3,5};
	static int[] ltopval = new int[] {92,81,69,63,49,46,25};
	static int[] llowval = new int[] {74,62,50,42,33,13,4};
	static List<Integer> SnakeTop = new ArrayList<>(Arrays.asList(toIntegerArray(stopval)));
	static List<Integer> SnakeLow = new ArrayList<>(Arrays.asList(toIntegerArray(slowval)));
	static List<Integer> LadderTop = new ArrayList<>(Arrays.asList(toIntegerArray(ltopval)));
	static List<Integer> LadderLow = new ArrayList<>(Arrays.asList(toIntegerArray(llowval)));
	static int player =1;
	static int btn2x =37;
	static int btn2y=577;
	static int btn1x =0;
	static int btn1y=540;
	static boolean btn1inc = true;
	static boolean btn2inc = true;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    int horizontal =0;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SnakeAndLadder frame = new SnakeAndLadder();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SnakeAndLadder() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 620, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // Set border to zero
        contentPane.setLayout(null);
        // Load an image
        ImageIcon backgroundImageIcon = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\MyProject\\src\\GUI\\snake.jpg");   // Replace with the path to your image
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
    //    contentPane.setLayout(null); // Set layout to null for absolute positioning
        backgroundLabel.setBounds(0, 0, backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
        contentPane.add(backgroundLabel);


        // You can add other components on top of the backgroundLabel here

        setResizable(false);
        setContentPane(contentPane);
        JLabel displaydice = new JLabel("6");
        displaydice.setBounds(315,615,40,40);
        contentPane.add(displaydice);
        JLabel player1 = new JLabel("Player 1");
        player1.setBounds(0,615,60,40);
        contentPane.add(player1);
        JLabel player2 = new JLabel("Player 2");
        player2.setBounds(450,615,60,40);
        contentPane.add(player2);
        JLabel player1score = new JLabel("Score 1");
        player1score.setBounds(60,615,60,40);
        contentPane.add(player1score);
        JLabel player2score = new JLabel("Score 2");
        player2score.setBounds(510,615,60,40);
        contentPane.add(player2score);
        
        JButton player1btn = new JButton();
        player1btn.setBackground(Color.RED);
        player1btn.setBounds(btn1x,btn1y,20,20);
        player1btn.setOpaque(true);
        contentPane.add(player1btn);
        player1btn.setVisible(true);
        
        JButton player2btn = new JButton();
        player2btn.setBackground(Color.GREEN);
        player2btn.setOpaque(true);
        player2btn.setBounds(btn2x,btn2y,20,20);
        contentPane.add(player2btn);
        player2btn.setVisible(true); 
        
        
        
        JButton dice = new JButton("Roll");
        dice.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			Random rm = new Random();
        			dice.setText(String.valueOf(player));
        			int dice_val = rm.nextInt(6)+1;
        			if(player1val==100||player2val==100) {
        				dice.setEnabled(false);
        			}
        			displaydice.setText(String.valueOf(dice_val));
        			if(player1val == 0 && dice_val == 6 && player ==1) {
        				player1val += 1;
        				btn1cordinate(0,540,0, btn1inc,player1btn);
        				player1score.setText(String.valueOf(player1val));
        				
        			}
        			else if(player2val == 0 && dice_val == 6 && player ==2) {
        				player2val += 1;
        				btn2cordinate(37,577,0, btn2inc,player2btn);
        				player2score.setText(String.valueOf(player2val));
        			}
        			else if(player == 1 && player1val>0) {
        				btn1cordinate(0,540,incrementval(player1val, dice_val)-1, true,player1btn);
        				player1val = incrementval(player1val, dice_val);
        				player1score.setText(String.valueOf(player1val));
        			}
        			
        			else if(player ==2&&player2val>0) {
        				btn2cordinate(37,577,incrementval(player2val, dice_val)-1, true,player2btn);
        				player2val = incrementval(player2val, dice_val);
        				player2score.setText(String.valueOf(player2val));
        			}
        			player =player(player);
        	}
        });
        dice.setBounds(250,615, 50, 40);
        contentPane.add(dice);
        
        
//        
        
    }
    public static int player(int a) {
		if(a==1) {
			return 2;
		}
		else {
			return 1;
		}
	}
    public static void btn2cordinate(int x,int y,int n,boolean inc,JButton btn2) {
		for(int i=0;i<n;i++) {
		    if(inc && x!=577){
		        x=x+60;
		    }
		    else if(!inc && x!=37){
		        x = x-60;
		    }
		    else{
		        y=y-60;
		        inc = !inc;
		    }
		    System.out.println(x+" "+y);
		}
		btn2inc = inc;
		btn2x =x;
		btn2y=y;
		btn2.setBounds(btn2x,btn2y,20,20);
		btn2.setOpaque(true);
		System.out.println("set completed for btn 2");
	}
    public static void btn1cordinate(int x,int y,int n,boolean inc,JButton btn1) {
		for(int i=0;i<n;i++) {
		    if(inc && x!=540){
		        x=x+60;
		    }
		    else if(!inc && x!=0){
		        x = x-60;
		    }
		    else{
		        y=y-60;
		        inc = !inc;
		    }
		    System.out.println(x+" "+y);
		}
		btn1inc = inc;
		btn1x =x;
		btn1y=y;
		btn1.setBounds(btn1x,btn1y,20,20);
		btn1.setOpaque(true);
		System.out.println("set completed for btn 2");
	}
	public static int incrementval(int previous_val, int dice_val) {
		if(previous_val+dice_val>100) {
			return previous_val;
		}
		else {
				if(SnakeTop.contains(previous_val+dice_val)) {
					int index = SnakeTop.indexOf(previous_val+dice_val);
					System.out.println("Apko saanp ne Kata");
					return SnakeLow.get(index);
				}
			
				else if(LadderLow.contains(previous_val+dice_val)) {
					int index = LadderLow.indexOf(previous_val+dice_val);
					System.out.println("Apke Dost ne uthaya");
					return LadderTop.get(index);
				}
			return previous_val+dice_val;
		}
	}
	private static Integer[] toIntegerArray(int[] intArray) {
        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = intArray[i];
        }
        return result;
    }
	
}