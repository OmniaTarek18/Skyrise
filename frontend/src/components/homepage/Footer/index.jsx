import React from 'react';
import Logo from '../../shared/Logo';
import whiteLogo from "../../../assets/whiteLogo.svg";
import { FaSquareFacebook, FaInstagram, FaLinkedin } from "react-icons/fa6";
import './style.css'

const Footer = () => {
  return (
    <footer className="footer" id='footer'>
      <div className="footer-content">
        <a className="footer-logo" href="#">
          <Logo color={whiteLogo} className="logo navbar-nav"/>
        </a>
        <div className="footer-contact">
          <h5>Contact</h5>
          <ul className="footer-link">
            <li><a href="/about">Address</a></li>
            <li><a href="/contact">Phone Number</a></li>
            <li><a href="/privacy-policy">Email</a></li>
          </ul>
        </div>
        <div className="footer-socail-media">
          <h5>Socail Media Links</h5>
          <ul className="footer-link">
            <li><a href="https://facebook.com" target="_blank" rel="noopener noreferrer"><FaSquareFacebook style={{ fontSize: "2em"}}/></a></li>
            <li><a href="https://instagram.com" target="_blank" rel="noopener noreferrer"><FaInstagram style={{fontSize: "2em"}}/></a></li>
            <li><a href="https://linkedin.com" target="_blank" rel="noopener noreferrer"><FaLinkedin style={{fontSize: "2em"}}/></a></li>
          </ul>
        </div>
        <div className="footer-copywrite">
            <p>© 2024 SkyRise. All rights reserved.</p>
          </div>
      </div>
    </footer>
  );
};

export default Footer;
