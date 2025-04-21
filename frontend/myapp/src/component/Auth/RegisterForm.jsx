import { Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import { Field, Form, Formik } from 'formik'
import React from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { registerUser } from '../State/Authentication/Action'

const initialValues={
    fullName:"",
    email:"",
    password:"",
    role:"ROLE_CUSTOMER"
}

const RegisterForm = () => {
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const handleSubmit = (values)=>{

        console.log("form values",values);
        dispatch(registerUser({userData:values,navigate}))
    }
  return (
    <div>
    <Typography variant='h5' className='text-center'>
        Register
    </Typography>
    <Formik onSubmit={handleSubmit} initialValues={initialValues}>
        <Form>
        <Field
            as={TextField}
            name="fullName"
            label="FullName"
            fullWidth
            variant="outlined"
        />
        <Field
            as={TextField}
            name="email"
            label="Email"
            fullWidth
            variant="outlined"
        />
        <Field
            as={TextField}
            name="password"
            label="Password"
            type="password"  
            fullWidth
            variant="outlined"
            margin="normal"
        />
        <FormControl fullWidth>
            <InputLabel id="demo-simple-select-label">Role</InputLabel>
            <Field
                as={Select}
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="role"
                // value={age}
                label="Role"
                // onChange={handleChange}
            >
                <MenuItem value={"ROLE_CUSTOMER"}>Customer</MenuItem>
                <MenuItem value={"ROLE_RESTAURANT_OWNER"}>Restaurant Owner</MenuItem>
            </Field>
        </FormControl>
        <Button sx={{mt:2, padding:"1rem"}} fullWidth type='submit' variant='contained'>Register</Button>
        </Form>                                 
    </Formik>
    <Typography variant='body2' align='center' sx={{mt:3}}>
        If have an account already?
        <Button onClick={()=>navigate("/account/login")}>
            Login
            </Button> 
    </Typography>
</div>
  )
}

export default RegisterForm