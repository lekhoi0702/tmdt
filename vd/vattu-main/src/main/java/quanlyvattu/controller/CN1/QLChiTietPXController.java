package quanlyvattu.controller.CN1;

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
import quanlyvattu.entity.CTPXEntity;
import quanlyvattu.entity.VatTuEntity;
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN1.ChiTietPNRepositoryCN1;
import quanlyvattu.repositoryCN1.ChiTietPXRepositoryCN1;
import quanlyvattu.repositoryCN1.PhieuXuatRepositoryCN1;
import quanlyvattu.repositoryCN1.VatTuRepositoryCN1;
import quanlyvattu.statics.InfoConnection;

@Controller
@RequestMapping(value = "quanlychitietphieuxuat/cn1")
public class QLChiTietPXController {
	@Autowired
	ChiTietPXRepositoryCN1 ctpxrepo;
	
	@Autowired
	ChiTietPNRepositoryCN1 ctpnrepo;

	@Autowired
	ServletContext session;

	AbstractDAO dao = new AbstractDAO();

	String idddh = "n/a";

	@Autowired
	VatTuRepositoryCN1 vtrepo;

	PhieuXuatRepositoryCN1 pxrepo;

	
//================================CHINHANH=====================================//
	@RequestMapping(value = {"chinhanh"}, method = RequestMethod.GET)
	public String getDDHCN(ModelMap model, HttpServletRequest request) {
		idddh = (String) request.getParameter("idpx");
		if (idddh != "" && idddh != null) {

			List<CTPXEntity> dhs = (List<CTPXEntity>) ctpxrepo.findByMaSoPX(idddh);
			model.addAttribute("ddhs", dhs);

			return "chinhanh/qlCTPX";
		} else {
			System.out.print("khong co ma kho");

			model.addAttribute("ddhs", ctpxrepo.findAll());
			return "chinhanh/qlCTPX";
		}

	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.GET)
	public String addDDHCN(ModelMap model) {
		model.addAttribute("ct", new CTPXEntity());
		model.addAttribute("vattus", vtrepo.findAll());
		return "chinhanh/form/add-CTPX";
	}

	@RequestMapping(value = "chinhanh/add", method = RequestMethod.POST)
	public String addchitietDDHCN(ModelMap model, @ModelAttribute("ct") CTPXEntity ct, HttpServletRequest request) {
		String vt = request.getParameter("maVT");
		CTPXEntity nvsave = null;
		UserModel user = (UserModel) session.getAttribute("USERMODEL");
		VatTuEntity vattu = vtrepo.findOne(vt);
		if(vattu.getSoLuongTon()-ct.getSoLuong()<0)
		{
			model.addAttribute("message","không đủ vật tư");
			return "redirect:/quanlychitietphieuxuat/cn1/chinhanh.htm?idpx=" + idddh;
		}
		else {

			
			try {
				System.out.println(idddh);

				

				System.out.println(InfoConnection.getUrlPM());
//				nvsave = ctdhrepo.save(ct);

				String sql = "INSERT INTO [dbo].[CTPX]\n" + "           ([MaPX]\n" + "           ,[MAVT]\n"
						+ "           ,[SOLUONG]\n" + "           ,[DONGIA]\n)" +

						"     VALUES (?,?,?,?)";
				System.out.println(sql);

				System.out.println(ct.getSoLuong());
				System.out.println(ct.getDonGia());
				dao.insertPM(sql, idddh, vt, ct.getSoLuong(), ct.getDonGia());
				model.addAttribute("message", "thêm chi tiết thành công");
				System.out.print("thêm chi tiết thành công");
				ctpxrepo.cnvt(vt,ct.getSoLuong());
			} catch (Exception e) {
				e.printStackTrace();
				

			}
			

			return "redirect:/quanlychitietphieuxuat/cn1/chinhanh.htm?idpx=" + idddh;	
		}
		
	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.GET)
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("vt", request.getParameter("vt"));
		System.out.println(request.getParameter("vt"));
		return "chinhanh/form/xoa-CTPX";

	}

	@RequestMapping(value = "chinhanh/xoa", method = RequestMethod.POST)
	public String xoaKCN1P(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		String vt = request.getParameter("vt");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				
				
				String sql = "DELETE FROM [dbo].[CTPX]\n" + "      WHERE MaPX=? and MAVT=?\n";
				System.out.println(id);
				System.out.println(id);
				System.out.println(vt);
				System.out.println(InfoConnection.getUrlPM());
				dao.updatePM(sql, id, vt);
				model.addAttribute("message", "xoá chi tiết PX thành công");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá ct phiếu xuất thất bại");
		}
		return "redirect:/quanlychitietphieuxuat/cn1/chinhanh.htm?idpx="+id;

	}
	//================================CONGTY=====================================//
		@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
		public String getDDHCTY(ModelMap model, HttpServletRequest request) {
			idddh = (String) request.getParameter("idpx");
			if (idddh != "" && idddh != null) {

				List<CTPXEntity> dhs = (List<CTPXEntity>) ctpxrepo.findByMaSoPX(idddh);
				model.addAttribute("ddhs", dhs);

				return "congty/qlCTPX";
			} else {
				System.out.print("khong co ma kho");

				model.addAttribute("ddhs", ctpxrepo.findAll());
				return "congty/qlCTPX";
			}

		}

		
		
		//================================USER=====================================//
		@RequestMapping(value = {"user"}, method = RequestMethod.GET)
		public String getDDHU(ModelMap model, HttpServletRequest request) {
			idddh = (String) request.getParameter("idpx");
			if (idddh != "" && idddh != null) {

				List<CTPXEntity> dhs = (List<CTPXEntity>) ctpxrepo.findByMaSoPX(idddh);
				model.addAttribute("ddhs", dhs);

				return "user/qlCTPX";
			} else {
				System.out.print("khong co ma kho");

				model.addAttribute("ddhs", ctpxrepo.findAll());
				return "user/qlCTPX";
			}

		}

		@RequestMapping(value = "user/add", method = RequestMethod.GET)
		public String addDDHU(ModelMap model) {
			model.addAttribute("ct", new CTPXEntity());
			model.addAttribute("vattus", vtrepo.findAll());
			return "user/form/add-CTPX";
		}

		@RequestMapping(value = "user/add", method = RequestMethod.POST)
		public String addchitietDDHU(ModelMap model, @ModelAttribute("ct") CTPXEntity ct, HttpServletRequest request) {
			String vt = request.getParameter("maVT");
			CTPXEntity nvsave = null;
			UserModel user = (UserModel) session.getAttribute("USERMODEL");
			VatTuEntity vattu = vtrepo.findOne(vt);
			if(vattu.getSoLuongTon()-ct.getSoLuong()<0)
			{
				model.addAttribute("message","không đủ vật tư");
				return "redirect:/quanlychitietphieuxuat/cn1/user.htm?idpx=" + idddh;
			}
			else {

				
				try {
					System.out.println(idddh);

					

					System.out.println(InfoConnection.getUrlPM());
//					nvsave = ctdhrepo.save(ct);

					String sql = "INSERT INTO [dbo].[CTPX]\n" + "           ([MaPX]\n" + "           ,[MAVT]\n"
							+ "           ,[SOLUONG]\n" + "           ,[DONGIA]\n)" +

							"     VALUES (?,?,?,?)";
					System.out.println(sql);

					System.out.println(ct.getSoLuong());
					System.out.println(ct.getDonGia());
					dao.insertPM(sql, idddh, vt, ct.getSoLuong(), ct.getDonGia());
					model.addAttribute("message", "thêm chi tiết thành công");
					System.out.print("thêm chi tiết thành công");
					ctpxrepo.cnvt(vt,ct.getSoLuong());
				} catch (Exception e) {
					e.printStackTrace();
					

				}
				

				return "redirect:/quanlychitietphieuxuat/cn1/user.htm?idpx=" + idddh;	
			}
			
		}

		@RequestMapping(value = "user/xoa", method = RequestMethod.GET)
		public String xoaNVU(ModelMap model, HttpServletRequest request) {

			model.addAttribute("id", request.getParameter("id"));
			model.addAttribute("vt", request.getParameter("vt"));
			System.out.println(request.getParameter("vt"));
			return "user/form/xoa-CTPX";

		}

		@RequestMapping(value = "user/xoa", method = RequestMethod.POST)
		public String xoaKU(ModelMap model, HttpServletRequest request) {

			String id = request.getParameter("id");
			String vt = request.getParameter("vt");
			System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
			try {
				if (request.getParameter("xacNhan").equals("YES")) {
//					VatTuEntity nvsave = vtrepo.findOne(id);
					
					
					String sql = "DELETE FROM [dbo].[CTPX]\n" + "      WHERE MaPX=? and MAVT=?\n";
					System.out.println(id);
					System.out.println(id);
					System.out.println(vt);
					System.out.println(InfoConnection.getUrlPM());
					dao.updatePM(sql, id, vt);
					model.addAttribute("message", "xoá chi tiết PX thành công");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "xoá ct phiếu xuất thất bại");
			}
			return "redirect:/quanlychitietphieuxuat/cn1/user.htm?idpx="+id;

		}
}
