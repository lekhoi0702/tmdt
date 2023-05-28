USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_TraCuu_TimMaNV]    Script Date: 5/30/2022 3:14:12 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_TraCuu_TimMaNV] as
begin
	if exists(select MAX(MANV)+1 from LINK0.QLVT_DATHANG.DBO.NHANVIEN )--neu ton tai it nhat 1  nv
	select MAX(MANV)+1 from LINK0.QLVT_DATHANG.DBO.NHANVIEN ;
	return 1; -- ko ton tai nv nao
end


GO

