package quanlyvattu.service;

import quanlyvattu.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
	UserModel findByEmail(String email);

}
