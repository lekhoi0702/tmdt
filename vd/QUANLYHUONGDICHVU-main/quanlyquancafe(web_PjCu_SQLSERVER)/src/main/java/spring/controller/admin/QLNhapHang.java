package spring.controller.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.ChiPhiEntity;
import spring.entity.NhanVienEntity;

@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLNhapHang {

	// show trang quan li nhap hang
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "admin-nhaphang", method = RequestMethod.GET)
	public <E> String showQLNH(HttpServletRequest request, ModelMap model) {
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		// model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}

	/* hiển thị form */
	@RequestMapping(value = "formNhapHang", method = RequestMethod.GET)
	public String index_formNhapHang(ModelMap model) {
		model.addAttribute("nh", new ChiPhiEntity());
		return "admin/form/inputNhapHang";
	}

	public List<String> checkInfo(ChiPhiEntity cp ) {

		List<String> listError = new ArrayList<>();

		if (cp.getDv().trim().equals("")) {
			listError.add("chưa nhập dịch vụ");
		}
		if (cp.getLoai().trim().equals("")) {
			listError.add("chưa nhập chưa nhập loại DV");
		}
		if (cp.getTenNL().trim().equals("")) {
			listError.add("chưa nhập nguyên liệu");
		}
		if (cp.getSoLuong()==null) {
			listError.add("chưa nhập số lượng");
		}
		if (cp.getGiaMoiDV()==null) {
			listError.add("chưa chọn giá mỗi dịch vụ");
		}
		if (cp.getDv().trim().equals("")) {
			listError.add("chưa chọn đơn vị");
		}
		
		return listError;
		

	}

	/* thêm đơn nhập hàng */
	@RequestMapping(value = "formNhapHang", params = "Insert", method = RequestMethod.POST)
	public <E> String add_DonNhapHang(HttpServletRequest request, ModelMap model,
			@ModelAttribute("nh") ChiPhiEntity nh) {

		/*
		 * NhanVienEntity nv= (NhanVienEntity)
		 * SessionUtil.getInstance().getValue(request, "NHANVIEN"); nh.setCpnv(nv);
		 */
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long idnv = user1.getID();
		nh.setCpnv(this.getNV(idnv));
		try {
			nh.setNgayNhap(Timestamp.valueOf(request.getParameter("ngaynhaphang").replace("T", " ") + ":00"));
		} catch (Exception e) {
			nh.setNgayNhap(new Date());
		}
		List<String> listError = checkInfo(nh);
		Integer temp = this.insert_NhapHang(nh);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nh.setDv(null);
			nh.setGhiChu(null);
			nh.setGiaMoiDV(null);
			nh.setLoai(null);
			nh.setNhaCungCap(null);
			nh.setSoLuong(null);
			nh.setTenNL(null);

		} else {
			model.addAttribute("message", "Thêm thất bại! "+listError);
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(3);
		model.addAttribute("pagedListHolder", pagedListHolder);
		// model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}

	public Integer insert_NhapHang(ChiPhiEntity nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	// end thêm

	/* phần chỉnh sửa */

	@RequestMapping(value = "formNhapHang", params = "linkEdit")
	public String editNhapHang_showform(HttpServletRequest request, ModelMap model) {
		String id1 = request.getParameter("id");
		long id = Long.parseLong(id1);
		List<ChiPhiEntity> nhanvien = this.getDonNhapHangs();
		model.addAttribute("pagedListHolder", nhanvien);
		ChiPhiEntity chiphi = this.getDonNhapHang(id);
		Timestamp ngaynhap = (Timestamp) chiphi.getNgayNhap();
		String ngaynhap1 = ngaynhap.toLocalDateTime().toString();
		model.addAttribute("ngaynhaphang", ngaynhap1);
		model.addAttribute("nh", chiphi);

		model.addAttribute("btnupdate", "true");
		return "admin/form/inputNhapHang";
	}

	@RequestMapping(value = "formNhapHang", params = "btnupdate", method = RequestMethod.POST)
	public <E> String edit_NhapHang(HttpServletRequest requets, ModelMap model, @ModelAttribute("nh") ChiPhiEntity nh) {
		/*
		 * UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(requets,
		 * "USERMODEL"); nh.setCpnv(user1.getUsernv());
		 */

		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(requets, "USERMODEL");
		Long idnv = user1.getID();
		nh.setCpnv(this.getNV(idnv));

		nh.setNgayNhap(new Date());
		List<String> listError = checkInfo(nh);
		Integer temp = this.updateNH(nh);
		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");

		} else {
			model.addAttribute("message", "Cập nhật không thành công! "+listError);

		}

		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		// model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}

	public Integer updateNH(ChiPhiEntity nh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nh);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	/* end phần chỉnh sửa */

//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "admin-nhaphang", params = "btnsearch", method = RequestMethod.POST)
	public <E> String searchBan1(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>(
				(List<E>) this.searchDonNhapHang(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(5);

		model.addAttribute("pagedListHolder", pagedListHolder);

		return "admin/qlnhaphang";
	}

	public List<ChiPhiEntity> searchDonNhapHang(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity where tenNL like :ngay OR ngayNhap like :ngay OR loai like :ngay OR convert(varchar,giaMoiDV) like :ngay OR convert(varchar,cpnv.maNV) like :ngay";
		Query query = session.createQuery(hql);
		query.setParameter("ngay", "%" + name + "%");
		List<ChiPhiEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm

//	XÓA
	@RequestMapping(value = "admin-nhaphang", params = "linkDelete")
	public <E> String deleteDonNhapHang(HttpServletRequest request, ModelMap model,
			@ModelAttribute("nh") ChiPhiEntity nh) {
		String id1 = request.getParameter("id");
		long id = Long.parseLong(id1);
		Integer temp = this.deleteDonNhapHang(this.getDonNhapHang(id));

		if (temp != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa không thành công");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		// model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}

	public Integer deleteDonNhapHang(ChiPhiEntity user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.delete(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	// END XÓA
	public List<ChiPhiEntity> getDonNhapHangs() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity";
		Query query = session.createQuery(hql);
		List<ChiPhiEntity> list = query.list();
		return list;
	}

	public ChiPhiEntity getDonNhapHang(long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ChiPhiEntity list = (ChiPhiEntity) query.list().get(0);
		return list;
	}

	public NhanVienEntity getNV(long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}

}