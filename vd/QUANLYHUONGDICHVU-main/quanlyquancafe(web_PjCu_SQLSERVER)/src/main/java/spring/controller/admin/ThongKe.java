package spring.controller.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

import spring.entity.ChiPhiEntity;
import spring.entity.HoaDonEntity;

@Transactional
@Controller
@RequestMapping(value = "/admin-home/")
public class ThongKe {
	@Autowired
	SessionFactory factory;
	
	
	
	@RequestMapping(value = "thong-ke" , method = RequestMethod.GET)
	public <E> String thongKe(ModelMap model,HttpServletRequest request) {
		
		Calendar calendar = Calendar.getInstance();
		Integer ngay = calendar.get(Calendar.DAY_OF_MONTH);
		Integer thang = calendar.get(Calendar.MONTH)+1;
		Integer nam = calendar.get(Calendar.YEAR);
		List<Long> s = new ArrayList<>();
		List<HoaDonEntity> b = this.getHoaDon(ngay, thang, nam);
		for(HoaDonEntity hd:b){
			s.add(this.getTongTienHD(hd.getId()));
		}
		model.addAttribute("timeradio", "day");
		model.addAttribute("day", ngay);
		model.addAttribute("month", thang);
		model.addAttribute("year", nam);
		model.addAttribute("soHoaDon", this.soHoaDon(ngay,thang,nam));
		model.addAttribute("doanhThu", this.doanhThu(ngay,thang,nam));
		model.addAttribute("chiPhi", this.chiPhi(ngay,thang,nam));
		model.addAttribute("loiNhuan",this.doanhThu(ngay,thang,nam)- this.chiPhi(ngay,thang,nam));
		
		/*PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getChiPhi(ngay, thang, nam));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);*/
		
		model.addAttribute("hoaDon", b);
		model.addAttribute("bangChiPhi", this.getChiPhi(ngay, thang, nam));	
		model.addAttribute("tongHD", s);
		return "admin/thongke";
	}
	
	@RequestMapping(value = "thong-ke",params="btn-search", method = RequestMethod.POST)
	public <E> String searchThongKe1(ModelMap model, HttpServletRequest request) {
		Integer ngay,thang;
		try {
			ngay = Integer.parseInt(request.getParameter("day"));
		}
		catch (Exception e) {
			ngay = null;
		}
		try {
			thang = Integer.parseInt(request.getParameter("month"));
		}
		catch (Exception e) {
			thang = null;
		}
		Integer nam = Integer.parseInt(request.getParameter("year"));
		List<Long> s = new ArrayList<>();
		List<HoaDonEntity> b = this.getHoaDon(ngay, thang, nam);
		for(HoaDonEntity hd:b){
			s.add(this.getTongTienHD(hd.getId()));
		}
		
		model.addAttribute("timeradio", request.getParameter("totime"));
		model.addAttribute("day", ngay);
		model.addAttribute("month", thang);
		model.addAttribute("year", nam);
		model.addAttribute("soHoaDon", this.soHoaDon(ngay, thang, nam));
		model.addAttribute("doanhThu", this.doanhThu(ngay, thang, nam));
		model.addAttribute("chiPhi", this.chiPhi(ngay, thang, nam));
		model.addAttribute("loiNhuan",this.doanhThu(ngay, thang, nam)- this.chiPhi(ngay, thang, nam));
		
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getChiPhi(ngay, thang, nam));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		model.addAttribute("hoaDon", this.getHoaDon(ngay, thang, nam));
		model.addAttribute("bangChiPhi", this.getChiPhi(ngay, thang, nam));
		model.addAttribute("tongHD", s);
		//bieu do
		List<Integer> cotX = new ArrayList<>();
		List<Long> soDonBD = new ArrayList<>();
		List<Long> doanhThuBD = new ArrayList<>();
		List<Long> chiPhiBD = new ArrayList<>();
		List<Long> loiNhuanBD = new ArrayList<>();
		if (thang != null) {
			int maxNgayTrongThang = new Date(nam,thang,0).getDate();
			
			for (int i=0; i<maxNgayTrongThang; i++) {
				cotX.add(i+1);
				soDonBD.add(this.soHoaDon(i+1, thang, nam));
				doanhThuBD.add(this.doanhThu(i+1, thang, nam));
				chiPhiBD.add(this.chiPhi(i+1, thang, nam));
				loiNhuanBD.add(this.doanhThu(i+1, thang, nam)-this.chiPhi(i+1, thang, nam));
			} 
		}
		else {
			for (int i=0; i<12; i++) {
				cotX.add(i+1);
				soDonBD.add(this.soHoaDon(null,i+1, nam));
				doanhThuBD.add(this.doanhThu(null,i+1, nam));
				chiPhiBD.add(this.chiPhi(null,i+1, nam));
				loiNhuanBD.add(this.doanhThu(null,i+1, nam)-this.chiPhi(null,i+1, nam));
			} 
		}
		model.addAttribute("cotX", cotX);
		model.addAttribute("soDonBD", soDonBD);
		model.addAttribute("doanhThuBD", doanhThuBD);
		model.addAttribute("chiPhiBD", chiPhiBD);
		model.addAttribute("loiNhuanBD", loiNhuanBD);
		return "admin/thongke";
	}
	public Long soHoaDon(Integer ngay, Integer thang, Integer nam) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "SELECT COUNT(*) FROM HoaDonEntity WHERE YEAR(ngayThucHien) = :nam";
			} else {
				hql = "SELECT COUNT(*) FROM HoaDonEntity WHERE MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam";
			}
		} else {
			hql = "SELECT COUNT(*) FROM HoaDonEntity WHERE DAY(ngayThucHien) = :ngay AND MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam";
		}
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		
		Long soHoaDon = (Long) query.list().get(0);
		return soHoaDon;
	}
	public Long doanhThu(Integer ngay, Integer thang, Integer nam) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE YEAR(hd.ngayThucHien) = :nam";
			} else {
				hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE MONTH(hd.ngayThucHien) = :thang AND YEAR(hd.ngayThucHien) = :nam";
			}
		} else {
			hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE DAY(hd.ngayThucHien) = :ngay AND MONTH(hd.ngayThucHien) = :thang AND YEAR(hd.ngayThucHien) = :nam";
		}
		
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		Long tongTien = (Long) query.list().get(0);
		if (tongTien == null) tongTien = (long) 0;
		return tongTien;
	}
	public Long chiPhi(Integer ngay, Integer thang, Integer nam) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE YEAR(ngayNhap) = :nam";
			} else {
				hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam";
			}
		} else {
			hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE DAY(ngayNhap) = :ngay AND MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam";
		}
		
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		Long chiPhi = (Long) query.list().get(0);
		if (chiPhi == null) chiPhi = (long) 0;
		return chiPhi;
	}
	public List<HoaDonEntity> getHoaDon(Integer ngay, Integer thang, Integer nam){
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "FROM HoaDonEntity WHERE YEAR(ngayThucHien) = :nam ORDER BY id DESC, ngayThucHien DESC";
			} else {
				hql = "FROM HoaDonEntity WHERE MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam ORDER BY id DESC, ngayThucHien DESC";
			}
		} else {
			hql = "FROM HoaDonEntity WHERE DAY(ngayThucHien) = :ngay AND MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam ORDER BY id DESC, ngayThucHien DESC";
		}	
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		List<HoaDonEntity> list = query.list();
		return list;
	}
	public Long getTongTienHD(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT sum(tongTien) FROM ChiTietHDEntity where hoaDon.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		Long tongTien = (Long) query.list().get(0);
		if (tongTien == null) tongTien = (long) 0;
		return tongTien;
	}
	public List<ChiPhiEntity> getChiPhi(Integer ngay, Integer thang, Integer nam){
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "FROM ChiPhiEntity WHERE YEAR(ngayNhap) = :nam ORDER BY id DESC, ngayNhap DESC";
			} else {
				hql = "FROM ChiPhiEntity WHERE MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam ORDER BY id DESC, ngayNhap DESC";
			}
		} else {
			hql = "FROM ChiPhiEntity WHERE DAY(ngayNhap) = :ngay AND MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam ORDER BY id DESC, ngayNhap DESC";
		}	
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		List<ChiPhiEntity> list = query.list();
		return list;
	}
}