import { Accordion, AccordionDetails, AccordionSummary, Button, Checkbox, FormControlLabel, FormGroup, Typography } from '@mui/material'
import React, { useState } from 'react'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
import { categorizeIngredients } from '../util/categorizeIngredients';
import { useDispatch } from 'react-redux';
import { addItemsToCart } from '../State/Cart/Action';

const ingredient = [
    [
        {
            "category": "Nuts & Seeds",
            "ingredients": ["Cashews", "Almonds", "Raisins"]
        },
        {
            "category": "Protein",
            "ingredients": ["Chicken", "Mutton", "Fish", "Prawns", "Eggs", "Paneer"]
        },
        {
            "category": "Vegetables",
            "ingredients": ["Onions", "Tomatoes", "Potatoes", "Green Chilies", "Coriander Leaves", "Mint Leaves"]
        },
        {
            "category": "Spices",
            "ingredients": ["Turmeric", "Cumin", "Coriander Powder", "Cardamom", "Cloves", "Cinnamon", "Bay Leaves", "Black Pepper", "Star Anise", "Mace", "Nutmeg", "Saffron", "Red Chili Powder", "Garam Masala"]
        },
        {
            "category": "Condiment",
            "ingredients": ["Yogurt", "Ghee", "Lemon Juice", "Rose Water", "Kewra Water"]
        }
    ]    
    
]
const MenuCard = ({item}) => {
   const [selectedIngredients, setSelectedIngredients] = useState([])
   const dispatch = useDispatch();
    const handleCheckBoxChange=(itemName)=>{
        if(selectedIngredients.includes(itemName)){
            setSelectedIngredients(selectedIngredients.filter((item)=>item!==itemName));
        }
        else{
            setSelectedIngredients([...selectedIngredients,itemName])
        }
    }

    const handleAddItemToCart=(e)=>{
        e.preventDefault();
        console.log("yaah! selected")
        const reqData = {
            token:localStorage.getItem("jwt"),
            cartItem:{
                foodId:item.id,
                quantity:1,
                ingredients:selectedIngredients,
            },
        };
        dispatch(addItemsToCart(reqData));
        console.log("requested Data : ",reqData);
    };

    // if (item && item.ingredient) {
    //     console.log("Ingredients:", item.ingredient);
    //     console.log("Categorized:", categorizeIngredients(item.ingredient));
    // } else {
    //     console.log("No ingredients available for this item");
    // }
    

  return (
    <Accordion>
    <AccordionSummary
      expandIcon={<ExpandMoreIcon />}
      aria-controls="panel1-content"
      id="panel1-header"
    >
      <div className='lg:flex items-center justify-between'>
        <div className='lg: flex items-center lg:gap-5'>
            <img className='w- [7rem] h-[7rem] object-cover pr-2 '
            src={item.images[0]} alt='' />

        </div>
        <div className='space-y-1 lg:space-y-5 lg:max-w-2xl'>
            <p className='font-semibold text-xl'>{item.name}</p>
            <p><CurrencyRupeeIcon/>{item.price}</p>
            <p className='text-gray-400'>{item.description}</p>
        </div>
      </div>
    </AccordionSummary>
      <Typography>
      Biryani is a flavorful and aromatic rice dish that 
      originated in the Indian subcontinent and is now popular worldwide.
      </Typography>
    <AccordionDetails>
        <form onSubmit={handleAddItemToCart}>
            <div className='flex gap-5 flex-wrap'>
                
            {Object.keys(categorizeIngredients(item.ingredient)).map((category) => ( 
                    <div key={category}>
                        <p>{category}</p>
                        <FormGroup>
                            {categorizeIngredients(item.ingredient)[category].map(
                                (ingredient) => (
                                <FormControlLabel 
                                control={<Checkbox 
                                    onChange={()=>handleCheckBoxChange(ingredient.name) }
                                />} 
                                key={ingredient.id} 
                                label={ingredient.name} />
                            ))}
                        </FormGroup>
                    </div>
                ))
            }

            </div>
            <div className='flex gap-5 flex-wrap'>
                <Button 
                variant='contained' 
                disabled={false} 
                type='submit'>
                    {true?"Add to Cart":"Out of Stock"}
                </Button>
            </div>
        </form>
    </AccordionDetails>
  </Accordion>
  )
}

export default MenuCard