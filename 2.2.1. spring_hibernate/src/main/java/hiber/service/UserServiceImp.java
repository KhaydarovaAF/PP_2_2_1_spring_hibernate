package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;
   @Autowired
   private Logger serviceLog;
   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void printUsers(List<User> usersList) {
      usersList.forEach(x -> serviceLog.log(Level.INFO, x.toString()));

   }

   @Override
   public void printUserByCar(String model, int series) {
      serviceLog.log(Level.INFO, (Supplier<String>) userDao.getUserByCar(model, series));

   }


}
