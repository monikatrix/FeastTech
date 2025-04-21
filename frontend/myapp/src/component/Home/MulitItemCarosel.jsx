import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { TopMeal } from "./TopMeal";
import { CaroselItem } from "./CaroselItem";

export const MultiItemCarosel = () =>{
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 5,
        slidesToScroll: 2,
        autoplay:true,
        autoplaySpeed:2000,
        arrows:false
      };
    
    return(
        <div>
            <Slider {...settings}>
                {TopMeal.map((item)=>(
                    <CaroselItem image={item.image} title={item.title}/>
                    ))}
            </Slider>
        </div>
    )
}