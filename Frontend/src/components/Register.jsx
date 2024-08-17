// import Form from "./Form";
// import { useAuth } from "../hooks/useAuth";
// import axios from "axios";
// import { useDispatch } from "react-redux";
// import { useNavigate } from "react-router-dom";
// import { setUser } from "../features/user/UserSlice";
// // import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";

// import React, { useState } from "react";

// const Register = () => {
//   const dispatch = useDispatch();
// const navigate = useNavigate();

//   const [email, setEmail] = useState('');
//   const [password, setPassword] = useState('');

//   const handleSubmit = async (e) => {
//     // e.preventDefault();
//     // try {
//     //   await register(username, password);
//     //   alert("Registration successful! Please log in.");
//     // } catch (error) {
//     //   console.error("Failed to register", error);
//     // }

//     e.preventDefault();

//     try {
//       const response = await axios.post('http://localhost:8080/api/customers', {
//         email,
//         password,
//       });

//       // Assuming the response contains user data and token
//       const { user, token } = response.data;

//       // Dispatch setUser action to store user info in Redux
//       dispatch(setUser({
//         email: user.email,
//         token: token,
//         id: user.id,
//         role: user.role, // or any other role-related data
//       }));

//       alert("Registration successful! Please log in.");
//       navigate('/login'); // Redirect to login page or any other page
//     } catch (error) {
//       console.error("Failed to register", error);
//       alert("Registration failed! Please try again.");
//     }
//   };

//   return (
//     <>
//       {/* <Form title="SIGN UP" handleClick={handleRegister} /> */}
//       <Form title="SIGN UP" handleClick={handleSubmit} />
//     </>
//   );
// };

// export default Register;


// Register.js

import React, { useState } from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setUser } from "../features/user/UserSlice";
import { TextField, Button, Box } from "@mui/material";

const Register = () => {
  // const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState(''); // Assuming email is part of registration
  const [address, setAddress] = useState('');
  const [phone, setPhone] = useState('');
  const [name, setName] = useState('');


  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/customers', {
        password,
        email,
        name,
        phone,
        address,
      });

      // const { user, token } = response.data;
      // dispatch(setUser({ email: user.email, token, id: user.id, role: user.role }));

      alert("Registration successful! Please log in.");
      navigate('/login');

      // if (response.data && response.data.user && response.data.token) {
      //   const { user, token } = response.data;
      //   dispatch(setUser({ email: user.email, token, id: user.id, role: user.role }));

      //   alert("Registration successful! Please log in.");
      //   navigate('/login');
      // } else {
      //   // Handle the case where the response structure is unexpected
      //   console.error("Unexpected response structure", response.data);
      //   alert("Registration failed! Please try again.");
      // }
    } catch (error) {
      console.error("Failed to register", error);
      alert("Registration failed! Please try again.");
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} noValidate>
      <TextField
        label="Email"
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        fullWidth
        margin="normal"
        required
      />
      {/* <TextField
        label="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        fullWidth
        margin="normal"
        required
      /> */}
      <TextField
        label="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        fullWidth
        margin="normal"
        required
      />

      <TextField
        label="address"
        type="address"
        value={address}
        onChange={(e) => setAddress(e.target.value)}
        fullWidth
        margin="normal"
        required
      />
      <TextField
        label="phone"
        type="phone"
        value={phone}
        onChange={(e) => setPhone(e.target.value)}
        fullWidth
        margin="normal"
        required
      />
      <TextField
        label="name"
        type="name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        fullWidth
        margin="normal"
        required
      />

      <Button
        type="submit"
        variant="contained"
        color="primary"
        fullWidth
        sx={{ mt: 2 }}
      >
        Sign Up
      </Button>
    </Box>
  );
};

export default Register;
