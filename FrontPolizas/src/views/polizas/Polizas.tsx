import './Polizas.scss';
import { useEffect, useState } from 'react';
import { apiRequestToken } from '../../Utilidades/apiRequest';
import { Row, Col } from 'react-bootstrap';
import Table from 'react-bootstrap/Table';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import StatusAlert, { StatusAlertService } from 'react-status-alert'
import 'react-status-alert/dist/status-alert.css'
import env from '../../env.json';

export const Polizas = () => {
  const [body, setBody] = useState<any>({});
  const [cargado, setCargado] = useState<boolean>(false);
  const [polizas, setPolizas] = useState([]);

  useEffect(() => {
    apiRequestToken({
      endpoint: 'polizas', body, method: 'GET'
    })
      .then((response) => {
        if (response.data && response.data.data) {
          setCargado(true);
          setPolizas(response.data.data);
        }
      });
  }, ['polizas', body]);

  const BorrarPoliza = async (idp: string, index: number) => {
    setCargado(false);
    const response: any = await apiRequestToken({
      endpoint: 'polizas/' + idp, body, method: 'DELETE'
    }).catch(({ response }) => {
      const { data } = response;
      StatusAlertService.showError(data.meta.message)
    });
    if (response.data && response.data.data) {
      polizas.splice(index, 1);
      setPolizas(polizas);
      setCargado(true);
      StatusAlertService.showSuccess('Borrado Exitosamente')
    }
  };

  return (
    <>
      <StatusAlert />
      <div className="m-5">
        <Row>
          <Col className="heading-1">Polizas</Col>
        </Row>
        <div className="d-flex flex-row-reverse bd-highlight mb-3">
          <Link to={env.RUTA_SERVIDOR + '/poliza/0'}>
            <Button variant="primary" className="float-end">
              Crear nueva poliza
            </Button>
          </Link>
        </div>
        <Table responsive>
          <thead className="colorgray2">
            <tr>
              <th className="borderradiuslefttop borderradiusleftbuttom"># Poliza</th>
              <th>Cantidad</th>
              <th>Fecha de creación</th>
              <th className="borderradiusrigthtop borderradiusrigthbuttom">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {polizas.length > 0 ?
              polizas.map((poliza, index) => (
                <tr key={poliza['poliza_id']} className="colorgray2">
                  <td className={index === 0 ? 'borderradiuslefttop' : index === polizas.length - 1 ? 'borderradiusleftbuttom' : ''}>{poliza['poliza_id']}</td>
                  <td>{poliza['cantidad']}</td>
                  <td>{poliza['fecInserta']}</td>
                  <td className={index === 0 ? 'borderradiusrigthtop' : index === polizas.length - 1 ? 'borderradiusrigthbuttom' : ''} width="140">
                    <Button variant="secondary" className='btntable me-2'
                      onClick={() => BorrarPoliza(poliza['poliza_id'], index)}>
                      <div className="material-icons">delete</div>
                    </Button>
                    <Link to={env.RUTA_SERVIDOR + '/poliza/' + poliza['poliza_id']}>
                      <Button variant="secondary" className='btntable'>
                        <div className="material-icons">edit</div>
                      </Button>
                    </Link>
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
