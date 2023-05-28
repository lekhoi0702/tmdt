USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_TraCuu_KiemTraMaKho]    Script Date: 5/30/2022 3:13:50 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_TraCuu_KiemTraMaKho]
	@MAKHO nchar(4)
as
begin
	if( exists( select 1 
				from LINK0.QLVT_DATHANG.DBO.KHO as K 
				where K.MAKHO = @MAKHO ) )
		select 1; -- ton tai
	select 0;-- khong ton tai
end

GO

