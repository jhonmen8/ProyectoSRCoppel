export interface PolizaProps {
    poliza_id: number;
    estatus: number;
    cantidad: number;
    polizaDetalle?: PolizaDetalleProps[];
}
export interface PolizaDetalleProps {
    polizadetalle_id: number;
    sku_guardar_id: number;
    empleado_guardar_id: number;
    cantidad: number;
    estatus: number;
    sku_id?: SkuProps;
    empleado_id?: EmpleadoProps;
}
export interface SkuProps {
    sku_id: number;
    nombre: string;
    cantidad: number;
}
export interface EmpleadoProps {
    empleado_id: number;
    nombre: string;
    apellido: string;
}