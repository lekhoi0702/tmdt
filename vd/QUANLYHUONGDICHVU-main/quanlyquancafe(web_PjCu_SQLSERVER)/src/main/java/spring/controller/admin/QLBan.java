package spring.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

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
import spring.entity.DatBanEntity;
import spring.entity.LoaiBanEntity;


@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLBan {

	//show trang quan li ban
	@Autowired
	ServletContext application;
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-qlban", method = RequestMethod.GET)
	public <E> String showQLB(HttpServletRequest request,ModelMap model){	
		
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	
	
	
 //hiển thị form
	@RequestMapping(value="formBan", method = RequestMethod.GET) 
    public String formBan(ModelMap model, HttpServletRequest request,@ModelAttribute("b") BanEntity b) {
		model.addAttribute("loaibans",getLoaiBans());	
          return "admin/form/inputBan1";
    }
	
	public List<String> checkInfo(BanEntity b ) {

		List<String> listError = new ArrayList<>();

		if (b.getSoGhe()==null) {
			listError.add("chưa nhập số ghế");
		}
		
		
		return listError;
		

	}
	
	//thêm bàn xổ combobox
	@RequestMapping(value = "formBan",params = "Insert", method = RequestMethod.POST )
	public <E> String add_BAN(HttpServletRequest request, ModelMap model /*,@ModelAttribute("lb") LoaiBanEntity lb*/ ) {
		BanEntity ban = new BanEntity();
		String id = request.getParameter("loaiBan"); 
		int id1=1;//mặc định là bàn thường
		id1 = Integer.parseInt(id);
		
		String a = request.getParameter("soGhe");
		
		int soghe = Integer.parseInt(a);
		
		ban.setSoGhe(soghe);
		ban.setTinhTrang(0);
		ban.setLoaiBan(getLoaiBan(id1));
		List<String> listError = checkInfo(ban);
		Integer temp1 = this.InsertBan(ban);
		if(temp1 != 0) {
		    model.addAttribute("message","Thêm thành công");
		    
			
		}else {
			model.addAttribute("message","Thêm thất bại! "+listError);
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
		
//	END THÊM
/* phần chỉnh sửa */
	
	@RequestMapping(value = "formBan", params = "linkEdit" )
	public String editBan_showform (@ModelAttribute("b") BanEntity b, ModelMap model) {
		
		long id = b.getId();
		model.addAttribute("idLoai",this.getBan(id).getLoaiBan().getId());
		model.addAttribute("loaibans",getLoaiBans());	
		model.addAttribute("soGhe1",this.getBan(id).getSoGhe());	
		
		model.addAttribute("btnupdate","true");
		return "admin/form/inputBan1";
	}

	@RequestMapping(value = "formBan", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String edit_QLBan(HttpServletRequest requets, ModelMap model 
			) {

		String id1 =requets.getParameter("id");
		long idB = Long.parseLong(id1);
		
		String id = requets.getParameter("loaiBan"); 
		int idLB = Integer.parseInt(id);
		String a = requets.getParameter("soGhe");
		int soghe = Integer.parseInt(a);
		
		BanEntity tmp = getBan(idB);
		
		
		tmp.setSoGhe(soghe);
		tmp.setTinhTrang(0);
		tmp.setLoaiBan(getLoaiBan(idLB));
		Integer temp = this.updateBan(tmp);
		if( temp == 0) {
			model.addAttribute("message", "Cập nhật thành công");
			
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
			
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	
	
	public Integer updateBan(BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(b);
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
	
	
	
	 public List<LoaiBanEntity> getLoaiBans(){
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiBanEntity";
			Query query = session.createQuery(hql);
			List<LoaiBanEntity> list = query.list();
			
			return list;
		}
	 public LoaiBanEntity getLoaiBan (long id) {
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiBanEntity where id =:id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			LoaiBanEntity list = (LoaiBanEntity) query.list().get(0);
			return list;
		}

	
	public List<BanEntity> getBans(){
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		return list;
	}
	
	public BanEntity getBan (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BanEntity list = (BanEntity) query.list().get(0);
		return list;
	}
	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="admin-qlban", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchBan1(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchBan(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(4);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/qlban";
	}
	public List<BanEntity> searchBan(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where id = :id OR soGhe = :soGhe OR loaiBan.tenLoai LIKE :name OR convert(varchar,loaiBan.giaDat) like :name";
		Query query = session.createQuery(hql);
		Long id = null;
		Integer soGhe = null;
		try {
			id = Long.parseLong(name);
		}
		catch (Exception e) {
		}
		
		try {
			soGhe = Integer.parseInt(name);
		}
		catch (Exception e) {
		}
		
		query.setParameter("id", id);			
		query.setParameter("soGhe", soGhe);
		
		query.setParameter("name", "%" +  name + "%");
		List<BanEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm
	
//	XÓA
	@RequestMapping(value = "admin-qlban", params = "linkDelete")
	public <E> String deleteDonNhapHang (HttpServletRequest request, ModelMap model, @ModelAttribute("b") BanEntity b) {
		
		Integer temp = this.deleteBan(b);
		//System.out.println(getBan(id).ge);
		
		if(temp != 0) {
			model.addAttribute("message","Xóa thành công");
		}
		else {
			model.addAttribute("message", "Xóa không thành công! Bàn đã có người đặt");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	public Integer deleteBan (BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(b);
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
	public Integer InsertBan (BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(b);
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
	
	//END XÓA

}