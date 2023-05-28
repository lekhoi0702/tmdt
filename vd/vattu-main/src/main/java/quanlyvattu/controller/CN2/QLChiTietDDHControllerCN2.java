package quanlyvattu.controller.CN2;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.dao.impl.AbstractDAO;
import quanlyvattu.entity.CTDDHEntity;
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN2.ChiTietDDHRepositoryCN2;
import quanlyvattu.repositoryCN2.DatHangRepositoryCN2;
import quanlyvattu.repositoryCN2.VatTuRepositoryCN2;
import quanlyvattu.statics.InfoConnection;

@Controller
@RequestMapping(value = "quanlychitietdondathang/cn2")
public class QLChiTietDDHControllerCN2 {
	@Autowired
	ChiTietDDHRepositoryCN2 ctdhrepo;

	@Autowired
	DatHangRepositoryCN2 dhrepo;

	@Autowired
	VatTuRepositoryCN2 vtrepo;

	@Autowired
	ServletContext session;

	AbstractDAO dao = new AbstractDAO();

	String idddh = "n/a";
//===============================CHINHANH=================================//
	
	
	
	@RequestMapping(value = {"chinhanh"}, method = RequestMethod.GET)
	public String getDDHCN(ModelMap model, HttpServletRequest request) {
		System.out.print("khong co ma dh");
		idddh = (String) request.getParameter("idddh");
		if (idddh != "" && idddh != null) {

			List<CTDDHEntity> dhs = (List<CTDDHEntity>) ctdhrepo.findByMaSoDDH(idddh);
			model.addAttribute("ddhs", dhs);

			return "chinhanh2/qlCTDDH";
		} else {
			System.out.print("khong co ma DDH");

			model.addAttribute("ddhs", ctdhrepo.findAll());
			return "chinhanh2/qlCTDDH";
		}

	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.GET)
	public String addDDHCN(ModelMap model) {
		model.addAttribute("ct", new CTDDHEntity());
		model.addAttribute("vattus", vtrepo.findAll());
		return "chinhanh2/form/add-CTDDH";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.POST)
	public String addchitietDDHCN(ModelMap model, @ModelAttribute("ct") CTDDHEntity ct, HttpServletRequest request) {

		CTDDHEntity nvsave = null;
		UserModel user = (UserModel) session.getAttribute("USERMODEL");

		try {
			ct.setDatHang(dhrepo.findOne(idddh));
			ct.setVatTu(vtrepo.findOne(request.getParameter("maVT")));

			System.out.println(InfoConnection.getUrlPM());
//			nvsave = ctdhrepo.save(ct);

			String sql = "INSERT INTO [dbo].[CTDDH]\n" + "           ([MasoDDH]\n" + "           ,[MAVT]\n"
					+ "           ,[SOLUONG]\n" + "           ,[DONGIA]\n)" +

					"     VALUES (?,?,?,?)";
			System.out.println(sql);
			System.out.println(ct.getDatHang().getMaSoDDH());
			System.out.println(ct.getVatTu().getMaVT());
			System.out.println(ct.getSoLuong());
			System.out.println(ct.getDonGia());
			dao.insertPM(sql, ct.getDatHang().getMaSoDDH(), ct.getVatTu().getMaVT(), ct.getSoLuong(), ct.getDonGia());
			model.addAttribute("message", "thêm chi tiết thành công");
			System.out.print("thêm chi tiết thành công");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "thêm chi tiết thất bại, loại vật tư đã tồn tại");
			System.out.print("thêm đơn hàng thất bại");

		}

		return "redirect:/quanlychitietdondathang/cn2/chinhanh.htm?idddh=" + idddh;
	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.GET)
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("vt", request.getParameter("vt"));
		System.out.println(request.getParameter("vt"));
		return "chinhanh2/form/xoa-CTDDH";

	}
	
	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.POST)
	public String xoaKCN1P(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		String vt = request.getParameter("vt");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
			
				String sql = "DELETE FROM [dbo].[CTDDH]\n" + 
						"      WHERE MasoDDH=? and MAVT=?\n";
				System.out.println(id);
				System.out.println(id);
				System.out.println(vt);
				System.out.println(InfoConnection.getUrlPM());
				dao.updatePM(sql, id,vt);
				model.addAttribute("message", "xoá chi tiết đơn hàng thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá ct đơn hàng thất bại");
		}
		return "redirect:/quanlychitietdondathang/cn2/chinhanh.htm?idddh=" + idddh;

	}
//=========================================CONGTY====================================//
	
	@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
	public String getDDHCNcty(ModelMap model, HttpServletRequest request) {
		System.out.print("khong co ma dh");
		idddh = (String) request.getParameter("idddh");
		if (idddh != "" && idddh != null) {

			List<CTDDHEntity> dhs = (List<CTDDHEntity>) ctdhrepo.findByMaSoDDH(idddh);
			model.addAttribute("ddhs", dhs);

			return "congty2/qlCTDDH";
		} else {
			System.out.print("khong co ma DDH");

			model.addAttribute("ddhs", ctdhrepo.findAll());
			return "congty2/qlCTDDH";
		}

	}

//================================USER=====================================//

	

	
	
	@RequestMapping(value = {"user"}, method = RequestMethod.GET)
	public String getDDHUSER(ModelMap model, HttpServletRequest request) {
		System.out.print("khong co ma dh");
		idddh = (String) request.getParameter("idddh");
		if (idddh != "" && idddh != null) {

			List<CTDDHEntity> dhs = (List<CTDDHEntity>) ctdhrepo.findByMaSoDDH(idddh);
			model.addAttribute("ddhs", dhs);

			return "user2/qlCTDDH";
		} else {
			System.out.print("khong co ma DDH");

			model.addAttribute("ddhs", ctdhrepo.findAll());
			return "user2/qlCTDDH";
		}

	}

	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public String addDDHUSER(ModelMap model) {
		model.addAttribute("ct", new CTDDHEntity());
		model.addAttribute("vattus", vtrepo.findAll());
		return "user2/form/add-CTDDH";
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public String addchitietDDHUSER(ModelMap model, @ModelAttribute("ct") CTDDHEntity ct, HttpServletRequest request) {

		CTDDHEntity nvsave = null;
		UserModel user = (UserModel) session.getAttribute("USERMODEL");

		try {
			ct.setDatHang(dhrepo.findOne(idddh));
			ct.setVatTu(vtrepo.findOne(request.getParameter("maVT")));

			System.out.println(InfoConnection.getUrlPM());
//			nvsave = ctdhrepo.save(ct);

			String sql = "INSERT INTO [dbo].[CTDDH]\n" + "           ([MasoDDH]\n" + "           ,[MAVT]\n"
					+ "           ,[SOLUONG]\n" + "           ,[DONGIA]\n)" +

					"     VALUES (?,?,?,?)";
			System.out.println(sql);
			System.out.println(ct.getDatHang().getMaSoDDH());
			System.out.println(ct.getVatTu().getMaVT());
			System.out.println(ct.getSoLuong());
			System.out.println(ct.getDonGia());
			dao.insertPM(sql, ct.getDatHang().getMaSoDDH(), ct.getVatTu().getMaVT(), ct.getSoLuong(), ct.getDonGia());
			model.addAttribute("message", "thêm chi tiết thành công");
			System.out.print("thêm chi tiết thành công");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "thêm chi tiết thất bại, loại vật tư đã tồn tại");
			System.out.print("thêm đơn hàng thất bại");

		}

		return "redirect:/quanlychitietdondathang/cn2/user.htm?idddh=" + idddh;
	}

	@RequestMapping(value = "user/xoa", method = RequestMethod.GET)
	public String xoaNVUSER(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("vt", request.getParameter("vt"));
		System.out.println(request.getParameter("vt"));
		return "user2/form/xoa-CTDDH";

	}
	
	@RequestMapping(value = "user/xoa", method = RequestMethod.POST)
	public String xoaKCUSER(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		String vt = request.getParameter("vt");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
			
				String sql = "DELETE FROM [dbo].[CTDDH]\n" + 
						"      WHERE MasoDDH=? and MAVT=?\n";
				System.out.println(id);
				System.out.println(id);
				System.out.println(vt);
				System.out.println(InfoConnection.getUrlPM());
				dao.updatePM(sql, id,vt);
				model.addAttribute("message", "xoá chi tiết đơn hàng thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá ct đơn hàng thất bại");
		}
		return "redirect:/quanlychitietdondathang/cn2/users.htm?idddh= + idddh";

	}
}
