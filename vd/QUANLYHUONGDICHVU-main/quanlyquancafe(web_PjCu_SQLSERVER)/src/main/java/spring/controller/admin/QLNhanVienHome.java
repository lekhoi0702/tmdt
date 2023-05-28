package spring.controller.admin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.NhanVienEntity;
import spring.entity.UserTBEntity;

@Transactional
@Controller
@RequestMapping(value = "/admin-home/" )
public class QLNhanVienHome {
	
	@Autowired
	SessionFactory factory;
	// CONTROLLER
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public <E> String index(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";
	}
	
	/*hiển thị form*/
	@RequestMapping(value="form", method = RequestMethod.GET) 
    public String index_form(ModelMap model) {
		model.addAttribute("nv",new NhanVienEntity());
          return "admin/form/inputNV";
    }
	
	public List<NhanVienEntity> getNhanVien(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where daNghi = false";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		return list;
	}
	
	public List<String> checkInfo(NhanVienEntity nv,String dateInString,String dateInString1) {
		List<String> listError = new ArrayList<>();
		
		
		if(nv.getHoTen().trim().equals("")) {
			listError.add("chưa nhập họ tên");
		}
		if( nv.getCmnd().trim().equals("")){
			listError.add("chưa nhập chứng minh");
		}
		if( nv.getDiaChi().trim().equals("")){
			listError.add("chưa nhập địa chỉ");
		}
		
		if ( nv.getLuong() == null) {
			listError.add("chưa nhập lương");
		}
		if(nv.getSdt().trim().equals("")) {
			listError.add("chưa số điện thoại");
		}
		if(dateInString.equals("")) {
			listError.add("chưa ngày sinh");
		}
		if( dateInString1.equals("")){
			listError.add("chưa nhập ngày vào làm");
		}
		return listError;
		
	}
	/*thêm nhân viên*/
	@RequestMapping(value = "form",params = "Insert", method = RequestMethod.POST )
	public <E> String addUser(HttpServletRequest request, ModelMap model,@ModelAttribute("nv") NhanVienEntity nv) {
		
		
		
		String dateInString = request.getParameter("ngaysinh");
		Date ngaysinh;
		try {
			ngaysinh = DateUtils.parseDate(dateInString, 
			  new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgaySinh(ngaysinh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dateInString1 = request.getParameter("ngayvaolam");
		Date ngayvaolam;
		try {
			ngayvaolam = DateUtils.parseDate(dateInString1, 
			  new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgayVaoLam(ngayvaolam);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> listError = checkInfo(nv, dateInString,dateInString1);
		nv.getLuong();
		nv.getHoTen().trim();
		nv.getCmnd().trim();
		nv.getDiaChi().trim();
		nv.getSdt().trim();
		
		nv.setDaNghi(false);
		
		Integer temp = this.insertUser(nv);
		if(temp != 0) {
			
		    model.addAttribute("message","Thêm Thành Công");
			nv.setHoTen(null);
			nv.setGioiTinh(null);
			nv.setLuong(null);
			nv.setSdt(null);
			nv.setCmnd(null);
			nv.setDiaChi(null);
			
		}else {
			model.addAttribute("message","Thêm thất bại! "+listError);
			
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";
	}
	
	public Integer insertUser(NhanVienEntity nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nv);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} 
	
	/* phần chỉnh sửa */
	
	@RequestMapping(value = "form", params = "linkEdit" )
	public String editNV_showform (HttpServletRequest request, ModelMap model) {
		String id1 =request.getParameter("id");
		long maNV = Long.parseLong(id1);
		/*List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("pagedListHolder", nhanvien);*/
		
		NhanVienEntity NV =  this.getNV(maNV);
		
		Date ngaysinh = NV.getNgaySinh();
		Date ngayvaolam = NV.getNgayVaoLam();
		/*String ngaysinh1 = ngaysinh.toString();
		String ngayvaolam1 = ngayvaolam.toString();*/
		
		model.addAttribute("ngaysinh",ngaysinh);
		model.addAttribute("ngayvaolam",ngayvaolam);
		
		model.addAttribute("nv",NV);
		model.addAttribute("btnupdate","true");
		return "admin/form/inputNV";
	}

	@RequestMapping(value = "form", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String editNV(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("nv") NhanVienEntity nv) {
		String dateInString = requets.getParameter("ngaysinh");
		Date ngaysinh;
		try {
			ngaysinh = DateUtils.parseDate(dateInString, 
			  new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgaySinh(ngaysinh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dateInString1 = requets.getParameter("ngayvaolam");
		Date ngayvaolam;
		try {
			ngayvaolam = DateUtils.parseDate(dateInString1, 
			  new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgayVaoLam(ngayvaolam);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> listError = checkInfo(nv, dateInString,dateInString1);
		nv.getLuong();
		nv.getHoTen().trim();
		nv.getCmnd().trim();
		nv.getDiaChi().trim();
		nv.getSdt().trim();
		
		Integer temp = this.updateNV(nv);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");	
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công! "+ listError);

		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";
	}
	
	
	public Integer updateNV(NhanVienEntity nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nv);
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
	
	//xóa tài khoản
	
	public UserTBEntity getTaiKhoan (String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where userName =:username and status=:status";
		
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("status", 1);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}
	
	public List<UserTBEntity> getTaiKhoans() {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where status =:status";
		Query query = session.createQuery(hql);
		query.setParameter("status", 1);
		List<UserTBEntity> list = query.list();
		return list;
	}
	public void updateTK(UserTBEntity tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(tk);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			
		}
		finally {
			session.close();
		}
		
	}
	
	public Boolean checkAdmin(long MaNV) {
		List<UserTBEntity> list = getTaiKhoans();
		int n = list.size();
		UserTBEntity tmp;
		for(int i=0;i<n;i++) {
			tmp =  	this.getTaiKhoan(list.get(i).getUserName());
		
			if(tmp.getUsernv().getMaNV() == MaNV && tmp.getChucVu().getId()== 1) {
				return true;
				
			}
		}
		
		return false;
	}
	
	public void delete_TK(long MaNV) {
		List<UserTBEntity> list = getTaiKhoans();
		int n = list.size();
		UserTBEntity tmp;
		for(int i=0;i<n;i++) {
			tmp =  	this.getTaiKhoan(list.get(i).getUserName());
		
			if(tmp.getUsernv().getMaNV() == MaNV) {
				tmp.setStatus(0);
				System.out.println(tmp.getUserName());
				this.updateTK(tmp);
				
			}
		}
	}
	
	
	
	
//	phần xóa
	@RequestMapping(value = "index", params = "linkDelete",method = RequestMethod.GET)
	public <E> String deleteNV (HttpServletRequest request, ModelMap model) {
		String id1 =request.getParameter("id");
		long maNV = Long.parseLong(id1);
		NhanVienEntity tmp = this.getNV(maNV);
		
		Integer temp=1;
		Boolean checkAdmin = checkAdmin(maNV);
		String error ="!!!";
		if(checkAdmin) {
			error = ", không được xóa admin ";
		}else {
			this.delete_TK(maNV);
			tmp.setDaNghi(true);

			temp = this.updateNV(tmp);
			
		}
		System.out.println(checkAdmin);
	
		if(temp != 0) {
			model.addAttribute("message","Xóa không thành công"+error);
		}
		else {
			model.addAttribute("message", "Xóa thành công");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";

}

	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="index", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchNhanVien(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchNhanVien(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(5);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/QLNV";
	}
	public List<NhanVienEntity> searchNhanVien(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV = :id OR hoTen like :name and daNghi = false OR ngaySinh like :name OR convert(varchar,gioiTinh) like :name OR diaChi like :name";
		Query query = session.createQuery(hql);
		Long id = null;
	
		try {
			id = Long.parseLong(name);
		}
		catch (Exception e) {
		}
		
		query.setParameter("id", id);			
		query.setParameter("name", "%" +  name + "%");
		List<NhanVienEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm
	
	public NhanVienEntity getNV (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id and daNghi = false ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
}
