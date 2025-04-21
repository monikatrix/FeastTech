import React, { useEffect, useState } from 'react'
import LocationPinIcon from '@mui/icons-material/LocationPin';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import { Divider, FormControl, FormControlLabel, Radio, RadioGroup, Typography } from '@mui/material';
import MenuCard from './MenuCard';
import { useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getRestaurantById, getRestaurantsCategory } from '../State/Restaurant/Action';
import { getMenuItemsByRestaurantId } from '../State/Menu/Action';


const foodTypes=[
    {label:"All", value:"all"},
    {label:"Vegetarian only",value:"vegetarian"},
    {label:"Non-Vegetarian only",value:"non_vegetarian"},
    {label:"Seasonal",value:"seasonal"},
]

const menu = [1,1,1,1,1,1]
const RestaurantDetails = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const jwt = localStorage.getItem("jwt");
    const {auth,restaurant,menu} = useSelector(store=>store)
    const [foodType, setFoodType] = useState("all");
    const [selectedCategory,setSelectedCategory] = useState("");

    const {id,city} = useParams();

    const handleFilter=(e)=>{   
        setFoodType(e.target.value)
        console.log(e.target.value, e.target.name)
    }
    const handleFilterCategory=(e,value)=>{   
        setSelectedCategory(e.target.value);
        console.log(e.target.value, e.target.name,value);
    }

    console.log("restaurant", restaurant)
    useEffect(()=>{
        dispatch(getRestaurantById({jwt,restaurantId:id}))
        dispatch(getRestaurantsCategory({jwt,restaurantId:id}))
    },[])

    useEffect(()=>{
        const isVegetarian = foodType === "vegetarian";
        const isNonVegetarian = foodType === "non_vegetarian";
        const isSeasonal = foodType === "seasonal";
        console.log("Consoled : ",isVegetarian);
        console.log("Selected Category : ",selectedCategory);
        dispatch(getMenuItemsByRestaurantId(
            {jwt,
            restaurantId:id,
            vegetarian:isVegetarian,
            nonveg:isNonVegetarian,
            seasonal:isSeasonal,
            foodCategory:selectedCategory
        })
    )
    },[selectedCategory,foodType])
  return (
    <div className='px-5 lg:px-20 '>
        <section>
            <h3 className='text-gray-500 py-2 mt-10'>Home/india/indian food/3</h3>
            <div>
            <div className="flex flex-col gap-2">
                
                <img className="w-full h-[40vh] object-cover" src="https://media.istockphoto.com/id/843610508/photo/interior-of-cozy-restaurant-loft-style.jpg?s=612x612&w=0&k=20&c=s_PVQJNzcilxKYpm3O-AxBMx4_om5G0TKuxUmiMl85Y=" alt="Restaurant" />

                <div className="flex flex-wrap gap-y-0.5">
                    <img className="w-full lg:w-1/2 h-[40vh] object-cover" src={restaurant.restaurant?.images[1]} alt="Restaurant" />
                    <img className="w-full lg:w-1/2 h-[40vh] object-cover" src={restaurant.restaurant?.images[0]} alt="Restaurant" />
                </div>
            </div>

            </div>
            <div className='pt-3 pb-5'>
                <h1 className='text-4xl font-semibold'>{restaurant.restaurant?.name}</h1>
                <p className='text-gray-500 mt-1'>
                {restaurant.restaurant?.description}
                </p>
                <div className='space-y-3 mt-3'>
                    <p className='text-gray-500 flex items-center gap-3'>
                        <LocationPinIcon/>
                        <span>
                        Coimbatore, Tamilnadu
                        </span>
                    </p>
                    <p className='text-gray-500 flex items-center gap-3'>
                        <CalendarTodayIcon/>
                        <span>
                        Mon-Sun 9:30 AM - 10:00 PM
                        </span>
                    </p>
                </div>
            </div>
        </section>
        <Divider/>
        <section className='pt-[2rem] lg:flex relative '>
            <div className='space-y-10 lg:w-[20%] filter p-5 shadow-md'>
                <div className='box space-y-5 lg:sticky top-28  p-5 shadow-md'>
                    <div className=''>
                        <Typography variant='h5' sx={{paddingBottom:"1rem"}}>
                            Food Type
                        </Typography>
                        <FormControl className='py-10 space-y-10 ' component={"fieldSet"}>
                            <RadioGroup onChange={handleFilter} name='food_type' value={foodType}>
                                {foodTypes.map((item)=> (
                                    <FormControlLabel 
                                    key={item.value}
                                    value={item.value} 
                                    control={<Radio />} 
                                    label={item.label} />

                                ))}
                            </RadioGroup>
                        </FormControl>
                    </div>
                    <Divider/>
                    <div className=''>
                        <Typography variant='h5' sx={{paddingBottom:"1rem"}}>
                            Food Category
                        </Typography>
                        <FormControl className='py-10 space-y-10 ' component={"fieldSet"}>
                            <RadioGroup onChange={handleFilterCategory} 
                            name='food_category' 
                            value={selectedCategory}
                            >
                                {restaurant.categories.map((item)=> (
                                    <FormControlLabel 
                                    key={item.id}
                                    value={item.name} 
                                    control={<Radio />} 
                                    label={item.name} />

                                ))}
                            </RadioGroup>
                        </FormControl>
                    </div>
                </div>
            </div>
            <div className='space-y-5 lg:w-[80%] lg:pl-10'>
            

               {menu.menuItems.map((item)=>
                <MenuCard 
                key={item.id} 
                item={item}/>
            )}
                </div>

        </section>

    </div>
  );
};

export default RestaurantDetails;