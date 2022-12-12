import './PolizasDetalle.scss';
import React from 'react'
import { useEffect, useState } from 'react';
import { Row, Col } from 'react-bootstrap';
import Table from 'react-bootstrap/Table';
import { Button } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { apiRequestToken } from '../../Utilidades/apiRequest';
import { PolizaProps, PolizaDetalleProps, SkuProps, EmpleadoProps } from '../../interfaces/polizaInterface';
import { useParams } from 'react-router-dom';
import StatusAlert, { StatusAlertService } from 'react-status-alert';
import 'react-status-alert/dist/status-alert.css';

export const PolizasDetalle = () => {
  const [empleadoid, setEmpleadoid] = useState('');
  const [nombreEmpleado, setNombreEmpleado] = useState('');
  const [articuloid, setArticuloid] = useState('');
  const [nombreArticulo, setNombreArticulo] = useState('');
  const [polizaid, setPolizaid] = useState('');
  const [cantidad, setCantidad] = useState('');
  const [polizas, setPolizas] = useState<PolizaProps>();
  const [polizaDetalle, setPolizaDetalle] = useState<PolizaDetalleProps[]>([]);
  const [body, setBody] = useState<any>({});
  const [cargado, setCargado] = useState<boolean>(false);
  const [cargadoPrincipal, setCargadoPrincipal] = useState<boolean>(false);

  const params = useParams();
  let idpoliza = params.id;

  useEffect(() => {
    if (idpoliza !== '0' && !cargadoPrincipal) {
      setCargadoPrincipal(true);
      setPolizaid(idpoliza!);
      BuscarPolizas(idpoliza!);
    }
  });


  const BuscarEmpleado = async () => {
    let endpo = empleadoid !== '' ? empleadoid : '0';
    const response: any = await apiRequestToken({
      endpoint: 'empleados/' + endpo, body, method: 'GET'
    }).catch(({ response }) => {
      const { data } = response;
      setNombreEmpleado('');
      setEmpleadoid('');
      StatusAlertService.showError(data.meta.message)
    });
    if (response.data && response.data.data) {
      let nombre = response.data.data['nombre'] + ' ' + response.data.data['apellido'];
      setNombreEmpleado(nombre);
    }
  };

  const BuscarArticulo = async () => {
    let endpo = articuloid !== '' ? articuloid : '0';
    const response: any = await apiRequestToken({
      endpoint: 'inventarios/' + endpo, body, method: 'GET'
    }).catch(({ response }) => {
      const { data } = response;
      setNombreArticulo('');
      setArticuloid('');
      StatusAlertService.showError(data.meta.message)
    });
    if (response.data && response.data.data) {
      let nombre = response.data.data['cantidad'] + '-' + response.data.data['nombre'];
      setNombreArticulo(nombre);
    }
  };

  const BuscarPolizas = async (idp: string) => {
    let endpo = idp;
    if (idp === '') {
      endpo = polizaid !== '' ? polizaid : '0';
    }
    setCargado(false);
    const response: any = await apiRequestToken({
      endpoint: 'polizas/' + endpo, body, method: 'GET'
    }).catch(({ response }) => {
      const { data } = response;
      setPolizaid('');
      const polDetalle: PolizaDetalleProps[] = [];
      setPolizaDetalle(polDetalle);
      setCargado(true);
      StatusAlertService.showError(data.meta.message)
    });
    if (response.data && response.data.data) {
      setPolizas(response.data.data);
      setPolizaDetalle(response.data.data.polizaDetalle);
      setCargado(true);
    }
  };

  const GuardarPoliza = async () => {
    let poliz = {} as PolizaProps;
    let sku = {} as SkuProps;
    let empleado = {} as EmpleadoProps;
    let polDetalle = {} as PolizaDetalleProps;

    sku.sku_id = Number(articuloid !== '' ? articuloid : '0');
    empleado.empleado_id = Number(articuloid !== '' ? articuloid : '0');

    polDetalle.polizadetalle_id = 0;
    polDetalle.cantidad = Number(cantidad !== '' ? cantidad : '0');
    polDetalle.empleado_id = {} as EmpleadoProps;
    polDetalle.empleado_id = empleado;
    polDetalle.sku_id = {} as SkuProps;
    polDetalle.sku_id = sku;
    polDetalle.estatus = 1;

    poliz.poliza_id = Number(polizaid !== '' ? polizaid : '0');
    poliz.estatus = 1;
    poliz.cantidad = Number(cantidad !== '' ? cantidad : '0');
    poliz.polizaDetalle = [];

    poliz.polizaDetalle?.push(polDetalle);

    //setBody(poliz);
    //console.log('poliz:', poliz);
    //console.log('body:', body);
    //const bod = poliz as any;

    let metodo = polizaid !== '' ? 'PUT' : 'POST';
    let endpo = metodo !== 'PUT' ? '' : polizaid;

    if(articuloid === '')
    {
      StatusAlertService.showError("Favor de Capturar la informacion")
    }
    else{
      const response: any = await apiRequestToken({
        endpoint: 'polizas/' + endpo, body: poliz, method: metodo
      }).catch(({ response }) => {
        const { data } = response;
        StatusAlertService.showError(data.meta.message)
      });
      if (response.data && response.data.data) {
        setPolizaid(response.data.data.poliza_id);
        setNombreArticulo('');
        setArticuloid('');
        setNombreEmpleado('');
        setEmpleadoid('');
        setCantidad('');
        BuscarPolizas(response.data.data.poliza_id);
        StatusAlertService.showSuccess('Guardado Exitosamente')
      }
    }
  };

  const BorrarPolizaDetalle = async (idp: string, index: number) => {
    setCargado(false);
    const response: any = await apiRequestToken({
      endpoint: 'polizasdetalle/' + idp, body, method: 'DELETE'
    }).catch(({ response }) => {
      const { data } = response;
      StatusAlertService.showError(data.meta.message)
    });
    if (response.data && response.data.data) {
      polizaDetalle.splice(index, 1);
      setPolizas(polizas);
      setCargado(true);
      StatusAlertService.showSuccess('Borrado Exitosamente')
    }
  };

  const handleChangeEmpleado = (event: any) => {
    setEmpleadoid(event.target.value);
  };
  const handleChangeArticulo = (event: any) => {
    setArticuloid(event.target.value);
  };
  const handleChangePoliza = (event: any) => {
    setPolizaid(event.target.value);
  };
  const handleCantidad = (event: any) => {
    setCantidad(event.target.value);
  };

  const enterPoliza = (target: any) => {
    if (target.key === 'Enter') {
      target.preventDefault();
      BuscarPolizas('');
    }
  };

  const enterArticulo = (target: any) => {
    if (target.key === 'Enter') {
      target.preventDefault();
      BuscarArticulo();
    }
  };

  const enterEmpleado = (target: any) => {
    if (target.key === 'Enter') {
      target.preventDefault();
      BuscarEmpleado();
    }
  };
  const enterCantidad = (target: any) => {
    if (target.key === 'Enter') {
      target.preventDefault();
    }
  };

  return (
    <>
      <StatusAlert />
      <div className="m-5">
        <Row>
          <Col className="heading-1">Detalle Poliza</Col>
        </Row>
        <div className="d-flex flex-row-reverse bd-highlight mb-3">
          <Button variant="primary" className="float-end" onClick={() => GuardarPoliza()}>
            Agregar
          </Button>
        </div>
        <Row className="mb-3">
          <Col sm={2} className="mb-2">
            <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="#Poliza"
                aria-label="Search"
                className="cajablanca"
                value={polizaid}
                onKeyPress={enterPoliza}
                onChange={handleChangePoliza}
              />
              <InputGroup.Text className="material-icons cursospointer" onClick={() => BuscarPolizas('')}>search</InputGroup.Text>
            </Form>
          </Col>
          <Col sm={3} className="minimocolumna mb-2">
            <Form className="d-flex">
              <Form.Control
                type="search"
                aria-label="Search"
                className="colorgray2 cajadesactivada"
                placeholder="Articulo"
                value={nombreArticulo}
              />
              <Form.Control className="cajabusqueda" value={articuloid}
                type="number"
                onKeyPress={enterArticulo}
                onChange={handleChangeArticulo} />
              <InputGroup.Text className="material-icons cursospointer" onClick={() => BuscarArticulo()}>search</InputGroup.Text>
            </Form>
          </Col>
          <Col sm={3} className="minimocolumna mb-2">
            <Form className="d-flex">
              <Form.Control
                type="search"
                aria-label="Search"
                className="colorgray2 cajadesactivada"
                placeholder="Empleado"
                value={nombreEmpleado}
              />
              <Form.Control className="cajabusqueda" value={empleadoid}
                type="number"
                onKeyPress={enterEmpleado}
                onChange={handleChangeEmpleado} />
              <InputGroup.Text className="material-icons cursospointer" onClick={() => BuscarEmpleado()}>search</InputGroup.Text>
            </Form>
          </Col>
          <Col sm={2} className="">
            <Form className="d-flex">
              <Form.Control type="number" className="cajacantidad" placeholder="Cantidad"
                value={cantidad}
                onKeyPress={enterCantidad}
                onChange={handleCantidad} />
            </Form>
          </Col>
        </Row>
        <Table responsive>
          <thead className="colorgray2">
            <tr>
              <th className="borderradiuslefttop borderradiusleftbuttom">#Poliza Detalle</th>
              <th>Empleado</th>
              <th>Articulo</th>
              <th>Cantidad</th>
              <th className="borderradiusrigthtop borderradiusrigthbuttom">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {polizaDetalle.length > 0 ?
              polizaDetalle.map((poliza, index) => (
                <tr key={poliza.polizadetalle_id} className="colorgray2">
                  <td className={index === 0 ? 'borderradiuslefttop' : index === polizaDetalle.length - 1 ? 'borderradiusleftbuttom' : ''}>{poliza['polizadetalle_id']}</td>
                  <td>{poliza.empleado_id!.nombre + ' ' + poliza.empleado_id!.apellido}</td>
                  <td>{poliza.sku_id!.nombre}</td>
                  <td >{poliza.cantidad}</td>
                  <td className={index === 0 ? 'borderradiusrigthtop' : index === polizaDetalle.length - 1 ? 'borderradiusrigthbuttom' : ''} width="140">
                    <Button variant="secondary" className='btntable me-2'
                      onClick={() => BorrarPolizaDetalle(String(poliza.polizadetalle_id), index)}>
                      <div className="material-icons">delete</div>
                    </Button>
                  </td>
                </tr>
              ))
              : cargado ? <tr className="colorgray2"><td className="nodatos borderradiuslefttop borderradiusleftbuttom borderradiusrigthtop borderradiusrigthbuttom" colSpan={4}>No Existen Datos</td></tr>
                : <tr></tr>
            }
          </tbody>
        </Table>
      </div>
    </>
  );
};
