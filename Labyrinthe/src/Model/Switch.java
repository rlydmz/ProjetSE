package Model;


public class Switch {
    private Vertex position;
    private Door[] doorList;
    private boolean isOn = false;

    public Vertex getPosition() {
        return position;
    }

    public void setPosition(Vertex position) {
        this.position = position;
    }

    public Door[] getDoorList() {
        return doorList;
    }

    public void setDoorList(Door[] doorList) {
        this.doorList = doorList;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn() {
        isOn = true;
    }

    public void setOff() {
        isOn = false;
    }
}
