package com.example.ooad_project;

public class Bee extends Parasite {

    public Bee(){
//        Bee should not have any damage
        super(0);
    }

//    Bee only affects flowers and that too in a positive way
//    Should speed up flower growth or something along those lines
    @Override
    public void affectPlant(Plant plant) {
    }

}
