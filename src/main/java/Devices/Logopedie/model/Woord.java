package Devices.Logopedie.model;

public class Woord {
    private int id;
    private String woord;
    private String type;
    private String moeilijkheidsgraad;

    public Woord(int id, String woord, String type, String moeilijkheidsgraad) {
        this.setId(id);
        this.setWoord(woord);
        this.setType(type);
        this.setMoeilijkheidsgraad(moeilijkheidsgraad);
    }

    public String getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    public void setMoeilijkheidsgraad(String moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}