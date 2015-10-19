package au.com.ibm.bluecoin.service;

import javax.ejb.Local;

import au.com.ibm.bluecoin.dao.IUserDao;
import au.com.ibm.bluecoin.dao.relational.repository.UserRepository;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.scaffold.IService;

@Local
public interface IUserSvc extends IService<AppUser, String, UserRepository, IUserDao> {

}
