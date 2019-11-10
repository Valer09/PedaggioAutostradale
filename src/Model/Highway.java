package Model;

import Controller.VarGetter;

public class Highway {
    protected double TU;

    public Highway(String A){

        TU= VarGetter.getTU(A);

    }

    public double getTU(){
        return TU;
    }
}
