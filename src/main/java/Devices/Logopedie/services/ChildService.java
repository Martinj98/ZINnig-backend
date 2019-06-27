package Devices.Logopedie.services;

import java.sql.Blob;
import java.util.List;

import Devices.Logopedie.model.Child;
import Devices.Logopedie.model.GameType;
import Devices.Logopedie.model.Land;
import Devices.Logopedie.model.Level;
import Devices.Logopedie.model.Score;
import Devices.Logopedie.model.Woord;
import Devices.Logopedie.persistence.ChildDAO;

public class ChildService {
	private ChildDAO childdao= new ChildDAO();
	
	
	public List<Child> getAll() {
		return childdao.findAll();
	}

	public List<Child> getOwn(int id) {
		return childdao.findOwn(id);
	}
	
	public List<Child> selectSpecificChild(int id) {
		return childdao.selectSpecificChild(id);
	}

	public String login(String username, String password) {
		return childdao.login(username, password);
	}

	public int getId(String username) {
		return childdao.findId(username);
	}

	public Boolean uploadScore(Score score) {
		return childdao.uploadScore(score);
	}

	public boolean createChild(Child child) {
		return childdao.createChild(child);
	}

	public boolean updatePhoto(int id, String photo) {
		return childdao.updatePhoto(id, photo);
	}
	
	public String getPhoto(int id) {
		return childdao.findPhoto(id);
	}

	public List<Score> selectVoortgang(int id) {
		return childdao.selectVoortgang(id);
	}

	public List<GameType> selectGametype(int id) {
		return childdao.selectGametype(id);
	}

	public List<Land> selectLevelLand(int id) {
		return childdao.selectLevelLand(id);
	}

	public List<Integer> selectLevelWords(int id) {
		return childdao.selectLevelWords(id);
	}

	public List<Woord> selectGoodWords(List<Integer> idlist) {
		return childdao.selectGoodWords(idlist);
	}

	public boolean updateMunten(int count, int id) {
		return childdao.updateMunten(count, id);
	}

	public List<Level> selectLevel(int id) {
		return childdao.selectLevel(id);
	}
	

}
