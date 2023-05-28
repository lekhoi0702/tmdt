package spring.controller.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.quancafehighland.model.NhanVienModel;
import com.quancafehighland.utils.SessionUtil;

import spring.bean.BanHoaDonModel;
import spring.entity.BanEntity;
import spring.entity.ChiTietHDEntity;
import spring.entity.DatBanEntity;
import spring.entity.HoaDonEntity;
import spring.entity.LoaiThucUongEntity;
import spring.entity.NhanVienEntity;
import spring.entity.ThucDonEntity;
import spring.service.web.BanService;

@Transactional
@Controller
public class ThanhToanController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext application;
	@Autowired
	ServletContext session;

	@RequestMapping(value = "thanh-toan", method = RequestMethod.GET)
	public String createList(ModelMap model) {
		// kt co list ban tong he thong ko co thi tao list nay2 set ra view
		if (application.getAttribute("listBan") == null) {
			Session session = factory.getCurrentSession();
			String hql = "FROM BanEntity";
			Query query = session.createQuery(hql);
			List<BanEntity> list = query.list();

			application.setAttribute("listBan", list);
		}
		// kt co list ban tong he thong ko co thi tao list nay tinh hoa don
		if (application.getAttribute("banHoaDons") == null) {
			List<BanHoaDonModel> listBHD = new ArrayList();
			List<Long> listIdsBan = new ArrayList();

			List<BanEntity> list = (List<BanEntity>) application.getAttribute("listBan");
			int n = list.size();
			for (int i = 0; i < n; i++) {
				listBHD.add(new BanHoaDonModel(i+1));
				listIdsBan.add(new Long(list.get(i).getId()));
			}
			application.setAttribute("banHoaDons", listBHD);
			application.setAttribute("banids", listIdsBan);
			application.setAttribute("thucDons", getThucDons());
		}

		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		model.addAttribute("bans", listBan);
		model.addAttribute("loaiTUs", getLoaiTUs());
		model.addAttribute("thucDons", getThucDons());
		Long ban=(Long) session.getAttribute("idBanHT");
		if (ban!=null)
		{
			BanHoaDonModel BHD=listBHD.get((int) findBanHD(ban,listBHD));
			model.addAttribute("banHD", BHD);
			model.addAttribute("tongtien", tinhTong(BHD.getCthds()));
		}

		return "web/thanhtoan";
	}

	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST, params="xem")
	public String view(ModelMap model, HttpServletRequest request) {
		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		List<ThucDonEntity> listTD = (List<ThucDonEntity>) application.getAttribute("thucDons");
		

		// lay data tu form
		long ban = Long.parseLong(request.getParameter("Ban"));

		session.setAttribute("idBanHT", ban);
		// set view
		model.addAttribute("bans", listBan);

	
		if (Long.valueOf(ban) != null) {
			BanHoaDonModel BHD=listBHD.get((int) findBanHD(ban,listBHD));
			model.addAttribute("banHD", BHD);
			model.addAttribute("tongtien", tinhTong(BHD.getCthds()));
		}

		// 
		
		

		listBHD.get((int) findBanHD(ban, listBHD)).xuat();
		return "web/thanhtoan";

	}
	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST, params="thanhtoan")
	public String pay(ModelMap model, HttpServletRequest request) {
		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		List<ThucDonEntity> listTD = (List<ThucDonEntity>) application.getAttribute("thucDons");

		// lay data tu form
		long ban = Long.parseLong(request.getParameter("Ban"));


		// set view
		model.addAttribute("bans", listBan);

		// tim ban có id hien tai trong ds banhd hien tai
		BanHoaDonModel banHD= listBHD.get((int) findBanHD(ban,listBHD));
		HoaDonEntity HD=banHD.getHoaDon();
		if(HD!=null)
		{
			HD.setChiTietHD(banHD.getCthds());
			HD.setBan( listBan.get((int) findBan(ban,listBan)));
			HD.setTinhTrang(1);
			HD.setNgayThucHien(new java.util.Date());
			NhanVienModel nv= (NhanVienModel) SessionUtil.getInstance().getValue(request, "NHANVIEN");;
			HD.setHdnv(getNV(nv.getMaNV()));
			int temp= themHD(HD);
			System.out.print(temp);
			if (temp==1) {
				int flag;
				/*for (ChiTietHDEntity cthd : banHD.getCthds()) {
					if (themCTHD(cthd)==0) flag=true;
				}*/
				flag= themCTHDs(banHD.getCthds());
				if (flag==1) {
					 banHD.setHoaDon(null);
					 banHD.setCthds(new ArrayList<ChiTietHDEntity>());
					 listBan.get((int) findBan(ban,listBan)).setTinhTrang(banHD.getTrangThaiCu());
					 model.addAttribute("message", "Thanh toán thành công");
				}
				else model.addAttribute("message", "Thanh toán thất bại");
				
			}
			
		}
		
		return "web/thanhtoan";

	}
	
	
	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST, params="print")
	public String print(ModelMap model, HttpServletRequest request) {
		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		List<ThucDonEntity> listTD = (List<ThucDonEntity>) application.getAttribute("thucDons");

		// lay data tu form
		long ban = Long.parseLong(request.getParameter("Ban"));
		// tim ban có id hien tai trong ds banhd hien tai
		BanHoaDonModel banHD= listBHD.get((int) findBanHD(ban,listBHD));
		

		// set view
		model.addAttribute("bans", listBan);

		List<ChiTietHDEntity>  cthds= banHD.getCthds();
	

		int tong=0;
		for (ChiTietHDEntity cthd:cthds) {
			tong+=cthd.getTongTien();
		}
		model.addAttribute("tongTien",tong);
		model.addAttribute("cthds",cthds);
		listBHD.get((int) findBanHD(ban, listBHD)).xuat();
		return "web/inhoadon";

	}
	
	
	
	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST, params="xoa")
	public String xoa(ModelMap model, HttpServletRequest request) {
		long idMon= Long.parseLong(request.getParameter("id"));
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		long idBHD=  (long) session.getAttribute("idBanHT");
		long index= findBanHD(idBHD, listBHD );

		BanHoaDonModel BHD=listBHD.get((int) findBanHD(idBHD, listBHD ));
		System.out.println("Xoa"+BHD.getCthds().get((int) idMon).getThucDon().getTen());
		BHD.getCthds().remove((int)idMon);
		//neu ko con mon nao sau khi xoa, set ban ve ko co mon dc goi
		if (BHD.getCthds().isEmpty()) {
			List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
			listBan.get((int) findBan(idBHD,listBan)).setTinhTrang(BHD.getTrangThaiCu());
			BHD.setTrangThaiCu(0);
			BHD.setHoaDon(null);
		}
		return "redirect:thanh-toan.htm";
		
	}

	public List<LoaiThucUongEntity> getLoaiTUs() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiThucUongEntity";
		Query query = session.createQuery(hql);
		List<LoaiThucUongEntity> list = query.list();
		return list;
	}

	public List<ThucDonEntity> getThucDons() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity";
		Query query = session.createQuery(hql);
		List<ThucDonEntity> list = query.list();
		return list;
	}

	public long findBan(long id, List<BanEntity> list) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId() == id)
				return i;
		return -1;
	}

	public long findBanHD(long id, List<BanHoaDonModel> list) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getIdBan() == id)
				return i;
		return -1;
	}

	public long findTD(String id, List<ThucDonEntity> list) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId().equals(id))
				return i;
		return -1;
	}
	public int tinhTong( List<ChiTietHDEntity> list) {
		int tong=0;
		for (int i = 0; i < list.size(); i++)
			tong += list.get(i).getTongTien();
			
		return tong;
	}
	public Integer themHD(HoaDonEntity hoadon) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			
			session.save(hoadon);
	
		
			System.out.println(hoadon.getBan());
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;			
		}
		finally {
			session.close();
		}
		return 1;
	}
	public Integer themCTHD(ChiTietHDEntity cthd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			
			session.save(cthd);
	
		
	
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;			
		}
		finally {
			session.close();
		}
		return 1;
	}
	public Integer themCTHDs(List<ChiTietHDEntity> cthds) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			for (ChiTietHDEntity cthd : cthds)
			session.save(cthd);
	
		
	
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;			
		}
		finally {
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
	public List<ChiTietHDEntity> getChiTietHD(Long id){
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHDEntity where hoaDon.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		List<ChiTietHDEntity> list = query.list();
		return list;
	}
}
