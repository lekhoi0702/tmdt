package quanlyvattu.dao.impl;

import java.util.List;

import quanlyvattu.mapper.DSPMMapper;
import quanlyvattu.model.DSPMModel;

public class DSPMDAO extends AbstractDAO<DSPMModel> {
	public List<DSPMModel> findAll() {
		String sql = "SELECT * from dbo.view_DanhSachPhanManh";
		return queryChu(sql, new DSPMMapper());
	}
}