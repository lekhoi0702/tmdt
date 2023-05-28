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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.ChiTietHDEntity;
import spring.entity.HoaDonEntity;

@Transactional
@Controller
public class HoaDonController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "hoa-don" , method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model,HttpServletRequest request) {
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user.getID();
		
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getHoaDon(id));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		List<HoaDonEntity> hoaDon = this.getHoaDon(id);
		model.addAttribute("hoaDon", hoaDon);
	
		return "web/bill";
	}
	
	@RequestMapping(value = "hoa-don", params="btnsearch")
	public <E> String showMen2u(ModelMap model,HttpServletRequest request) {
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user.getID();
		
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getSearchHoaDon(id,request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		List<HoaDonEntity> hoaDon = this.getHoaDon(id);
		model.addAttribute("hoaDon", hoaDon);
	
		return "web/bill";
	}
	
	@RequestMapping(value = "hoa-don/{id}.htm", params = "linkView")
	public <E> String xemChiTietHD(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) {
		List<ChiTietHDEntity>  cthds= getChiTietHD(id);
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) cthds);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		/*List<ChiTietHDEntity> chiTietHD = this.getChiTietHD(id);
		model.addAttribute("chiTietHD", chiTietHD);*/
		int tong=0;
		for (ChiTietHDEntity cthd:cthds) {
			tong+=cthd.getTongTien();
		}
		model.addAttribute("tongTien",tong);
		model.addAttribute("idhd",id);
		return "web/bill2";
	}

	
	
	public List<HoaDonEntity> getHoaDon(Long id){
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDonEntity where hdnv.maNV = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		List<HoaDonEntity> list = query.list();
		return list;
	}
	
	public List<HoaDonEntity> getSearchHoaDon(Long id, String name){
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDonEntity where hdnv.maNV = :id AND (convert(varchar,id) like :name OR ngayThucHien like :name OR hdnv.hoTen like :name)";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("name","%" + name + "%");
		List<HoaDonEntity> list = query.list();
		return list;
	}
	
	public List<ChiTietHDEntity> getChiTietHD(Long id){
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHDEntity where hoaDon.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		List<ChiTietHDEntity> list = query.list();
		return list;
	}
}