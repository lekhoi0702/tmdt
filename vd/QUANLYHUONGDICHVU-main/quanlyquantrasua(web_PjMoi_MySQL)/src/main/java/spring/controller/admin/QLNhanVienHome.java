package spring.controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.BanHoaDonModel;
import spring.bean.Collector;
import spring.dto.ChiTietHDDTO;
import spring.dto.NhanVienDTO;
import spring.dto.UserDTO;

@Controller
@RequestMapping(value = "/admin-home/")
public class QLNhanVienHome {

	// CONTROLLER
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public <E> String index(HttpServletRequest request, ModelMap model) {
		List<NhanVienDTO> list = null;
		try {
			list = Collector.getListAll("/nhanvien", NhanVienDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		return "admin/QLNV";
	}

	/* hiển thị form */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String index_form(ModelMap model) {
		NhanVienDTO nv = new NhanVienDTO();

		model.addAttribute("nv", nv);
		return "admin/form/inputNV";
	}

	public List<String> checkInfo(NhanVienDTO nv, String dateInString, String dateInString1) {
		List<String> listError = new ArrayList<>();

		if (nv.getHoTen().trim().equals("")) {
			listError.add("chưa nhập họ tên");
		}
		if (nv.getCmnd().trim().equals("")) {
			listError.add("chưa nhập chứng minh");
		}
		if (nv.getDiaChi().trim().equals("")) {
			listError.add("chưa nhập địa chỉ");
		}

		if (nv.getLuong() == 0) {
			listError.add("chưa nhập lương");
		}
		if (nv.getSdt().trim().equals("")) {
			listError.add("chưa số điện thoại");
		}
		if (dateInString.equals("")) {
			listError.add("chưa ngày sinh");
		}
		if (dateInString1.equals("")) {
			listError.add("chưa nhập ngày vào làm");
		}
		return listError;

	}

	public Integer insertUser(NhanVienDTO nv) {
		String flag = Collector.postMess("/nhanvien", nv);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

	@RequestMapping(value = "form", params = "Insert", method = RequestMethod.POST)
	public <E> String addUser(HttpServletRequest request, ModelMap model, @ModelAttribute("nv") NhanVienDTO nv) {

		String dateInString = request.getParameter("ngaysinh");
		Date ngaysinh;
		try {
			ngaysinh = DateUtils.parseDate(dateInString, new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgaySinh(ngaysinh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String dateInString1 = request.getParameter("ngayvaolam");
		Date ngayvaolam;
		try {
			ngayvaolam = DateUtils.parseDate(dateInString1, new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgayVaoLam(ngayvaolam);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> listError = checkInfo(nv, dateInString, dateInString1);
		nv.getLuong();
		nv.getHoTen().trim();
		nv.getCmnd().trim();
		nv.getDiaChi().trim();
		nv.getSdt().trim();

		nv.setDaNghi(false);

		Integer temp = this.insertUser(nv);
		if (temp != 0) {

			model.addAttribute("message", "Thêm Thành Công");
			nv.setHoTen(null);
			nv.setGioiTinh(true);
			nv.setLuong(0);
			nv.setSdt(null);
			nv.setCmnd(null);
			nv.setDiaChi(null);

		} else {
			model.addAttribute("message", "Thêm thất bại! " + listError);

		}
		return "admin/QLNV";
		// "redirect:/admin-home/index.htm"
	}

	long temp = 0;

	@RequestMapping(value = "form", params = "linkEdit")
	public String editNV_showform(HttpServletRequest request, ModelMap model) {
		String id1 = request.getParameter("id");
		long maNV = Long.parseLong(id1);
		/*
		 * List<NhanVienEntity> nhanvien = this.getNhanVien();
		 * model.addAttribute("pagedListHolder", nhanvien);
		 */
		temp = maNV;
		NhanVienDTO NV = this.getNV(maNV);

		Date ngaysinh = NV.getNgaySinh();
		Date ngayvaolam = NV.getNgayVaoLam();
		/*
		 * String ngaysinh1 = ngaysinh.toString(); String ngayvaolam1 =
		 * ngayvaolam.toString();
		 */

		model.addAttribute("ngaysinh", ngaysinh);
		model.addAttribute("ngayvaolam", ngayvaolam);

		model.addAttribute("nv", NV);
		model.addAttribute("btnupdate", "true");
		return "admin/form/inputNV";
	}

	public NhanVienDTO getNV(long id) {
		List<NhanVienDTO> list = null;
		try {
			list = Collector.getListAll("/nhanvien", NhanVienDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NhanVienDTO ss = new NhanVienDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaNV() == id)
				ss = list.get(i);
		}

		return ss;
	}

	@RequestMapping(value = "form", params = "btnupdate", method = RequestMethod.POST)
	public <E> String editNV(HttpServletRequest requets, ModelMap model, @ModelAttribute("nv") NhanVienDTO nv) {
		String dateInString = requets.getParameter("ngaysinh");
		Date ngaysinh;
		try {
			ngaysinh = DateUtils.parseDate(dateInString, new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgaySinh(ngaysinh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String dateInString1 = requets.getParameter("ngayvaolam");
		Date ngayvaolam;
		try {
			ngayvaolam = DateUtils.parseDate(dateInString1, new String[] { "yyyy-MM-dd", "dd/MM-yyyy" });
			nv.setNgayVaoLam(ngayvaolam);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NhanVienDTO NV = this.getNV(temp);
		List<String> listError = checkInfo(nv, dateInString, dateInString1);
		nv.getLuong();
		nv.getHoTen().trim();
		nv.getCmnd().trim();
		nv.getDiaChi().trim();
		nv.getSdt().trim();
		nv.setNgaySinh(NV.getNgaySinh());
		nv.setNgayVaoLam(NV.getNgayVaoLam());
		Integer temp = this.updateNV(nv);
		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật không thành công! " + listError);

		}

		return "admin/QLNV";
	}

	public Integer updateNV(NhanVienDTO nv) {
		String flag = Collector.putMess("/nhanvien", nv);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

	public List<UserDTO> getTaiKhoans() {
		List<UserDTO> list = null;
		try {
			list = Collector.getListAll("/user", UserDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Boolean checkAdmin(long MaNV) {
		List<UserDTO> list = getTaiKhoans();
		int n = list.size();
		for(int i=0;i<n;i++) {
			if(list.get(i).getID() == MaNV)return false;
		}
		
		return true;
	}
	
	@RequestMapping(value = "index", params = "linkDelete", method = RequestMethod.GET)
	public <E> String deleteNV(HttpServletRequest request, ModelMap model) {
		String id1 = request.getParameter("id");
		long maNV = Long.parseLong(id1);
		NhanVienDTO tmp = this.getNV(maNV);

		Integer temp = 1;
		Boolean checkAdmin = checkAdmin(maNV);
		String error = "!!!";
		if (!checkAdmin) {
			error = ", nhân viên đã có tài khoản, xóa tài khoản trước";
		} else {
			tmp.setDaNghi(true);
			
			temp = this.updateNV(tmp);

		}

		if (temp == 0) {
			model.addAttribute("message", "Xóa không thành công" + error);
		} else {
			model.addAttribute("message", "Xóa thành công");
		}

		return "admin/QLNV";

	}
	
	public int delete_TK(NhanVienDTO nv) {
		String flag = Collector.delMess("/nhanvien", nv);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
}
