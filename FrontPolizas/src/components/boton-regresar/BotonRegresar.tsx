import { useNavigate } from 'react-router-dom';
import './BotonRegresar.scss';

export const BotonRegresar = () => {
  const navigate = useNavigate();
  return (
    <>
      <div className="boton-regresar link" onClick={() => navigate(-1)}>
        <div className="material-icons-outlined">chevron_left</div> Regresar
      </div>
    </>
  );
};
