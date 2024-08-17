import React, { useEffect, useState } from 'react'

import { Card, CardContent, Typography, TextField, Button, Box } from '@mui/material';
import { useSelector } from 'react-redux';
import axios from 'axios';

const DeliveriesList = () => {
    // const [deliveries, setDeliveries] = useState([]);
    // const [otp, setOtp] = useState({});
    // const [verificationStatus, setVerificationStatus] = useState({});
    // const [otpStatus, setOtpStatus] = useState(false);
    // const [loading, setLoading] = useState(true);
    // const [error, setError] = useState(null);

    // const { id } = useSelector((state) => state.user);
    // useEffect(() => {
    //     const fetchDeliveries = async () => {
    //         try {
    //             const response = await axios.get(`http://localhost:8080/api/deliveries/${id}`);
    //             setDeliveries(response.data);
    //             setLoading(false);
    //         } catch (err) {
    //             setError(err.message);
    //             setLoading(false);
    //         }
    //     };

    //     fetchDeliveries();
    // }, []);

    // const handleOtpChange = (e, deliveryId) => {
    //     setOtp({ ...otp, [deliveryId]: e.target.value });
    // };

    // const handleVerifyOtp = (deliveryId, orderId) => {
    //     const otpcode = otp[deliveryId];
    //     const verifyOtp = async () => {
    //         try {
    //             const response = await axios.post(`http://localhost:8080/api/deliveries`, { orderId, otpcode });
    //             response.data?setOtpStatus(true):setOtpStatus(false);
    //             setLoading(false);
    //         } catch (err) {
    //             setError(err.message);
    //             setLoading(false);
    //         }
    //     };
    //     verifyOtp();

    //     if (otpStatus) {        
    //         setVerificationStatus({ ...verificationStatus, [deliveryId]: 'Verified' });
    //     } else {
    //         setVerificationStatus({ ...verificationStatus, [deliveryId]: 'Invalid OTP' });
    //     }
    // };

    const [deliveries, setDeliveries] = useState([]);
    const [otp, setOtp] = useState({});
    const [verificationStatus, setVerificationStatus] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const { id } = useSelector((state) => state.user);

    useEffect(() => {
        const fetchDeliveries = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/deliveries/${id}`);
                setDeliveries(response.data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };

        fetchDeliveries();
    }, [id]);

    const handleOtpChange = (e, deliveryId) => {
        setOtp({ ...otp, [deliveryId]: e.target.value });
    };

    const handleVerifyOtp = async (deliveryId, orderId) => {
        const otpcode = otp[deliveryId];
        try {
            const response = await axios.post(`http://localhost:8080/api/deliveries`, { orderId, otpcode });

            if (response.data) {
                setVerificationStatus((prev) => ({
                    ...prev,
                    [deliveryId]: 'Verified',
                }));
            } else {
                setVerificationStatus((prev) => ({
                    ...prev,
                    [deliveryId]: 'Invalid OTP',
                }));
            }
        } catch (err) {
            setError(err.message);
            setVerificationStatus((prev) => ({
                ...prev,
                [deliveryId]: 'Error verifying OTP',
            }));
        }
    };

    return (
        <Box sx={{ padding: '2rem' }}>
            {deliveries.map((delivery) => (
                <Card key={delivery.deliveryId} sx={{ marginBottom: '1rem' }}>
                    <CardContent>
                        <Typography variant="h6">Delivery ID: {delivery.deliveryId}</Typography>
                        <Typography variant="body1">Order: {delivery.orderId}</Typography>
                        <Typography variant="body2">Delivery Date: {delivery.deliveryDate}</Typography>
                        <Typography variant="body2">DeliveryStatus: {delivery.status}</Typography>

                        <Box sx={{ marginTop: '1rem' }}>
                            <TextField
                                label="Enter OTP"
                                variant="outlined"
                                value={otp[delivery.deliveryId] || ''}
                                onChange={(e) => handleOtpChange(e, delivery.deliveryId)}
                                sx={{ marginRight: '1rem' }}
                            />
                            <Button variant="contained" color="primary" onClick={() => handleVerifyOtp(delivery.deliveryId,delivery.orderId)}>
                                Verify OTP
                            </Button>
                        </Box>

                        {verificationStatus[delivery.deliveryId] && (
                            <Typography variant="body2" color={verificationStatus[delivery.deliveryId] === 'Verified' ? 'green' : 'red'} sx={{ marginTop: '0.5rem' }}>
                                {verificationStatus[delivery.deliveryId]}
                            </Typography>
                        )}
                    </CardContent>
                </Card>
            ))}
        </Box>
    );
};

export default DeliveriesList;
