package Model;


public class Switch extends Entity{
    private Vertex position;
    private Door[] doorList;
    private boolean isOn = false;

    /**
     * Contructeur de la classe Switch
     */
    public Switch(){
        doorList =  new Door[5];
        isOn = false;
    }

    /**
     * Retourne la liste des Door associees a ce Switch
     * @return Un tableau d'objet de type Door
     */
    public Door[] getDoorList() {
        return doorList;
    }

    /**
     * Fixe la liste des Door associees a ce Switch
     * @param doorList Un tableau d'objet de type Door
     */
    public void setDoorList(Door[] doorList) {
        this.doorList = doorList;
    }

    /**
     * Indique l'etat d'activitite d'un Switch
     * @return true si le Switch est acitive, false sinon
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Fixe la valeur d'activite du Switch a la valeur true
     */
    public void setOn() {
        isOn = true;
    }

    /**
     * Fixe la valeur d'activite du Switch a la valeur false
     */
    public void setOff() {
        isOn = false;
    }
}
