import React from "react";
import { Button } from "../Button";
import "./Footer.css";
import { Link } from "react-router-dom";

function Footer() {
  return (
    <div className="footer-container">
      <section className="footer-subscription">
        <p className="footer-subscription-heading">Ingin Banyak Ilmu?</p>
        <p className="footer-subscription-text">Ayo Gabung!</p>
        <div className="input-areas">
          <form>
            {/* <input
              type="email"
              name="email"
              placeholder="Your
                        Email"
              className="footer-input"
            /> */}
            <Button buttonStyle="btn--outline">Subrek</Button>
          </form>
        </div>
      </section>
      <div className="footer-links">
        <div className="footer-link-wrapper">
          <div className="footer-link-items">
            <h2>Achmad Farhan</h2>
            <Link to="/sign-up">Manajer</Link>
            <Link to="/">World Window Book</Link>
            <Link to="/">Data Analyst</Link>
            <Link to="/">Investors</Link>
            <Link to="/">081123123123</Link>
          </div>
          <div className="footer-link-items">
            <h2>Ahmad Faisal</h2>
            <Link to="/sign-up">Direktur</Link>
            <Link to="/">Thanks Library!</Link>
            <Link to="/">Developer</Link>
            <Link to="/">Investors</Link>
            <Link to="/">081222333444</Link>
          </div>
        </div>
        <div className="footer-link-wrapper">
          <div className="footer-link-items">
            <h2>Wahyu Dodo</h2>
            <Link to="/sign-up">Manajer</Link>
            <Link to="/">Sangat Bermanfaat!</Link>
            <Link to="/">Front-End</Link>
            <Link to="/">Investors</Link>
            <Link to="/">082333444111</Link>
          </div>
          <div className="footer-link-items">
            <h2>Dimas Firman</h2>
            <Link to="/sign-up">Direktur</Link>
            <Link to="/">Is Good!</Link>
            <Link to="/">Full-Stack</Link>
            <Link to="/">Investors</Link>
            <Link to="/">082888777666</Link>
          </div>
        </div>
      </div>
      <section class="social-media">
        <div class="social-media-wrap">
          <div class="footer-logo">
            <h1>Over Thinking</h1>
          </div>
          <small class="website-rights">Library © 2023</small>
          <div class="social-icons">
            <Link
              class="social-icon-link facebook"
              to="/"
              target="_blank"
              aria-label="Facebook"
            >
              <i class="fab fa-facebook-f" />
            </Link>
            <Link
              class="social-icon-link instagram"
              to="/"
              target="_blank"
              aria-label="Instagram"
            >
              <i class="fab fa-instagram" />
            </Link>
            <Link
              class="social-icon-link youtube"
              to="/"
              target="_blank"
              aria-label="Youtube"
            >
              <i class="fab fa-youtube" />
            </Link>
            <Link
              class="social-icon-link twitter"
              to="/"
              target="_blank"
              aria-label="Twitter"
            >
              <i class="fab fa-twitter" />
            </Link>
            <Link
              class="social-icon-link twitter"
              to="/"
              target="_blank"
              aria-label="LinkedIn"
            >
              <i class="fab fa-linkedin" />
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Footer;
