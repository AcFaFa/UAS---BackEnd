import React, { useState, useEffect } from "react";
import axios from "axios";

const BooksShow1 = () => {
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

  return (
    <div className="columns mt-5 is-centered">
      <div className="column is-half">
        <h1>Tambah Buku</h1>
        <a href="http://localhost:3000/admin/addbook">
          <button className="button is-small is-info">Add Book</button>
        </a>
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
              <th>Actions</th>
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
                <td>
                  <button className="button is-small is-info">Edit</button>
                  <button className="button is-small is-danger">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default BooksShow1;
