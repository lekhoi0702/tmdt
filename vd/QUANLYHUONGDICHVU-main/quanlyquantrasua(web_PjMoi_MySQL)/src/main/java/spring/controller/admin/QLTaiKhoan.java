package spring.controller.admin;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.ChucVuDTO;
import spring.dto.LoaiBanDTO;
import spring.dto.NhanVienDTO;
import spring.dto.ThucDonDTO;
import spring.dto.UserDTO;




@Controller
@RequestMapping(value = "/admin-home/")
public class QLTaiKhoan {



	
	// CONTROLLER
	@RequestMapping(value = "admin-taikhoan", method = RequestMethod.GET)
	public <E> String index_TaiKhoan(HttpServletRequest request, ModelMap model) {
		
		List<UserDTO> list=null;
		try {
			list = Collector.getListAll("/user",UserDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		return "admin/qltaikhoan";
	}
	
	public List<ChucVuDTO> getChucVus() {
		List<ChucVuDTO> list=null;
		try {
			list = Collector.getListAll("/chucvu",ChucVuDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public List<NhanVienDTO> getNhanVien() {
		List<NhanVienDTO> list=null;
		try {
			list = Collector.getListAll("/nhanvien",NhanVienDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<UserDTO> getTaiKhoans() {
		List<UserDTO> list=null;
		try {
			list = Collector.getListAll("/user",UserDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping(value = "formTaiKhoan", method = RequestMethod.GET)
	public String formInputTaikhoan(ModelMap model) {
		model.addAttribute("tk", new UserDTO());
		model.addAttribute("chucvus", this.getChucVus());
		model.addAttribute("doc", "false");
		return "admin/form/inputTaiKhoan";
	}

	
	
	public int CheckUserName_Email(String username, String email) { // check xem email và username đã có hay chưa
		List<UserDTO> list = getTaiKhoans();
		int n = list.size();
		String user;
		String emailtmp;
		for (int i = 0; i < n; i++) {
			user = list.get(i).getUserName();
			if (user.equals(username)) {
				return 1;
			}

			emailtmp = list.get(i).getEmail();
			if (emailtmp.equals(email)) {
				return 2;
			}
		}
		return 0;
	}
	
	public Integer insertTaiKhoan(UserDTO tk) {
		String flag = Collector.postMess("/user", tk);
		System.out.println(flag);
		if (flag.equals("00")) {
			return 1;
		} else
			return 0;
	}
	
	
	
	public boolean CheckMaNhanVien(long manv) { // nếu có nhân viên đang làm việc trả về true
		List<NhanVienDTO> list = getNhanVien();
		int n = list.size();
		NhanVienDTO nv;
		for (int i = 0; i < n; i++) {
			nv = list.get(i);
			if (manv == nv.getMaNV() && nv.getDaNghi()  == false) {
				return true;
			}
		}
		return false;
	}
	

	
	public UserDTO getTaiKhoan(long manv) {
		List<UserDTO> list = null;
		try {
			list = Collector.getListAll("/user", UserDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDTO ss = new UserDTO();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getID() == manv)
				ss = list.get(i);
				break;
		}
		
		return ss;
	}
	
	@RequestMapping(value = "formTaiKhoan", params = "Insert", method = RequestMethod.POST)
	public <E> String addTaiKhoan(HttpServletRequest request, ModelMap model, @ModelAttribute("tk") UserDTO tk) {
		
		
		String error = "";
		Integer temp = 0;
		int check = CheckUserName_Email(tk.getUserName(), tk.getEmail());
		
		if (tk.getUserName().trim().equals("")) {
			error = "Tên tài khoản không được để trống!";
		} else if (check == 1) {
			error = "vì tên tài khoản đã tồn tại!!!";
		} else if (check == 2) {
			error = "email không được trùng!!!";
		} else {
//			String maNVtmp = request.getParameter("manv");
//
//			Integer maNV = Integer.parseInt(maNVtmp);
			String tmp = request.getParameter("chucvu").trim();
			Integer idChucVU = Integer.parseInt(tmp);
			if(CheckMaNhanVien(tk.getID())==false) {
				//System.out.println(maNV);
				error = "không tồn tại nhân viên";
			}else {
				
			tk.setID(tk.getID());
			tk.setRoleID((long)idChucVU);
			tk.setStatus(1);
			//tk.setPasswd(request.getParameter("mk"));
			temp = this.insertTaiKhoan(tk);
			 }
		}

		if (temp != 0) {
			model.addAttribute("message", "Thêm mới thành công");

		} else {
			model.addAttribute("message", "Thêm thất bại " + error + " ");
		}
		System.out.println(error + "1");
		return "admin/qltaikhoan";
	}

	public UserDTO getTaiKhoan(String username) {
		List<UserDTO> list = null;
		try {
			list = Collector.getListAll("/user", UserDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDTO ss = new UserDTO();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getUserName().equals(username))
				ss = list.get(i);
		}
		
		return ss;
	}
	
	
	
	@RequestMapping(value = "formTaiKhoan", params = "linkEdit")
	public String editTK_showform(HttpServletRequest request, ModelMap model, @ModelAttribute("tk") UserDTO tk) {

		model.addAttribute("tk", this.getTaiKhoan(tk.getUserName()));
		//model.addAttribute("maNV", tk.getID());
		model.addAttribute("chucvus", this.getChucVus());
		model.addAttribute("idCV", this.getTaiKhoan(tk.getUserName()).getRoleID());
		
		model.addAttribute("fixmanv", "true");// không đc sủa mã username
		model.addAttribute("doc", "readonly");// không được sửa mã nhân viên
		model.addAttribute("btnupdate", "true");
		return "admin/form/inputTaiKhoan";
	}

	

	@RequestMapping(value = "formTaiKhoan", params = "btnupdate", method = RequestMethod.POST)
	public <E> String editTK(HttpServletRequest requets, ModelMap model, @ModelAttribute("tk") UserDTO tk) {

		Integer temp = 0;

		
		String tmp = requets.getParameter("chucvu");
		Integer idChucVU = Integer.parseInt(tmp);
		tk.setPasswd(tk.getPasswd());
		tk.setID(tk.getID());
		tk.setRoleID((long)idChucVU);
		tk.setStatus(1);
		tk.setIcon("1");
		
		temp = this.updateTK(tk);

		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật không thành công");

		}
		
		return "admin/qltaikhoan";
	}

	public Integer updateTK(UserDTO tk) {
		String flag = Collector.putMess("/user", tk);
		System.out.println(flag);
		if (flag.equals("00")){
			return 1;
		} else
			return 0;
	}
	
	public Boolean checkAdmin(String username) {
		List<UserDTO> list = getTaiKhoans();
		int n = list.size();
		UserDTO tmp;
		for (int i = 0; i < n; i++) {
			tmp = this.getTaiKhoan(list.get(i).getUserName());

			if (tmp.getRoleID() == 1 && tmp.getUserName().equals(username)) {
				return true;

			}
		}

		return false;
	}
	
	@RequestMapping(value = "admin-taikhoan", params = "linkDelete", method = RequestMethod.GET)
	public <E> String deleteNV(HttpServletRequest requests, ModelMap model, @ModelAttribute("tk") UserDTO tk) {

		String error = "";
		Integer temp = 0;

		String userName = tk.getUserName();

		System.out.println(userName);
		if (checkAdmin(userName)) {
			error = ", không thể xóa tài khoản admin";
		} else {
			UserDTO tmp = this.getTaiKhoan(userName);
			tmp.setStatus(0);
			temp = this.updateTK(tmp);
		}
		if (temp != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa k thành công" + error);
		}
		return "admin/qltaikhoan";

	}
}