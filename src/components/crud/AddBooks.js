import React, { useState } from "react";
import axios from "axios";
// import { useNavigate } from "react-router-dom";

const AddBooks = () => {
  const [title, setTitle] = useState("");
  const [genre, setGenre] = useState("");
  const [stok, setStok] = useState("");
  const [pengarang, setPengarang] = useState("");
  const [penerbit, setPenerbit] = useState("");
  const [rak, setRak] = useState("A001");
  const [status, setStatus] = useState("TERSEDIA");
  const [dateOfEntry, setDateOfEntry] = useState("2023-04-10");
  const [dateOfUpdate, setDateOfUpdate] = useState("2023-04-10");
  // const navigate = useNavigate();

  const saveBook = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/buku/tambah-buku", {
        title,
        genre,
        stok,
        pengarang,
        penerbit,
        rak,
        status,
        dateOfEntry,
        dateOfUpdate,
      });
      // navigate("localhost:3000/books");
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <div className="columns mt-5 is-centered">
      <div className="column is-half">
        <form onSubmit={saveBook}>
          <div className="field">
            <label className="label">Title</label>
            <div className="control">
              <input
                type="text"
                className="input"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="Title"
              />
            </div>
          </div>
          <div className="field">
            <label className="label">Genre</label>
            <div className="control">
              <input
                type="text"
                className="input"
                value={genre}
                onChange={(e) => setGenre(e.target.value)}
                placeholder="Genre"
              />
            </div>
          </div>
          <div className="field">
            <label className="label">Stok</label>
            <div className="control">
              <input
                type="text"
                className="input"
                value={stok}
                onChange={(e) => setStok(e.target.value)}
                placeholder="Stok"
              />
            </div>
          </div>
          <div className="field">
            <label className="label">Pengarang</label>
            <div className="control">
              <input
                type="text"
                className="input"
                value={pengarang}
                onChange={(e) => setPengarang(e.target.value)}
                placeholder="Pengarang"
              />
            </div>
          </div>
          <div className="field">
            <label className="label">Penerbit</label>
            <div className="control">
              <input
                type="text"
                className="input"
                value={penerbit}
                onChange={(e) => setPenerbit(e.target.value)}
                placeholder="Penerbit"
              />
            </div>
          </div>
          <div className="field">
            <label className="label">Rak</label>
            <div className="control">
              <div className="select is-fullwidth">
                <select value={rak} onChange={(e) => setRak(e.target.value)}>
                  <option value="A001">A001</option>
                  <option value="A002">A002</option>
                  <option value="A003">A003</option>
                  <option value="A004">A004</option>
                </select>
              </div>
            </div>
          </div>
          <div className="field">
            <button type="submit" className="button is-succes">
              Save
            </button>
          </div>
        </form>
        <a href="http://localhost:3000/admin/books">
          <button className="button is-small is-info">Show Books</button>
        </a>
      </div>
    </div>
  );
};

export default AddBooks;
