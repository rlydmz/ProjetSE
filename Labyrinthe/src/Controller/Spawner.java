package Controller;

import Model.BadGuy;
import Model.Candy;
import Model.Labyrinthe;
import Model.Vertex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Spawner {

    private Labyrinthe laby;
    private static int MIN_BAD_GUY = 2;
    private static int MAX_BAD_GUY = 4;
    private static int MIN_CANDY = 5;
    private static int MAX_CANDY = 8;

    /**
     * Constructeur de la classe Spawner
     * @param laby L'instance d'un objet Labyrinthe
     */
    public Spawner(Labyrinthe laby){
        this.laby = laby;
    }

    /**
     * Gere la position d'apparition sur un jeu vierge du joueur, des ennemis, de la sortie, et des bonbons
     */
    public void HandleSpawning(){
        //NiceGuy
        Vertex niceGuyPos = NiceGuyHandler();
        //Exit
        Vertex exitPos = ExitHandler(niceGuyPos);
        //BadGuys
        BadGuysHandler(niceGuyPos, exitPos);
        //Candies
        CandyHandler(niceGuyPos, exitPos);
    }

    /**
     * Retourne la position sous forme d'un objet Vertex de la position aleatoirement generee du joueur
     * @return Un objet de la classe Vertex
     */
    public Vertex NiceGuyHandler(){
        laby.getPackman().startPosition();
        Vertex niceGuyPos = laby.getPackman().getPosition();
        System.out.println(niceGuyPos);
        if(niceGuyPos != null)
            return niceGuyPos;
        return null;
    }

    /**
     * Retourne la position sous forme d'un objet Vertex de la position aleatoirement generee du joueur en prenant en compte la position de la sortie pour que les deux ne se superposent pas au depart du jeu
     * @param niceGuyPos La position sous forme d'un objet Vertex du joueur
     * @return Un objet de la classe Vertex
     */
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

    /**
     * Fixe la position de tous les ennemis de manière aleatoire, en évitant de les faire apparaitre sur le joueur
     * @param niceGuyPos La position sous forme d'un objet Vertex du joueur
     * @param exitPos La position sous forme d'un objet Vertex de la sortie
     */
    public void BadGuysHandler(Vertex niceGuyPos, Vertex exitPos){
        int nbBadGuy = ThreadLocalRandom.current().nextInt(MIN_BAD_GUY, MAX_BAD_GUY);
        for (int i = 0; i < nbBadGuy; i++) {
            BadGuy badGuy = new BadGuy();
            badGuy.startPosition();
            Vertex badGuyPos = badGuy.getPosition();
            while(badGuyPos.equals(niceGuyPos) && badGuyPos.equals(exitPos)){
                badGuy.startPosition();
            }
            laby.addBadGuyToArmy(badGuy);
        }
    }

    /**
     * Fixe la position de tous les bonbons de manière aleatoire, en évitant de les faire apparaitre sur le joueur
     * @param niceGuyPos La position sous forme d'un objet Vertex du joueur
     * @param exitPos La position sous forme d'un objet Vertex de la sortie
     */
    public void CandyHandler(Vertex niceGuyPos, Vertex exitPos){
        int nbCandy = ThreadLocalRandom.current().nextInt(MIN_CANDY, MAX_CANDY);
        for (int i = 0; i < nbCandy; i++) {
            Candy candy = new Candy();
            candy.startPosition();
            Vertex candyPos = candy.getPosition();
            laby.addCandy(candy);
        }
    }

}
