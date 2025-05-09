import { Avatar, Badge, Box, IconButton } from "@mui/material";
import React from "react";
import SearchIcon from '@mui/icons-material/Search';
import { red } from "@mui/material/colors";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import "./Navbar.css"
import PersonIcon from '@mui/icons-material/Person';
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { store } from "../State/store";
import Cart from "../Cart/Cart";

export const Navbar = () =>{
  const {auth,cart} = useSelector(store=>store)
  const navigate = useNavigate()

  const handleAvatarClick=()=>{
    if(auth.user?.role==="ROLE_CUSTOMER"){
      navigate("/my-profile")
    }
    else{
      navigate("/admin/restaurant")
    }
  }
    return(
          <Box className="px-5 sticky top-0 z-[100] py-[.8rem] bg-red-700 lg:px-20 flex justify-between">
          <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
            <span onClick={()=>navigate("/")} className="logo font-semibold text-gray-300 text-2xl">
              FoodFeast
            </span>
          </div>
          <div className="flex items-center space-x-2 lg:space-x-10">
            <div>
              <IconButton>
                <SearchIcon sx={{ fontSize: "1.5rem" }} />
              </IconButton>
            </div>
            <div>
                {auth.user?(<Avatar onClick = {handleAvatarClick} sx={{ bgcolor: "white", color: red[800] }}>
                  {/* {auth.user?.fullName[0].toUpperCase()} */}
                  {auth.user.fullName[0]?.toUpperCase()}
                  </Avatar>):(
                <IconButton onClick={()=>navigate("/account/login")}>
                  <PersonIcon/>
                </IconButton>

                )}
            </div>
            <div>
              <IconButton onClick={()=>navigate("/cart")}>
                <Badge color="primary" badgeContent={cart.cart?.items?.length||0}> 
                    <ShoppingCartIcon sx={{ fontSize: "1.5rem" }} />
                </Badge>
              </IconButton>
            </div>
          </div>
        </Box>
    );
}
