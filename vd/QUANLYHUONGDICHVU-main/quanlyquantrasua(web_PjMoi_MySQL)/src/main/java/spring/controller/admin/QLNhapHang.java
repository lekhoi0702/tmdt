package spring.controller.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.BanHoaDonModel;
import spring.bean.Collector;
import spring.dto.ChiPhiDTO;
import spring.dto.ChiTietHDDTO;
import spring.dto.NhanVienDTO;


@Controller

@RequestMapping(value = "/admin-home/")
public class QLNhapHang {

	// show trang quan li nhap hang


	@RequestMapping(value = "admin-nhaphang", method = RequestMethod.GET)
	public <E> String showQLNH(HttpServletRequest request, ModelMap model) {
		
		List<ChiPhiDTO> list=null;
		try {
			list = Collector.getListAll("/chiphi",ChiPhiDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		
		return "admin/qlnhaphang";
	}

	/* hiển thị form */
	@RequestMapping(value = "formNhapHang", method = RequestMethod.GET)
	public String index_formNhapHang(ModelMap model) {
		model.addAttribute("nh", new ChiPhiDTO());
		return "admin/form/inputNhapHang";
	}

	public List<String> checkInfo(ChiPhiDTO cp ) {

		List<String> listError = new ArrayList<>();
		
		return listError;
		

	}

	public Integer insert_NhapHang(ChiPhiDTO nv) {
		String flag = Collector.postMess("/chiphi", nv);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;

	}
	
	/* thêm đơn nhập hàng */
	@RequestMapping(value = "formNhapHang", params = "Insert", method = RequestMethod.POST)
	public <E> String add_DonNhapHang(HttpServletRequest request, ModelMap model,
			@ModelAttribute("nh") ChiPhiDTO nh) {

		
		nh.setNvTao((long)1);;
		
		nh.setNgayNhap(new Date());
		
		List<String> listError = checkInfo(nh);
		Integer temp = this.insert_NhapHang(nh);

		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");

			nh.setTenChiPhi(null);
			nh.setGhiChu(null);
			nh.setGiaDonVi(0);
			nh.setLoai(null);
			nh.setNhaCungCap(null);
			nh.setSoLuong(0);

		} else {
			model.addAttribute("message", "Thêm thất bại! "+listError);
		}
		
		return "admin/qlnhaphang";
	}
	long temp = 0;
	@RequestMapping(value = "formNhapHang", params = "linkEdit")
	public String editNhapHang_showform(HttpServletRequest request, ModelMap model) {
		String id1 = request.getParameter("id");
		long id = Long.parseLong(id1);
		temp = id;
		List<ChiPhiDTO> nhanvien = this.getDonNhapHangs();
		//model.addAttribute("pagedListHolder", nhanvien);
		ChiPhiDTO chiphi = this.getDonNhapHang(id);
		//Timestamp ngaynhap = (Timestamp) chiphi.getNgayNhap();
		//String ngaynhap1 = ngaynhap.toLocalDateTime().toString();
		//model.addAttribute("ngaynhaphang", ngaynhap1);
		model.addAttribute("nh", chiphi);

		model.addAttribute("btnupdate", "true");
		return "admin/form/inputNhapHang";
	}
	
	public List<ChiPhiDTO> getDonNhapHangs() {
		List<ChiPhiDTO> list = null;
		try {
			list = Collector.getListAll("/chiphi", ChiPhiDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ChiPhiDTO getDonNhapHang(long id) {
		List<ChiPhiDTO> list = null;
		try {
			list = Collector.getListAll("/chiphi", ChiPhiDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChiPhiDTO ss = new ChiPhiDTO();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id)
				ss = list.get(i);
		}
		
		return ss;
	}
	
	@RequestMapping(value = "formNhapHang", params = "btnupdate", method = RequestMethod.POST)
	public <E> String edit_NhapHang(HttpServletRequest requets, ModelMap model, @ModelAttribute("nh") ChiPhiDTO nh) {
		/*
		 * UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(requets,
		 * "USERMODEL"); nh.setCpnv(user1.getUsernv());
		 */
		ChiPhiDTO cp = new ChiPhiDTO();
		cp = getDonNhapHang(temp);
		nh.setNgayNhap(cp.getNgayNhap());
		nh.setNvTao(cp.getNvTao());
		if(nh.getDonVi().startsWith("C")){
			nh.setDonVi("Cai");
		}else if (nh.getDonVi().startsWith("N")) {
			nh.setDonVi("Nguoi");
		}else if (nh.getDonVi().startsWith("KG")) {
			nh.setDonVi("KG");
		}
		else {
			nh.setDonVi("Khac");
		}
		List<String> listError = checkInfo(nh);
		Integer temp = this.updateNH(nh);
		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");

		} else {
			model.addAttribute("message", "Cập nhật không thành công! "+listError);

		}

		// model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}

	public Integer updateNH(ChiPhiDTO nh) {
		String flag = Collector.putMess("/chiphi", nh);
		System.out.println(flag);
		if (flag.equals("00")){
			return 1;
		} else
			return 0;
	}
	
	@RequestMapping(value = "admin-nhaphang", params = "linkDelete")
	public <E> String deleteDonNhapHang(HttpServletRequest request, ModelMap model,
			@ModelAttribute("nh") ChiPhiDTO nh) {
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

	public Integer deleteDonNhapHang(ChiPhiDTO user) {
		String flag = Collector.delMess("/chiphi", user);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
	
}