package spring.service.web;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import spring.entity.UserTBEntity;

@Transactional
public class UserService {
/*	public UserTBEntity getUser(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where usernv.maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}*/
}