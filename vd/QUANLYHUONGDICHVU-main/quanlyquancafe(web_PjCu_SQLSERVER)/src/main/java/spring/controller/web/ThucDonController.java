package spring.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.BanEntity;
import spring.entity.ThucDonEntity;

@Transactional

@Controller
public class ThucDonController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "thuc-don", method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model, HttpServletRequest request) {
		
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		
		List<ThucDonEntity> list = this.getThucDons();
		model.addAttribute("thucDons", list);
		return "web/menu";
	}

	@RequestMapping(value = "thuc-don", params="btnsearch")
	public <E> String showMenu2(ModelMap model, HttpServletRequest request) {
		
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getSearchThucDons(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		
		List<ThucDonEntity> list = this.getThucDons();
		model.addAttribute("thucDons", list);
		return "web/menu";
	}
	
	public List<ThucDonEntity> getThucDons(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity";
		Query query = session.createQuery(hql);
		List<ThucDonEntity> list = query.list();
		return list;
	}
	public List<ThucDonEntity> getSearchThucDons(String name){
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity where id like :name OR loaiThucUong.tenLoai like :name OR ten like :name OR convert(varchar,gia) like :name";
		Query query = session.createQuery(hql);
		query.setParameter("name","%" + name + "%");
		List<ThucDonEntity> list = query.list();
		return list;
	}
	
}