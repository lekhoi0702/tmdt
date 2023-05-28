package quanlyvattu.service.impl;

import quanlyvattu.dao.impl.AbstractDAO;
import quanlyvattu.service.ICheckService;

public class CheckService implements ICheckService{

	@Override
	public boolean ckUserPassword(String user, String pass) {
		// TODO Auto-generated method stub
		if (new AbstractDAO().getConnectionPM(user, pass)==null)
		return false;
		else
			return true;
	}
	
}
