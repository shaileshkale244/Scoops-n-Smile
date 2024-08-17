import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Card,
  Container,
  CardContent,
  CardMedia,
  Typography,
  CardActions,
  Button,
  Box,
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import {
  addProduct,
  deleteProduct,
  deleteAllProduct,
  clearCart,selectProductIdsAndQuantities
} from '../features/cart/CartSlice';
import { AmountButtons } from '../styles/buttons/buttons';
import { PageContainer } from '../styles/page/containers';
import { Colors } from '../styles/theme/theme';
import { useNavigate } from 'react-router';
import { useAuth } from '../hooks/useAuth';


const CartPage = () => {
  const { cartProducts } = useSelector((state) => state.cart);
  const { email, id, role } = useSelector((state) => state.user);
  const productIdsAndQuantities = useSelector(selectProductIdsAndQuantities);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [total, setTotal] = useState(0);
  const { token } = useAuth()

  useEffect(() => {
    let number = 0;
    cartProducts.map((item) => (number += item.quantity * item.price));
    setTotal(number.toFixed(2));
  }, [cartProducts]);


  //payement handling mechanism
  const handlePayment = async (amount) => {
    try {
      console.log(productIdsAndQuantities);

      // const response = await createPaymentOrder(amount, "INR");
      const response = await axios({
        method: "post",
        baseURL: "http://localhost:8080/api",
        url: "/payments/create-order",
        params: {
          amount: amount,
          currency: 'INR'
        },
        data: {
          customerId: id,
          products:productIdsAndQuantities
        },
        headers: {
          "Content-Type": "application/json", // Set the content type if needed
          "Authorization": `Bearer ${token}`, // Include a token if your API requires authentication
        }
      })
      const { order, orderId } = response.data;

      //console.log(order);
      const pasredOrder = JSON.parse(order);
      const parsedId = JSON.parse(orderId);

      console.log(typeof parsedId);



      const options = {
        key: 'rzp_test_hJjVKLZjSCpVAy',
        amount: pasredOrder.amount,
        currency: pasredOrder.currency,
        name: "ScoopsNSmile",
        description: "Test Transaction for user " + id,
        order_id: pasredOrder.id,
        handler: async function (response) {
          try {
            const paymentResponse = await axios({
              method: "post",
              baseURL: "http://localhost:8080/api",
              url: "/payments/success",
              params: {
                razorpayPaymentId: response.razorpay_payment_id,
                razorpayOrderId: response.razorpay_order_id,
                razorpaySignature: response.razorpay_signature,
                oid: parsedId
              }

            })
            alert('Payment successful');
            // You can update the order status or navigate to another page here
            dispatch(clearCart());
            navigate("/");
          } catch (error) {
            console.error('Payment success handling failed', error);
            alert('There was an issue handling payment success. Please contact support.');
          }
        },
        prefill: {
          name: role,
          email: email,
          contact: "9999999999"
        },
        theme: {
          color: "#3399cc"
        }
      };

      const rzp1 = new window.Razorpay(options);
      rzp1.open();
    } catch (error) {
      console.error('Payment failed', error);
    }
  };

  return (
    <PageContainer>
      {cartProducts.length === 0 ? (
        <Typography variant="h4" sx={{ color: Colors.primary }}>
          Your Cart is empty
        </Typography>
      ) : (
        <>
          <Typography variant="h4" sx={{ textAlign: 'center', mb: '3rem' }}>
            MY CART
          </Typography>
          <Box>
            {cartProducts.map((item) => (
              <Card
                key={item.id}
                sx={{
                  display: 'flex',
                  flexDirection: { xs: 'column', md: 'row' },
                  alignItems: 'center',
                  justifyContent: 'center',
                  mb: '2rem',
                  padding: '1rem',
                }}
              >
                <CardMedia
                  sx={{ height: '20rem', width: '40vw' }}
                  image={item.image}
                />
                <CardContent sx={{ width: { xs: '90vw', md: '60vw' } }}>
                  <Typography variant="h5">{item.title}</Typography>
                  <Typography
                    variant="subtitle1"
                    sx={{ mt: '1rem', fontWeight: 600, fontSize: '1.5rem' }}
                  >
                    Rs. {item.price}
                  </Typography>
                </CardContent>
                <CardActions>
                  <AmountButtons>
                    <RemoveIcon
                      onClick={() => dispatch(deleteProduct(item))}
                      aria-label="Remove one unit"
                    />

                    <Typography>{item.quantity}</Typography>
                    <AddIcon
                      onClick={() => dispatch(addProduct(item))}
                      aria-label="Add one unit"
                    />
                  </AmountButtons>

                  <Button
                    sx={{ marginLeft: '1rem' }}
                    onClick={() => dispatch(deleteAllProduct(item))}
                    aria-label="delete all units of this product"
                  >
                    <DeleteIcon color="primary" sx={{ fontSize: '2rem' }} />
                  </Button>
                </CardActions>
              </Card>
            ))}
            <Typography
              variant="h5"
              sx={{ fontWeight: '600', marginTop: '4rem' }}
            >
              TOTAL: Rs. {total}
            </Typography>
            <Button variant="contained" sx={{ marginLeft: '1rem' }} onClick={() => handlePayment(total)}>
              Proceed to checkout
            </Button>
          </Box>
        </>
      )}
    </PageContainer>
  );
};

export default CartPage;
