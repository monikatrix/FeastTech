const{ createTheme } = require("@mui/material")

export const darkTheme=createTheme({
    palette:{
        mode:"dark",
        primary:{
            main:"#e91e64"
        },
        secondary:{
            main:"#242B2E"
        },
        black:{
            main:"#0D0D0D"
        },
        background:{
            main:"#000000",
            default:"#0D0D0D",
            paper:"#0D0D0D"
        },
        textColor:{
            primary: "#ffffff",  // Corrected from `textColor.main`
            secondary: "#aaaaaa"
        }
    }
})