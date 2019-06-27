package Devices.Logopedie.model;

public class Level {
    private int id;
    private int Game_typeid;
    private int niveau;

    public Level(int id, int Game_typeid, int niveau) {
        this.setId(id);
        this.setGame_typeid(Game_typeid);
        this.setNiveau(niveau);
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getGame_typeid() {
        return Game_typeid;
    }

    public void setGame_typeid(int game_typeid) {
        this.Game_typeid = game_typeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}