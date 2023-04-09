import React, { useState, useEffect } from "react";
import axios from "axios";

const PengembalianShow = () => {
  const [users, setUser] = useState([]);

  useEffect(() => {
    getUsers();
  }, []);
  const getUsers = async () => {
    const response = await axios.get(
      "http://localhost:8080/pengembalian/find-all"
    );
    setUser(response.data);

    const data = await response.json();
    setUser(response.data);
  };

  return (
    <div className="columns mt-5 is-centered">
      <div className="column is-half">
        <h1>Tambah Pengembalian</h1>
        <a href="http://localhost:3000/admin/pengembalian">
          <button className="button is-small is-info">Pengembalian</button>
        </a>

        <table className="table is-striped is-fullwidth">
          <thead>
            <tr>
              <th>No</th>
              <th>Tanggal Pinjam</th>
              <th>Tanggal Kembali</th>
              <th>Anggota</th>
              <th>Buku</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr key={user.id}>
                <td>{index + 1}</td>
                <td>{user.dateOfEntry}</td>
                <td>{user.returnDate}</td>
                <td>{user.anggota.name}</td>
                <td>{user.buku.name}</td>
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

export default PengembalianShow;
