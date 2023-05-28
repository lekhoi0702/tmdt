package spring.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.BanEntity;
import spring.entity.ChiPhiEntity;
import spring.entity.HoaDonEntity;
import spring.entity.LoaiBanEntity;
import spring.entity.LoaiThucUongEntity;
import spring.entity.NhanVienEntity;
import spring.entity.ThucDonEntity;
import spring.entity.UserTBEntity;
@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLThucDon {

	@Autowired
	SessionFactory factory;
	// CONTROLLER
	@RequestMapping(value = "admin-qlthucdon", method = RequestMethod.GET)
	public <E> String showThucDon(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qlthucdon";
	}
	
	/*hiển thị form*/
	@RequestMapping(value="formThucDon", method = RequestMethod.GET) 
    public String index_formThucDon(ModelMap model) {
		model.addAttribute("td",new ThucDonEntity());
		
		model.addAttribute("loaithucuongs",getLoaiThucUongs());	
		
          return "admin/form/inputThucDon";
    }
	
	 public List<LoaiThucUongEntity> getLoaiThucUongs(){
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiThucUongEntity";
			Query query = session.createQuery(hql);
			List<LoaiThucUongEntity> list = query.list();
			
			return list;
		}
	 
	 public LoaiThucUongEntity getLoaiThucUong (String id) {
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiThucUongEntity where id =:id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			LoaiThucUongEntity list = (LoaiThucUongEntity) query.list().get(0);
			return list;
		}
	 
		
	 public List<String> checkInfo(ThucDonEntity td) {
			List<String> listError = new ArrayList<>();
			
			
			if(td.getGia()==null) {
				listError.add("chưa nhập giá thành");
			}
			if( td.getId().trim().equals("")){
				listError.add("chưa nhập ID");
			}
			
			return listError;
			
		}
	 
	 public boolean CheckIDThucDon(String IDthucDon) {
			List<ThucDonEntity> list = getThucDons();
			int n = list.size();
			String user;
			for (int i = 0; i < n; i++) {
				user = list.get(i).getId();
				if (user.equals(IDthucDon)) {
					return true;
				}
			}
			return false;
		}
	//thêm 
	@RequestMapping(value = "formThucDon",params = "Insert", method = RequestMethod.POST )
	public <E> String addThucDon(HttpServletRequest request, ModelMap model,@ModelAttribute("td") ThucDonEntity td) {
		String error = "";
		Integer temp = 0;
		if(CheckIDThucDon(td.getId())) {
			error="ID thực đơn đã tồn tại!!!";
		}
		else {
		String idLoaiTU =request.getParameter("loaithucuong");	
		String tmp =request.getParameter("gia");
		Integer giaTD = Integer.parseInt(tmp);
		String tenTD =request.getParameter("ten");
		
		
		td.setLoaiThucUong(this.getLoaiThucUong(idLoaiTU));
		td.setGia(giaTD);
		td.setTen(tenTD);
		temp = this.insertThucDon(td);
		}
		if(temp != 0) {
		    model.addAttribute("message","Thêm thành công");
			
		
		}else {
			model.addAttribute("message","Thêm thất bại " + error);
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qlthucdon";
	}
	
	public Integer insertThucDon(ThucDonEntity td) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(td);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	}
	//end thêm
	
	
	
	
	
	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="admin-qlthucdon", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchThucDon(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchThucDon(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(5);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/qlthucdon";
	}
	public List<ThucDonEntity> searchThucDon(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity where convert(varchar,gia) like :name OR ten like :name "
				+"OR loaiThucUong.tenLoai like :name OR id like :name";
			
		Query query = session.createQuery(hql);
			
		query.setParameter("name", "%" +  name + "%");
		List<ThucDonEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm
	
	public List<ThucDonEntity> getThucDons(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity";
		Query query = session.createQuery(hql);
		List<ThucDonEntity> list = query.list();
		return list;
	}
	
	//xóa
	@RequestMapping(value = "admin-qlthucdon", params = "linkDelete")
	public <E> String deleteDonNhapHang (HttpServletRequest request, ModelMap model, @ModelAttribute("td") ThucDonEntity td) {
		
		Integer temp = this.deleteThucDon(td);
		if(temp != 0) {
			model.addAttribute("message","Delete thành công");
		}
		else {
			model.addAttribute("message", "Delete không thành công ! Thực đơn đã có trong hóa đơn");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlthucdon";
	}
	public Integer deleteThucDon (ThucDonEntity td) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(td);
			t.commit();
		}
		catch (Exception e){
			t.rollback();
			return 0;
		}
		finally{
			session.close();
		}
		return 1;
	}
	//end xóa
	
	public ThucDonEntity getTD (String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ThucDonEntity list = (ThucDonEntity) query.list().get(0);
		return list;
	}
	
/* phần chỉnh sửa */
	
	@RequestMapping(value = "formThucDon", params = "linkEdit" )
	public String editTD_showform (HttpServletRequest request, ModelMap model,@ModelAttribute("td") ThucDonEntity td) {
		
		String idTD = td.getId();
		model.addAttribute("loaithucuongs",this.getLoaiThucUongs());
		model.addAttribute("idloaiTU", this.getTD(idTD).getLoaiThucUong().getId());
		
		String ten = this.getTD(idTD).getTen();
		model.addAttribute("ten",ten);
		model.addAttribute("gia",this.getTD(idTD).getGia());

		/*model.addAttribute("td",td);*/
		model.addAttribute("btnupdate","true");
		model.addAttribute("read","true");
		return "admin/form/inputThucDon";
	}

	@RequestMapping(value = "formThucDon", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String editTD(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("td") ThucDonEntity td) {
		String idLoaiTU =requets.getParameter("loaithucuong");	
		td.setLoaiThucUong(getLoaiThucUong(idLoaiTU));
		
		
		String tmp =requets.getParameter("gia");
		Integer giaTD = Integer.parseInt(tmp);
		String tenTD =requets.getParameter("ten");
		
		td.setGia(giaTD);
		td.setTen(tenTD);
		Integer temp = this.updateTD(td);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");	
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");

		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qlthucdon";
	}
	
	
	
	public Integer updateTD(ThucDonEntity td) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(td);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	/* end phần chỉnh sửa */

}