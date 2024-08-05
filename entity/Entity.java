package entity;

abstract class Entity {
    private int x,y;
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    private int speed;
    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    

    
}
