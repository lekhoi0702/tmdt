package quanlyvattu.controller.CN2;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.dao.impl.AbstractDAO;
import quanlyvattu.entity.NhanVienEntity;
import quanlyvattu.mapper.HDNVMapper;
import quanlyvattu.model.HDNVModel;
import quanlyvattu.repositoryCN2.DatHangRepositoryCN2;
import quanlyvattu.repositoryCN2.KhoRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;
import quanlyvattu.statics.InfoConnection;

@Controller
@RequestMapping(value = "hoatdongnhanvien/cn2")
public class HoatdongnhanvienControllerCN2 {
	@Autowired
	DatHangRepositoryCN2 dhrepo;

	@Autowired
	KhoRepositoryCN2 krepo;

	@Autowired
	NhanVienRepositoryCN2 nvrepo;

	@Autowired
	ServletContext session;

	AbstractDAO dao = new AbstractDAO();

//==================================CHINHANH=====================================//
	@RequestMapping(value = { "chinhanh/nhap"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model, HttpServletRequest request) {
		model.addAttribute("nv", new NhanVienEntity());

		return "chinhanh2/form/choose-HDNV-nhap";

//		String sql = "EXEC [dbo].[sp_ChiTietSoLuongTriGiaHangHoaNhapXuat] ?,?,?,?";
//		List<CTSLHHXNModel> ctxn=dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql, new CTSLHHXNMapper(),"CHINHANH" "NHAP", );
//		System.out.println(ctxn.get(0).getThangNam());
//		model.addAttribute("ctxn", ctxn);
//		model.addAttribute("nvs", nvrepo.findAllNV());
//		return "chinhanh2/ctsoluongtrigiahanghoanhapxuat";

//		Timestamp tungay= Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
//		Timestamp denngay= Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");

//		String sql = "EXEC [dbo].[sp_ChiTietSoLuongTriGiaHangHoaNhapXuat] ?,?,?,?";
//		@SuppressWarnings("deprecation")
//		List<CTSLHHXNModel> ctxn=dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql, new CTSLHHXNMapper(),"CHINHANH" ,"NHAP",request.getParameter("tungay"),request.getParameter("denngay"));
//		model.addAttribute("ctpns", ctxn;
//		model.addAttribute("nvs", nvrepo.findAllNV());
//		System.out.println(ctxn.get(0).getTenVT());
//		return "chinhanh2/ctsoluongtrigiahanghoanhapxuat";
	}

	@RequestMapping(value = { "chinhanh/nhap"}, method = RequestMethod.POST)
	public String getPNCN1(ModelMap model, HttpServletRequest request) {
		Timestamp tungay = Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
		Timestamp denngay = Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");
		int manv = Integer.parseInt(request.getParameter("manhanvien"));
		String sql = "EXEC	 [dbo].[sp_HoatDongNhanVien]\r\n" + "		@MANV = ?,\r\n" + "		@LOAI = ?,\r\n"
				+ "		@DATEFROM =?,\r\n" + "		@DATETO = ?";
		List<HDNVModel> hdn = dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql,
				new HDNVMapper(), manv, "NHAP", tungay, denngay);
//		System.out.println(ctxn.get(0).getThangNam());
		System.out.println(tungay + " " + denngay);
		System.out.println(hdn.get(0).getTenVT());
		model.addAttribute("hdns", hdn);
		model.addAttribute("nvs", nvrepo.findOne(manv));
//		System.out.println(ctxn.get(0).getTenVT());
		return "chinhanh2/bchoatdongnhanvienctn";

	}

	@RequestMapping(value = { "chinhanh/xuat"}, method = RequestMethod.GET)
	public String getPXCN1(ModelMap model, HttpServletRequest request) {

		return "chinhanh2/form/choose-HDNV-xuat";

	}

	@RequestMapping(value = { "chinhanh/xuat"}, method = RequestMethod.POST)
	public String getHDPX12(ModelMap model, HttpServletRequest request) {
		Timestamp tungay = Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
		Timestamp denngay = Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");
		int manv = Integer.parseInt(request.getParameter("manhanvien"));
		String sql = "EXEC	 [dbo].[sp_HoatDongNhanVien]\r\n" + "		@MANV = ?,\r\n" + "		@LOAI = ?,\r\n"
				+ "		@DATEFROM =?,\r\n" + "		@DATETO = ?";
		List<HDNVModel> hdx = dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql,
				new HDNVMapper(), manv, "XUAT", tungay, denngay);
		model.addAttribute("hdxs", hdx);
		model.addAttribute("nvs", nvrepo.findOne(manv));
		return "chinhanh2/bchoatdongnhanvienctx";

	}

	// ==================================CONGTY=====================================//
	@RequestMapping(value = { "congty/nhap", }, method = RequestMethod.GET)
	public String getNVCTY(ModelMap model, HttpServletRequest request) {
		model.addAttribute("nv", new NhanVienEntity());

		return "congty2/form/choose-HDNV-nhap";

//			String sql = "EXEC [dbo].[sp_ChiTietSoLuongTriGiaHangHoaNhapXuat] ?,?,?,?";
//			List<CTSLHHXNModel> ctxn=dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql, new CTSLHHXNMapper(),"CHINHANH" "NHAP", );
//			System.out.println(ctxn.get(0).getThangNam());
//			model.addAttribute("ctxn", ctxn);
//			model.addAttribute("nvs", nvrepo.findAllNV());
//			return "chinhanh2/ctsoluongtrigiahanghoanhapxuat";

//			Timestamp tungay= Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
//			Timestamp denngay= Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");

//			String sql = "EXEC [dbo].[sp_ChiTietSoLuongTriGiaHangHoaNhapXuat] ?,?,?,?";
//			@SuppressWarnings("deprecation")
//			List<CTSLHHXNModel> ctxn=dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql, new CTSLHHXNMapper(),"CHINHANH" ,"NHAP",request.getParameter("tungay"),request.getParameter("denngay"));
//			model.addAttribute("ctpns", ctxn;
//			model.addAttribute("nvs", nvrepo.findAllNV());
//			System.out.println(ctxn.get(0).getTenVT());
//			return "chinhanh2/ctsoluongtrigiahanghoanhapxuat";
	}

	@RequestMapping(value = {  "congty/nhap",}, method = RequestMethod.POST)
	public String getPNCTY(ModelMap model, HttpServletRequest request) {
		Timestamp tungay = Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
		Timestamp denngay = Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");
		int manv = Integer.parseInt(request.getParameter("manhanvien"));
		String sql = "EXEC	 [dbo].[sp_HoatDongNhanVien]\r\n" + "		@MANV = ?,\r\n" + "		@LOAI = ?,\r\n"
				+ "		@DATEFROM =?,\r\n" + "		@DATETO = ?";
		List<HDNVModel> hdn = dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql,
				new HDNVMapper(), manv, "NHAP", tungay, denngay);
//			System.out.println(ctxn.get(0).getThangNam());
		System.out.println(tungay + " " + denngay);
		System.out.println(hdn.get(0).getTenVT());
		model.addAttribute("hdns", hdn);
		model.addAttribute("nvs", nvrepo.findOne(manv));
//			System.out.println(ctxn.get(0).getTenVT());
		return "congty2/bchoatdongnhanvienctn";

	}

	@RequestMapping(value = {"congty/xuat", }, method = RequestMethod.GET)
	public String getPXCTY(ModelMap model, HttpServletRequest request) {

		return "congty2/form/choose-HDNV-xuat";

	}

	@RequestMapping(value = { "congty/xuat", }, method = RequestMethod.POST)
	public String getHDCTY(ModelMap model, HttpServletRequest request) {
		Timestamp tungay = Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
		Timestamp denngay = Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");
		int manv = Integer.parseInt(request.getParameter("manhanvien"));
		String sql = "EXEC	 [dbo].[sp_HoatDongNhanVien]\r\n" + "		@MANV = ?,\r\n" + "		@LOAI = ?,\r\n"
				+ "		@DATEFROM =?,\r\n" + "		@DATETO = ?";
		List<HDNVModel> hdx = dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql,
				new HDNVMapper(), manv, "XUAT", tungay, denngay);
		model.addAttribute("hdxs", hdx);
		model.addAttribute("nvs", nvrepo.findOne(manv));
		return "congty2/bchoatdongnhanvienctx";

	}

	
}
