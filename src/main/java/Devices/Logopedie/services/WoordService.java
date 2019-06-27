package Devices.Logopedie.services;

import java.sql.Blob;
import java.util.List;

import Devices.Logopedie.model.Child;
import Devices.Logopedie.model.GameType;
import Devices.Logopedie.model.Land;
import Devices.Logopedie.model.Level;
import Devices.Logopedie.model.Score;
import Devices.Logopedie.model.Woord;
import Devices.Logopedie.persistence.WoordDAO;

public class WoordService {
	private WoordDAO woorddao= new WoordDAO();
	
	public List<Woord> findAll() {
        return woorddao.findAll();
    }
	

}
