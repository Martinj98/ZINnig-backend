package Devices.Logopedie.model;

public class Score {
    private int Levelid;
    private int Childid;
    private int Landid;
    private String score_goed;
    private String score_fout;
    private int progress;

    public Score(int Levelid, int Childid, int landid, String score_goed, String score_fout, int progress) {
        this.setLevelid(Levelid);
        this.setChildid(Childid);
        this.setLandid(landid);
        this.setScore_goed(score_goed);
        this.setScore_fout(score_fout);
        this.setProgress(progress);
    }

    public int getLandid() {
        return Landid;
    }

    public void setLandid(int landid) {
        this.Landid = landid;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getScore_fout() {
        return score_fout;
    }

    public void setScore_fout(String score_fout) {
        this.score_fout = score_fout;
    }

    public String getScore_goed() {
        return score_goed;
    }

    public void setScore_goed(String score_goed) {
        this.score_goed = score_goed;
    }

    public int getChildid() {
        return Childid;
    }

    public void setChildid(int childid) {
        this.Childid = childid;
    }

    public int getLevelid() {
        return Levelid;
    }

    public void setLevelid(int levelid) {
        this.Levelid = levelid;
    }
}