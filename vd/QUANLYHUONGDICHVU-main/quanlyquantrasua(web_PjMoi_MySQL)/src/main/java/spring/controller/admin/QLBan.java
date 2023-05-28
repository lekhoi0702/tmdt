package spring.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.BanHoaDonModel;
import spring.bean.Collector;
import spring.dto.BanDTO;
import spring.dto.ChiPhiDTO;
import spring.dto.ChiTietHDDTO;
import spring.dto.LoaiBanDTO;
import spring.dto.NhanVienDTO;




@Controller

@RequestMapping(value = "/admin-home/")
public class QLBan {

	//show trang quan li ban
	@Autowired
	ServletContext application;

	@RequestMapping(value = "admin-qlban", method = RequestMethod.GET)
	public <E> String showQLB(HttpServletRequest request,ModelMap model){	
		
		List<BanDTO> list=null;
		try {
			list = Collector.getListAll("/ban",BanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		

		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	
	@RequestMapping(value="formBan", method = RequestMethod.GET) 
    public String formBan(ModelMap model, HttpServletRequest request,@ModelAttribute("b") BanDTO b) {
		model.addAttribute("loaibans",getLoaiBans());	
          return "admin/form/inputBan1";
    }
	
	public List<LoaiBanDTO> getLoaiBans(){
		List<LoaiBanDTO> list=null;
		try {
			list = Collector.getListAll("/loaiban",LoaiBanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<String> checkInfo(BanDTO b ) {

		List<String> listError = new ArrayList<>();

		if (b.getSoGhe()==null) {
			listError.add("chưa nhập số ghế");
		}
		
		
		return listError;
		

	}
	
	public Integer InsertBan (BanDTO b) {
		String flag = Collector.postMess("/ban", b);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
	
	//thêm bàn xổ combobox
	@RequestMapping(value = "formBan",params = "Insert", method = RequestMethod.POST )
	public <E> String add_BAN(HttpServletRequest request, ModelMap model /*,@ModelAttribute("lb") LoaiBanEntity lb*/ ) {
		BanDTO ban = new BanDTO();
		String id = request.getParameter("loaiBan"); 
		int id1=1;//mặc định là bàn thường
		id1 = Integer.parseInt(id);
		
		String a = request.getParameter("soGhe");
		
		int soghe = Integer.parseInt(a);
		
		ban.setSoGhe(soghe);
		ban.setTinhTrang(1);
		ban.setLoaiBan((long)id1);
		List<String> listError = checkInfo(ban);
		Integer temp1 = this.InsertBan(ban);
		if(temp1 != 0) {
		    model.addAttribute("message","Thêm thành công");
			
		}else {
			model.addAttribute("message","Thêm thất bại! "+listError);
		}
		
		return "admin/qlban";
	}
 
	public BanDTO getBan (long id) {
		List<BanDTO> list = null;
		try {
			list = Collector.getListAll("/ban", BanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BanDTO ss = new BanDTO();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id)
				ss = list.get(i);
		}
		
		return ss;
	}
	
	@RequestMapping(value = "formBan", params = "linkEdit" )
	public String editBan_showform (@ModelAttribute("b") BanDTO b, ModelMap model) {
		
		long id = b.getId();
		long s = this.getBan(id).getLoaiBan();
		model.addAttribute("idLoai", s);
		model.addAttribute("loaibans",getLoaiBans());	
		model.addAttribute("soGhe1",this.getBan(id).getSoGhe());	
		
		model.addAttribute("btnupdate","true");
		return "admin/form/inputBan1";
	}

	@RequestMapping(value = "formBan", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String edit_QLBan(HttpServletRequest requets, ModelMap model 
			) {

		String id1 =requets.getParameter("id");
		long idB = Long.parseLong(id1);
		
		String id = requets.getParameter("loaiBan"); 
		long idLB = Integer.parseInt(id);
		String a = requets.getParameter("soGhe");
		int soghe = Integer.parseInt(a);
		
		BanDTO tmp = getBan(idB);
		
		
		tmp.setSoGhe(soghe);
		tmp.setTinhTrang(1);
		tmp.setLoaiBan(idLB);
		tmp.setTenLoai(null);
		Integer temp = this.updateBan(tmp);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
			
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
			
		}
		
	
		
		return "admin/qlban";
	}
	
	
	public Integer updateBan(BanDTO b) {
		String flag = Collector.putMess("/ban", b);
		System.out.println(flag);
		if (flag.equals("00")){
			return 1;
		} else
			return 0;
	}
	
	@RequestMapping(value = "admin-qlban", params = "linkDelete")
	public <E> String deleteDonNhapHang (HttpServletRequest request, ModelMap model, @ModelAttribute("b") BanDTO b) {
		String id1 =request.getParameter("id");
		long idB = Long.parseLong(id1);
		

		BanDTO tmp = getBan(idB);
		tmp.setTinhTrang(0);
		tmp.setTenLoai(null);
		Integer temp = this.deleteBan(tmp);
		//System.out.println(getBan(id).ge);
		
		if(temp != 0) {
			model.addAttribute("message","Xóa thành công");
		}
		else {
			model.addAttribute("message", "Xóa không thành công! Bàn đã có người đặt");
		}
		
		return "admin/qlban";
	}
	public Integer deleteBan (BanDTO b) {
		String flag = Collector.putMess("/ban", b);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}

}