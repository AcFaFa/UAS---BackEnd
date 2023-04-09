import React from "react";
import CardItem from "./CardItem";
import "./Cards.css";

function Cards() {
  return (
    <div className="cards">
      <h1>Popular books</h1>
      <div className="cards__container">
        <div className="cards__wrapper">
          <ul className="cards__items">
            <CardItem
              src="images/1.jpg"
              text="Sebuah Seni Untuk Bersikap Bodo Amat"
              label="Seni"
              path="/rekomendasi"
            />
            <CardItem
              src="images/2.jpg"
              text="Aku Tau Kapan Kamu Mati"
              label="Horror"
              path="/rekomendasi"
            />
          </ul>
          <ul className="cards__items">
            <CardItem
              src="images/3.jpg"
              text="Segala Galanya Ambyar"
              label="Live"
              path="/rekomendasi"
            />
            <CardItem
              src="images/4.jpg"
              text="MADILOG"
              label="Biografi"
              path="/rekomendasi"
            />
            <CardItem
              src="images/5.jpg"
              text="Popular Culture"
              label="Budaya"
              path="/rekomendasi"
            />
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Cards;
