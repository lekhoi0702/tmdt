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
@RequestMapping(value = "baocaodanhsachvattu/cn1")
public class BCDanhSachVatTu {
	@Autowired
	VatTuRepositoryCN1 vtrepo;

	@Autowired
	ServletContext session;

//===============================CHINHANH==============================//

	@RequestMapping(value = { "chinhanh" }, method = RequestMethod.GET)
	public String getVTCN(ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 */ model.addAttribute("vts", vtrepo.findAll());
		return "chinhanh/bcdanhsachvattu";
	}

	// ===============================CONGTY==============================//

	@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
	public String getVTCTY(ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 */ model.addAttribute("vts", vtrepo.findAll());
		return "congty/bcdanhsachvattu";
	}


}
