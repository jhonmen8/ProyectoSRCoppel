import './NavBar.scss';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import logo from '../../assets/images/logo.png';
import env from '../../env.json';

export const NavBar = () => {
  const clientId = env.API_CLIENTID_GOOGLE;
  return (
    <>
      <Navbar expand="lg" sticky="top" className="shadow mb-0">
        <Container fluid className="px-5">
          <img src={logo} />
        </Container>
      </Navbar>
    </>
  );
};
