package spring.service.web;

import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import spring.entity.BanEntity;
@Transactional
public class BanService {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext application;
	
	public BanEntity getBan(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BanEntity list = (BanEntity) query.list().get(0);
		return list;
	}
	public List<BanEntity> getBans () {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		return list;
	}
	public void UpdateListBan() {
		application.setAttribute("listBan", getBans());
	}
}
