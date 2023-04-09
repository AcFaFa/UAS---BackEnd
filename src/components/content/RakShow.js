import React, { useState, useEffect } from "react";
import axios from "axios";

const RakShow = () => {
  const [users, setUser] = useState([]);

  useEffect(() => {
    getUsers();
  }, []);
  const getUsers = async () => {
    const response = await axios.get("http://localhost:8080/rak/find-all");
    setUser(response.data);

    const data = await response.json();
    setUser(response.data);
  };

  return (
    <div className="columns mt-5 is-centered">
      <div className="column is-half">
        <h1>Tambah Buku</h1>
        <a href="http://localhost:3000/admin/rak">
          <button className="button is-small is-info">Rak</button>
        </a>

        <table className="table is-striped is-fullwidth">
          <thead>
            <tr>
              <th>No</th>
              <th>Genre</th>
              <th>Type</th>
              <th>Rack Code</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr key={user.id}>
                <td>{index + 1}</td>
                <td>{user.genre}</td>
                <td>{user.type}</td>
                <td>{user.rackCode}</td>
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

export default RakShow;
