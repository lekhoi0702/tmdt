USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_DangNhap]    Script Date: 5/30/2022 3:11:52 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_DangNhap] @TENLOGIN NVARCHAR( 100)
AS
	DECLARE @UID INT
	DECLARE @MANV NVARCHAR(100)
	SELECT @UID= uid , @MANV= NAME FROM sys.sysusers 
  	WHERE sid = SUSER_SID(@TENLOGIN)

	SELECT TOP 1  MANV=@MANV, 
       		HOTEN = (SELECT  TOP 1 HO+ ' '+TEN FROM dbo.NHANVIEN WHERE MANV=@MANV ), 
       		TENNHOM=NAME
  	FROM sys.sysusers
    	WHERE UID = (SELECT TOP 1 groupuid FROM sys.sysmembers WHERE memberuid=@uid)


GO

