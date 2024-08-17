import Form from "./Form";
import { useDispatch } from "react-redux";
import { setUser } from "../features/user/UserSlice";
// import {
//   getAuth,
//   signInWithEmailAndPassword,
//   signInWithPopup,
//   GoogleAuthProvider,
// } from "firebase/auth";
import { useNavigate } from "react-router-dom";
// import { provider } from "../firebase";
import { Button, Typography, Box } from "@mui/material";
// import { Google } from "@mui/icons-material";
// import { Colors } from "../styles/theme/theme";
import axios from "axios";

const Login = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogin = async (email, password) => {
    try {
      // Send a POST request to your backend API for login
      const response = await axios.post('http://localhost:8080/authenticate', { email, password });

      // Assuming your backend returns a user object and a token
      const { user, token, role } = response.data;
      console.log(response.data)
      // Dispatch the user details to Redux store
      dispatch(
        setUser({
          email: user.email,
          id: user.id,
          token: token,
          role: user.authorities[0].authority
        })
      );




      // Store the token in localStorage for future API requests
      localStorage.setItem('token', token);

      console.log(role);

      // Redirect to the homepage or another protected route
      switch (role) {
        case "ROLE_ADMIN": navigate("/admin/products")
          break;
        case "ROLE_CUSTOMER": navigate("/")
          break;
        case "ROLE_DELIVERY": navigate("/delivery")
          break;
        default: navigate("/")
          break;
      }
    } catch (error) {
      alert("User Does not Exist or wrong credentials!");
      console.log(error.response ? error.response.data : error.message);
    }

  }
  return (
    <Box>
      {/* <Form title="LOGIN" handleClick={handleLogin} /> */}
      <Form title="LOGIN" handleClick={handleLogin} />
      <Typography variant="subtitle2">OR</Typography>
    </Box>
  );
};

export default Login;
