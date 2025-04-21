// import logo from './logo.svg';
import './App.css';
import { Navbar } from './component/Navbar/Navbar';
import { ThemeProvider } from '@emotion/react';
import { darkTheme } from './Theme/DarkTheme';
import { CssBaseline } from '@mui/material';
import {Home} from './component/Home/Home'
import RestaurantDetails from './component/Restaurant/RestaurantDetails';
import Cart from './component/Cart/Cart';
import Profile from './component/Profile/Profile';
import CustomerRouter from './Routers/CustomerRouter';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getUser } from './component/State/Authentication/Action';
import { store } from './component/State/store';
import { findCart } from './component/State/Cart/Action';

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const {auth} = useSelector((store)=>store);

  useEffect(()=>{
      dispatch(getUser(auth.jwt || jwt))
      dispatch(findCart(jwt))
  },[auth.jwt]);
  return (
    <ThemeProvider theme={darkTheme}>
       <CssBaseline/>
      <CustomerRouter/>
     
    </ThemeProvider>
  );
}

export default App;
