package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;

public class View extends JFrame implements Observer{
    private Model model;
    private byte size;

    //Components of the view
    private Container c;
    private JButton buttons[][];
    private JPanel rightSide=new JPanel();
    private JButton reset=new JButton("Reset");
    public JCheckBox on=new JCheckBox();
    public JCheckBox off=new JCheckBox();
    //constructor
    public View(Model m){
        this.model=m;//the view should know the model
        m.addObserver(this);//register the observer
        this.size = this.model.getSize();
        this.buttons = new JButton[size][size];
        c=getContentPane();
        JPanel buttonField=new JPanel();
        buttonField.setLayout(new GridLayout(size,size));
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                buttons[row][col]=new JButton();
                buttons[row][col].setPreferredSize(new Dimension(50,50));
                buttons[row][col].setForeground(Color.GRAY);
                buttons[row][col].setBackground(Color.GRAY);
                buttonField.add(buttons[row][col]);
            }
        }//outer for
        this.setTitle("LIGHTS ON !!!");
        this.setSize(500,400);
        this.setVisible(true);
        this.setLocation(450,250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add the gaming field on the left hand side
        c.add(buttonField,BorderLayout.CENTER);

        //setting the right side
        //first the reset button
        reset.setPreferredSize(new Dimension(100,50));
        //here will be the statistics
        on.setForeground(Color.BLACK);
        on.setBackground(Color.YELLOW);
        off.setForeground(Color.BLACK);
        off.setBackground(Color.BLUE);
        //add everything to the right side panel
        rightSide.add(reset,BorderLayout.SOUTH);
        rightSide.add(on,BorderLayout.CENTER);
        rightSide.add(off,BorderLayout.NORTH);
        rightSide.setBackground(Color.WHITE);
        //add the right side panel to the main panel
        c.add(rightSide,BorderLayout.EAST);
        c.setBackground(Color.WHITE);

    }//constructor

    //getter methods
    public JButton getResetButton(){
        return this.reset;
    }//getResetButton

    public JButton[][] getButtons(){
        return this.buttons;
    }//getButtons

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        on.setText(""+model.getTrue());
        off.setText(""+model.getFalse());
        byte end=0;
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                if (model.matrix[row][col]==true){
                    buttons[row][col].setForeground(Color.YELLOW);
                    buttons[row][col].setBackground(Color.YELLOW);
                    end++;
                }
                else{
                    buttons[row][col].setForeground(Color.BLUE);
                    buttons[row][col].setBackground(Color.BLUE);
                }
            }
        }
        //what should be done when the player wins
        if(end==size*size){
            JOptionPane.showMessageDialog(null, "Congratulation!! You have won!!!");
        }
    }//update

    public void addActionListener(ActionListener a){
        //inputButton.addActionListener(a);
        this.reset.addActionListener(a);
        this.on.addActionListener(a);
        this.off.addActionListener(a);
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                buttons[row][col].addActionListener(a);
            }
        }
    }//addActionListener

}//View

