import React, { useState } from 'react';
import { TextField, Button, Container } from '@mui/material';
import { createDeliveryPerson, updateDeliveryPerson } from '../../api/api';

const DeliveryPersonForm = ({ selectedDeliveryPerson, refreshDeliveryPersons }) => {
    const [deliveryPerson, setDeliveryPerson] = useState(selectedDeliveryPerson || { name: '', phone: '' ,email:'',password:''});

    const handleChange = (e) => {
        setDeliveryPerson({ ...deliveryPerson, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        if (deliveryPerson.id) {
            await updateDeliveryPerson(deliveryPerson);
        } else {
            await createDeliveryPerson(deliveryPerson);
        }
        refreshDeliveryPersons();
    };

    return (
        <Container>
            <TextField
                label="Delivery Person Name"
                name="name"
                value={deliveryPerson.name}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Phone"
                name="phone"
                value={deliveryPerson.phone}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Delivery Person email"
                name="email"
                value={deliveryPerson.email}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Delivery Person password"
                name="password"
                value={deliveryPerson.password}
                onChange={handleChange}
                fullWidth
                margin="normal"
            />
            <Button variant="contained" color="primary" onClick={handleSubmit}>
                {deliveryPerson.id ? 'Update Delivery Person' : 'Save Delivery Person'}
            </Button>
        </Container>
    );
};

export default DeliveryPersonForm;
