package quanlyvattu.controller.CN1;

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
import quanlyvattu.entity.VatTuEntity;
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN1.VatTuRepositoryCN1;

@Controller
@RequestMapping(value = "quanlyvattu/cn1")
public class QLVatTuController {
	@Autowired
	VatTuRepositoryCN1 vtrepo;

	@Autowired
	ServletContext session;
//===================================CHINHANH==================================//
	
	
	@RequestMapping(value = {"chinhanh"}, method = RequestMethod.GET)
	public String getVTCN(ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 */ model.addAttribute("vts", vtrepo.findAll());
		return "chinhanh/qlvattu";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.GET)
	public String addVTCN(ModelMap model) {
		VatTuEntity vt = new VatTuEntity();
		vt.setDonViTinh("kg");
		model.addAttribute("vt", vt);

		return "chinhanh/form/add-vattu";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.POST)
	public String addVTCN1(ModelMap model, @ModelAttribute("vt") VatTuEntity vt, HttpServletRequest request) {

		System.out.print(vt.getMaVT());

		System.out.print(vt.getDonViTinh());
		System.out.print(vt.getSoLuongTon());
		System.out.print(vt.getTenVT());
		if (vtrepo.findOne(vt.getMaVT()) == null) {
			VatTuEntity nvsave = null;

			try {
				nvsave = vtrepo.save(vt);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "thêm vật tư thất bại");
				System.out.print("thêm vật tư thất bại");
			}
			if (nvsave != null) {
				model.addAttribute("message", "thêm vật tư thành công");
				System.out.print("thêm vật tư thành công");
			}
		} else {
			model.addAttribute("message", "thêm vật tư thất bại, mã vật tư đã tồn tại");
			System.out.print("thêm vật tư thất bại");
		}

		return "redirect:/quanlyvattu/cn1/chinhanh/add.htm";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.GET)
	public String editVTCN1(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");

		VatTuEntity vt = vtrepo.findOne(id);
		if (vt != null) {
			model.addAttribute("vt", vt);
			System.out.print("tồn tại vt");
		}

		else {
			System.out.print("không tồn tại vt");
			return "redirect:quanlyvattu/cn1/chinhanh.htm";
		}

		return "chinhanh/form/edit-vattu";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.POST)
	public String editVTCN1(ModelMap model, @ModelAttribute("vt") VatTuEntity vt, HttpServletRequest request) {

		VatTuEntity vtsave = vtrepo.findOne(vt.getMaVT());

		System.out.println(vtsave.getTenVT());

		try {
			vtsave.setDonViTinh(vt.getDonViTinh());
			vtsave.setTenVT(vt.getTenVT());
			vtsave.setSoLuongTon(vt.getSoLuongTon());
			vtrepo.save(vtsave);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (vtsave != null) {

			model.addAttribute("message", "sửa Vật tư thành công");
			System.out.print("sửa Vật tư thành công");
		}

		return "redirect:/quanlyvattu/cn1/chinhanh.htm";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.GET)
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "chinhanh/form/xoa-vattu";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.POST)
	public String xoaNVCN1P(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				vtrepo.delete(id);
				
				model.addAttribute("message", "xoá vật tư thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá vật tư thất bại");
		}
		return "redirect:/quanlyvattu/cn1/chinhanh.htm";

	}

	//===================================CONGTY==================================//
	
	
		@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
		public String getVTCTY(ModelMap model) {
			/*
			 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
			 */ model.addAttribute("vts", vtrepo.findAll());
			return "congty/qlvattu";
		}

		
		//===================================USER==================================//
		
		
		@RequestMapping(value = {"user"}, method = RequestMethod.GET)
		public String getVTU(ModelMap model) {
			/*
			 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
			 */ model.addAttribute("vts", vtrepo.findAll());
			return "user/qlvattu";
		}

		
}
