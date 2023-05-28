package quanlyvattu.controller.CN2;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.entity.PhieuXuatEntity;
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN2.KhoRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;
import quanlyvattu.repositoryCN2.PhieuXuatRepositoryCN2;

@Controller
@RequestMapping(value = "quanlyphieuxuat/cn2")
public class QLPhieuXuatControllerCN2 {

	@Autowired
	PhieuXuatRepositoryCN2 pxrepo;
	@Autowired
	NhanVienRepositoryCN2 nvrepo;

	@Autowired
	KhoRepositoryCN2 krepo;

	@Autowired
	ServletContext session;
	private String idKho = "N/A";
//======================================CHINHANH=====================================//

	@RequestMapping(value = { "chinhanh" }, method = RequestMethod.GET)
	public String getPXCN(ModelMap model, HttpServletRequest request) {
		idKho = (String) request.getParameter("idkho");
		if (idKho != "" && idKho != null) {
			Sort sort = new Sort(Sort.Direction.DESC, "maPX");
			List<PhieuXuatEntity> pxs = (List<PhieuXuatEntity>) pxrepo.findByKho(idKho);
			model.addAttribute("ddhs", pxs);
			/* for (PhieuXuatEntity dh:pxs) System.out.print(dh.pxrepo()); */
			return "chinhanh2/qlphieuxuat";
		} else {
			System.out.print("khong co ma kho");
			Sort sort = new Sort(Sort.Direction.DESC, "maPX");
			model.addAttribute("ddhs", pxrepo.findAll(sort));
			return "chinhanh2/qlphieuxuat";
		}

	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.GET)
	public String addDDHCN(ModelMap model) {

		model.addAttribute("px", new PhieuXuatEntity());
		model.addAttribute("idkho", idKho);

		return "chinhanh2/form/add-phieuxuat";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.POST)
	public String addDHCN1(ModelMap model, @ModelAttribute("px") PhieuXuatEntity dh, HttpServletRequest request) {

		boolean ckpx = false;
		try {
			ckpx = (pxrepo.ckPX(dh.getMaPX()) == 0);
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (ckpx) {
			PhieuXuatEntity nvsave = null;
			UserModel user = (UserModel) session.getAttribute("USERMODEL");

			try {
				dh.setKho(krepo.findOne(idKho));
				dh.setNgay(new Date());
				dh.setNhanVien(nvrepo.findOne(user.getMaNV()));

				nvsave = pxrepo.save(dh);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "thêm PHIẾU XUẤT thất bại");
				System.out.print("thêm đơn hàng thất bại");
			}
			if (nvsave != null) {
				model.addAttribute("message", "thêm PHIẾU XUẤT thành công");
				System.out.print("thêm đơn hàng thành công");
			}
		} else {
			model.addAttribute("message", "thêm PHIẾU XUẤT thất bại, mã PHIẾU XUẤT đã tồn tại");
			System.out.print("thêm đơn hàng thất bại");
			System.out.println("Ma px da ton tai");
		}

		return "redirect:/quanlyphieuxuat/cn2/chinhanh/add.htm";
	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.GET)
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "chinhanh2/form/xoa-phieuxuat";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.POST)
	public String xoaKCN1P(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				pxrepo.delete(id);

				model.addAttribute("message", "xoá phiếu xuất thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá phiếu xuất thất bại, chỉ có thể xoá phiếu xuất trống");
		}
		return "redirect:/quanlyphieuxuat/cn2/chinhanh.htm";

	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.GET)
	public String editVTCN1(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");

		PhieuXuatEntity k = pxrepo.findOne(id);
		if (k != null) {
			model.addAttribute("dh", k);
			model.addAttribute("idkho", idKho);
			System.out.print("tồn tại px");
		}

		else {
			System.out.print("không tồn tại px");
			return "redirect:quanlyphieuxuat/cn2/chinhanh.htm";
		}

		return "chinhanh2/form/edit-phieuxuat";
	}

	@RequestMapping(value = "chinhanh/edit", method = RequestMethod.POST)
	public String editVTCN1(ModelMap model, @ModelAttribute("k") PhieuXuatEntity k, HttpServletRequest request) {

		UserModel user = (UserModel) session.getAttribute("USERMODEL");
		PhieuXuatEntity ksave2 = null;
		try {
			k.setNgay(new Date());
			k.setKho(krepo.findAll().get(0));

			k.setNhanVien(nvrepo.findOne(user.getMaNV()));
			ksave2 = pxrepo.save(k);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ksave2 != null) {

			model.addAttribute("message", "sửa PHIEU XUAT thành công");
			System.out.print("sửa PHIEU XUAT thành công");
		}

		return "redirect:/quanlyphieuxuat/cn2/chinhanh.htm";

	}

	// ======================================USER=====================================//

	@RequestMapping(value = { "user" }, method = RequestMethod.GET)
	public String getPXU(ModelMap model, HttpServletRequest request) {
		idKho = (String) request.getParameter("idkho");
		if (idKho != "" && idKho != null) {
			Sort sort = new Sort(Sort.Direction.DESC, "maPX");
			List<PhieuXuatEntity> pxs = (List<PhieuXuatEntity>) pxrepo.findByKho(idKho);
			model.addAttribute("ddhs", pxs);
			/* for (PhieuXuatEntity dh:pxs) System.out.print(dh.pxrepo()); */
			return "user2/qlphieuxuat";
		} else {
			System.out.print("khong co ma kho");
			Sort sort = new Sort(Sort.Direction.DESC, "maPX");
			model.addAttribute("ddhs", pxrepo.findAll(sort));
			return "user2/qlphieuxuat";
		}

	}

	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public String addDDHU(ModelMap model) {

		model.addAttribute("px", new PhieuXuatEntity());
		model.addAttribute("idkho", idKho);

		return "user2/form/add-phieuxuat";
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public String addDHU(ModelMap model, @ModelAttribute("px") PhieuXuatEntity dh, HttpServletRequest request) {

		boolean ckpx = false;
		try {
			ckpx = (pxrepo.ckPX(dh.getMaPX()) == 0);
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (ckpx) {
			PhieuXuatEntity nvsave = null;
			UserModel user = (UserModel) session.getAttribute("USERMODEL");

			try {
				dh.setKho(krepo.findOne(idKho));
				dh.setNgay(new Date());
				dh.setNhanVien(nvrepo.findOne(user.getMaNV()));

				nvsave = pxrepo.save(dh);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "thêm PHIẾU XUẤT thất bại");
				System.out.print("thêm đơn hàng thất bại");
			}
			if (nvsave != null) {
				model.addAttribute("message", "thêm PHIẾU XUẤT thành công");
				System.out.print("thêm đơn hàng thành công");
			}
		} else {
			model.addAttribute("message", "thêm PHIẾU XUẤT thất bại, mã PHIẾU XUẤT đã tồn tại");
			System.out.print("thêm đơn hàng thất bại");
			System.out.println("Ma px da ton tai");
		}

		return "redirect:/quanlyphieuxuat/cn2/user/add.htm";
	}

	@RequestMapping(value = "user/xoa", method = RequestMethod.GET)
	public String xoaNVU(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "user2/form/xoa-phieuxuat";

	}

	@RequestMapping(value = "user/xoa", method = RequestMethod.POST)
	public String xoaKU(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//					VatTuEntity nvsave = vtrepo.findOne(id);
				pxrepo.delete(id);

				model.addAttribute("message", "xoá phiếu xuất thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá phiếu xuất thất bại, chỉ có thể xoá phiếu xuất trống");
		}
		return "redirect:/quanlyphieuxuat/cn2/user.htm";

	}

	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public String editVTU(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");

		PhieuXuatEntity k = pxrepo.findOne(id);
		if (k != null) {
			model.addAttribute("dh", k);
			model.addAttribute("idkho", idKho);
			System.out.print("tồn tại px");
		}

		else {
			System.out.print("không tồn tại px");
			return "redirect:quanlyphieuxuat/cn2/user.htm";
		}

		return "user2/form/edit-phieuxuat";
	}

	@RequestMapping(value = "user/edit", method = RequestMethod.POST)
	public String editVTU(ModelMap model, @ModelAttribute("k") PhieuXuatEntity k, HttpServletRequest request) {

		UserModel user = (UserModel) session.getAttribute("USERMODEL");
		PhieuXuatEntity ksave2 = null;
		try {
			k.setNgay(new Date());
			k.setKho(krepo.findAll().get(0));

			k.setNhanVien(nvrepo.findOne(user.getMaNV()));
			ksave2 = pxrepo.save(k);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ksave2 != null) {

			model.addAttribute("message", "sửa PHIEU XUAT thành công");
			System.out.print("sửa PHIEU XUAT thành công");
		}

		return "redirect:/quanlyphieuxuat/cn2/user.htm";

	}

	// ======================================CONGTY=====================================//

	@RequestMapping(value = { "congty" }, method = RequestMethod.GET)
	public String getPXCTY(ModelMap model, HttpServletRequest request) {
		idKho = (String) request.getParameter("idkho");
		if (idKho != "" && idKho != null) {
			Sort sort = new Sort(Sort.Direction.DESC, "maPX");
			List<PhieuXuatEntity> pxs = (List<PhieuXuatEntity>) pxrepo.findByKho(idKho);
			model.addAttribute("ddhs", pxs);
			/* for (PhieuXuatEntity dh:pxs) System.out.print(dh.pxrepo()); */
			return "congty2/qlphieuxuat";
		} else {
			System.out.print("khong co ma kho");
			Sort sort = new Sort(Sort.Direction.DESC, "maPX");
			model.addAttribute("ddhs", pxrepo.findAll(sort));
			return "congty2/qlphieuxuat";
		}

	}

}