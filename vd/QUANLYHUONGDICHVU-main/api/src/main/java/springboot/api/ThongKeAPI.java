package springboot.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.ChiPhiDTO;
import springboot.dto.DateDTO;
import springboot.dto.HoaDonDTO;
import springboot.dto.ThongKeDTO;
import springboot.entity.ChiPhiEntity;
import springboot.entity.HoaDonEntity;
import springboot.repository.ChiPhiRepository;
import springboot.repository.HoaDonRepository;

@RestController
public class ThongKeAPI {
	@Autowired
	ChiPhiRepository cprepo;
	@Autowired
	HoaDonRepository hdrepo;

	@PostMapping(value = "/thongke")
	public ThongKeDTO thongKe(HttpServletRequest request,@RequestBody DateDTO model) {
		String flaglist = request.getParameter("flaglist");
		List<ChiPhiEntity> findCPEntity=null;
		List<HoaDonEntity> findHDEntity=null;
		List<ChiPhiDTO> cpDto= new ArrayList<ChiPhiDTO>();
		List<HoaDonDTO> hdDto=new ArrayList<HoaDonDTO>();
		ThongKeDTO e = new ThongKeDTO();
		System.out.println(model.getNam());

		System.out.println(model.getThang());
		System.out.println(model.getNgay());
		try {
			if (flaglist != null) {
				if (model.getNgay()==-1) {
					if (model.getThang() == -1)
					{
						findCPEntity=cprepo.getChiPhiNam(model.getNam());
						findHDEntity=hdrepo.getHoaDonNam(model.getNam());

						
					} else {
						findCPEntity=cprepo.getChiPhiThang(model.getThang(),model.getNam());
						findHDEntity=hdrepo.getHoaDonThang(model.getThang(),model.getNam());
						
						
					}
				} else {
					findCPEntity=cprepo.getChiPhiNgay(model.getNgay(),model.getThang(),model.getNam());
					findHDEntity=hdrepo.getHoaDonNgay(model.getNgay(),model.getThang(),model.getNam());
					
				
				}
				for (ChiPhiEntity item: findCPEntity) {
					cpDto.add(new ChiPhiDTO(item));
				}
				for (HoaDonEntity item: findHDEntity) {
					hdDto.add(new HoaDonDTO(item));
				}
				
				e.setChiPhis(cpDto);
				e.setHoaDons(hdDto);
			}
			
			else {
				if (model.getNgay()==-1) {
					if (model.getThang() == -1)
					{
						e.setChiPhi(cprepo.tongChiPhiNam(model.getNam()));
						e.setDoanhThu(hdrepo.doanhThuNam(model.getNam()));
						e.setLoiNhuan(e.getDoanhThu()-e.getChiPhi());
						e.setSoHoaDon(hdrepo.soHoaDonNam(model.getNam()));
					
					} else {
						e.setChiPhi(cprepo.tongChiPhiThang(model.getThang(),model.getNam()));
						e.setDoanhThu(hdrepo.doanhThuThang(model.getThang(),model.getNam()));
						e.setLoiNhuan(e.getDoanhThu()-e.getChiPhi());
						e.setSoHoaDon(hdrepo.soHoaDonThang(model.getThang(),model.getNam()));
					}
				} else {
//					System.out.println(cprepo.tongChiPhiNgay(model.getNgay(),model.getThang(),model.getNam()));

					e.setChiPhi(cprepo.tongChiPhiNgay(model.getNgay(),model.getThang(),model.getNam()));
					e.setDoanhThu(hdrepo.doanhThuNgay(model.getNgay(),model.getThang(),model.getNam()));
					e.setLoiNhuan(e.getDoanhThu()-e.getChiPhi());
					e.setSoHoaDon(hdrepo.soHoaDonNgay(model.getNgay(),model.getThang(),model.getNam()));
				}
			}
			
			return e;
			
		}catch(

	Exception ex)
	{
		return new ThongKeDTO();
	}
}

}
