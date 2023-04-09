import React from "react";
import CardItem from "./CardItem";
import "./Cards.css";

function Cards1() {
  return (
    <div className="cards">
      <h1>Books is The Best</h1>
      <div className="cards__container">
        <div className="cards__wrapper">
          <ul className="cards__items">
            <CardItem
              src="images/6.jpg"
              text="Pramoedya"
              label="Seni"
              path="/rekomendasi"
            />
            <CardItem
              src="images/7.jpg"
              text="Artikel Ilmiah"
              label="Karya"
              path="/rekomendasi"
            />
          </ul>
          <ul className="cards__items">
            <CardItem
              src="images/8.jpg"
              text="Proses Kreatif Menulis"
              label="Seni"
              path="/rekomendasi"
            />
            <CardItem
              src="images/9.jpg"
              text="Melangkah"
              label="Horror"
              path="/rekomendasi"
            />
            <CardItem
              src="images/10.jpg"
              text="Dia Dimana-mana"
              label="Biografi"
              path="/rekomendasi"
            />
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Cards1;
