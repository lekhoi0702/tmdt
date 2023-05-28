package quanlyvattu.controller.CN2;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.entity.ChiNhanhEntity;
import quanlyvattu.entity.KhoEntity;
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN2.ChiNhanhRepositoryCN2;
import quanlyvattu.repositoryCN2.KhoRepositoryCN2;

@Controller
@RequestMapping(value = "quanlykho/cn2")
public class QLKhoControllerCN2 {
	@Autowired
	KhoRepositoryCN2 krepo;

	@Autowired
	ChiNhanhRepositoryCN2 cnrepo;
	@Autowired
	ServletContext session;
//==================================CHINHANH=============================//

	@RequestMapping(value = { "chinhanh"}, method = RequestMethod.GET)
	public String getKCN(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "maKho");

		model.addAttribute("ks", krepo.findAll(sort));
		return "chinhanh2/qlkho";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.GET)
	public String addDDHCN(ModelMap model) {
		model.addAttribute("k", new KhoEntity());

		return "chinhanh2/form/add-kho";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.POST)
	public String addKCN1(ModelMap model, @ModelAttribute("k") KhoEntity k, HttpServletRequest request) {
		UserModel user = (UserModel) session.getAttribute("USERMODEL");
		System.out.print(user.getChiNhanh());
		boolean ckk = false;
		try {
			ckk = (krepo.ckK(k.getMaKho()) == 0);
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (ckk) {

			KhoEntity nvsave = null;
			ChiNhanhEntity cn = cnrepo.findAll().get(0);
			k.setChiNhanh(cn);

			try {
				nvsave = krepo.save(k);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "thêm kho thất bại");
				System.out.print("thêm kho thất bại");
			}
			if (nvsave != null) {
				model.addAttribute("message", "thêm kho thành công");
				System.out.print("thêm kho thành công");
			}
		} else {
			model.addAttribute("message", "thêm kho thất bại, mã kho đã tồn tại");
			System.out.print("thêm kho thất bại");
		}

		return "redirect:/quanlykho/cn2/chinhanh/add.htm";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.GET)
	public String editVTCN1(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");

		KhoEntity k = krepo.findOne(id);
		if (k != null) {
			model.addAttribute("k", k);
			System.out.print("tồn tại k");
		}

		else {
			System.out.print("không tồn tại k");
			return "redirect:quanlykho/cn2/chinhanh.htm";
		}

		return "chinhanh2/form/edit-kho";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.POST)
	public String editVTCN1(ModelMap model, @ModelAttribute("k") KhoEntity k, HttpServletRequest request) {

		KhoEntity ksave = krepo.findOne(k.getMaKho());

		System.out.println(ksave.getTenKho());

		try {
			ksave.setTenKho(k.getTenKho());

			ksave.setDiaChi(k.getDiaChi());
			krepo.save(ksave);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ksave != null) {

			model.addAttribute("message", "sửa kho thành công");
			System.out.print("sửa kho thành công");
		}

		return "redirect:/quanlykho/cn2/chinhanh.htm";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.GET)
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "chinhanh2/form/xoa-kho";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.POST)
	public String xoaKCN1P(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				krepo.delete(id);

				model.addAttribute("message", "xoá kho thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá kho thất bại");
		}
		return "redirect:/quanlykho/cn2/chinhanh.htm";

	}
	// ==================================CONGTY=============================//

	@RequestMapping(value = { "congty" }, method = RequestMethod.GET)
	public String getKCTY(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "maKho");

		model.addAttribute("ks", krepo.findAll(sort));
		return "congty2/qlkho";
	}

	
	// ==================================USER=============================//

	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public String getKU(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "maKho");

		model.addAttribute("ks", krepo.findAll(sort));
		return "user2/qlkho";
	}

	
}
