import { Button, Card } from '@mui/material'
import React from 'react'

const OrderCard = ({item,order}) => {
  return (
    <Card sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: 2 }}>
        <div className='flex items-center space-x-5'>
            <img 
            className='h-16 w-16'
            src={item.food.images[0]} alt=''/>
            <div>
                <p>{item.food.name}</p>
                <p>₹{item.totalPrice}</p>
            </div>
        </div>
        <div>
            <Button variant='outlined' className='cursor-not-allowed'>{order.orderStatus}</Button>
        </div>
    </Card>
  )
}

export default OrderCard