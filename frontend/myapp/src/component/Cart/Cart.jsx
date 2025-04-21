import { Box, Button, Card, Divider, Grid, Modal, TextField } from '@mui/material'
import React from 'react'
import CartItem from './CartItem'
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
import AddressCart from './AddressCart';
import AddLocationAltIcon from '@mui/icons-material/AddLocationAlt';
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import { useDispatch, useSelector } from 'react-redux';
import { createOrder } from '../State/Order/Action';
const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    outline:"none",
    boxShadow: 24,
    p: 4,
  };
  const initialValues={
    streetAddress:"",
    state:"",
    pincode:'',
    city:""
  }

//   const validationSchema=Yup.object({
//     streetAddress:Yup.string().required("Street address is required"),
//     state:Yup.string().required("State address is required"),
//     pincode:Yup.number().required("Pincode is required"),
//     city:Yup.string().required("City is required")
//   })
  
const items=[1,1,1,1]
const Cart = () => {
    const createOrderUsingSelectedAddress=()=>{

    };
    
    const handleOpenAddressModel=()=> setOpen(true);
    const [open, setOpen] = React.useState(false);
    
    const {cart,auth} = useSelector(store=>store)
    const handleClose = () => setOpen(false);
    const dispatch = useDispatch();
    const handleSubmit=(values)=>{
        const data={
            jwt:localStorage.getItem("jwt"),
            order:{
                restaurantId:cart.cartItems[0].food?.restaurant.id,
                deliveryAddress:{
                    fullName:auth.user?.fullName,
                    streetAddress:values.streetAddress,
                    city:values.city,
                    state:values.state,
                    postalCode:values.pincode,
                    country:"India"
                }
            }
        }
        dispatch(createOrder(data))
        console.log("form value",values)
    };
    
  return (
    <div>
        <main className='lg:flex justify-center'>
            <section className='lg:w-[30%] space-y-6 lg:min-h-screen pt-10'>
                {cart.cartItems.map((item)=>(
                    <CartItem item={item}/>
                ))}
        <Divider/>
        <div className='billDetails px-5 text-sm'>
            <p className='font-extralight py-5'>Bill Details</p>
            <div className='space-y-3'>
                <div className='flex justify-between text-gray-400'>
                    <p>Item Total</p>
                    <p><CurrencyRupeeIcon/>{cart.cart?.total}</p>
                </div>
                <div className='flex justify-between text-gray-400'>
                    <p>Delivery Fee</p>
                    <p><CurrencyRupeeIcon/>20</p>

                </div>
                <div className='flex justify-between text-gray-400'>
                    <p>GST and Restaurant charges</p>
                    <p><CurrencyRupeeIcon/>10</p>
                </div>
                <Divider/>
            </div>
            <div className='flex justify-between text-gray-400'>
                <p>Total Pay</p>
                <p><CurrencyRupeeIcon/>{cart.cart?.total+10+20}</p>

            </div>
        </div>
            </section>
            <Divider orientation='vertical' flexItem/>
            <section className='lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0 '>
                <div>
                    <h1 className='text-center font-semibold text-2xl py-10'>Choose Delivery Address</h1>
                    <div className='flex gap-5 flex-wrap justify-center'>
                        {[1,1,1,1,1].map((item)=>(<AddressCart handleSelectAddress={createOrderUsingSelectedAddress} item={item} showButton={true}/>
                        ))}
                         <Card className="flex gap-5 w-64 p-5" >
        <AddLocationAltIcon/>
        <div className='space-y-3 text-gray-500'>
            <h1 className='font-semibold text-lg text-white'>Add Location</h1>
            <Button variant='outlined' fullWidth onClick={handleOpenAddressModel}>Add</Button>
        </div>
    </Card>
                    </div>
                </div>
            </section>
        </main>
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
            >
            <Box sx={style}>
               <Formik
               initialValues={initialValues}
            //    validationSchema={validationSchema}
               onSubmit={handleSubmit}
               >

                {({ errors, touched }) => (
                    <Form>
                            <Grid container spacing={2}>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="streetAddress"
                                        label="Street Address"
                                        fullWidth
                                        variant="outlined"
                                        error={touched.streetAddress && Boolean(errors.streetAddress)}
                                        helperText={<ErrorMessage name="streetAddress" component="div" className="text-red-600" />}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="state"
                                        label="State"
                                        fullWidth
                                        variant="outlined"
                                        error={touched.state && Boolean(errors.state)}
                                        helperText={<ErrorMessage name="state" component="div" className="text-red-600" />}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="pincode"
                                        label="Pincode"
                                        fullWidth
                                        variant="outlined"
                                        error={touched.pincode && Boolean(errors.pincode)}
                                        helperText={<ErrorMessage name="pincode" component="div" className="text-red-600" />}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="city"
                                        label="City"
                                        fullWidth
                                        variant="outlined"
                                        error={touched.city && Boolean(errors.city)}
                                        helperText={<ErrorMessage name="city" component="div" className="text-red-600" />}
                                    />
                                <Button fullWidth variant='contained' type='submit' color='primary'>Deliver Here</Button>        
                                </Grid>
                            </Grid>
                            </Form>
                        )}
            </Formik>
            </Box>
        </Modal>
    </div>
  )
}

export default Cart;