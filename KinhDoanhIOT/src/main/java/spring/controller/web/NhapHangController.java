package spring.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.CTPNDTO;
import spring.dto.PhieuNhapDTO;
import spring.dto.CTPNDTO;
import spring.dto.LoginDTO;
@Controller
public class NhapHangController {
	
	
	private long getNvThucHienFromSession(HttpServletRequest request) {
	    String manv = (String) request.getSession().getAttribute("USERMODEL.userName");
	    if (manv == null) {
	        return 1;
	    }
	    return Long.parseLong(manv);
	}
	    		
	    		
	    		
	@RequestMapping(value = "nhaphang", method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model, HttpServletRequest request) {

		List<PhieuNhapDTO> list = null;
		try {
			list = Collector.getListAll("/phieunhap", PhieuNhapDTO.class);
			String manv = (String) request.getSession().getAttribute("USERMODEL.userName");
			if (manv != null && !manv.isEmpty()) {

				// Lọc danh sách phiếu nhập theo MANV trong session
				list = list.stream().filter(pn -> pn.getNvThucHien() == Long.parseLong(manv))
						.collect(Collectors.toList());
			} else {
				// Nếu MANV là null, trả về danh sách rỗng hoặc thông báo lỗi cho người dùng
				list = new ArrayList<>();
				// Hoặc model.addAttribute("error", "MANV is null");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);

		return "web/nhaphang";
	}
/* chi tiet phieu nhap */
	@RequestMapping(value = "nhanvien-nhaphang/{id}.htm", params = "linkView")
	public String xemChiTietPN(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id) {
		List<CTPNDTO> cthds = this.getCtnps(id);

		model.addAttribute("chiTiet", cthds);

		int tong = 0;
		for (CTPNDTO ctpn : cthds) {
			tong += ctpn.getDonGia() * ctpn.getSoLuong();
		}
		model.addAttribute("tongTien", tong);
		model.addAttribute("idhd", id);
		return "web/ctpn";
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
	/* them phieu nhap */	
	/* form nhap */
	@RequestMapping(value = "formNhapHang", method = RequestMethod.GET)
	public String index_formNhapHang(ModelMap model) {
		model.addAttribute("nh", new PhieuNhapDTO());
		return "web/form/inputNhapHangNV";

	}
	
	/* xac nhan them*/

	
	@RequestMapping(value = "formNhapHang", params = "Insert", method = RequestMethod.POST)
	public String add_DonNhapHang(HttpServletRequest request, ModelMap model,
			@ModelAttribute("nh") PhieuNhapDTO nh) {
		
		long nvThucHien = getNvThucHienFromSession(request);
	    nh.setNvThucHien(nvThucHien);
		nh.setNgayThucHien(new Date());

		List<String> listError = checkInfo(nh);
		Integer temp = this.insert_NhapHang(nh);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nh = new PhieuNhapDTO();

		} else {
			model.addAttribute("message", "Thêm thất bại! " + listError);
		}

		return "web/nhaphang";
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
	


}