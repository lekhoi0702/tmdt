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
@RequestMapping(value = "baocaodanhsachnhanvien/cn2")
public class BCDanhSachNhanVienCN2 {
	@Autowired
	NhanVienRepositoryCN2 nvrepo;
	@Autowired
	ChiNhanhRepositoryCN2 cnrepo;
	@Autowired
	ServletContext session;

	@RequestMapping(value = {"chinhanh","congty"}, method = RequestMethod.GET)
	public String getNVCN2(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
	
		model.addAttribute("nvs", nvrepo.findAllNV());
		return "chinhanh2/bcdanhsachnhanvien";
	}

}	