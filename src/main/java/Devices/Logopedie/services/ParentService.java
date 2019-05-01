package Devices.Logopedie.services;

import java.util.List;

import Devices.Logopedie.model.Parent;
import Devices.Logopedie.persistence.ParentDAO;

public class ParentService {
    private ParentDAO parentdao = new ParentDAO();

    public List<Parent> getAll(){
        return parentdao.findAll();
    }

    public List<Parent> getChildParents(int Childid){
        return parentdao.findChildParents(Childid);
    }

    public boolean createParent(Parent parent){
        return parentdao.createParent(parent);
    }
}