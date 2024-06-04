package com.example.ooad_project.SubSystems;

public class PesticideSystem implements Runnable{

    public PesticideSystem() {}

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
