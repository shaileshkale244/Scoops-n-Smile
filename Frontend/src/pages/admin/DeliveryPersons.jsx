import React, { useState, useEffect } from 'react';
import DeliveryPersonForm from '../../components/admin/DeliveryPersonForm';
import { fetchDeliveryPersons, deleteDeliveryPerson } from '../../api/api';
import { List, ListItem, ListItemText, Button, Container, CircularProgress } from '@mui/material';

const DeliveryPersons = () => {
    const [deliveryPersons, setDeliveryPersons] = useState([]);
    const [selectedDeliveryPerson, setSelectedDeliveryPerson] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        refreshDeliveryPersons();
    }, []);

    const refreshDeliveryPersons = async () => {
        setLoading(true);
        setError(null);
        try {
            const result = await fetchDeliveryPersons();
            setDeliveryPersons(result.data);

        } catch (err) {
            setError('Failed to load delivery persons.');
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (deliveryPersonId) => {
        setLoading(true);
        setError(null);
        try {
            await deleteDeliveryPerson(deliveryPersonId);
            refreshDeliveryPersons();
        } catch (err) {
            setError('Failed to delete delivery person.');
        } finally {
            setLoading(false);
        }
    };

    const handleEdit = (deliveryPerson) => {
        setSelectedDeliveryPerson(deliveryPerson);
    };

    return (
        <Container>
            <h2>Manage Delivery Persons</h2>
            <DeliveryPersonForm
                selectedDeliveryPerson={selectedDeliveryPerson}
                refreshDeliveryPersons={refreshDeliveryPersons}
                clearSelection={() => setSelectedDeliveryPerson(null)}  // Clears the form after edit
            />
            {loading ? (
                <CircularProgress />
            ) : error ? (
                <p>{error}</p>
            ) : (
                <List>
                    {deliveryPersons.map((deliveryPerson) => (
                        <ListItem key={deliveryPerson.id}>
                            <ListItemText
                                primary={deliveryPerson.name}
                                secondary={`Phone: ${deliveryPerson.phone}`}
                            />
                            <Button
                                variant="contained"
                                color="success"
                                onClick={() => handleEdit(deliveryPerson)}
                                style={{ marginRight: '10px' }}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="contained"
                                color="error"
                                onClick={() => handleDelete(deliveryPerson.id)}
                            >
                                Delete
                            </Button>
                        </ListItem>
                    ))}
                </List>
            )}
        </Container>
    );
};

export default DeliveryPersons;
