USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_CapNhatSoLuongVatTu]    Script Date: 5/30/2022 3:11:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_CapNhatSoLuongVatTu]
	@CHEDO NVARCHAR(6),
	@MAVT NCHAR(4),
	@SOLUONG INT
AS
BEGIN
	-- NEU XUAT VAT TU RA
	IF( @CHEDO = 'XUAT')
	BEGIN
		IF( EXISTS(SELECT * FROM DBO.Vattu AS VT WHERE VT.MAVT = @MAVT))
			BEGIN
				UPDATE DBO.Vattu
				SET SOLUONGTON = SOLUONGTON - @SOLUONG
				WHERE MAVT = @MAVT
			END
	END

	-- NEU NHAP VAT TU VAO
	IF( @CHEDO = 'NHAP')
	BEGIN
		IF( EXISTS(SELECT * FROM DBO.Vattu AS VT WHERE VT.MAVT = @MAVT) )
			BEGIN
				UPDATE DBO.Vattu
				SET SOLUONGTON = SOLUONGTON + @SOLUONG
				WHERE MAVT = @MAVT
			END
	END
END

GO
