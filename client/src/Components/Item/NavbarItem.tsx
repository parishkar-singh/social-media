import React from "react";
import {addTransition} from "@/Utils/DarkMode";

const NavbarLinks:React.FC=()=>{
    return (
        <div className={`flex gap-2 text-black font-poppins  dark:text-white select-none items-center`}>
            <span className={`cursor-pointer`}>Explore</span>
            <span className={`cursor-pointer`}>Write</span>
            <span className={`cursor-pointer`}>Sign in</span>
            <span className={addTransition+` cursor-pointer bg-white dark:bg-primary text-black font-poppins font-bold rounded-3xl py-1 px-2`}>Get Started</span>
        </div>
    )
}

export default NavbarLinks