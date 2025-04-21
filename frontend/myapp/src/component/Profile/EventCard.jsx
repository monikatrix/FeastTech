import { Card, CardActions, CardContent, CardMedia, IconButton, Typography } from '@mui/material'
import React from 'react'
import DeleteIcon from '@mui/icons-material/Delete';

const EventCard = () => {
  return (
    <div>
        <Card className=''>
            <CardMedia
            sx={{width:345 ,height:345}}
             image='https://cdn.pixabay.com/photo/2013/09/05/10/38/catering-179046_640.jpg'/>

             <CardContent>
                <Typography variant='h5'>
                    Indian Foods
                </Typography>
                <Typography variant='body2'>
                    25% off on your first Order
                </Typography>
                <div className='py-2 space-y-2'>
                    <p>{"tamilnadu"}</p>
                    <p className='text-sm text-blue-500'>April 14, 2025 8:00 AM</p>
                    <p className='text-sm text-red-500'>April 15, 2025 8:00 PM</p>
                </div>
             </CardContent>
             {false && <CardActions>
                <IconButton>
                    <DeleteIcon/>
                </IconButton>
             </CardActions> }
        </Card>
    </div>
  )
}

export default EventCard