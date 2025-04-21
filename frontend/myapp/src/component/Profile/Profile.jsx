import React, { useState } from 'react'
import ProfileNavigation from './ProfileNavigation'
import { Route, Routes } from 'react-router-dom';
import UserProfile from './UserProfile';
import Address from './Address';
import Orders from './Orders';
import Events from './Events';
import Favourites from './Favourites';

const Profile = () => {
  const [openSideBar, setOpenSideBar] = useState(false);
  return (
    <div className='lg:flex justify-between'>
        <div className='sticky h-[80vh] lg:w-[20%] '>
            <ProfileNavigation open={openSideBar}/>
        </div>
        <div className='lg:w-[80%] '>
          <Routes>
            <Route path='/' element = {<UserProfile/>}></Route>
            <Route path='/orders' element = {<Orders/>}></Route>
            <Route path='/address' element = {<Address/>}></Route>
            <Route path='/favorites' element = {<Favourites/>}></Route>
            <Route path='/events' element = {<Events/>}></Route>
            
          </Routes>
        </div>
    </div>
  )
}

export default Profile