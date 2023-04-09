import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";
import { Button } from "./Button";

function Navbar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-container">
          <Link to="/admin/" className="navbar-logo" onClick={closeMobileMenu}>
            Admin Library <i className="fab fa-typo3" />
          </Link>
          <div className="menu-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"} />
          </div>
          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <Link
                to="/admin/books"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Books
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/admin/anggota"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Anggota
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/admin/peminjaman"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Peminjaman
              </Link>
            </li>
            <li>
              <Link
                to="/admin/pengembalian"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                pengembalian
              </Link>
            </li>
          </ul>
          <a href="http://localhost:3000/about" target="blank">
            {button && <Button buttonStyle="btn--outline">Log Out</Button>}
          </a>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
