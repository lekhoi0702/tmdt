package quanlyvattu.controller.CN2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quanlyvattu.model.LoginModel;
import quanlyvattu.repositoryCN2.NhanVienRepositoryCN2;



@Controller
@RequestMapping(value = "taologin/cn2")
public class TaoLoginControllerCN2 {
	
	@Autowired
	NhanVienRepositoryCN2 nvrepo;
//================================CHINHANH=====================================================
	@RequestMapping(value = {"chinhanh"}, method = RequestMethod.GET)
	public String getDDHCN(ModelMap model, HttpServletRequest request){	
		

			model.addAttribute("newLogin",new LoginModel());
			return "chinhanh2/taologin";
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
			
				
		
			
			return "chinhanh2/taologin";
		}
		
	
	//================================CONGTY=====================================================
	@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
	public String taoLoginCTY1(ModelMap model, HttpServletRequest request){	
		

			model.addAttribute("newLogin",new LoginModel());
			return "congty2/taologin";
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
				 
			
	
			return "congty2/taologin";
		}
		

}
