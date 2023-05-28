package spring.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.BanHoaDonModel;
import spring.bean.Collector;
import spring.dto.BanDTO;
import spring.dto.ChiTietHDDTO;
import spring.dto.HoaDonDTO;
import spring.dto.LoaiThucUongDTO;
import spring.dto.ThucDonDTO;

@Controller
public class GoiMonController {

	@Autowired
	ServletContext application;
	@Autowired
	ServletContext session;

	@RequestMapping(value = "goi-mon", method = RequestMethod.GET)
	public String createList(ModelMap model) throws IOException {
		// kt co list ban tong he thong ko co thi tao list nay2 set ra view

		if (application.getAttribute("listBan") == null) {

			List<BanDTO> listBan = Collector.getListAll("/ban", BanDTO.class);
			application.setAttribute("loaiTUs", Collector.getListAll("/loaithucuong", LoaiThucUongDTO.class));

			application.setAttribute("thucDons", Collector.getListAll("/thucdon", ThucDonDTO.class));

			application.setAttribute("listBan", listBan);
			List<BanHoaDonModel> listBHD = new ArrayList<BanHoaDonModel>();
			List<Long> listIdsBan = new ArrayList<Long>();

			@SuppressWarnings("unchecked")
			List<BanDTO> list = (List<BanDTO>) application.getAttribute("listBan");

			for (BanDTO ban : list) {
				listBHD.add(new BanHoaDonModel(ban.getId()));
				listIdsBan.add(ban.getId());
				/*
				 * System.out.println("ban so" + (ban.getId()));
				 */ }
			application.setAttribute("banHoaDons", listBHD);
			application.setAttribute("banids", listIdsBan);

		}
		@SuppressWarnings("unchecked")
		List<BanDTO> listBan = (List<BanDTO>) application.getAttribute("listBan");
		@SuppressWarnings("unchecked")
		List<LoaiThucUongDTO> loaiTUs = (List<LoaiThucUongDTO>) application.getAttribute("loaiTUs");
		@SuppressWarnings("unchecked")
		List<ThucDonDTO> thucDons = (List<ThucDonDTO>) application.getAttribute("thucDons");

		model.addAttribute("bans", listBan);
		model.addAttribute("loaiTUs", loaiTUs);
		model.addAttribute("thucDons", thucDons);

		return "web/pay";
	}

	@RequestMapping(value = "goi-mon", method = RequestMethod.POST)
	public String order(ModelMap model, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<BanDTO> listBan = (List<BanDTO>) application.getAttribute("listBan");
		@SuppressWarnings("unchecked")
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		@SuppressWarnings("unchecked")
		List<ThucDonDTO> thucDons = (List<ThucDonDTO>) application.getAttribute("thucDons");
		@SuppressWarnings("unchecked")
		List<LoaiThucUongDTO> loaiTUs = (List<LoaiThucUongDTO>) application.getAttribute("loaiTUs");

		// lay data tu form
		long ban = Long.parseLong(request.getParameter("Ban"));
		session.setAttribute("idBanHT", ban);
		String loai = (String) request.getParameter("loaiTU");
		String thucDon = (String) request.getParameter("thucDon");
		int sl = Integer.parseInt(request.getParameter("sl"));
		if (sl <= 0)
			sl = 1;
		else if (sl > 50)
			sl = 50;
		// set view
		model.addAttribute("bans", listBan);
		model.addAttribute("loaiTUs", loaiTUs);
		model.addAttribute("thucDons", thucDons);
		// set ban co nguoi dung
		if (Long.valueOf(ban) != null) {
			listBan.get((int) BanHoaDonModel.findBan(ban, listBan)).setTinhTrang(1);
		}
		// set ban co nguoi dang chon chua goi
		if (loai.equals("DC")) {
			listBan.get((int) ban - 1).setTinhTrang(1);
		}
		// neu da goi set hoa don
		else {
			// nay goi lan dau thi tao hd, goi them thi chi cap vao hd co san
			if (listBHD.get((int) BanHoaDonModel.findBanHD(ban, listBHD)).getHoaDon() == null) {
				HoaDonDTO hoaDon = new HoaDonDTO();
				listBHD.get((int) BanHoaDonModel.findBanHD(ban, listBHD)).setHoaDon(hoaDon);
			}

			BanHoaDonModel BHD = listBHD.get((int) BanHoaDonModel.findBanHD(ban, listBHD));
			// neu khong ton tai mon do trong chi tiet tao chi tiet moi
			long index = BanHoaDonModel.findCTHD_ThucDon(thucDon, BHD.getCthds());
			
			if (index == -1) {
				ThucDonDTO td=thucDons.get((int) BanHoaDonModel.findTD(thucDon, thucDons));
				ChiTietHDDTO chiTiet = new ChiTietHDDTO();
				chiTiet.setMaHD(null);
				chiTiet.setSoLuong(sl);
				chiTiet.setMaSP(td.getid());
				chiTiet.setTongTien(td.getGia() * sl);
				BHD.getCthds().add(chiTiet);

				System.out.println(chiTiet.getMaSP());
			}
			// nếu co thi cong sl vao
			else {
				int oldSL = BHD.getCthds().get((int) index).getSoLuong();
				if (oldSL + sl <= 999)
					BHD.getCthds().get((int) index).setSoLuong(oldSL + sl);
				System.out.println(oldSL);
				System.out.println(oldSL + sl);
			}

		}
		/*
		 * System.out.println(ban); System.out.println(loai); System.out.println(sl);
		 * System.out.println(thucDon);
		 */
		/* listBHD.get((int)findBanHD(ban,listBHD)).xuat(); */
		return "web/pay";

	}

}
