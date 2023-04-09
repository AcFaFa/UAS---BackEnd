import React, { useState, useEffect } from "react";
import axios from "axios";

const BooksShow = () => {
  // wahyu
  // const [dosen, setDosen] = useState([]);
  // useEffect(() => {
  //   const fetchata = async () => {
  //     const response = await fetch("http://localhost:8080/buku/find-all");
  //     const data = await response.json();
  //     setDosen(data);
  //     console.log(data);
  //   };
  //   // Call the function
  //   fetchata();
  // }, []);
  // angggi
  // const [movie, setMovie] = useState({});

  // useEffect(() => {
  //   window.scrollTo(0, 0);
  //   fetch(`http://localhost:8080/buku/find-all`)
  //     .then((response) => response.json())
  //     .then((movie) => setMovie(movie));
  //     console.log(movie);
  // }, []);
  // Aku
  const [users, setUser] = useState([]);

  useEffect(() => {
    getUsers();
  }, []);
  const getUsers = async () => {
    const response = await axios.get("http://localhost:8080/buku/find-all");
    setUser(response.data);

    const data = await response.json();
    setUser(response.data);
  };
  // cara 4

  return (
    <div className="columns mt-5 is-centered">
      <div className="column is-half">
        <table className="table is-striped is-fullwidth">
          <thead>
            <tr>
              <th>No</th>
              <th>Title</th>
              <th>Status</th>
              <th>Genre</th>
              <th>Tanggal Masuk</th>
              <th>Tanggal Update</th>
              <th>Stock</th>
              <th>Pengarang</th>
              <th>Penerbit</th>
              <th>Rak</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr key={user.id}>
                <td>{index + 1}</td>
                <td>{user.title}</td>
                <td>{user.status}</td>
                <td>{user.genre}</td>
                <td>{user.dateOfEntry}</td>
                <td>{user.dateOfUpdate}</td>
                <td>{user.stock}</td>
                <td>{user.pengarang.name}</td>
                <td>{user.penerbit.name}</td>
                <td>{user.rak.rackCode}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default BooksShow;
