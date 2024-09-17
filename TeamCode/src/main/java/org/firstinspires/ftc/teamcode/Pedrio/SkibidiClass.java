package org.firstinspires.ftc.teamcode.Pedrio;

public class SkibidiClass {

    final private int alphaness = 100 ;

    public double checkForSkibidiness(double aura_level, double sigmaness){
        // add aura and sigmaness and square it and then divide it by our alphaness
        double skibidiness = Math.sqrt(aura_level + sigmaness) / alphaness;


        return skibidiness;
    }


    public void main(){
        double i = checkForSkibidiness(10.6,5000);
    }
}
