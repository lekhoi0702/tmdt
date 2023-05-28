package spring.controller.khachhang;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.bean.Collector;
import spring.dto.LoaiSPDTO;
import spring.dto.Nhan_SanPhamDTO;
import spring.dto.SanPhamDTO;
import spring.dto.TuKhoa_NhanDTO;

@Controller

public class TimKiemController {

	// CONTROLLER

	@RequestMapping(value = "khachhangtimkiemnhan", method = RequestMethod.GET)
	public String sanPham_Nhan(HttpServletRequest request, ModelMap model) {
		List<SanPhamDTO> sps2 = null;

		String tenNhan = request.getParameter("searchnhan");
		sps2 = this.locTheoNhan(tenNhan);
		model.addAttribute("SanPhams", sps2);
		model.addAttribute("LoaiSPs", getLoaiSPs());

		return "khachhang/home";
	}

	@RequestMapping(value = "khachhangtimkiemtukhoa", method = RequestMethod.GET)
	public String sanPham_TuKhoa(HttpServletRequest request, ModelMap model) {
		List<SanPhamDTO> sps2 = new ArrayList<SanPhamDTO>();
		List<SanPhamDTO> sps1 = this.getSanPhams();
		String tenTuKhoa = this.removeAccent(request.getParameter("searchtukhoa").toLowerCase());
		List<Long> masps = this.getMaSPTheoTuKhoa(tenTuKhoa);

		// List<TuKhoa_NhanDTO> tuKhoa_Nhans=this.getNhanTuKhoa(tenTuKhoa);
		// sps2 = this.locTheoNhan(tenNhan);
		String tenTuKhoas[] = tenTuKhoa.split(" ", 0);
		for (SanPhamDTO sp : sps1) {

			if (this.locTheoTenSP(tenTuKhoas, sp.getTen()) == true  ) {
				sps2.add(sp);
			}
		}
		for (SanPhamDTO sp :sps1) {
			System.out.println("test: "+tenTuKhoas[0].toString()+" "+sp.getTen());

			if (masps.contains(sp.getID()) ) {
				sps2.add(sp);
			}
			
		}
		List<SanPhamDTO> sps3=this.removeDuplicates(sps2);
		model.addAttribute("SanPhams", sps3);
		model.addAttribute("LoaiSPs", getLoaiSPs());

		return "khachhang/home";
	}

	public List<Nhan_SanPhamDTO> getNhanSPs(String tenNhan) {
		List<Nhan_SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/nhansanpham?tennhan=" + tenNhan, Nhan_SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<TuKhoa_NhanDTO> getNhanTuKhoa(String tenTuKhoa) {
		List<TuKhoa_NhanDTO> list = null;
		try {
			list = Collector.getListAll("/tukhoanhan?tentukhoa=" + tenTuKhoa, TuKhoa_NhanDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<SanPhamDTO> getSanPhams() {
		List<SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/sanpham", SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public LoaiSPDTO findOneLoai(Long id) {
		List<LoaiSPDTO> list = null;
		try {
			list = Collector.getListAll("/loaisp", LoaiSPDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoaiSPDTO ss = new LoaiSPDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id))
				ss = list.get(i);
		}

		return ss;
	}

	public SanPhamDTO getSP(Long id) {
		List<SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/sanpham", SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SanPhamDTO ss = new SanPhamDTO();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(id))
				ss = list.get(i);
		}

		return ss;
	}

	public List<SanPhamDTO> locTheoNhan(String tenNhan) {
		List<SanPhamDTO> sps2 = new ArrayList<SanPhamDTO>();

		List<Nhan_SanPhamDTO> nsps = this.getNhanSPs(tenNhan);
		for (Nhan_SanPhamDTO nsp : nsps) {
			sps2.add(this.getSP(nsp.getSanPham()));
		}

		return sps2;
	}

	public boolean locTheoTenSP(String tuKhoa[], String tenSP) {
		String tenSPs = this.removeAccent(tenSP.toLowerCase());

		for (int i = 0; i < tuKhoa.length; i++) {
			if (tenSPs.contains(tuKhoa[i])) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<SanPhamDTO> removeDuplicates(List<SanPhamDTO> sps2) {
		HashSet<Long> set = new HashSet<>();
		ArrayList<SanPhamDTO> newList = new ArrayList<>();
		for (SanPhamDTO sp : sps2) {
			if (!set.contains(sp.getID())) { // giả sử getMaSP() là phương thức để lấy mã sản phẩm
				set.add(sp.getID());
				newList.add(sp);
			}
		}
		return newList;
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

	public List<Long> getMaSPTheoTuKhoa(String tuKhoa) {
		List<TuKhoa_NhanDTO> listTuKhoaNhan = new ArrayList<TuKhoa_NhanDTO>();
		List<Nhan_SanPhamDTO> listNhanSP = new ArrayList<Nhan_SanPhamDTO>();
		List<Long> listMaSP = new ArrayList<Long>();
		if (tuKhoa == null || tuKhoa == "") {
			return listMaSP;
		}
		try {
			listTuKhoaNhan = Collector.getListAll("/tukhoanhan", TuKhoa_NhanDTO.class);
			// listNhanSP = Collector.getListAll("/loaisp", Nhan_SanPhamDTO.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp;
		tuKhoa = this.removeAccent(tuKhoa.toLowerCase());
		for (TuKhoa_NhanDTO tkn : listTuKhoaNhan) {
			temp = this.removeAccent(tkn.getTuKhoa().toLowerCase());

			if (tuKhoa.indexOf(temp) != -1 || temp.indexOf(tuKhoa) != -1) {
				try {

					listNhanSP = Collector.getListAll("/nhansanpham?tennhan=" + tkn.getNhan(), Nhan_SanPhamDTO.class);
					for (Nhan_SanPhamDTO nsp : listNhanSP) {
						listMaSP.add(nsp.getSanPham());
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return listMaSP;
	}

	public String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		temp = pattern.matcher(temp).replaceAll("");
		return temp.replaceAll("đ", "d");
	}

}