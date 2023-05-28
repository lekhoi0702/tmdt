package springboot.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.PhieuNhapDTO;
import springboot.dto.DateDTO;
import springboot.dto.DDHDTO;
import springboot.dto.ThongKeDTO;
import springboot.entity.PhieuNhapEntity;
import springboot.entity.DDHEntity;
import springboot.repository.PhieuNhapRepository;
import springboot.repository.DDHRepository;

@RestController
public class ThongKeAPI {
	@Autowired
	PhieuNhapRepository pnrepo;
	@Autowired
	DDHRepository ddhrepo;

	@PostMapping(value = "/thongke")
	public ThongKeDTO thongKe(HttpServletRequest request,@RequestBody DateDTO model) {
		String flaglist = request.getParameter("flaglist");
		List<PhieuNhapEntity> findCPEntity=null;
		List<DDHEntity> findHDEntity=null;
		List<PhieuNhapDTO> cpDto= new ArrayList<PhieuNhapDTO>();
		List<DDHDTO> hdDto=new ArrayList<DDHDTO>();
		ThongKeDTO e = new ThongKeDTO();
		System.out.println(model.getNam());

		System.out.println(model.getThang());
		System.out.println(model.getNgay());
		try {
			if (flaglist != null) {
				if (model.getNgay()==-1) {
					if (model.getThang() == -1)
					{
						findCPEntity=pnrepo.getPhieuNhapNam(model.getNam());
						findHDEntity=ddhrepo.getDDHNam(model.getNam());

						
					} else {
						findCPEntity=pnrepo.getPhieuNhapThang(model.getThang(),model.getNam());
						findHDEntity=ddhrepo.getDDHThang(model.getThang(),model.getNam());
						
						
					}
				} else {
					findCPEntity=pnrepo.getPhieuNhapNgay(model.getNgay(),model.getThang(),model.getNam());
					findHDEntity=ddhrepo.getDDHNgay(model.getNgay(),model.getThang(),model.getNam());
					
				
				}
				for (PhieuNhapEntity item: findCPEntity) {
					cpDto.add(new PhieuNhapDTO(item));
				}
				for (DDHEntity item: findHDEntity) {
					hdDto.add(new DDHDTO(item));
				}
				
				e.setPhieuNhaps(cpDto);
				e.setDDHs(hdDto);
			}
			
			else {
				if (model.getNgay()==-1) {
					if (model.getThang() == -1)
					{
						e.setPhieuNhap(pnrepo.tongPhieuNhapNam(model.getNam()));
						e.setDoanhThu(ddhrepo.doanhThuNam(model.getNam()));
						e.setLoiNhuan(e.getDoanhThu()-e.getPhieuNhap());
						e.setSoDDH(ddhrepo.soDDHNam(model.getNam()));
					
					} else {
						e.setPhieuNhap(pnrepo.tongPhieuNhapThang(model.getThang(),model.getNam()));
						e.setDoanhThu(ddhrepo.doanhThuThang(model.getThang(),model.getNam()));
						e.setLoiNhuan(e.getDoanhThu()-e.getPhieuNhap());
						e.setSoDDH(ddhrepo.soDDHThang(model.getThang(),model.getNam()));
					}
				} else {
//					System.out.println(pnrepo.tongPhieuNhapNgay(model.getNgay(),model.getThang(),model.getNam()));

					e.setPhieuNhap(pnrepo.tongPhieuNhapNgay(model.getNgay(),model.getThang(),model.getNam()));
					e.setDoanhThu(ddhrepo.doanhThuNgay(model.getNgay(),model.getThang(),model.getNam()));
					e.setLoiNhuan(e.getDoanhThu()-e.getPhieuNhap());
					e.setSoDDH(ddhrepo.soDDHNgay(model.getNgay(),model.getThang(),model.getNam()));
				}
			}
			
			return e;
			
		}catch(

	Exception ex)
	{
			ex.printStackTrace();
		return new ThongKeDTO();
	}
}

}
