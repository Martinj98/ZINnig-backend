package Devices.Logopedie.services;

import java.util.List;

import Devices.Logopedie.model.Logopedist;
import Devices.Logopedie.persistence.LogopedistDAO;

public class LogopedistService {
	private LogopedistDAO logopedistdao= new LogopedistDAO();
	
	
	public List<Logopedist> getAll() {
		return logopedistdao.findAll();
	}

	public String login(String username, String password) {
		return logopedistdao.login(username, password);
	}

	public int getId(String username) {
		return logopedistdao.findId(username);
	}
	
	public List<Logopedist> getLogopedist(int id) {
		return logopedistdao.findLogopedist(id);
	}
	

}
