import './Main.scss';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { NavBar } from '../../components/nav-bar/NavBar';
import { Col, Container, Row } from 'react-bootstrap';
import { Footer } from '../../components/footer/Footer';
import { SideBar } from '../../components/side-bar/SideBar';
import { PolizasDetalle } from '../polizas-detalle/PolizasDetalle';
import { Polizas } from '../polizas/Polizas';
import env from '../../env.json';

export const Main = () => {
  const rutaServidor = "/ProyectoCoppel"; //produccion
  return (
    <>
      <NavBar></NavBar>
      <Container className="p-0 d-flex" fluid>
        <Router>
          <SideBar></SideBar>
          <Row className="main-content">
            <Col className="pb-3">
              <Routes>
                <Route path={env.RUTA_SERVIDOR + "/"} element={<Polizas />} />
                <Route path={env.RUTA_SERVIDOR + "/poliza"} element={<PolizasDetalle />} />
                <Route path={env.RUTA_SERVIDOR + "/poliza/:id"} element={<PolizasDetalle />} />
              </Routes>
            </Col>
          </Row>
        </Router>
      </Container>
      <Footer></Footer>
    </>
  );
};
