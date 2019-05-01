package Devices.Logopedie.services;

import java.util.List;

import Devices.Logopedie.model.Child;
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

	public boolean createChild(Child child) {
		return childdao.createChild(child);
	}
	

}
