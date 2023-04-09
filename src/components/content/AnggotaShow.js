import React, { useState, useEffect } from "react";
import axios from "axios";

const Anggota = () => {
  const [users, setUser] = useState([]);

  useEffect(() => {
    getUsers();
  }, []);
  const getUsers = async () => {
    const response = await axios.get("http://localhost:8080/anggota/find-all");
    setUser(response.data);

    const data = await response.json();
    setUser(response.data);
  };

  return (
    <div className="columns mt-5 is-centered">
      <div className="column is-half">
        <h1>Tambah Anggota</h1>
        <a href="http://localhost:3000/admin/Anggota">
          <button className="button is-small is-info">Anggota</button>
        </a>

        <table className="table is-striped is-fullwidth">
          <thead>
            <tr>
              <th>No</th>
              <th>Name</th>
              <th>No HP</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr key={user.id}>
                <td>{index + 1}</td>
                <td>{user.name}</td>
                <td>{user.phoneNumber}</td>
                <td>{user.status}</td>
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

export default Anggota;
