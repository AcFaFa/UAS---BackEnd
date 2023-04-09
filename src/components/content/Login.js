import React, { useState, useEffect } from "react";
import "./Login.css";

function Register() {
  return (
    <>
      <div id="fullBg" />
      <div class="container">
        <form class="form-signin" action="http://localhost:3000/admin">
          <h1 class="form-signin-heading">Please Login</h1>
          <input
            type="text"
            class="form-control"
            name="username"
            placeholder="Username"
            required=""
            autofocus=""
          />
          <input
            type="password"
            class="form-control"
            name="password"
            placeholder="Password"
            required=""
          />
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Login
          </button>
        </form>
      </div>
    </>
  );
}

export default Register;
