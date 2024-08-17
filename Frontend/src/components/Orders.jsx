import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Typography, Card, Paper, Divider, ListItemText, ListItem, List } from '@mui/material';
import { Colors } from '../styles/theme/theme';
import { useSelector } from 'react-redux';


const Orders = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const { id } = useSelector((state) => state.user);
  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/orders/${id}`);
        console.log(response.data);
        setOrders(response.data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchOrders();
  }, []);

  return (
    <Paper
      elevation={3}
      sx={{
        padding: '1.5rem',
        maxWidth: { xs: '90vw', md: '70vw' },
        margin: '0 auto',
      }}
    >
      <Typography variant="h5" textAlign="left">
        My Orders
      </Typography>
      {loading && <Typography mt="1rem">Loading...</Typography>}
      {error && <Typography mt="1rem" color="error">{error}</Typography>}
      {!loading && orders.length === 0 && (
        <Typography
          mt="1rem"
          sx={{ color: Colors.grayLight }}
          variant="subtitle1"
        >
          You have no orders yet
        </Typography>
      )}
      {!loading && orders.length > 0 && (
        <div>
          {orders.map((order) => (
            <Card key={order.id} sx={{ margin: '1rem 0', padding: '1rem' }}>
              <Typography variant="h6">Order #{order.id}</Typography>
              <Typography variant="body2" sx={{ color: Colors.grayDark }}> Date: {order.orderDate}
              </Typography>
              <Typography variant="body2" sx={{ color: Colors.grayDark }}>
                Status: {order.status}
              </Typography>

              <Divider sx={{ margin: '1rem 0' }} />
            </Card>
          ))}
        </div>
      )}
    </Paper>
  );

};

export default Orders;