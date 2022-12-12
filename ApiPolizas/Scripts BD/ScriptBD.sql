
USE [PolizasCoppel]
GO
IF EXISTS(SELECT '' FROM sysobjects where name='polizas_detalle' and xtype='U')
	drop table [dbo].[polizas_detalle];
GO
IF EXISTS(SELECT '' FROM sysobjects where name='empleados' and xtype='U')
	drop table [dbo].[empleados];
GO
IF EXISTS(SELECT '' FROM sysobjects where name='inventarios' and xtype='U')
	drop table [dbo].[inventarios];
GO
IF EXISTS(SELECT '' FROM sysobjects where name='polizas' and xtype='U')
drop table [dbo].[polizas];
GO

CREATE TABLE [dbo].[inventarios](
	[sku_id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[nombre] [varchar](250) NOT NULL,
	[cantidad] [int] NOT NULL,
	[estatus] [int] NOT NULL,
	[fec_inserta] [datetime2](7) NULL,
	[fec_actualiza] [datetime2](7) NOT NULL,
	[idu_usuario_inserta] [bigint] NULL,
	[idu_usuario_actualiza] [bigint] NULL
	)

GO

CREATE TABLE [dbo].[empleados](
	[empleado_id] [bigint] IDENTITY(1,1) PRIMARY KEY,	
	[nombre] [varchar](100) NOT NULL,
	[apellido] [varchar](100) NOT NULL,	
	[puesto] [varchar](100) NOT NULL,
	[estatus] [int] NOT NULL,
	[fec_inserta] [datetime2](7) NULL,
	[fec_actualiza] [datetime2](7) NOT NULL,
	[idu_usuario_inserta] [bigint] NULL,
	[idu_usuario_actualiza] [bigint] NULL
	)

GO

CREATE TABLE [dbo].[polizas](
	[poliza_id] [bigint] IDENTITY(1,1) PRIMARY KEY,	
	[cantidad] [int] NOT NULL,
	[estatus] [int] NOT NULL,
	[fec_inserta] [datetime2](7) NULL,
	[fec_actualiza] [datetime2](7) NOT NULL,
	[idu_usuario_inserta] [bigint] NULL,
	[idu_usuario_actualiza] [bigint] NULL
	);

GO


CREATE TABLE [dbo].[polizas_detalle](
	[polizadetalle_id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[idu_poliza] [bigint] NOT NULL,
	[empleado_id] [bigint] NOT NULL,
	[sku_id] [bigint] NOT NULL,
	[cantidad] [int] NOT NULL,
	[estatus] [int] NOT NULL,
	[fec_inserta] [datetime2](7) NULL,
	[fec_actualiza] [datetime2](7) NOT NULL,
	[idu_usuario_inserta] [bigint] NULL,
	[idu_usuario_actualiza] [bigint] NULL
	);
GO
ALTER TABLE [dbo].[polizas_detalle]  WITH CHECK ADD CONSTRAINT [FK_idu_poliza] FOREIGN KEY([idu_poliza])
REFERENCES [dbo].[polizas] ([poliza_id])
GO
ALTER TABLE [dbo].[polizas_detalle] CHECK CONSTRAINT [FK_idu_poliza]
GO
ALTER TABLE [dbo].[polizas_detalle]  WITH CHECK ADD  CONSTRAINT [FK_SkuID] FOREIGN KEY([sku_id])
REFERENCES [dbo].[inventarios] ([sku_id])
GO
ALTER TABLE [dbo].[polizas_detalle] CHECK CONSTRAINT [FK_SkuID]
GO
ALTER TABLE [dbo].[polizas_detalle]  WITH CHECK ADD  CONSTRAINT [FK_EmpleadoID] FOREIGN KEY([empleado_id])
REFERENCES [dbo].[empleados] ([empleado_id])
GO
ALTER TABLE [dbo].[polizas_detalle] CHECK CONSTRAINT [FK_EmpleadoID]
GO