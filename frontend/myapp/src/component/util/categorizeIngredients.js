export const categorizeIngredients = (ingredients) => {
  return ingredients.reduce((acc,ingredient) => {
    const {category} = ingredient;
    if(!acc[category.name]){
        acc[category.name] = [];
    }
    acc[category.name].push(ingredient);
    return acc;
  },{});
};

// export const categorizeIngredients = (ingredients) => {
//     if (!ingredients || !Array.isArray(ingredients)) return {};
  
//     return ingredients.reduce((acc, ingredient) => {
//       const categoryName = ingredient?.category?.name || 'Uncategorized';
//       if (!acc[categoryName]) {
//         acc[categoryName] = [];
//       }
//       acc[categoryName].push(ingredient);
//       return acc;
//     }, {});
//   };
    