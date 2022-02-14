package main;

import model.*;
import view.*;
import controller.*;

public class MainClass  {

    public static void main(String[] args){
        Model myModel=new Model();
        View v=new View(myModel);
        Controller controller=new Controller(myModel,v);

    }

}//MainClass

