import { createSlice } from "@reduxjs/toolkit";
import { clearCart } from "../cart/CartSlice";

const initialState = {
  email: "",
  token: "",
  id: 0,
  role: "",
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setUser: (state, action) => {
      state.email = action.payload.email;
      state.token = action.payload.token;
      state.id = action.payload.id;
      state.role = action.payload.role;
    },
    removeUser: (state) => {
      state.email = "";
      state.token = "";
      state.id = 0;
      state.role = "";
    },
  },
});
export const logoutAndClearCart = () => (dispatch) => {
  dispatch(removeUser());
  dispatch(clearCart());
};
export const { setUser, removeUser } = userSlice.actions;
export default userSlice.reducer;