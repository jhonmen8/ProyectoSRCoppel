INSERT INTO empleados(nombre,apellido,puesto,estatus,fec_inserta,fec_actualiza,idu_usuario_inserta,idu_usuario_actualiza) VALUES
('JESUS','OJEDA','INTENDENTE',1,GETDATE(),GETDATE(),0,0),
('DELFINO','AVILA','INTENDENTE',1,GETDATE(),GETDATE(),0,0),
('IVAN','OCHOA','BODEGITA',1,GETDATE(),GETDATE(),0,0),
('MARIA','BELTRAN','BODEGITA',1,GETDATE(),GETDATE(),0,0),
('ORLANDO','FELIX','RECEPCION',1,GETDATE(),GETDATE(),0,0),
('ALFREDO','MENDOZA','INTENDENTE',1,GETDATE(),GETDATE(),0,0),
('JOSE','LUNA','CARGADOR',1,GETDATE(),GETDATE(),0,0),
('IRIS','BELTRAN','RECEPCION',1,GETDATE(),GETDATE(),0,0),
('EUGENIA','SOTO','INTENDENTE',1,GETDATE(),GETDATE(),0,0),
('FELIPE','ALVAREZ','CARGADOR',1,GETDATE(),GETDATE(),0,0)

INSERT INTO Inventarios(nombre,cantidad,estatus,fec_inserta,fec_actualiza,idu_usuario_inserta,idu_usuario_actualiza) VALUES
('TRAPEADOR',100,1,GETDATE(),GETDATE(),0,0),
('ESCOBA',100,1,GETDATE(),GETDATE(),0,0),
('RECOGEDOR',100,1,GETDATE(),GETDATE(),0,0),
('PINTARRONES',100,1,GETDATE(),GETDATE(),0,0),
('PIZARRON',100,1,GETDATE(),GETDATE(),0,0),
('GATO HIDRAULICO',100,1,GETDATE(),GETDATE(),0,0),
('PLUMAS',100,1,GETDATE(),GETDATE(),0,0),
('LAPICEROS',100,1,GETDATE(),GETDATE(),0,0),
('LIQUIDO LIMPIA VIDRIOS',100,1,GETDATE(),GETDATE(),0,0),
('GEL ANTIBACTERIAL 5 LITROS',100,1,GETDATE(),GETDATE(),0,0)




--PRUEBA INSERT POLIZAS
insert into [polizas](cantidad,estatus,fec_inserta,fec_actualiza,idu_usuario_inserta,idu_usuario_actualiza) values 
(10,1,GETDATE(),GETDATE(),0,0)

insert into [polizas_detalle](idu_poliza,empleado_id,sku_id,cantidad,estatus,fec_inserta,fec_actualiza,idu_usuario_inserta,idu_usuario_actualiza) values 
(1,1,6,9,1,GETDATE(),GETDATE(),0,0),
(1,2,2,2,1,GETDATE(),GETDATE(),0,0),
(1,3,3,10,1,GETDATE(),GETDATE(),0,0);