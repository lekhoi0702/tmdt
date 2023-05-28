USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_KiemTraMaPhieuNhap]    Script Date: 5/30/2022 3:12:54 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_KiemTraMaPhieuNhap]
@MAPN nChar(8)
AS
BEGIN
	IF( EXISTS( SELECT * FROM PhieuNhap WHERE MAPN = @MAPN ) )
		select 1;--TON TAI MA PHIEU NHAP

	ELSE IF( EXISTS( SELECT * FROM LINK1.QLVT_DATHANG.DBO.PhieuNhap WHERE MAPN = @MAPN ) )
		select 1;--TON TAI MA PHIEU NHAP
	select 0;-- KHONG TON TAI
END

GO

