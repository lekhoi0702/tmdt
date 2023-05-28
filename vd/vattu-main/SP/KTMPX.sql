USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_KiemTraMaPhieuXuat]    Script Date: 5/30/2022 3:13:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_KiemTraMaPhieuXuat] @MAPX nChar(8)
AS
BEGIN
	if( EXISTS( SELECT * FROM DBO.PhieuXuat AS PX WHERE PX.MAPX = @MAPX))
		select 1;-- ton tai o chi nhanh hien tai
	ELSE IF( EXISTS( SELECT * FROM LINK1.QLVT_DATHANG.DBO.PhieuXuat AS PX WHERE PX.MAPX = @MAPX) )
		select 1;-- ton tai o chi nhanh khac 
	select 0;
END

GO

