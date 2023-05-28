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
import quanlyvattu.mapper.THNXMapper;
import quanlyvattu.model.THNXModel;
import quanlyvattu.repositoryCN2.DatHangRepositoryCN2;
import quanlyvattu.repositoryCN2.KhoRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;
import quanlyvattu.statics.InfoConnection;

@Controller
@RequestMapping(value = "tonghopnhapxuat/cn2")
public class TongHopNhapXuatControllerCN2 {
	@Autowired
	DatHangRepositoryCN2 dhrepo;

	@Autowired
	KhoRepositoryCN2 krepo;

	@Autowired
	NhanVienRepositoryCN2 nvrepo;

	@Autowired
	ServletContext session;

	AbstractDAO dao = new AbstractDAO();

	@RequestMapping(value = {"chinhanh/nhap","congty/nhap"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model,HttpServletRequest request) {
		
		
		return "chinhanh2/form/choose-nx";
		
		
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

	@RequestMapping(value = {"chinhanh/nhap","congty/nhap"}, method = RequestMethod.POST)
	public String getPNCN1(ModelMap model, HttpServletRequest request) {
		Timestamp tungay= Timestamp.valueOf(request.getParameter("tungay").replace("T", " ") + ":00");
		Timestamp denngay= Timestamp.valueOf(request.getParameter("denngay").replace("T", " ") + ":00");
		
		String sql = "EXEC	[dbo].[sp_TongHopNhapXuat]\r\n" + 
				"		@FromDate = ?,\r\n" + 
				"		@ToDate = ?";
		List<THNXModel> thnx=dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(),
										sql, new THNXMapper(),tungay,denngay);
//		System.out.println(ctxn.get(0).getThangNam());
		System.out.println(tungay+" "+denngay);
		System.out.println(thnx.get(0).getNhap());
		model.addAttribute("thnxs", thnx);
		model.addAttribute("nvs", nvrepo.findAllNV());
//		System.out.println(ctxn.get(0).getTenVT());
		return "chinhanh2/tonghopnhapxuat";
		
	}
	
	
}
