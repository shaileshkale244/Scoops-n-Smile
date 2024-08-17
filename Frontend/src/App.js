import React from "react";
import Navbar from "./components/Navbar";
import HomePage from "./pages/HomePage";
import Footer from "./components/Footer";
import { Routes, Route } from "react-router";
import ProductsPage from "./pages/ProductsPage";
import ProductsItemPage from "./pages/ProductsItemPage";
import CartPage from "./pages/CartPage";
import AccountPage from "./pages/AccountPage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ErrorPage from "./pages/ErrorPage";
import DeliveryPersons from "./pages/admin/DeliveryPersons";
import DeliveriesList from "./pages/DeliveriesList";
import Products from "./pages/admin/Products";
import Orders from "./pages/admin/Orders";
import Header from "./components/admin/Header"
import Sidebar from "./components/admin/Sidebar"
import { Container } from '@mui/material';

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/products" element={<ProductsPage />} />
        <Route path="/products/:id" element={<ProductsItemPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/account" element={<AccountPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/waiting" element={<ErrorPage />} />
        <Route path="/delivery" element={<DeliveriesList />} />

        <Route
          path="/admin/*"
          element={
            <>
              <Header />
              <Sidebar />
              <Container style={{ marginLeft: 240, marginTop: 64 }}>
                <Routes>
                  <Route path="delivery-persons" element={<DeliveryPersons />} />
                  <Route path="products" element={<Products />} />

                  <Route path="orders" element={<Orders />} />
                </Routes>
              </Container>
            </>
          }
        />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
