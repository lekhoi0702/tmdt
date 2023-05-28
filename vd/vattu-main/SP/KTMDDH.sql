USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_KiemTraMaDonDatHang]    Script Date: 5/30/2022 3:12:40 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_KiemTraMaDonDatHang]
	@id nvarchar(15)
AS
BEGIN
	IF( EXISTS(SELECT * FROM DatHang AS DH WHERE DH.MasoDDH = @id) )
		select 1;
	ELSE IF ( EXISTS( SELECT * FROM LINK1.QLVT_DATHANG.DBO.DATHANG AS DH WHERE DH.MasoDDH = @id ) )
		select 1;
	select 0;-- id nhap vao khong ton tai
END

GO

