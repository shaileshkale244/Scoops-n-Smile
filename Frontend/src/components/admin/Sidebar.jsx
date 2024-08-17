import React from 'react';
import { List, ListItem, ListItemIcon, ListItemText, Drawer } from '@mui/material';
import { ShoppingCart, Person, Assignment } from '@mui/icons-material';
import { Link, useNavigate } from 'react-router-dom';
import {  removeUser } from '../../features/user/UserSlice';
import { useDispatch } from 'react-redux';
import { MyButton } from "../../styles/buttons/buttons";
import { Output } from "@mui/icons-material";

const Sidebar = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleSignOut = () =>{
        dispatch(removeUser());
        navigate("/login");
    }

    return (
        <Drawer variant="permanent" anchor="left">
            <List>
                <ListItem button component={Link} to="/admin/products">
                    <ListItemIcon><ShoppingCart /></ListItemIcon>
                    <ListItemText primary="Products" />
                </ListItem>
                <ListItem button component={Link} to="/admin/delivery-persons">
                    <ListItemIcon><Person /></ListItemIcon>
                    <ListItemText primary="Delivery Persons" />
                </ListItem>
                <ListItem button component={Link} to="/admin/orders">
                    <ListItemIcon><Assignment /></ListItemIcon>
                    <ListItemText primary="Orders" />
                </ListItem>
                <MyButton
        variant="contained"
        onClick={() => handleSignOut()}
        endIcon={<Output />}
        aria-label="Sign out"
      >
        Sign out
      </MyButton>
            </List>
        </Drawer>
    );
};

export default Sidebar;
