// import React, { useState, useEffect } from 'react';
// import { List, ListItem, ListItemText, Button, TextField } from '@mui/material';
// import { fetchOrders, updateOrderStatus } from '../../api/api';
// import axios from 'axios';

// const OrderList = () => {
//     const [orders, setOrders] = useState([]);
//     const [deliveryPersonID, setDeliveryPersonID] = useState({});
//     const [error, setError] = useState(null);

//     useEffect(() => {
//         const fetchData = async () => {
//             const result = await fetchOrders();
//             setOrders(result.data);
//         };
//         fetchData();
//     }, []);

//     const handleChange = (e, id) => {
//         setDeliveryPersonID({ ...deliveryPersonID, [id]: e.target.value });
//     };

//     const handleAsignDeliveryPerson = async (orderId) => {
//         try {
//             const deliveryPersonID = deliveryPersonID[orderId];
//             console.log(deliveryPersonID);
//             console.log(orderId);

//             const response = await axios.post(`http://localhost:8080/api/deliveries`, { deliveryPersonID, orderId });

//             console.log(response);


//         } catch (err) {
//             setError(err.message);
//         }
//     };

//     return (
//         <List>
//             {orders.map(order => (
//                 <ListItem key={order.id}>
//                     <ListItemText primary={`Order ${order.id}`} secondary={`Status: ${order.status}`} />

//                     <TextField
//                         label="Enter DeliveryPersonID"
//                         variant="outlined"
//                         value={deliveryPersonID[order.id] || ''}
//                         onChange={(e) => handleChange(e, order.id)}
//                         sx={{ marginRight: '1rem' }}
//                     />
//                     <Button
//                         variant="contained"
//                         color="primary"
//                         onClick={() => handleAsignDeliveryPerson(order.id)}
//                     >
//                         Assign DeliveryPerson
//                     </Button>
//                 </ListItem>
//             ))}
//         </List>
//     );
// };

// export default OrderList;



import React, { useState, useEffect } from 'react';
import { List, ListItem, ListItemText, Button, TextField } from '@mui/material';
import { fetchOrders } from '../../api/api'; // Assuming this function exists to fetch orders
import axios from 'axios';

const OrderList = () => {
    const [orders, setOrders] = useState([]);
    const [deliveryPersonID, setDeliveryPersonID] = useState({});
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await fetchOrders();
                setOrders(result.data);
            } catch (err) {
                setError('Error fetching orders');
            }
        };
        fetchData();
    }, []);

    const handleChange = (e, id) => {
        setDeliveryPersonID({ ...deliveryPersonID, [id]: e.target.value });
        console.log("Updated deliveryPersonID:", deliveryPersonID); // Debugging
    };

    const handleAsignDeliveryPerson = async (orderId) => {
        try {
            const deliveryPersonIdValue = deliveryPersonID[orderId];
            console.log("Delivery Person ID:", deliveryPersonIdValue);
            console.log("Order ID:", orderId);

            const response = await axios.post(`http://localhost:8080/api/deliveries/asign-delivery-person`, {
                orderId: orderId,
                deliveryPersonId: deliveryPersonIdValue
            });

            console.log("API Response:", response.data); // Debugging

        } catch (err) {
            console.error("API Error:", err); // Debugging
            setError('Error assigning delivery person');
        }
    };

    return (
        <List>
            {orders.map(order => (
                <ListItem key={order.id}>
                    <ListItemText primary={`Order ${order.id}`} secondary={`Status: ${order.status}`} />

                    <TextField
                        label="Enter DeliveryPersonID"
                        variant="outlined"
                        value={deliveryPersonID[order.id] || ''}
                        onChange={(e) => handleChange(e, order.id)}
                        sx={{ marginRight: '1rem' }}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={() => handleAsignDeliveryPerson(order.id)}
                    >
                        Assign DeliveryPerson
                    </Button>
                </ListItem>
            ))}
            {error && (
                <p style={{ color: 'red' }}>{error}</p>
            )}
        </List>
    );
};

export default OrderList;
