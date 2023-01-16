package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void add(User user) {

      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   @Transactional
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String carModel, int carSeries) {
      TypedQuery<Car> carTypedQuery = sessionFactory.getCurrentSession()
              .createQuery("from Car where model =: model and series =: series ");
      carTypedQuery.setParameter("model", carModel);
      carTypedQuery.setParameter("series", carSeries);
      TypedQuery<User> result = sessionFactory.getCurrentSession()
              .createQuery("from User where car =: car");
      result.setParameter("car", carTypedQuery.getSingleResult());
      return result.getSingleResult();
   }


}
