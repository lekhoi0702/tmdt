package quanlyvattu.controller.CN2;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.dao.impl.AbstractDAO;
import quanlyvattu.mapper.DDHnoPNMapper;
import quanlyvattu.model.DDHnoPNModel;
import quanlyvattu.repositoryCN2.DatHangRepositoryCN2;
import quanlyvattu.repositoryCN2.KhoRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;
import quanlyvattu.statics.InfoConnection;

@Controller
@RequestMapping(value = "baocaodondathangkhongcophieunhap/cn2")
public class BCDonDatHangNoPhieuNhapCN2 {
	@Autowired
	DatHangRepositoryCN2 dhrepo;

	@Autowired
	KhoRepositoryCN2 krepo;

	@Autowired
	NhanVienRepositoryCN2 nvrepo;

	@Autowired
	ServletContext session;

//===========================================CHINHANH============================================//

	AbstractDAO dao = new AbstractDAO();

	@RequestMapping(value = { "chinhanh"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model) {
		String sql = "EXEC [dbo].[sp_DonHangKhongPhieuNhap]";
		List<DDHnoPNModel> ddhs = dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql,
				new DDHnoPNMapper());
		System.out.println(ddhs.get(0).getMaSoDDH());
		model.addAttribute("ddhs", ddhs);
		model.addAttribute("nvs", nvrepo.findAllNV());
		return "chinhanh2/bcdonhangkhongcophieunhap";
	}

	// ===========================================CONGTY============================================//



	@RequestMapping(value = { "congty"}, method = RequestMethod.GET)
	public String getNVCTY(ModelMap model) {
		String sql = "EXEC [dbo].[sp_DonHangKhongPhieuNhap]";
		List<DDHnoPNModel> ddhs = dao.queryPM(InfoConnection.getUserNamePM(), InfoConnection.getPassWordPM(), sql,
				new DDHnoPNMapper());
		System.out.println(ddhs.get(0).getMaSoDDH());
		model.addAttribute("ddhs", ddhs);
		model.addAttribute("nvs", nvrepo.findAllNV());
		return "congty2/bcdonhangkhongcophieunhap";
	}
	

}
