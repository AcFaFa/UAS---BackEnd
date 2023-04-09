import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/pages/Home";
import SignUp from "./components/pages/SignUp";
import About from "./components/pages/About";
import Books from "./components/pages/Books";
import Admin from "./components/pages/Admin";

// cara jalanin front end
// instal npx create-react-app namanya
// instal ekstensi ES7 React/Redux/Graph
// npm install react-router-dom
// npm install react-bootstrap bootstrap
// npm install bootstrap
// mdb-react-ui-kit
// install extensi moesif CORS agar tidak error jika ngambil data lintas port

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/books" component={Books} />
          <Route path="/about" component={About} />
          <Route path="/signup" component={SignUp} />
          <Route path="/admin" component={Admin} />
        </Switch>
      </Router>
    </>
  );
}
export default App;
