package spring.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.APIFunction;
import spring.bean.Collector;
import spring.dto.CTPNDTO;
import spring.dto.PhieuNhapDTO;

@Controller

@RequestMapping(value = "/admin-home/")
public class QLNhapHangController {

	// show trang quan li nhap hang

	@RequestMapping(value = "admin-nhaphang", method = RequestMethod.GET)
	public <E> String showQLNH(HttpServletRequest request, ModelMap model) {

		List<PhieuNhapDTO> list = null;
		try {
			list = Collector.getListAll("/phieunhap", PhieuNhapDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "admin/qlnhaphang";
	}

	@RequestMapping(value = "admin-nhaphang/{id}.htm", params = "linkView")
	public String xemChiTietPN(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id) {
		List<CTPNDTO> cthds = this.getCtnps(id);
		for (CTPNDTO ct : cthds) {
			ct.setTenSP(APIFunction.getSP(ct.getSanPham()).getTen());
		}

		model.addAttribute("chiTiet", cthds);

		int tong = 0;
		for (CTPNDTO ctpn : cthds) {
			tong += ctpn.getDonGia() * ctpn.getSoLuong();
		}
		model.addAttribute("tongTien", tong);
		model.addAttribute("id", id);
		return "admin/ctpn";
	}

	/* hiển thị form */
	@RequestMapping(value = "formNhapHang", method = RequestMethod.GET)
	public String index_formNhapHang(ModelMap model, @ModelAttribute("pn") PhieuNhapDTO pn) {
		if (pn == null) {
			model.addAttribute("pn", new PhieuNhapDTO());

		} else {
			model.addAttribute("pn", pn);

		}
		return "admin/form/inputNhapHang";
	}

	@RequestMapping(value = "admin-nhaphang", params = "linkDelete")
	public String deleteDonNhapHang(HttpServletRequest request, ModelMap model, @ModelAttribute("pn") PhieuNhapDTO nh) {
		String id1 = request.getParameter("id");
		long id = Long.parseLong(id1);
		Integer temp = this.deleteDonNhapHang(this.getDonNhapHang(id));

		if (temp != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa không thành công");
		}

		return "admin/qlnhaphang";
	}

	/* thêm đơn nhập hàng */
	@RequestMapping(value = "formNhapHang", params = "Insert", method = RequestMethod.POST)
	public String add_DonNhapHang(HttpServletRequest request, ModelMap model, @ModelAttribute("pn") PhieuNhapDTO nh) {

		nh.setNvThucHien((long) 1);

		nh.setNgayThucHien(new Date());

		List<String> listError = checkInfo(nh);
		Integer temp = this.insert_NhapHang(nh);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nh = new PhieuNhapDTO();

		} else {
			model.addAttribute("message", "Thêm thất bại! " + listError);
		}

		return "admin/qlnhaphang";
	}

	@RequestMapping(value = "formNhapHang", params = "btnupdate", method = RequestMethod.POST)
	public String edit_NhapHang(HttpServletRequest requets, ModelMap model, @ModelAttribute("pn") PhieuNhapDTO nh) {
		/*
		 * UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(requets,
		 * "USERMODEL"); nh.setCpnv(user1.getUsernv());
		 */
		PhieuNhapDTO cp = new PhieuNhapDTO();
		cp = getDonNhapHang(nh.getId());
		nh.setNgayThucHien(cp.getNgayThucHien());
		nh.setNvThucHien(cp.getNvThucHien());

		List<String> listError = checkInfo(nh);
		Integer temp = this.updateNH(nh);
		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");

		} else {
			model.addAttribute("message", "Cập nhật không thành công! " + listError);

		}

		// model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}

	@RequestMapping(value = "formNhapHang", params = "linkEdit")
	public String editNhapHang_showform(HttpServletRequest request, ModelMap model) {
		String id1 = request.getParameter("id");
		long id = Long.parseLong(id1);

		// model.addAttribute("pagedListHolder", nhanvien);
		PhieuNhapDTO phieunhap = this.getDonNhapHang(id);
		// Timestamp ngaynhap = (Timestamp) phieunhap.getNgayThucHien();
		// String ngaynhap1 = ngaynhap.toLocalDateTime().toString();
		// model.addAttribute("ngaynhaphang", ngaynhap1);
		model.addAttribute("pn", phieunhap);

		model.addAttribute("btnupdate", "true");
		return "admin/form/inputNhapHang";
	}

	
	public List<String> checkInfo(PhieuNhapDTO cp) {

		List<String> listError = new ArrayList<>();

		return listError;

	}

	public Integer insert_NhapHang(PhieuNhapDTO nv) {
		String flag = Collector.postMess("/phieunhap", nv);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;

	}

	public List<PhieuNhapDTO> getDonNhapHangs() {
		List<PhieuNhapDTO> list = null;
		try {
			list = Collector.getListAll("/phieunhap", PhieuNhapDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<CTPNDTO> getCtnps(Long idpn) {
		List<CTPNDTO> list = null;
		try {
			list = Collector.getListAll("/ctpn?mapn=" + idpn.toString(), CTPNDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public PhieuNhapDTO getDonNhapHang(long id) {
		List<PhieuNhapDTO> list = null;
		try {
			list = Collector.getListAll("/phieunhap", PhieuNhapDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PhieuNhapDTO ss = new PhieuNhapDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id)
				ss = list.get(i);
		}

		return ss;
	}

	public Integer updateNH(PhieuNhapDTO nh) {
		String flag = Collector.putMess("/phieunhap", nh);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

	public Integer deleteDonNhapHang(PhieuNhapDTO user) {
		String flag = Collector.delMess("/phieunhap", user);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

}