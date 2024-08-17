import React, { useState, useEffect } from 'react';
import ProductForm from '../../components/admin/ProductForm';
import { fetchProducts, deleteProduct } from '../../api/api';
import { List, ListItem, ListItemText, Button, Container } from '@mui/material';

const Products = () => {
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState(null);

    useEffect(() => {
        refreshProducts();
    }, []);

    const refreshProducts = async () => {
        const result = await fetchProducts();
        setProducts(result.data);
    };

    const handleDelete = async (productId) => {
        await deleteProduct(productId);
        refreshProducts();
    };

    const handleEdit = (product) => {
        setSelectedProduct(product);
    };

    return (
        <Container>
            <h2>Manage Products</h2>
            <ProductForm selectedProduct={selectedProduct} refreshProducts={refreshProducts} />
            <List>
                {products.map(product => (
                    <ListItem key={product.id}>
                        <ListItemText primary={product.name} secondary={`Price: ${product.price}`} />
                        <Button variant="contained" color="success" onClick={() => handleEdit(product)}>
                            Edit
                        </Button>
                        <Button variant="contained" color="error" onClick={() => handleDelete(product.id)}>
                            Delete
                        </Button>
                    </ListItem>
                ))}
            </List>
        </Container>
    );
};

export default Products;
