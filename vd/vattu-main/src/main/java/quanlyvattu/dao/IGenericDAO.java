package quanlyvattu.dao;

import java.util.List;

import quanlyvattu.mapper.RowMapper;

public interface IGenericDAO<T> {
	@SuppressWarnings("hiding")
	<T> List<T> queryChu(String sql, RowMapper<T> rowMapper, Object... parameters);
	<T> List<T> queryTC(String sql, RowMapper<T> rowMapper, Object... parameters);
	<T> List<T> queryPM(String username,String password,String sql, RowMapper<T> rowMapper, Object... parameters);
	void updateChu (String sql, Object... parameters);
	Long insertChu (String sql, Object... parameters);


}
