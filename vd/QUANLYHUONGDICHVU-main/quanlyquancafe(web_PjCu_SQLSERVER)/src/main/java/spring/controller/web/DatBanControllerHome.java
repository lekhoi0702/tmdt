package spring.controller.web;

import java.sql.Timestamp;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.BanEntity;
import spring.entity.DatBanEntity;
import spring.entity.NhanVienEntity;

@Transactional
@Controller
public class DatBanControllerHome {

	@Autowired
	SessionFactory factory;

	// CONTROLLER
	@RequestMapping(value = "trang-chu", method = RequestMethod.GET)
	public <E> String datban(HttpServletRequest request, ModelMap model) {
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);

		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		// model.addAttribute("bans", list);
		return "web/datban";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "trang-chu", params = "btnsearch", method = RequestMethod.POST)
	public <E> String searchBan1(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>(
				(List<E>) this.searchBan(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);

		pagedListHolder.setPageSize(10);

		model.addAttribute("pagedListHolder", pagedListHolder);

		return "web/datban";
	}

	@RequestMapping(value = "dat-ban/{id}.htm", params = "linkView")
	public <E> String xemDatBan(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id) {
		// page
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) getDatBan(id));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);

		pagedListHolder.setPageSize(10);

		model.addAttribute("pagedListHolder", pagedListHolder);

		model.addAttribute("id", id);
		return "web/datban2";
	}

	@RequestMapping(value = "dat-ban/{id}.htm", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchDatBan(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>(
				(List<E>) this.searchDatBan(request.getParameter("searchInput"),id));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);

		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("id", id);
		return "web/datban2";
	}
	
	@RequestMapping(value = "dat-ban/{id}.htm", params = "btndatban", method = RequestMethod.POST)
	public <E> String datBan1(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id,
			@ModelAttribute("datban") DatBanEntity datban, BindingResult er) {
		// lay thong tin
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long idnv = user1.getID();
		// thiet lap dat ban
		datban.setDbnv(this.getNV(idnv));
		datban.setNgayDat(new Date());
		datban.setBan(getBan(id));

		try {
			datban.setTgDuKien(Timestamp.valueOf(request.getParameter("tg").replace("T", " ") + ":00"));
		} catch (Exception e) {
			datban.setTgDuKien(null);
		}
		if (datban.getHoTen().equals("")) {
			er.rejectValue("hoTen", "datban", "Vui lòng nhập họ tên");
		}
		if (datban.getSdt().length() < 10 || datban.getSdt().length() > 12||!checknum(datban.getSdt())) {
			er.rejectValue("sdt", "datban", "Vui lòng nhập sdt đúng định dạng");
		}

		if (datban.getTienCoc() == null || datban.getTienCoc() % 1000 != 0) {
			er.rejectValue("tienCoc", "datban", "Vui lòng nhập tiền cọc chia hết cho 1000");
		}
		if (datban.getTgDuKien() == null) {
			er.rejectValue("ngayDat", "datban", "Vui lòng chọn ngày đến");
		}
		if (er.hasErrors()) {
			model.addAttribute("message", "Thêm mới đặt bàn thất bại, kiểm tra lại các trường");
		} else {
			// save dat ban
			Integer temp = this.themDatBan(datban);
			if (temp != 0) {
				model.addAttribute("message", "Thêm mới đặt bàn thành công");

			} else {
				model.addAttribute("message", "Thêm mới đặt bàn thất bại");
			}
		}

		// page
		List<DatBanEntity> listDatBan = getDatBan(id);

		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) listDatBan);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);

		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);

		model.addAttribute("id", id);
		return "web/datban2";
	};

	// END CONTROLLER
	public List<BanEntity> searchBan(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where convert(varchar,id) like :name OR  convert(varchar,soGhe) like :name OR loaiBan.tenLoai LIKE :name OR loaiBan.giaDat like :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		List<BanEntity> list = query.list();
		return list;
	}

	public List<DatBanEntity> searchDatBan(String name, Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DatBanEntity where ban.id =:id AND (hoTen like :name OR sdt like :name OR convert(varchar,tienCoc) like :name OR "
				+"ngayDat like :name OR tgDuKien like :name OR convert(varchar,trangThai) like :name)";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("id", id);
		List<DatBanEntity> list = query.list();
		return list;
	}
	
	public Integer themDatBan(DatBanEntity datban) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {

			session.save(datban);

			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public NhanVienEntity getNV(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}

	public List<DatBanEntity> getDatBan(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DatBanEntity where ban.id =:id AND tgDuKien > GETDATE()";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<DatBanEntity> list = query.list();
		return list;
	}

	public List<BanEntity> getBans() {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		return list;
	}

	public BanEntity getBan(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BanEntity list = (BanEntity) query.list().get(0);
		return list;
	}

	public boolean checknum(String str) {

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				return false;
		}
		return true;
	}
}