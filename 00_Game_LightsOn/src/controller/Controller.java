package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;
import view.*;

public class Controller implements ActionListener{
    Model model;
    View view;

    //constructor
    public Controller(Model m, View v){
        this.model=m;
        this.view=v;
        view.addActionListener(this);
    }//constructor

    public void actionPerformed(ActionEvent e) {
        int r=0,c=0;
        if (view.getResetButton().hasFocus()==true){
            model.resetMatrix();
            view.update(model, view.getResetButton());
        }else{
            for(int row=0;row<model.getSize();row++){
                for(int col=0;col<model.getSize();col++){

                    if(view.getButtons()[row][col].hasFocus()==true){
                        r=row;
                        c=col;
                    }

                }
            }
            model.changeNeighbours(r,c);
        }//end of the if-else
    }//actionPerformed

}//Controller


