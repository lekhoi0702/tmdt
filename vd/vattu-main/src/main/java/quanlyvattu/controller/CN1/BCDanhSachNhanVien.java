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
import quanlyvattu.model.UserModel;
import quanlyvattu.repositoryCN1.ChiNhanhRepositoryCN1;
import quanlyvattu.repositoryCN1.NhanVienRepositoryCN1;
import quanlyvattu.repositoryCN2.ChiNhanhRepositoryCN2;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;

@Controller
@RequestMapping(value = "baocaodanhsachnhanvien/cn1")
public class BCDanhSachNhanVien {
	@Autowired
	NhanVienRepositoryCN1 nvrepo;
	@Autowired
	ChiNhanhRepositoryCN1 cnrepo;
	@Autowired
	ServletContext session;

	@RequestMapping(value = {"chinhanh","congty"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
	
		model.addAttribute("nvs", nvrepo.findAllNV());
		return "chinhanh/bcdanhsachnhanvien";
	}

}	