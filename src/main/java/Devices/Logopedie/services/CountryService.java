package Devices.Logopedie.services;

import java.util.List;

import Devices.Logopedie.model.Country;
import Devices.Logopedie.persistence.CountryDAO;

public class CountryService {
	private CountryDAO countrydao= new CountryDAO();
	
	
	public List<Country> getAll() {
		return countrydao.findAll();
	}
	
	

}
