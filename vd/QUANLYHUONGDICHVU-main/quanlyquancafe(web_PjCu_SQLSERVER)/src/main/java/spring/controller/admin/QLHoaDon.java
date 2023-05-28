package spring.controller.admin;


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

import spring.entity.HoaDonEntity;



@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLHoaDon {

	/*@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-hoadon" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDonEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		model.addAttribute("hoadon", list);
		return "admin/qlhoadon";
	}*/
	
	@Autowired
	SessionFactory factory;
	// CONTROLLER
	@RequestMapping(value = "admin-hoadon", method = RequestMethod.GET)
	public <E> String showHoaDon(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getHoaDons());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qlhoadon";
	}
	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="admin-hoadon", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchHoaDon(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchHoaDon(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(5);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/qlhoadon";
	}
	public List<HoaDonEntity> searchHoaDon(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDonEntity where id = :id OR hdnv.hoTen like :name OR convert(varchar,ngayThucHien) like :name";	
		Query query = session.createQuery(hql);
		Long id = null;
	
		try {
			id = Long.parseLong(name);
		}
		catch (Exception e) {
		}
		
		query.setParameter("id", id);			
		query.setParameter("name", "%" +  name + "%");
		List<HoaDonEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm
	
	public List<HoaDonEntity> getHoaDons(){
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDonEntity";
		Query query = session.createQuery(hql);
		List<HoaDonEntity> list = query.list();
		return list;
	}

}