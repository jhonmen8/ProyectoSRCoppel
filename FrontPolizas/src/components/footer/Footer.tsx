import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import './Footer.scss';
import { Row, Col } from 'react-bootstrap';

export const Footer = () => {
  return (
    <>
      <Navbar expand="lg" className="footer">
        <Container className="px-5 py-2 justify-items-center">
          <Row className="w-100">
            <Col xs={12} className="text-center">
              <a href="asdf">Terminos y Condiciones</a>
            </Col>
            <Col xs={12} className="text-center">
              <a href="asdf">Aviso de Privacidad</a>
            </Col>
          </Row>
        </Container>
      </Navbar>
    </>
  );
};
