import Container from 'react-bootstrap/Container';
import { Row, Col, Button, Tooltip, OverlayTrigger } from 'react-bootstrap';
import './SideBar.scss';
import { Link, useLocation } from 'react-router-dom';
import React from 'react';
import env from '../../env.json';

export const SideBar = () => {

  let location = useLocation();

  const isRouteActive = (path: string) => location.pathname === path;

  const selectedClass = (path: string) =>
    isRouteActive(path) ? 'selected' : '';

  return (
    <>
      <div className="sidebar">
        <div className="avatar mt-4">
          <div className="profile-photo"></div>
        </div>
        <Link to={env.RUTA_SERVIDOR + '/'}>
          <OverlayTrigger
            placement="right"
            overlay={<Tooltip>Principal</Tooltip>}
          >
            <Button
              className={'mt-5 ' + selectedClass('/')}
              variant={isRouteActive(env.RUTA_SERVIDOR + '/') ? 'red-light' : 'light'}
            >
              <div className="material-icons-outlined">home</div>
            </Button>
          </OverlayTrigger>
        </Link>
        <Link to={env.RUTA_SERVIDOR + '/poliza/0'}>
          <OverlayTrigger
            placement="right"
            overlay={<Tooltip>Nueva Poliza</Tooltip>}
          >
            <Button
              className={'mt-3 ' + selectedClass('/poliza')}
              variant={
                isRouteActive(env.RUTA_SERVIDOR + '/poliza/:id') ? 'red-light' : 'light'
              }
            >
              <div className="material-icons-outlined">note_add</div>
            </Button>
          </OverlayTrigger>
        </Link>
      </div>
    </>
  );
};
