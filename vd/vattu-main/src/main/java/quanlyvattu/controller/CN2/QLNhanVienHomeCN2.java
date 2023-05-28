package quanlyvattu.controller.CN2;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.entity.NhanVienEntity;
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN2.ChiNhanhRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;

@Controller
@RequestMapping(value = "quanlynhanvien/cn2")
public class QLNhanVienHomeCN2 {
	@Autowired
	NhanVienRepositoryCN2 nvrepo;
	@Autowired
	ChiNhanhRepositoryCN2 cnrepo;
	@Autowired
	ServletContext session;

//=======================================CHINHANH=========================================//
	@RequestMapping(value = { "chinhanh"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");

		model.addAttribute("nvs", nvrepo.findAllNV());
		return "chinhanh2/qlnhanvien";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.GET)
	public String addDDHCN(ModelMap model) {
		model.addAttribute("nv", new NhanVienEntity());

		UserModel user = (UserModel) session.getAttribute("USERMODEL");
		model.addAttribute("chiNhanhHT", cnrepo.findOne(user.getChiNhanh()));
		return "chinhanh2/form/add-nhanvien";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.POST)
	public String addNVCN1(ModelMap model, @ModelAttribute("nv") NhanVienEntity nv, HttpServletRequest request) {
		model.addAttribute("nv", new NhanVienEntity());
		UserModel user = (UserModel) session.getAttribute("USERMODEL");
		Integer idMoi = null;
		idMoi = nvrepo.TimMaNV();
		System.out.print(idMoi);
		nv.setMaNV(idMoi);
		try {
			nv.setNgaySinh(Timestamp.valueOf(request.getParameter("tg").replace("T", " ") + ":00"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		nv.setChiNhanh(cnrepo.findOne(user.getMaChiNhanh()));
		NhanVienEntity nvsave = null;

		try {
			nvsave = nvrepo.save(nv);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "thêm nhân viên thất bại");
			System.out.print("thêm nhân viên thất bại");
		}
		if (nvsave != null) {
			model.addAttribute("message", "thêm nhân viên thành công");
			System.out.print("thêm nhân viên thành công");
		}

		return "redirect:/quanlynhanvien/cn2/chinhanh/add.htm";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.GET)
	public String editNVCN1(ModelMap model, HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));

		NhanVienEntity nv = nvrepo.findOne(id);
		if (nv != null) {
			model.addAttribute("nv", nv);
			System.out.print("tồn tại nv");
		}

		else {
			System.out.print("không tồn tại nv");
			return "redirect:quanlynhanvien/cn2/chinhanh.htm";
		}

		return "chinhanh2/form/edit-nhanvien";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.POST)
	public String editNVCN1(ModelMap model, @ModelAttribute("nv") NhanVienEntity nv, HttpServletRequest request) {

		NhanVienEntity nvsave = nvrepo.findOne(nv.getMaNV());

		try {
			nv.setNgaySinh(Timestamp.valueOf(request.getParameter("tg").replace("T", " ") + ":00"));
		} catch (Exception e) {
		}

		try {
			if (nv.getNgaySinh() != null)
				nvsave.setNgaySinh(nv.getNgaySinh());
			nvsave.setDiaChi(nv.getDiaChi());
			nvsave.setHo(nv.getHo());
			nvsave.setTen(nv.getTen());
			nvsave.setLuong(nv.getLuong());
			nvrepo.save(nvsave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nvsave.getHo());

		if (nvsave != null) {
			model.addAttribute("message", "sửa nhân viên thành công");
			System.out.print("sửa nhân viên thành công");
		}

		return "redirect:/quanlynhanvien/cn2/chinhanh.htm";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.GET)
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "chinhanh2/form/xoa-nhanvien";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.POST)
	public String xoaNVCN1P(ModelMap model, HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
				NhanVienEntity nvsave = nvrepo.findOne(id);
				nvsave.setTrangThaiXoa(1);
				System.out.print(nvsave.getHo());
				System.out.print(nvsave.getTrangThaiXoa());
				nvrepo.save(nvsave);
				model.addAttribute("message", "xoá nhân viên thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá nhân viên thất bại");
		}
		return "redirect:/quanlynhanvien/cn2/chinhanh.htm";

	}

	// =======================================CONGTY=========================================//
	@RequestMapping(value = { "congty" }, method = RequestMethod.GET)
	public String getNVCTY(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");

		model.addAttribute("nvs", nvrepo.findAllNV());
		return "congty2/qlnhanvien";
	}

	

	

	// =======================================USER=========================================//
	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public String getNVU(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");

		model.addAttribute("nvs", nvrepo.findAllNV());
		return "user2/qlnhanvien";
	}

	
	
}
