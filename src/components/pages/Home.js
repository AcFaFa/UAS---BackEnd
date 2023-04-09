import React from "react";
import "../../App.css";
import HeroSection from "../content/HeroSection";
import Footer from "../content/Footer";
import Cards from "../content/Cards";
import Batasan from "../content/Batasan";
import Cards1 from "../content/Cards1";
import Navbar from "../Navbar";

function Home() {
  return (
    <>
      {/* <h1>HelloHome</h1> */}
      <Navbar />
      <HeroSection />
      <Cards />
      <Batasan />
      <Cards1 />
      <Footer />
    </>
  );
}

export default Home;
