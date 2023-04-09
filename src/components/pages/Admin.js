import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "../../App.css";
import HomeAdmin from "../content/HomeAdmin";
import Navbar1 from "../Navbar1";
import BooksShow1 from "../content/BooksShow1";
import AddBooks from "../crud/AddBooks";
import AnggotaShow from "../content/AnggotaShow";
import PeminjamanShow from "../content/PeminjamanShow";
import PengembalianShow from "../content/PengembalianShow";
import RakShow from "../content/RakShow";

function Admin() {
  return (
    <>
      {/* <h1>Jkkas</h1> */}
      <Router>
        <Navbar1 />
        <Switch>
          {/* <BooksShow1 /> */}
          <Route path="/admin" exact component={HomeAdmin} />
          <Route path="/admin/books" component={BooksShow1} />
          <Route path="/admin/addbook" component={AddBooks} />
          <Route path="/admin/anggota" component={AnggotaShow} />
          <Route path="/admin/peminjaman" component={PeminjamanShow} />
          <Route path="/admin/pengembalian" component={PengembalianShow} />
          <Route path="/admin/rak" component={RakShow} />
        </Switch>
      </Router>
      {/* <BooksShow1 /> */}
      {/* <AnggotaShow />
      <PeminjamanShow />
      <PengembalianShow />
      <RakShow /> */}
    </>
  );
}

export default Admin;
