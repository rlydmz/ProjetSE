package Controller;

import Model.BadGuy;
import Model.Labyrinthe;
import Model.Vertex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Spawner {

    private Labyrinthe laby;

    public Spawner(Labyrinthe laby){
        this.laby = laby;
    }

    public void HandleSpawning(){
        //NiceGuy
        Vertex niceGuyPos = NiceGuyHandler();
        //Exit
        Vertex exitPos = ExitHandler(niceGuyPos);
        //BadGuys
        BadGuysHandler(niceGuyPos, exitPos);
        //Candies
    }

    public Vertex NiceGuyHandler(){
        laby.getPackman().startPosition();
        Vertex niceGuyPos = laby.getPackman().getPosition();
        System.out.println(niceGuyPos);
        if(niceGuyPos != null)
            return niceGuyPos;
        return null;
    }

    public Vertex ExitHandler(Vertex niceGuyPos){
        laby.GetExit().startPosition();
        Vertex exitPos = laby.GetExit().getPosition();
        while(exitPos.equals(niceGuyPos)){
            laby.GetExit().startPosition();
        }
        System.out.println(exitPos);
        if(exitPos != null)
            return exitPos;
        return null;
    }

    public void BadGuysHandler(Vertex niceGuyPos, Vertex exitPos){
        int nbBadGuy = ThreadLocalRandom.current().nextInt(0, 6);
        for (int i = 0; i < nbBadGuy; i++) {
            BadGuy badGuy = new BadGuy();
            badGuy.startPosition();
            Vertex badGuyPos = badGuy.getPosition();
            laby.addBadGuyToArmy(badGuy);
        }
    }

}
