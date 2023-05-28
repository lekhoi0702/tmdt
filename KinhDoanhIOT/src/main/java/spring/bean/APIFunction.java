package spring.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import spring.dto.CTDDHDTO;
import spring.dto.DDHDTO;
import spring.dto.GioHangDTO;
import spring.dto.LoaiSPDTO;
import spring.dto.SanPhamDTO;

public class APIFunction {
	static public List<LoaiSPDTO> getLoaiSPs() {
		List<LoaiSPDTO> list = null;
		try {
			list = Collector.getListAll("/loaisp", LoaiSPDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	static public DDHDTO getDDH(Long id)

	{
		List<DDHDTO> listDDH = new ArrayList<DDHDTO>();
		try {
			listDDH = Collector.getListAll("/ddh", DDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (DDHDTO ddh : listDDH) {
			if (ddh.getId() == id) {
				return ddh;
			}

		}
		return null;
	}

	static public List<SanPhamDTO> getSanPhams() {
		List<SanPhamDTO> list = null;
		try {
			list = Collector.getListAll("/sanpham", SanPhamDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	static public LoaiSPDTO findOneLoai(Long id) {
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

	static public List<CTDDHDTO> getCtDDHs(Long idpn) {
		List<CTDDHDTO> list = null;
		try {
			list = Collector.getListAll("/ctddh?maddh=" + idpn.toString(), CTDDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	static public List<CTDDHDTO> getCtDDHFull() {
		List<CTDDHDTO> list = null;
		try {
			list = Collector.getListAll("/ctddh", CTDDHDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	static public SanPhamDTO getSP(Long id) {
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

	static public List<GioHangDTO> getGioHangs(Long id) {
		List<GioHangDTO> list = null;
		try {
			list = Collector.getListAll("/giohang?makh=" + id.toString(), GioHangDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	static public String postGioHang(GioHangDTO gh) {
		String check;
		check = Collector.postMess("/giohang", gh);

		return check;
	}

	static public String postDDH(DDHDTO ddh) {
		String check;
		check = Collector.postMess("/ddh", ddh);

		return check;
	}

	static public String delGioHang(GioHangDTO gh) {
		String check;
		ObjDelLong del = new ObjDelLong();
		del.setId(gh.getID());
		check = Collector.delMess("/giohang", del);

		return check;
	}

	static public SanPhamDTO getSP(Long id, List<SanPhamDTO> list) {

		SanPhamDTO ss = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(id))
				ss = list.get(i);
		}

		return ss;
	}
}
