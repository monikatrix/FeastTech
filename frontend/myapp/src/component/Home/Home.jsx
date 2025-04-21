import React, { useEffect } from "react";
import './Home.css'
import { MultiItemCarosel } from "./MulitItemCarosel";
import { RestaurantCard } from "../Restaurant/RestaurantCard";
import { useDispatch, useSelector } from 'react-redux';
import {getAllRestaurantActions} from '../State/Restaurant/Action'
import { store } from "../State/store";
import { findCart } from "../State/Cart/Action";


const restaurants=[1,1,1,1,1,1,1,1,]
export const Home = () =>{
    const dispatch = useDispatch()

    const jwt = localStorage.getItem("jwt")
    const {restaurant} = useSelector(store=>store)

    console.log("restaurant", restaurant)

    useEffect(()=>{
        dispatch(getAllRestaurantActions(jwt))
    },[])

    return(
        <div className="pb-10">
            <section className='banner relative flex flex-col justify-center items-center text-center'>
                <div className='w-[50vw] z-10 text-center'>
                    <p className='text-2xl lg:text-6xl font-bond z-10 py-5'>Food Feast</p>
                    <p className="z-10 text-gray-300 text-xl lg-4xl">Good food is the foundation of genuine happiness</p>

                </div>
                <div className='cover absolute top-0 left-0 right - 0'>

                </div>
                <div className='fadout'>

                </div>

            </section>
            <section className="p-10 lg:py-10 lg:px-10">
                <p className="text-2xl font-semibold text-grey-400 py-3 pb-8">Top Meals</p>
                <MultiItemCarosel/>
            </section>
            <section className="px-5 lg:px-20 pt-5">
                <h1 className="text-2xl font-semibold text-gray-400 py-3">
                    Order From Our Handipicked Favourites
                </h1>
                <div className='flex flex-wrap items-center justify-around gap-5'>
                    {
                        restaurant.restaurants?.map((item)=><RestaurantCard item={item}/>)
                    }
                </div>
            </section>

        </div>
    )
}