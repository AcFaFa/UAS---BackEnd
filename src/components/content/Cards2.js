import React from "react";
import CardItem from "./CardItem";
import "./Cards.css";

function Cards2() {
  return (
    <div className="cards">
      <h1>Books is The Best</h1>
      <div className="cards__container">
        <div className="cards__wrapper">
          <ul className="cards__items">
            <CardItem
              src="images/p1.jpg"
              text="Tempat Baca Sambil Duduk"
              label="Duduk"
              path="/rekomendasi"
            />
            <CardItem
              src="images/p2.jpg"
              text="Buku Tersusun Berdasar Rak"
              label="Rak"
              path="/rekomendasi"
            />
          </ul>
          <ul className="cards__items">
            <CardItem
              src="images/p3.jpg"
              text="Buku-Buku Tradisional"
              label="Tradisional"
              path="/rekomendasi"
            />
            <CardItem
              src="images/p4.jpg"
              text="Buku-Buku Dahulu"
              label="Legend"
              path="/rekomendasi"
            />
            <CardItem
              src="images/p5.jpg"
              text="Buku-Buku Modern"
              label="Modern"
              path="/rekomendasi"
            />
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Cards2;
