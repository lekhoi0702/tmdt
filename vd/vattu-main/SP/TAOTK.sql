USE [QLVT_DATHANG]
GO

/****** Object:  StoredProcedure [dbo].[sp_TaoTaiKhoan]    Script Date: 5/30/2022 3:13:20 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_TaoTaiKhoan] @LGNAME VARCHAR(50),
	@PASS VARCHAR(50),
	@USERNAME VARCHAR(50),
	@ROLE VARCHAR(50)
AS
BEGIN
  DECLARE @RET INT
  EXEC @RET= SP_ADDLOGIN @LGNAME, @PASS,'QLVT_DATHANG'                     

  IF (@RET =1)  -- LOGIN NAME BI TRUNG
  begin
     select 1
	 RAISERROR ('Login name bị trùng', 16,1)
end
  EXEC @RET= SP_GRANTDBACCESS @LGNAME, @USERNAME
  IF (@RET =1)  -- USER  NAME BI TRUNG
  BEGIN
       EXEC SP_DROPLOGIN @LGNAME
	   RAISERROR ('Login name bị trùng', 16,2)
       select 2
  END
  EXEC sp_addrolemember @ROLE, @USERNAME

  IF @ROLE= 'CONGTY' 
	BEGIN
	select 0
	EXEC sp_addsrvrolemember @LGNAME, 'DBCREATOR'
		EXEC sp_addsrvrolemember @LGNAME, 'SecurityAdmin'
		EXEC sp_addsrvrolemember @LGNAME, 'ProcessAdmin'
	END

  IF @ROLE= 'CHINHANH'
	BEGIN 
	select 0
		EXEC sp_addsrvrolemember @LGNAME, 'sysadmin'
	END
  IF @ROLE= 'USER'
	BEGIN  
	select 0
		EXEC sp_addsrvrolemember @LGNAME, 'DBCREATOR'
		EXEC sp_addsrvrolemember @LGNAME, 'ProcessAdmin'
	END

END
GO

