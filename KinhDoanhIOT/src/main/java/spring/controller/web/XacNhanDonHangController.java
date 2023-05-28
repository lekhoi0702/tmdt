package spring.controller.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.Collector;
import spring.dto.CTDDHDTO;
import spring.dto.DDHDTO;
import spring.dto.LoginDTO;



@Controller
public class XacNhanDonHangController {

	@RequestMapping(value = "xacnhanddh" , method = RequestMethod.GET)
	public <E> String showMenu(ModelMap model,HttpServletRequest request) {

		List<DDHDTO> list=null;
		try {
			list = Collector.getListAll("/ddh",DDHDTO.class);
			list = list.stream()
                    .filter(ddh -> ddh.getTinhTrang() == 0)
                    .collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
        
		return "web/xacnhanddh";
	}
	
	@RequestMapping(value = "xacnhanddh/{id}.htm", params = "linkView",method = RequestMethod.GET)
	public <E> String xemChiTietDDH(HttpServletRequest request, ModelMap model, @PathVariable("id") Long id) {
		List<CTDDHDTO> cthds = this.getCtDDHs(id);

		
		  model.addAttribute("chiTiet", cthds);
		 
		int tong = 0;
		for (CTDDHDTO ctpn : cthds) {
			tong += ctpn.getTongTien();
		}
		model.addAttribute("tongTien", tong);
		model.addAttribute("idddh", id);
		return "web/xacnhanCTDDH";
	}
	

	
	
	@RequestMapping(value = "xacnhanddh/{id}", method = RequestMethod.POST)
	public String edit_NhapHang(HttpServletRequest requets, ModelMap model, @PathVariable("id") long id) {
		/*
		 * UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(requets,
		 * "USERMODEL"); nh.setCpnv(user1.getUsernv());
		 */
		DDHDTO cp = new DDHDTO();
		cp = getDonDatHang(id);
		cp.setTinhTrang((int) 1);
		LoginDTO nhanvien = (LoginDTO) SessionUtil.getInstance().getValue(requets, "USERMODEL");
		cp.setNvThucHien(nhanvien.getMaNV());

		Integer temp = this.updateNH(cp);
		if (temp == 0) {
			model.addAttribute("message", "Cập nhật không thành công! " );

		} 

	
		List<DDHDTO> list=null;
		try {
			list = Collector.getListAll("/ddh",DDHDTO.class);
			list = list.stream()
                   .filter(ddh -> ddh.getTinhTrang() == 0)
                   .collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
        
		return "web/xacnhanCTDDH";
	}

	
	

	public List<CTDDHDTO> getCtDDHs(Long idpn) {
		List<CTDDHDTO> list = null;
		try {
			list = Collector.getListAll("/ctddh?maddh=" + idpn.toString(), CTDDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}



	public DDHDTO getDonDatHang(long id) {
		List<DDHDTO> list = null;
		try {
			list = Collector.getListAll("/ddh", DDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DDHDTO ss = new DDHDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == null) {
				return null;
			}
			if (list.get(i).getId() == id)
				ss = list.get(i);
		}

		return ss;
	}

	public Integer updateNH(DDHDTO nh) {
		String flag = Collector.putMess("/ddh", nh);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

	
}