USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_TraCuu_KiemTraMaNhanVien]    Script Date: 5/30/2022 3:14:01 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_TraCuu_KiemTraMaNhanVien]
	@MANHANVIEN int
as
begin
	if exists( select * from LINK0.QLVT_DATHANG.DBO.NHANVIEN as NV where NV.MANV = @MANHANVIEN)
		return 1; -- ma nhan vien ton tai
	return 0; -- ma nhan vien khong ton tai
end 



GO

