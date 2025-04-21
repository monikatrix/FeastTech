import React from "react";
export const CaroselItem=({image,title})=>{
    return(
        <div className='flex flex-col justify-center items-center'>
            <img className="w-[10rem] h-[10rem] lg:h-[14rem] lg:w-[14rem] rounded-full object-cover object-center " src={image} alt=""/>
            <span className="py-5 font-semibold text-xl test-gray-400">{title}</span>

        </div>
    )
}