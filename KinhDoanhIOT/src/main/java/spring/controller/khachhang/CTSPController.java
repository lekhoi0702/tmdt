package spring.controller.khachhang;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.utils.SessionUtil;

import spring.bean.APIFunction;
import spring.bean.Collector;
import spring.bean.ObjDelLong;
import spring.dto.CTDDHDTO;
import spring.dto.DDHDTO;
import spring.dto.LoaiSPDTO;
import spring.dto.LoginKHDTO;
import spring.dto.SanPhamDTO;
import spring.dto.UserKHDTO;

@Controller

public class CTSPController {

	// CONTROLLER
	@RequestMapping(value = "khachhang-ctsp.htm", method = RequestMethod.GET)
	public <E> String showSanPham(HttpServletRequest request, ModelMap model) {
		try {
			LoginKHDTO kh = (LoginKHDTO) SessionUtil.getInstance().getValue(request, "USERKHMODEL");
			if(kh!=null) {
			long sl=Collector.getObj("/slgiohang?makh="+kh.getMaKH().toString(), ObjDelLong.class).getId();
			model.addAttribute("slGioHang", sl);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		Long idTD = Long.parseLong(request.getParameter("id"));
		SanPhamDTO td = APIFunction.getSP(idTD);

		List<CTDDHDTO> danhGias = this.getDanhGia(idTD);

		model.addAttribute("danhGias", danhGias);
		model.addAttribute("LoaiSPs", getLoaiSPs());

		model.addAttribute("td", td);
		model.addAttribute("loai", APIFunction.findOneLoai(td.getLoai()));

		return "khachhang/ctsp";

	}

	public List<CTDDHDTO> getDanhGia(Long id) {
		List<CTDDHDTO> list = null;
		List<UserKHDTO> listuser = null;
		List<DDHDTO> listddh = null;
		try {
			list = Collector.getListAll("/ctddh?maspreview=" + id.toString(), CTDDHDTO.class);
			listuser = Collector.getListAll("/userkh", UserKHDTO.class);
			listddh = Collector.getListAll("/ddh", DDHDTO.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CTDDHDTO ct : list) {
			 ct.setUserName(this.getUsernameDG(ct.getDdh(), listuser,listddh ));
		}
		return list;
	}

	public String getUsernameDG(Long idddh, List<UserKHDTO> users, List<DDHDTO> ddhs) {
		for (DDHDTO ddh : ddhs) {

			if (ddh.getId() == idddh)
				for (UserKHDTO ur : users) {
					if (ur.getID() == ddh.getKhThucHien())
						return ur.getUserName();
				}

		}
		return null;
	}
	public List<LoaiSPDTO> getLoaiSPs() {
		List<LoaiSPDTO> list = null;
		try {
			list = Collector.getListAll("/loaisp", LoaiSPDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}