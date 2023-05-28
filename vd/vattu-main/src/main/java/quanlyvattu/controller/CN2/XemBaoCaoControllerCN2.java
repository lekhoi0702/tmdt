package quanlyvattu.controller.CN2;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.repositoryCN2.ChiNhanhRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;

@Controller
@RequestMapping(value = "xembaocao/cn2")
public class XemBaoCaoControllerCN2 {
	@Autowired
	NhanVienRepositoryCN2 nvrepo;
	@Autowired
	ChiNhanhRepositoryCN2 cnrepo;
	@Autowired
	ServletContext session;

//==============================CHINHANH============================//
	@RequestMapping(value = { "chinhanh"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "ten");

		model.addAttribute("nvs", nvrepo.findAllNV());
		return "chinhanh2/Xembaocao";
	}

	// ==============================CONGTY============================//
	@RequestMapping(value = { "congty"}, method = RequestMethod.GET)
	public String getNVCTY(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "ten");

		model.addAttribute("nvs", nvrepo.findAllNV());
		return "congty2/Xembaocao";
	}

	
}