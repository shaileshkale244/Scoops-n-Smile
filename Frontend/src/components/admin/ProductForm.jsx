import React, { useState, useEffect } from 'react';
import { TextField, Button, Container, CircularProgress } from '@mui/material';
import { createProduct, updateProduct } from '../../api/api';

const ProductForm = ({ selectedProduct, refreshProducts }) => {
    const [product, setProduct] = useState({ name: '', price: '' });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (selectedProduct) {
            setProduct(selectedProduct);
        }
    }, [selectedProduct]);

    const handleChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        setLoading(true);
        setError(null);

        try {
            if (product.id) {
                await updateProduct(product);
            } else {
                await createProduct(product);
            }
            refreshProducts();
            if (!product.id) {
                setProduct({ name: '', price: '' }); // Reset form after creation
            }
        } catch (err) {
            setError('An error occurred while saving the product.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <Container>
            <TextField
                label="Product Name"
                name="name"
                value={product.name}
                onChange={handleChange}
                fullWidth
                margin="normal"
                error={Boolean(error)}
                helperText={error}
            />
            <TextField
                label="Price"
                name="price"
                value={product.price}
                onChange={handleChange}
                fullWidth
                margin="normal"
                error={Boolean(error)}
                helperText={error}
            />
            <Button
                variant="contained"
                color="primary"
                onClick={handleSubmit}
                disabled={loading}
                startIcon={loading && <CircularProgress size={20} />}
            >
                {product.id ? 'Update Product' : 'Save Product'}
            </Button>
        </Container>
    );
};

export default ProductForm;
