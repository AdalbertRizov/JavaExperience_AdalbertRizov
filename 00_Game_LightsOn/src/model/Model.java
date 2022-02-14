package model;

import java.util.Observable;

public class Model extends Observable {
    private  final byte size = 5;
    public  Boolean[][] matrix=new Boolean[size][size];

    //constructor
    public  Model(){
        for(byte row=0;row<=size-1;row++){
            for(byte col=0;col<=size-1;col++){
                matrix[row][col]=false;
            }
        }
    }//constructor

    //methods
    public  void resetMatrix(){
        for(byte r=0;r<=size-1;r++){
            for(byte c=0;c<=size-1;c++){
                matrix[r][c]=false;
            }
        }
    }//resetMatrix

    public byte getSize(){
        return this.size;
    }//getSize

    public byte getTrue(){
        byte res=0;
        for(byte r=0;r<=size-1;r++){
            for(byte c=0;c<=size-1;c++){
                if(matrix[r][c]==true){
                    res++;
                }
            }
        }
        return res;
    }//getTrue

    public byte getFalse(){
        byte res=0;
        for(byte r=0;r<=size-1;r++){
            for(byte c=0;c<=size-1;c++){
                if(matrix[r][c]==false){
                    res++;
                }
            }
        }
        return res;
    }//getFalse

    public  void changeNeighbours(int row,int col){
        matrix[row][col]= !matrix[row][col];
        if((row!=0)&&(row<size-1)){
            matrix[row+1][col]=(!matrix[row+1][col]);
            matrix[row-1][col]=(!matrix[row-1][col]);
        }else{
            if(row==0){
                matrix[row+1][col]=(!matrix[row+1][col]);
            }
            if(row==size-1){
                matrix[row-1][col]=(!matrix[row-1][col]);
            }
        }
        if((col!=0)&&(col<size-1)){
            matrix[row][col+1]=(!matrix[row][col+1]);
            matrix[row][col-1]=(!matrix[row][col-1]);
        }else{
            if(col==0){
                matrix[row][col+1]=(!matrix[row][col+1]);
            }
            if(col==size-1){
                matrix[row][col-1]=(!matrix[row][col-1]);
            }
        }
        super.setChanged();
        super.notifyObservers();
    }//changeNeighbours

}//class Model

