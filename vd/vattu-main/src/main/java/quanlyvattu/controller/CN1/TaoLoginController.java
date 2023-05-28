package quanlyvattu.controller.CN1;

import javax.servlet.http.HttpServletRequest;
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
import quanlyvattu.repositoryCN1.NhanVienRepositoryCN1;
import quanlyvattu.repositoryCN1.VatTuRepositoryCN1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.model.LoginModel;



@Controller
@RequestMapping(value = "taologin/cn1")
public class TaoLoginController {
	
	@Autowired
	NhanVienRepositoryCN1 nvrepo;
//================================CHINHANH=====================================================
	@RequestMapping(value = {"chinhanh"}, method = RequestMethod.GET)
	public String getDDHCN(ModelMap model, HttpServletRequest request){	
		

			model.addAttribute("newLogin",new LoginModel());
			return "chinhanh/taologin";
		}
	@RequestMapping(value = {"chinhanh"}, method = RequestMethod.POST)
	public String taoLogin(ModelMap model, @ModelAttribute("newLogin") LoginModel lg){	
		
		System.out.println(lg.getLgName());
		System.out.println(lg.getMaNV());
		System.out.println(lg.getPass());
		System.out.println(lg.getQuyen());
				try {
				 nvrepo.taoLogin(lg.getLgName(), lg.getPass(), lg.getMaNV(), lg.getQuyen());
				 System.out.println("thành công");
				 model.addAttribute("message","thêm login thành  công"); 
				}
				catch(Exception e){
					e.printStackTrace();
					model.addAttribute("message","thêm login thất bại,kiểm tra lại nhân viên đã có account chưa ?");
				}
			
				
		
			
			return "chinhanh/taologin";
		}
		
	
	//================================CONGTY=====================================================
	@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
	public String taoLoginCTY1(ModelMap model, HttpServletRequest request){	
		

			model.addAttribute("newLogin",new LoginModel());
			return "congty/taologin";
		}
	@RequestMapping(value = {"congty"}, method = RequestMethod.POST)
	public String taoLoginCTY(ModelMap model, @ModelAttribute("newLogin") LoginModel lg){	
		int f=1;
		System.out.println(lg.getLgName());
		System.out.println(lg.getMaNV());
		System.out.println(lg.getPass());
		System.out.println(lg.getQuyen());
			
		try {
				 nvrepo.taoLogin(lg.getLgName(), lg.getPass(), lg.getMaNV(), lg.getQuyen());
				 System.out.println("thành công");
				 model.addAttribute("message","thêm login thành  công");
				 }
		
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message","thêm login thất bại, vui lòng kiểm tra lại thông tin account");
		}
				 
			
	
			return "congty/taologin";
		}
		

}
