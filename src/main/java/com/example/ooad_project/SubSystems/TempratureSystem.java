package com.example.ooad_project.SubSystems;

public class TempratureSystem implements Runnable{


    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("Temperature System is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}
